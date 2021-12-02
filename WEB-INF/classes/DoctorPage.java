import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/DoctorPage")


public class DoctorPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();		
	
		Utilities utility = new Utilities(request,pw);
		Doctor doctor ;
		String postId;
		if  (request.getAttribute("data") != null) {
			doctor = (Doctor) request.getAttribute("data");
			postId = doctor.getPostId()+"";
		} else {
			postId = request.getParameter("postId");

			doctor = MySqlDataStoreUtilities.getDoctor(Integer.parseInt(postId));
		}




		utility.printHtml("Header.html");


		pw.println("<script>");
		pw.println("function initMap() {");

		// Lat Long go here
		pw.println("const myLatLng = { lat: "+doctor.getLat()+", lng: "+ doctor.getLongt() +" };");
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
		//pw.println("<script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyAHnfZFSq_y9Rf14NyCxvjgBLSQS3sN-0Q&callback=initMap&libraries=&v=weekly' defer></script>");
		pw.println("<div class='9u'>");
		pw.println("<section>");
		pw.println("<header>");
		pw.println("<div align='center'><section><h2>Doctor INFORMATION</h2></section></div><hr>");
		pw.println("<table style='width:100% '>");
		pw.println("<tr><th>RealName</th><th>Department</th><th>Address</th><th>Location</th></tr>");
		//Real value
		pw.println("<tr><td>" + doctor.getRealName()  +"</td><td>"+ doctor.getDepartment() +"</td><td>"+ doctor.getAddress() +"</td><td>"+ doctor.getLocation() +"</td></tr>");

		pw.println("<tr><th>OpenTime</th><th>Close Time</th><th>Book Service</th></tr>");
		//Real value
		pw.println("<tr><td>"+  doctor.getOpenTime()+"</td><td> "+  doctor.getCloseTime()+"</td><td>");

		if (!utility.isLoggedin()) {

		} else {
			User user = utility.getUser();
			pw.println("<form method='Post' action='DoctorPage'>");
			pw.println("<input type='date' name='date' value=''></input>");
			
			pw.println("<select name='bookTime' class='input'><option value='9:00' selected>9:00</option><option value='10:00' selected>10:00</option>");
			pw.println("<option value='11:00' selected>11:00</option><option value='12:00' selected>12:00</option><option value='13:00' selected>13:00</option>");
			pw.println("<option value='14:00' selected>14:00</option><option value='15:00' selected>15:00</option><option value='16:00' selected>16:00</option>");
			pw.println("<option value='17:00' selected>17:00</option><option value='18:00' selected>18:00</option><option value='19:00' selected>19:00</option>");
			pw.println("<option value='20:00' selected>20:00</option></select>");
	
			//DoctorID
			pw.println("<input type='hidden' name='doctorId' value='"+ doctor.getDoctorId()  +  "'></input>");
			//CustomerId
			pw.println("<input type='hidden' name='customerId' value='"+ user.getId()  +"'></input>");
			pw.println("<input type='hidden' name='postId' value='"+ postId  +"'></input>");
			pw.println("<input type='submit' class='btnbuy' value='Book' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
			pw.println("</form>");


		}


		pw.println("</td></tr></table>");
		//pw.println("<span class='byline'></span> <br><div id='map' style='height:400px; width:100%'></div></section>");
		pw.println("<section align='center'><header><h2>Review</h2><hr>");


		if (!utility.isLoggedin()) {
			System.out.println("LogIn: Not");
		} else {
			User user = utility.getUser();
			// pw.println("<button type='button' class='btn btn-primary' style='background-color: #4CAF50;  border: none;color: white;padding: 10px 20px;text-align: center;");
			// pw.println("text-decoration: none;display: inline-block;font-size: 16px;'><a href='PostDoctorReview?doctorId='"+doctor.getDoctorId()+"&postId="+doctor.getPostId()+">Add Review</a></button>");
	
			pw.println("<a href='PostDoctorReview?doctorId="+doctor.getDoctorId()+"&postId="+doctor.getPostId()+"'><button type='button' class='btn btn-primary' style='background-color: #4CAF50;  border: none;color: white;padding: 10px 20px;text-align: center;");
			pw.println("text-decoration: none;display: inline-block;font-size: 16px;'>Add Review</button></a>");
			
			
			// pw.println("<button type='button' class='btn btn-info' style='background-color: #008CBA;  border: none;color: white;padding: 10px 20px;text-align: center;");
			// pw.println("text-decoration: none;display: inline-block;font-size: 16px;'>Modify Review</button>");
	
	
			// pw.println("<button type='button' class='btn btn-danger ' style='  margin: 20px 2px; background-color: #FF0000;  border: none;color: white;padding: 10px 20px;");
			// pw.println("text-align: center;text-decoration: none;display: inline-block;font-size: 16px;'>Remove Review</button>");
			pw.println("<hr>");
		}



		pw.println("<table style='width:100% '>");
		pw.println("<tr><th>Review</th><th>Rate</th><th>Date</th></tr>");


		HashMap<String, ArrayList<Review>> hm =MongoDBDataStoreUtilities.selectDoctorReview();
		for (Map.Entry<String, ArrayList<Review>> entry: hm.entrySet() ){
			
			if (entry.getKey().equals(doctor.getDoctorId()+"")) {
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
		String doctorId = request.getParameter("doctorId");
		String customerId = request.getParameter("customerId");
		String bookTime = request.getParameter("bookTime");
		String date = request.getParameter("date");
		String postId = request.getParameter("postId");
		System.out.println(doctorId + " | " + customerId + " | " + bookTime + " | " + date );
		MySqlDataStoreUtilities.insertBook(Integer.parseInt(doctorId),Integer.parseInt(customerId), date, bookTime);




		response.sendRedirect("DoctorPage?postId="+postId);
	}





}
