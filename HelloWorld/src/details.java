

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class details
 */
@WebServlet("/details")
public class details extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
		 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String custID = request.getParameter("customerID");
		String message2 = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	    //URL of Oracle database server
	    String url = "jdbc:oracle:thin:testuser/password@localhost"; 

	    
	    //properties for creating connection to Oracle database
	    Properties props = new Properties();
	    props.setProperty("user", "testdb");
	    props.setProperty("password", "password");
	  
	    //creating connection to Oracle database using JDBC
	    Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,props);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	   // String sql ="select sysdate as current_day from dual";
	    String sql ="select * from demo_customers where CUSTOMER_ID =" +custID;
	    //creating PreparedStatement object to execute query
	    PreparedStatement preStatement = null;
		try {
			preStatement = conn.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    ResultSet result = null;
		try {
			result = preStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    try {
			while(result.next()){
			    //System.out.println("Current Date from Oracle : " +         result.getString("current_day"));
				//System.out.printf("%s %s, %s\n",
				message2 += "<div class=\"container\">"+
				  "<h2>Customer Details</h2>"+
				  "<ul class=\"list-group\">"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_FIRST_NAME")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_LAST_NAME")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_STREET_ADDRESS1")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_STREET_ADDRESS2")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_CITY")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_STATE")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_POSTAL_CODE")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("PHONE_NUMBER1")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("PHONE_NUMBER2")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CREDIT_LIMIT")+"</li>"+
				  "<li class=\"list-group-item\">"+result.getString("CUST_EMAIL")+"</li>"+
				  
				    
				  "</ul></div>";
				
						//city = result.getString("CUST_CITY");
						//state = result.getString("CUST_STATE");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    response.setContentType("text/html");
	      //message = "Shoes!";
	    
	      request.setAttribute("message2", message2);
	      getServletContext()
	      .getRequestDispatcher("/output.jsp")
	      .forward(request,  response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
