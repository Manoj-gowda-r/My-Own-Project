package MiniProject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class Servlet2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
			
			PreparedStatement ps=con.prepareStatement("select * from myproject where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			
			HttpSession session=req.getSession();
			session.setAttribute("email",email);
			
			while(rs.next())
			{
				RequestDispatcher rd=req.getRequestDispatcher("Home.html");
		   	 	rd.forward(req,res);
			}
			
			}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		pw.print("Incorrect Username or Password");
		
		RequestDispatcher rd=req.getRequestDispatcher("Login.html");
		rd.include(req,res);	
		}
			
	}




