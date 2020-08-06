import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchContentServlet")
public class SearchContentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
		
		String accno=request.getParameter("search");
        response.setContentType("text/html");  
        out.println("<html><body><center>");
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>");
        out.println("<br><br><br><form action='SearchContentServlet'  method='post' width='40%' height='40%'>" + 
        		"  <input type='text' size='40' placeholder='Search..' name='search'>" + 
        		"  <button type='submit'><i class='fa fa-search'></i></button>" + 
        		"</form><br>");
        try 
        { 
        	Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "s@myukth@0512");  
            Statement stmt = con.createStatement();  
            ResultSet rs;
			String sql = "select Txn_Ref_Number,Account_Number,Date_Time,Description,Withdrawals,Credit from sample where Account_Number="+accno;
            rs = stmt.executeQuery(sql);  
            out.println("<table border=1 width=40% height=40%>");  
            out.println("<tr><th>Account Number</th><th>Description</th><th>Withdrawals</th><th>Credit</th></tr>");  
            while (rs.next()) 
            {  
            	String acc=rs.getString("Account_Number");
                String n = rs.getString("Description");  
                int nm = rs.getInt("Withdrawals");  
                int cre = rs.getInt("Credit");   
                out.println("<tr><td>" + acc+"</td><td>"+n + "</td><td>" + nm + "</td><td>" + cre + "</td></tr>");   
            }  
            out.println("</table>");  
            out.println("<br><br><br>");
            out.println("<a href='Sampleservlet'>"+"Click here to download the statement as pdf"+"</a>");
            out.println("</center></html></body>");  
            con.close();  
           }  
            catch (Exception e) 
           {  
//            out.println("error");
            	System.out.println(e);
        }  
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}