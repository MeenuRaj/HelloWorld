

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class helloworld
 */
@WebServlet("/helloworld")
public class helloworld extends HttpServlet {
	 private String message = "";
	 String fname = "";
     String lname = "";
     String id = "";
	 

	  public void doGet(HttpServletRequest request,
	                    HttpServletResponse response)
	            throws ServletException, IOException
	  {
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
	        String sql ="select * from demo_customers";
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
	        	message=" <thead><tr><th>Lastname</th><th>Firstname</th></tr></thead>";
				while(result.next()){
				    //System.out.println("Current Date from Oracle : " +         result.getString("current_day"));
					//System.out.printf("%s %s, %s\n",
					fname = result.getString("CUST_LAST_NAME");
					lname = result.getString("CUST_FIRST_NAME");	
					id = result.getString("CUSTOMER_ID");
					message += "<tr><td><a href=\"details?customerID="+id+"\">"+fname+"</a></td><td>"+lname+"</td></tr>\n";
					
							//city = result.getString("CUST_CITY");
							//state = result.getString("CUST_STATE");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      // Set response content type
	      response.setContentType("text/html");
	      //message = "Shoes!";
	    
	      request.setAttribute("message", message);
	      getServletContext()
	      .getRequestDispatcher("/table.jsp")
	      .forward(request,  response);
	      
	      //PrintWriter out = response.getWriter();
	    //  out.println("<h1>" + message + "<h1>");
	      // Actual logic goes here.
	      
	     
	    
	     // out.println("&lt;h1&gt;" + message + "&lt;/h1&gt;"); 
	     // out.println("<h1>" + message + "<h1>"); 
	   } 
	  
	
	            

	   public void destroy() 
	   { 
	     // do nothing. 
	   } 
	}