import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/DetailedInsurance")

/* 
	Home class uses the printHtml Function of Utilities class and prints the Header,LeftNavigationBar,
	Content,Footer of Game Speed Application.

*/

public class DetailedInsurance extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		
	
		Utilities utility = new Utilities(request,pw);
		Insurance insurance ;
		String postId;
		if  (request.getAttribute("data") != null) {
			insurance = (Insurance) request.getAttribute("data");
			postId = insurance.getPostId()+"";
		} else {
			postId = request.getParameter("postId");

			insurance = MySqlDataStoreUtilities.getInsurance(Integer.parseInt(postId));
		}




		utility.printHtml("Header.html");


		pw.println("<script>");
		pw.println("function initMap() {");

		// Lat Long go here
		pw.println("const myLatLng = { lat: "+insurance.getLocationLat()+", lng: "+ insurance.getLocationLong() +" };");
		pw.println("const map = new google.maps.Map(document.getElementById('map'), {");
		pw.println("zoom: 10,");
		pw.println("center: myLatLng,");
		pw.println("});");
		pw.println("new google.maps.Marker({");
		pw.println("position: myLatLng,");
		pw.println("map,");
		pw.println("title: 'Hello World!',");
		pw.println("});");
		pw.println("} </script>");
		pw.println("<script src='https://maps.googleapis.com/maps/api/js?AIzaSyAHnfZFSq_y9Rf14NyCxvjgBLSQS3sN-0Q&callback=initMap&libraries=&v=weekly' defer></script>");
		pw.println("<div class='9u'>");
		pw.println("<section>");
		pw.println("<header>");
		pw.println("<div align='center'><section><h2>Insurance INFORMATION</h2></section></div><hr>");
		pw.println("<table style='width:100% '>");
		pw.println("<tr><th>PostTime</th><th>Insurance Name</th><th>Address</th></tr>");
		//Real value
		pw.println("<tr><td>"+ insurance.getPostTime()  +"</td><td>"+ insurance.getInsuranceName()  +"</td><td>"+ insurance.getAddress() +"</td></tr>");

		pw.println("<tr><th>Location</th><th>OpenTime</th><th>Close Time</th><th>Book Service</th></tr>");
		//Real value
		pw.println("<tr><td>"+ insurance.getLocation()   +"</td><td>"+  insurance.getOpenTime()+"</td><td> "+  insurance.getCloseTime()+"</td><td>");

		if (!utility.isLoggedin()) {

		} else {
			User user = utility.getUser();
			pw.println("<form method='Post' action='DetailedInsurance'>");
			pw.println("<input type='date' name='date' value=''></input>");
			
			pw.println("<select name='bookTime' class='input'><option value='9:00' selected>9:00</option><option value='10:00' selected>10:00</option>");
			pw.println("<option value='11:00' selected>11:00</option><option value='12:00' selected>12:00</option><option value='13:00' selected>13:00</option>");
			pw.println("<option value='14:00' selected>14:00</option><option value='15:00' selected>15:00</option><option value='16:00' selected>16:00</option>");
			pw.println("<option value='17:00' selected>17:00</option><option value='18:00' selected>18:00</option><option value='19:00' selected>19:00</option>");
			pw.println("<option value='20:00' selected>20:00</option></select>");
	
			//DoctorID
			pw.println("<input type='hidden' name='insuranceId' value='"+ insurance.getInsuranceId()  +  "'></input>");
			//CustomerId
			pw.println("<input type='hidden' name='customerId' value='"+ user.getId()  +"'></input>");
			pw.println("<input type='hidden' name='postId' value='"+ postId  +"'></input>");
			pw.println("<input type='submit' class='btnbuy' value='Book' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
			pw.println("</form>");


		}


		pw.println("</td></tr></table>");
		pw.println("<span class='byline'></span> <br><div id='map' style='height:400px; width:100%'></div></section>");
		pw.println("<section align='center'><header><h2>Review</h2><hr>");


		if (!utility.isLoggedin()) {
			System.out.println("LogIn: Not");
		} else {
			User user = utility.getUser();
			// pw.println("<button type='button' class='btn btn-primary' style='background-color: #4CAF50;  border: none;color: white;padding: 10px 20px;text-align: center;");
			// pw.println("text-decoration: none;display: inline-block;font-size: 16px;'><a href='PostDoctorReview?doctorId='"+doctor.getDoctorId()+"&postId="+doctor.getPostId()+">Add Review</a></button>");
	
			pw.println("<a href='PostInsuranceReview?insuranceId="+insurance.getInsuranceId()+"&postId="+insurance.getPostId()+"'><button type='button' class='btn btn-primary' style='background-color: #4CAF50;  border: none;color: white;padding: 10px 20px;text-align: center;");
			pw.println("text-decoration: none;display: inline-block;font-size: 16px;'>Add Review</button></a>");
			
			
			// pw.println("<button type='button' class='btn btn-info' style='background-color: #008CBA;  border: none;color: white;padding: 10px 20px;text-align: center;");
			// pw.println("text-decoration: none;display: inline-block;font-size: 16px;'>Modify Review</button>");
	
	
			// pw.println("<button type='button' class='btn btn-danger ' style='  margin: 20px 2px; background-color: #FF0000;  border: none;color: white;padding: 10px 20px;");
			// pw.println("text-align: center;text-decoration: none;display: inline-block;font-size: 16px;'>Remove Review</button>");
			pw.println("<hr>");
		}



		pw.println("<table style='width:100% '>");
		pw.println("<tr><th>Review</th><th>Rate</th><th>Date</th></tr>");


		HashMap<String, ArrayList<Review>> hm =MongoDBDataStoreUtilities.selectInsuranceReview();
		for (Map.Entry<String, ArrayList<Review>> entry: hm.entrySet() ){

		
			if (entry.getKey().equals(insurance.getInsuranceId()+"")) {
				for (Review r :entry.getValue()  ) {
					// System.out.println(r.getId() + ":" + r.getReviewdate() + ":" + r.getReviewtext() + ":" + r.getReviewrating());
					pw.println("<tr>");
					pw.println("<td>"+ r.getReviewtext() +"</td><td> "+ r.getReviewrating()  + " </td><td>"+ r.getReviewdate()  +"</td>");
					pw.println("</tr>");

				}
			}

		}




		
		pw.println("</table></section></div>");


		utility.printHtml("Footer.html");

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String insuranceId = request.getParameter("insuranceId");
		String customerId = request.getParameter("customerId");
		String bookTime = request.getParameter("bookTime");
		String date = request.getParameter("date");
		String postId = request.getParameter("postId");
		MySqlDataStoreUtilities.insertBook(Integer.parseInt(insuranceId),Integer.parseInt(customerId), date, bookTime);




		response.sendRedirect("DetailedInsurance?postId="+postId);
	}





}
