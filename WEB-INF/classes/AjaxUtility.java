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
		// ArrayList<Doctor> doctors;
		// doctors = viewPartialDoctor(searchId);
		// data=getData();
		// for (Doctor doctor : doctors) {
		// sb.append("<doctor>");
		// sb.append("<id>" + doctor.getDoctorId() "</id>");
		// sb.append("<doctorName>" + doctor.getRealName() + "</doctorName>");
		// sb.append("</doctor>");
		// }
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

	// public static ArrayList<Doctor> viewPartialDoctor(String partial) {
	// 	ArrayList<Doctor> doctors = new ArrayList<>();
	// 	try {
	// 		getConnection();
	// 		Statement stmt = conn.createStatement();
	// 		String selectDoctorQuery = "select * from Doctordetails where realName like '%" + partial
	// 				+ "%' or department like '%" + partial + "%';";
	// 		ResultSet rs = stmt.executeQuery(selectDoctorQuery);
	// 		while (rs.next()) {
	// 			doctors.add(new Doctor(rs.getInt("postId"), rs.getInt("doctorId"), rs.getString("realName"),
	// 					rs.getString("department"), rs.getString("address"), rs.getString("locationLat"),
	// 					rs.getString("locationLong"), rs.getString("location"), rs.getString("OpenTime"),
	// 					rs.getString("closeTime"), rs.getString("postTime")));
	// 		}
	// 	} catch (Exception e) {
	// 		System.out.println(e.getMessage());

	// 	}
	// 	return doctors;
	// }

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

				// Product product = new
				// Product(rs.getString("ProductType"),rs.getString("productName"),rs.getDouble("productPrice"),
				// rs.getString("productManufacturer"),rs.getString("productCondition"),
				// rs.getDouble("productDiscount"), rs.getInt("productRebate"));
				// product.setId(rs.getString("productName"));
				hm.put(rs.getString("realName"), doctor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hm;
	}
	// public static void storeData(HashMap<String,Product> productdata)
	// {
	// try
	// {

	// getConnection();

	// String insertIntoProductQuery = "INSERT INTO
	// product(productId,productName,image,retailer,productCondition,productPrice,productType,discount)
	// "
	// + "VALUES (?,?,?,?,?,?,?,?);";
	// for(Map.Entry<String, Product> entry : productdata.entrySet())
	// {

	// PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
	// //set the parameter for each column and execute the prepared statement
	// pst.setString(1,entry.getValue().getId());
	// pst.setString(2,entry.getValue().getName());
	// pst.setString(3,entry.getValue().getImage());
	// pst.setString(4,entry.getValue().getRetailer());
	// pst.setString(5,entry.getValue().getCondition());
	// pst.setDouble(6,entry.getValue().getPrice());
	// pst.setString(7,entry.getValue().getType());
	// pst.setDouble(8,entry.getValue().getDiscount());
	// pst.execute();
	// }
	// }
	// catch(Exception e)
	// {

	// }
	// }

}
