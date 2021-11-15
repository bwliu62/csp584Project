import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;

public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
	static String message;

	public static String getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/project_database?autoReconnect=true&useSSL=false", "root", "root");
			message = "Successfull";
			return message;
		} catch (SQLException e) {
			message = "unsuccessful";
			return message;
		} catch (Exception e) {
			message = "unsuccessful";
			return message;
		}
	}

	public StringBuffer readdata(String searchId) {
		HashMap<String, Doctor> data;
		data = getData(searchId);
		Iterator it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pi = (Map.Entry) it.next();
			if (pi != null) {
				Doctor p = (Doctor) pi.getValue();
				if (p.getRealName().toLowerCase().contains(searchId)) {
					sb.append("<product>");
					sb.append("<id>" + p.getPostId() + "</id>");
					sb.append("<productName>" + p.getRealName() + "</productName>");
					sb.append("</product>");
				}
			}
		}

		return sb;
	}


	public static HashMap<String, Doctor> getData(String partial) {
		HashMap<String, Doctor> hm = new HashMap<String, Doctor>();
		try {
			getConnection();

			String selectproduct = "select * from Doctordetails where realName like '%" + partial
					+ "%' or department like '%" + partial + "%';";
			PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Doctor doctor = new Doctor(rs.getInt("postId"), rs.getInt("doctorId"), rs.getString("realName"),
						rs.getString("department"), rs.getString("address"), rs.getString("locationLat"),
						rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"),
						rs.getString("closeTime"), rs.getString("postTime"));
				hm.put(rs.getString("realName"), doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}

}
