import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@WebServlet("/Sampleservlet")
public class AccountStatementGeneration extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
		public void doGet(HttpServletRequest request, HttpServletResponse response)
			      throws ServletException, IOException {
		try
		{
//			String a=request.getParameter("balance");
			Class.forName ("com.mysql.cj.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "s@myukth@0512");
			Statement stmt = conn.createStatement();
		/* Define the SQL query */
			ResultSet query_set = stmt.executeQuery("select Txn_Ref_Number,Account_Number,Date_Time,Description,Withdrawals,Credit,Running_Balance from sample");
	    /* Step-2: Initialize PDF documents - logical objects */
			Document my_pdf_report = new Document();
			PdfWriter.getInstance(my_pdf_report, new FileOutputStream("C:\\csv\\pdf_report.pdf"));
			my_pdf_report.open();            
	    //we have four columns in our table
			PdfPTable my_report_table = new PdfPTable(7);
	    //create a cell object
			PdfPCell table_cell;
			table_cell=new PdfPCell(new Phrase("Txn_Ref_Number"));
			my_report_table.addCell(table_cell);
			table_cell=new PdfPCell(new Phrase("Account Number"));
			my_report_table.addCell(table_cell);
			table_cell=new PdfPCell(new Phrase("Date Time"));
			my_report_table.addCell(table_cell);
			table_cell=new PdfPCell(new Phrase("Description"));
			my_report_table.addCell(table_cell);
			table_cell=new PdfPCell(new Phrase("Withdrawals"));
			my_report_table.addCell(table_cell);
			table_cell=new PdfPCell(new Phrase("Credit"));
			my_report_table.addCell(table_cell);
			table_cell=new PdfPCell(new Phrase("Running Balance"));
			my_report_table.addCell(table_cell);
			while (query_set.next()) 
			{                
				String txn = query_set.getString("Txn_Ref_Number");
	            table_cell=new PdfPCell(new Phrase(txn));
	            my_report_table.addCell(table_cell);
	            String accno=query_set.getString("Account_Number");
	            table_cell=new PdfPCell(new Phrase(accno));
	            my_report_table.addCell(table_cell);
	            String date=query_set.getString("Date_Time");
	            table_cell=new PdfPCell(new Phrase(date));
	            my_report_table.addCell(table_cell);
	            String des=query_set.getString("Description");
	            table_cell=new PdfPCell(new Phrase(des));
	            my_report_table.addCell(table_cell);
	            String wd = query_set.getString("Withdrawals");
	            table_cell=new PdfPCell(new Phrase(wd));
	            my_report_table.addCell(table_cell);
	            String cre = query_set.getString("Credit");
	            table_cell=new PdfPCell(new Phrase(cre));
	            my_report_table.addCell(table_cell);
	            String run = query_set.getString("Running_Balance");
	            table_cell=new PdfPCell(new Phrase(run));
	            my_report_table.addCell(table_cell);
	                  
			}
	    my_pdf_report.add(my_report_table);                       
	    my_pdf_report.close();
	    query_set.close();
	    stmt.close(); 
	    conn.close();
	    PrintWriter out=response.getWriter();
		out.println("<h1>"+"Account Statement downloaded successfully"+"</h1>");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
