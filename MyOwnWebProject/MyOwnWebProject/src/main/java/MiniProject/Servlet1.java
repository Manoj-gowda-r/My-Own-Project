package MiniProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registration")
public class Servlet1 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
   	 	String name = request.getParameter("name");
   	 	String email=request.getParameter("email");
   	 	String phone=request.getParameter("phone");
   	 	String gender=request.getParameter("gender");
   	 	String password=request.getParameter("password");
   	 
   	 	try {
   	 		Class.forName("com.mysql.cj.jdbc.Driver");
   	 		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
   	 		PreparedStatement ps=con.prepareStatement("insert into myproject values(?,?,?,?,?)");
   	 		ps.setString(1,name);
   	 		ps.setString(2,email);
   	 		ps.setString(3,password);
   	 		ps.setString(4,phone);
   	 		ps.setString(5,gender);
   	 		
   	 		ps.executeUpdate();	
   	 	}
   	 	catch(Exception e)
   	 	{
   	 		e.printStackTrace();
   	 	}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		RequestDispatcher rd=request.getRequestDispatcher("Login.html");
   	 	rd.forward(request,response);
	}
}

