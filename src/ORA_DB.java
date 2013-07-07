/* ORA_DB
 * Description:
 *   1) Connects to Oracle DB
 *   2) Contains methods to fetch rows, columns, and tables from Oracle DB
 * @param jdbcDriver 
 * @param dbURL1 Connection String for connection to database
 * @param userName1 User name of user
 * @param userPassword1 Password of user
 * @param conn
 * @param pstmt Prepared Statement for prepared SQL Query
 * @param rs Result Set
 * @param connected this boolean variable tells the status of connection with Oracle DB
 * 
 * @author GurpreetSingh 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ORA_DB {
	String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
    String dbURL1 =  "jdbc:oracle:thin:@bonnet19.cs.qc.edu:1521:dminor";
    String userName1 =  "lackner";
    String userPassword1 = "guest";
    String oracle_driver = "oracle.jdbc.driver.OracleDriver";   
    Connection conn;               // to connect to database
    PreparedStatement pstmt;       // for Prepared SQL Query
    ResultSet rs;                  // A table of data representing a database Result Set
	int flag = 0;
	String newTime;
	boolean connected;

	public ORA_DB (){
		 try
	     {
	        Class.forName(oracle_driver);
	        conn = DriverManager.getConnection(dbURL1, userName1, userPassword1);         // Connect to data base
	        connected = true; 
	     }
	     catch (Exception e)
	     {
	        System.out.println(e.getMessage()); 
	        connected = false;
	     }
	}
     
    public int createTable (String stmtquery, String commentQuery) {    
        try
        {        
            pstmt = conn.prepareStatement(stmtquery);
            rs = pstmt.executeQuery();
            pstmt = conn.prepareStatement(commentQuery);
            rs = pstmt.executeQuery();
            rs.close();
            pstmt.close();
            try
            {
              conn.close();
            } 
            catch (SQLException e) { };
        }
        catch (SQLException e)
        {
          System.out.println(e.getMessage());
          JOptionPane.showMessageDialog(null, e.getMessage(),"Inane error", JOptionPane.ERROR_MESSAGE); 
          flag = -1;
        }
        return flag;
    }
    
    public String[] showTables(String stmtquery) {
        String[] tables = new String[100];       // need to Edit in here...
    	try
        {        
            pstmt = conn.prepareStatement(stmtquery);
            rs = pstmt.executeQuery(); 
            
            int i=0;
            while (rs.next())
            {   System.out.println(tables[i] = new String((String)(rs.getObject(1))));
            	i++;
            }
            rs.close();
            pstmt.close();
            
            try
            {
              conn.close();
            } 
            catch (SQLException e) { };
        }
        catch (SQLException e)
        {
          System.out.println(e.getMessage());
          JOptionPane.showMessageDialog(null, e.getMessage(),"Inane error", JOptionPane.ERROR_MESSAGE); 
          flag = -1;
        }
        return tables;
  }
    
public ShowTableData getTable(String stmtquery) {
       ShowTableData table_data = null;
	   try
       {        
            pstmt = conn.prepareStatement(stmtquery);
            rs = pstmt.executeQuery(); 
            table_data = new ShowTableData(rs);
            rs.close();
            pstmt.close();
            
            try
            {
              conn.close();
            } 
            catch (SQLException e) { }; 
        }
        catch (SQLException e)
        {
          System.out.println(e.getMessage());
          JOptionPane.showMessageDialog(null, e.getMessage(),"Inane error", JOptionPane.ERROR_MESSAGE); 
          flag = -1;
        }
	    return table_data;
    }
	
    public boolean SQL_QueryExecuter(String stmtquery){
    	try
        {        
            pstmt = conn.prepareStatement(stmtquery);
            rs = pstmt.executeQuery(); 
            rs.close();
            pstmt.close();
            try
            {
              conn.close();
            } 
            catch (SQLException e) { }; 
        }
        catch (SQLException e)
        {
          System.out.println(e.getMessage());
          JOptionPane.showMessageDialog(null, e.getMessage()+"\n Try again ","Inane error", JOptionPane.ERROR_MESSAGE); 
          flag = -1;
          return false;
        }
    	return true;
    }
		
} // Class ORA_DB

