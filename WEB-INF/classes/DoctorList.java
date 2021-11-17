import java.io.IOException;
import java.io.PrintWriter;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@WebServlet("/DoctorList")



public class DoctorList extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);


		ArrayList<Doctor> doctors;

		if (request.getParameter("searchName") != null) {
			doctors = MySqlDataStoreUtilities.viewPartialDoctor( request.getParameter("searchName"));
		} else {
			doctors = MySqlDataStoreUtilities.viewDoctor();
		}


		utility.printHtml("Header.html");
		// pw.println("<script type='text/javascript ' src ='javascript.js'></script>");

		pw.println("<h2 style='font-size: 25px;'>We provide you with the most suitable choice!</h2>");
		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			System.out.println("LogIn: Not");
		} else {
			HttpSession session = request.getSession();
			User user = utility.getUser();
			if (user.getUsertype().equals("doctor")) {
				pw.println("<div class='updateDoctor' style='height:0px'><b>Add new doctor</b>");
				pw.println("<div> <a href='PostDoctor'><button type='button' class='btn btn-primary' style='background-color: #009879;  border: none;color: white;padding: 10px 20px;text-align: center;text-decoration: none;display: inline-block;font-size: 16px;'>Post information</button></a></div>");
				pw.println("</div>");
				pw.println("<section style='margin-left: 136px; margin-top: 81px;'><article id='doctorsearch' ><hr style='width: 82%'><h2 style='font-size: 25px;'> find doctor here:</h2><hr style='width: 82%'>");
			}
			// System.out.println("User|" + user.getUserName() + " | " + user.getPassword());
		}
		//pw.println("<section style='margin-left: 136px; margin-top: 81px;'><article id='doctorsearch' ><hr style='width: 82%'><h2 style='font-size: 25px;'> find doctor here:</h2><hr style='width: 82%'>");

		pw.println("<form  method='Get' action='DoctorList' >");
		pw.println("<input type='text' class='input	' id='searchId' placeholder='Search here' size='50' name='searchName' onkeyup='doCompletion()' value='' >");
		pw.println("<div id='auto-row'><table id='complete-table' class='gridtable' style='width: 315px;'></table></div>");
		pw.println("<button class='btnbuy'>Submit</button>");
		pw.println("</form>		");

		pw.println("<table id='table1' style='width:82%; height:600px; display: inline-block; overflow: auto; border-collapse: collapse;'>");
//		pw.println("<tr><td>&nbsp;</td><th><div>DoctorName </div></th><th ><div >Department </div></th><th><div>Make Reservation</div></th></tr>		");
		pw.println("<tr><th ><b >Department </b></th><th><b>DoctorName </b></th><th><b>Make Reservation</b></th></tr>		");

		for (Doctor doctor : doctors) {
//			pw.println("<tr><td>&nbsp;</td>		");
			pw.println("<tr>");
			pw.println("<td>"+  doctor.getDepartment()    +"</td>		");
			pw.println("<td>"+doctor.getRealName()+"</td>");
			pw.println("<td>");
			pw.println("<a href=DoctorPage?postId="+  doctor.getPostId()  +" >Click Here</a> ");
			pw.println("</td>");
			pw.println("</tr>");
		}



		pw.println("</table><article></section></div>");
		pw.println("<div class='9u'><section><a href=HospitalList>Search Hospital</a><br><a href=InsuranceList>Search Insurance</a><br>");

		utility.printHtml("Footer.html");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("doctor").equals("Create")) {
			String doctorId = request.getParameter("doctorId");
			String realName = request.getParameter("realName");
			String department = request.getParameter("department");
			String address = request.getParameter("address");
			String lat="";
			String longt="";
			ArrayList<String> res = ApiUtilities.getLatLongPositions(address);
			lat = res.get(0);
			longt = res.get(1);


			String location = request.getParameter("location");
			String openTime = request.getParameter("openTime");
			String closeTime = request.getParameter("closeTime");
			String postTime = request.getParameter("postTime");

			MySqlDataStoreUtilities.insertDoctor(Integer.parseInt(doctorId) , realName, department, address, lat, longt, location, openTime, closeTime, postTime);
		} 
		

		doGet(request, response);
	}

}
