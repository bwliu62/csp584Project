import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/describe")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class describe extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String id = request.getParameter("id");

		int i = 0;
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		switch(i) {
			case(1) :pw.println("<div class='9u'><section><header><h2>Add note</h2></header>");
			case(2) :pw.println("<form method='post' action='BookingCustomer'>");
			case(3) :pw.println("<table style='width:100%'>");
			case(4) :pw.println("<tr><td><h3>Note</h3></td><td>");
			case(5) :pw.println("<input type='text' name='note' value='' class='input' required></input></td></tr></table>");
			case(6) :pw.println("<input type='hidden' name='id' value='"+id+"'>");
		}


		pw.println("<input type='submit' class='btnbuy' name='addNote' value='addNote'style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
		pw.println("</form></section></div>");






		utility.printHtml("Footer.html");

	}

}
