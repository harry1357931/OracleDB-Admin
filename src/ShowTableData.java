/* ShowTableData
 * Description:
 *   Extracts Rows, Columns from ResultSet returned by SQL Query
 *   and saves them in corresponding arrays
 * @param rsMetaData
 * @param rs1
 * @param numberOfColumns
 * @param numberOfRows
 * @param column_names
 * @param column_class_types
 * @param allRowsAsStrings
 *  
 * @author GurpreetSingh 
 */
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ShowTableData {
	ResultSetMetaData rsMetaData;
	ResultSet rs1;
	int numberOfColumns;
	int numberOfRows = 0;
	String[] column_names;
	String[] column_class_types;
	String[][] allRowsAsStrings;               
	
	// Constructor
	public ShowTableData(ResultSet rs) throws SQLException{	
		try{
			rs1 = rs;
			rsMetaData = rs.getMetaData();
	        numberOfColumns = rsMetaData.getColumnCount();
	        column_names = new String[numberOfColumns];
	        column_class_types = new String[numberOfColumns];
	        
	        for(int i=0; i < column_names.length; i++){
	        	column_names[i] = rsMetaData.getColumnName(i+1);            // as Column index starts from 1... 
	        	column_class_types[i] = rsMetaData.getColumnTypeName(i+1);  // as Column index starts from 1...
	        } // for
	        
	        String[][] roughRowsAsStrings = new String[100][numberOfColumns];       // can take maximum of 100 rows... 
	        while (rs.next())                             // this while loop to count number of rows
	        {           
	        	for(int col=0; col < numberOfColumns; col++ ){
	        	     roughRowsAsStrings[numberOfRows][col] = rs.getString(col+1);
	        	}
	            numberOfRows++;  
	        } // while
	        
	        allRowsAsStrings = new String[numberOfRows][numberOfColumns];
	        for(int i=0; i< numberOfRows; i++ ){
	        	for(int j=0; j< numberOfColumns; j++){
	        		allRowsAsStrings[i][j] = roughRowsAsStrings[i][j];  
	        	}
	        } // for
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
	        JOptionPane.showMessageDialog(null, e.getMessage(),"Inane error", JOptionPane.ERROR_MESSAGE);
		}
	}  // Constructor
	
}// ShowTableData
