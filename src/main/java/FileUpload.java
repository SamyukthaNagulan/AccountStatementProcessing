import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.opencsv.CSVReader;
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name="";
	public FileUpload() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if(ServletFileUpload.isMultipartContent(request)){
    try {
			List <FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for(FileItem item : multiparts){
			if(!item.isFormField())
			{
			     name = new File(item.getName()).getName();
			     item.write( new File("c:/csv" + File.separator + name));
			}
		}
		System.out.println(name);
        request.setAttribute("message", "File Uploaded Successfully");
       }
     catch (Exception ex) 
     {
	       request.setAttribute("message", "File Upload Failed due to " + ex);
	 }         		
	 }else{
			
			            request.setAttribute("message","No File found");
			        }
			        request.getRequestDispatcher("/result.jsp").forward(request, response);
			        try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			        name="C:\\csv\\"+name;
			        System.out.println(name);
			        try (CSVReader reader = new CSVReader(new FileReader(name), ','); 
							Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?characterEncoding=latin1&useSSL=false&useUnicode=true", "root", "s@myukth@0512"))
					{
							String insertQuery = "Insert into sample (id,Txn_Ref_Number,Account_Number,Date_Time,Description,Withdrawals,Credit) values (null,?,?,?,?,?,?)";
							PreparedStatement pstmt = connection.prepareStatement(insertQuery);
							String[] rowData = null;
							int i = 0;
							while((rowData = reader.readNext()) != null){
							for (String data : rowData)
							{
									pstmt.setString((i % 6) + 1, data);
									System.out.println("Entered");
									if (++i % 6 == 0)
									{
											pstmt.addBatch();// add batch
											System.out.println("Entering");

									}

									if (i % 10 == 0)// insert when the batch size is 10
									{
											pstmt.executeBatch();
											System.out.println("Entered");

									}
							}
							}
							System.out.println("Data Successfully Uploaded");
//							Statement stmt = connection.createStatement();
//							ResultSet rs ;
//							String set="set @a=16295";
//							rs=stmt.executeQuery(set);
//							String set1=" set @b=0";
//							rs=stmt.executeQuery(set1);
//							//String sql = "select Txn_Ref_Number,Date_Time,Description,Withdrawals,Credit,@b:=@a as Running_Balance,@a:=@b+(Withdrawals-Credit) as Balance from sample order by Date_Time desc";
//						    String sql="select Txn_Ref_Number,Date_Time,Description,Withdrawals,Credit,@b:=@a as Running_Balance,@a:=@b+(Withdrawals-Credit) as Balance from sample order by Date_Time desc"; 
//							rs = stmt.executeQuery(sql);
//						      //STEP 5: Extract data from result set
//						      while(rs.next()){
//						         //Retrieve by column name
//						         String id  = rs.getString("Txn_Ref_Number");
//						         String date=rs.getString("Date_Time");
//						         String des = rs.getString("Description");
//						         int age = rs.getInt("Withdrawals");
//						         int cre = rs.getInt("Credit");
//						         int run=rs.getInt("Running_Balance");
//						         int bal=rs.getInt("Balance");

						         //Display values
//						         request.setAttribute("id", "ID: ");
//						         request.setAttribute("date", "date: "+date);
//						         request.setAttribute("des", "Description: " +des);
//						         request.setAttribute("wd", "Withdrawals: " + age);
//						         request.setAttribute("cre", "Credit: " + cre);
//						         request.setAttribute("runbal", "Running Balance: "+run);
//						         request.setAttribute("bal", "Balance: "+bal);
//						         PrintWriter out=response.getWriter();
//						         System.out.println("ID: " + id);
//						         System.out.print("date ;"+date);
//						         System.out.print(", Description: " +des);
//						         System.out.print(", Withdrawals: " + age);
//						         System.out.print(", Credit: " + cre);
//						         System.out.print("Running Balance: "+run);
//						         System.out.println("Balance: "+bal);
//						      }
//						      rs.close();
							
							System.out.println("Wowwwwwwwww");
				           
					}
					catch (Exception e)
					{
							e.printStackTrace();
					}
			        System.out.println("Oh No OMG");
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "s@myukth@0512"))
					{
			        	System.out.println("Oh No OMG");
							String loadQuery = "LOAD DATA LOCAL INFILE '" + name + "' INTO TABLE sample FIELDS TERMINATED BY ','"
											+ " LINES TERMINATED BY '\n' (Txn_Ref_Number,Account_Number,Date_Time,Description,Withdrawals,Credit) ";
							System.out.println("Oh No OMG");
							System.out.println(loadQuery);
							Statement stmt = connection.createStatement();
							System.out.println("Oh No OMG");
							stmt.execute(loadQuery);
							System.out.println("Oh No OMG");
							
					}
					catch (Exception e)
					{
						//e.printStackTrace();
						
					}
			    }
//	private String queryExec() {
//		String query="set @a=16295 set @b=0;select Txn_Ref_Number,Account_Number,Date_Time,Description,Withdrawals,Credit,@b:=@a as Running_Balance,@a:=@b+(Withdrawals-Credit) as Balance from sample"+ "order by Date_Time desc";
//		try{
//			Connection connection = null;
//			PreparedStatement stmt = connection.prepareStatement(query);
//			stmt.executeUpdate(query);
//		}
//		catch(Exception e)
//		{
//			
//		}
//		String list=query.toString();
//		return list; 
//	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}