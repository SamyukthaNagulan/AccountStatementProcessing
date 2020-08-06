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
						Class.forName("com.mysql.cj.jdbc.Driver");
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
			        name="C:\\csv\\"+name;
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
									if (++i % 6 == 0)
									{
											pstmt.addBatch();// add batch
									}

									if (i % 10 == 0)// insert when the batch size is 10
									{
											pstmt.executeBatch();
									}
							}
							}
					}
					catch (Exception e)
					{
							e.printStackTrace();
					}
			        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "s@myukth@0512"))
					{
							String loadQuery = "LOAD DATA LOCAL INFILE '" + name + "' INTO TABLE sample FIELDS TERMINATED BY ','"
											+ " LINES TERMINATED BY '\n' (Txn_Ref_Number,Account_Number,Date_Time,Description,Withdrawals,Credit) ";
							Statement stmt = connection.createStatement();
							stmt.execute(loadQuery);							
					}
					catch (Exception e)
					{
						//e.printStackTrace();
					}
			    }
}