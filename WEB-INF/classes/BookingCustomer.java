import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/BookingCustomer")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class BookingCustomer extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		int signin = 0;
		Utilities utility = new Utilities(request,pw);
		User user=utility.getUser();
		if (user.getId() == 99999) {
			pw.println("Last user");
			signin = 1;
		}
		List<Book> bookList = MySqlDataStoreUtilities.viewCustomerBook(user.getId());	

		utility.printHtml("Header.html");


		pw.println("<div class='6u'><section><header><h2>Review Booking</h2><span class='byline'></span> <br><hr><header></section>");
		int i = 0;
		pw.println("<div><table><tr><th>Customer Name</th><th>Provider Name</th><th>Provider Type</th><th>Date</th><th>Time</th><th>Cancel</th></tr>");
		for (Book book : bookList) {
			pw.println("<form method='POST' action='BookingCustomer'><tr>" +
			"<td>"+ book.getCustomerName() + "</td>" +
			"<td>"+ book.getProvideName() + "</td>" +
			"<td>"+ book.getProviderType() + "</td>" +
			"<td>"+ book.getAppointmentDate()+ "</td>" +
			"<td>"+ book.getTime()+ "</td>" +
			"<td><input type='submit' class='btnbuy' name='cancel' value='"+book.getId()+"'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input></td></tr></form>");

		}
		pw.println("</table></div></div>");

		utility.printHtml("Footer.html");

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("addNote")!= null) {
			String id = request.getParameter("id");
			String note = request.getParameter("note");
			MySqlDataStoreUtilities.updateMessage(Integer.parseInt(id),note);
		} else {
			String cancelId = request.getParameter("cancel");
			MySqlDataStoreUtilities.removeBook(Integer.parseInt(cancelId) );
		}

		doGet(request, response);
	}





}
