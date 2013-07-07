/* CreateTableHandler
 * Description:
 *   1) Gets Activated whenever 'Create New Table' is selected
 *   2) Sets up the Interface for Creating New Table
 *   3) After user entered Table Name and Column Names, 
 *      it checks for Errors & Warnings, if no error is found
 *      it connects to database, and creates Table 
 * **********
 * Parameters
 * **********
 * @param jpanel
 * @param mainFrame
 * @param xYLayout1
 * @param TableName JLabel for Table Name
 * @param TableNameField JTextField for TableName
 * @param Total JLabel
 * @param TotalField It lets the user enter total no. of Columns user wants to create. 
 * @param TotalColumnFields default value for initial no. of columns
 * @param columns
 * @param go JButton to display specified no. of column
 * @param structure
 * @param Name JLabel to display String 'Name' as Label
 * @param Type JLabel to display String 'Types' as Label
 * @param Length JLabel to display String 'Length' as Label
 * @param Default JLabel to display String 'Default' as Label
 * @param Constraints JLabel to display String 'Constraints' as Label
 * @param nameEntries 1-D array of Type JTextField, default size is 4
 * @param type 1-D array of Type JTextField, default size is 4
 * @param length 1-D array of Type JTextField, default size is 4
 * @param defaultValue 1-D array of Type JTextField, default size is 4
 * @param constraints 1-D array of Type JTextField, default size is 4
 * @param table_comments 
 * @param table_comments_area
 * @param createButton JButton to create Table
 * @param cancelButton JButton to cancel create table setup
 * 
 * @author GurpreetSingh
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

 public class CreateTableHandler implements ActionListener {

      JPanel jpanel;
	  OracleDatabaseAdminMain mainFrame;    			
	  XYLayout xYLayout1 = new XYLayout(); 
      
	  // Table Name & Number of Columns Related
	  JLabel TableName = new JLabel();           
	  JTextField TableNameField = new JTextField(16);
	  JLabel Total = new JLabel();           
	  JTextField TotalField = new JTextField(16);
	  int TotalColumnFields = 4;
	  JLabel columns = new JLabel();
	  JButton go = new JButton("<html><b>Go</b></html>");
	  JLabel structure = new JLabel();
	  
	  //Columns Related
	  JLabel Name = new JLabel();                
	  JLabel Type = new JLabel();
	  JLabel Length = new JLabel();
	  JLabel Default = new JLabel();
	  JLabel Constraints = new JLabel();
	  JTextField[] nameEntries = new JTextField[4];
	  JComboBox[] type = new JComboBox[4];
	  JTextField[] length = new JTextField[4];
	  JComboBox[] defaultValue = new JComboBox[4];
	  JComboBox[]  constraints = new JComboBox[4];
	  
	  // Table Comments, Create & Cancel Button
      JLabel table_comments = new JLabel();
      JTextField table_comments_area = new JTextField();
      JButton createButton = new JButton("<html><b>Create Table</b></html>");   
      JButton cancelButton = new JButton("<html><b>Cancel</b></html>");        
      
	// Constructor
    public CreateTableHandler (OracleDatabaseAdminMain actualFrame) {
	     mainFrame = actualFrame; 
		 jpanel = actualFrame.MainPanel;
	}
	   
	public void actionPerformed(ActionEvent event) {
	      String menuName = event.getActionCommand();
	      if (menuName.equals("Create New Table")){
	             try {
	            	 CreateTableSetUp();         // size of add column = 0 
	             }catch(Exception ex){	 
	             }
	      }else if (menuName.equals("")){        // for Create Table button in Menu Panel 
	    	 try {
	            	CreateTableSetUp();         // size of add column = 0 
	             }catch(Exception ex){ 	 
	             }
	      }  
	} //actionPerformed
	   
	public void CreateTableSetUp() throws Exception{
		   
		    jpanel.removeAll();	    
		    go.addActionListener(new java.awt.event.ActionListener()
		     {
		        public void actionPerformed(ActionEvent e)
		        {      
		        	   int size = Integer.parseInt(TotalField.getText());
		        	   if(size < 1){
		        		   JOptionPane.showMessageDialog(null, "Total Columns should be >= 1 !", "Message", JOptionPane.WARNING_MESSAGE);
		        	       return;
		        	   }
		        	   jpanel.removeAll();
		        	   nameEntries = new JTextField[size];  
		 			   type = new JComboBox[size]; 
		 			   length = new JTextField[size];
		 			   defaultValue = new JComboBox[size];
		 			   constraints = new JComboBox[size];   
		 			   TotalColumnFields = size;
		 			
                       try {
                    	   CreateTableSetUp();
                       }catch (Exception ex){	   
                       }        
                  }
		     }
		    );
            
		   createButton.addActionListener(new java.awt.event.ActionListener()
		     {
		        public void actionPerformed(ActionEvent e)
		        {	   
		        	   if(TableNameField.getText().equalsIgnoreCase("")){
		        		    JOptionPane.showMessageDialog(null, "Table Name is Not Set!", "Message", JOptionPane.WARNING_MESSAGE);
                            return;
		        	   }
		        	   else{
		        		   int TotalColumns = 0;     		   
			        	   for(int i=0; i<nameEntries.length; i++){
			        		   if(!nameEntries[i].getText().equalsIgnoreCase("") ) {
			        			   TotalColumns++; 
			        		   }
			        	   } 
			        	   if(TotalColumns == 0){             // Check if #columns > 0 or not
			        		   JOptionPane.showMessageDialog(null, "Add atleast 1 Column!", "Message", JOptionPane.ERROR_MESSAGE);
			        		   return;
			        	   }   
		        	    } // else
		        	
		        	   // To check whether there are two columns with same name or not...
		        	   for(int i=0; i< nameEntries.length; i++){
		        		   for(int j=0; j< nameEntries.length; j++){
			        		   if(!nameEntries[j].getText().equalsIgnoreCase("")){
			        			   if( i!=j && nameEntries[i].getText().equalsIgnoreCase(nameEntries[j].getText())){
			        				   JOptionPane.showMessageDialog(null, "Duplicate Column name exists!", "Inane error", JOptionPane.ERROR_MESSAGE);
			        				   return;
			        			   }
			        		   }
			        	   }   
		        	   }
		        	   
		        	   String[] checkSizeSet = {"NVARCHAR2", "VARCHAR2"}; 
		        	   String[] Types = { "BFILE", "BINARY_DOUBLE", "BINARY_FLOAT", "BLOB", "BOOLEAN", "BINARY_INTEGER", "CHAR", "CLOB",
				    			  "DATE", "DEC", "DECIMAL", "DOUBLE", "FLOAT", "INT", "INTEGER", "LONG RAW", "MLSLABEL",
				    			  "NATURAL", "NATURALN", "NCHAR", "NCLOB", "NUMBER", "NUMERIC", "NVARCHAR2", "PLS_INTEGER", "POSITIVE", "POSITIVEN", 
				    			   "RAW", "REAL", "ROWID", "SIGNTYPE", "SMALLINT", "STRING", "TIMESTAMP", "TIMESTAMP WITH TIME ZONE", 
					    		 "TIMESTAMP WITH LOCAL TIME ZONE", "UROWID", "VARCHAR2", "XMLTYPE",   "YEAR" };    
		        	 
	                   String ColumnName, typeVal, size = "", defaultVal, indexVal;
	                   String stmtQuery = "CREATE TABLE "+ TableNameField.getText() + " ( ";
	                   boolean firstEntry = true;
		        	   
		        	   for(int i=0; i<nameEntries.length; i++){
		        		   if(!nameEntries[i].getText().equalsIgnoreCase("") ) {
		        		       
		        			   ColumnName = nameEntries[i].getText();
		        			   int index1 = type[i].getSelectedIndex();
		        		       typeVal = Types[index1]; 
		        			   
		        		        boolean setSize = false;
		        		        for(int j=0; j < checkSizeSet.length; j++){ 
		        		    	    if(Types[index1].equalsIgnoreCase(checkSizeSet[j])){
		        		    	    	
		        		    	    	if(length[i].getText().equals("")){
		        		    	    	  JOptionPane.showMessageDialog(null, "'Length/Value' field can't be empty", "Warning", JOptionPane.WARNING_MESSAGE);	
		        		    	    	  return;	
		        		    	    	}
		        		    	    	 else {
		        		    	    		try{
		        		    	    		   int checksize1 = Integer.parseInt(length[i].getText());
		        		    	    		   size = "("+checksize1+")";  
		        		    	    		   setSize = true;
		        		    	    		}catch (Exception ex){
		        		    	    			JOptionPane.showMessageDialog(null, "Value of Length/Values must be an 'integer' type!", "Error", JOptionPane.ERROR_MESSAGE);
		        		    	    			return;
				        		    	    }
		        		    	    	 }
		        		    	         break;
		        		    	      }
		        		    	}  // for j      
		        		       
		        		        if(setSize == false){
		        		        	if(length[i].getText().equals(""))
		        		        		size = "";
		        		        	else{
		        		        		 try{
		        		        			int sizeVal = Integer.parseInt(length[i].getText());
		        		        		    size = "("+sizeVal+")";	 
		        		        		 } 
		        		        		 catch(Exception ex){
		        		        			 JOptionPane.showMessageDialog(null, "Value of Length/Values must be an 'integer' type!", "Inane error", JOptionPane.ERROR_MESSAGE);
		        		    	    	     return;
		        		        		 }
		        		        	}  
		        		        }// if setSize
		        		        
		        		        String[] defaults2 = { "None", "NOT NULL", "NULL", "CURRENT_TIMESTAMP"};
		        		        if(defaultValue[i].getSelectedIndex() == 0)
		        		        	defaultVal = "";
		        		        else{
		        		           defaultVal = defaults2[defaultValue[i].getSelectedIndex()] ; 
		        		        }
		        		        
		        		        String[] indexes2 = { "---", "PRIMARY KEY", "FOREIGN KEY", "UNIQUE", "INDEX", "BITMAP"};
		        		        if(constraints[i].getSelectedIndex() == 0)
		        		        	  indexVal = "";
		        		        else{
		        		           indexVal = indexes2[constraints[i].getSelectedIndex()] ; 
		        		        }
		        		        
		        		        if(firstEntry == true){
		        		           stmtQuery = stmtQuery + ColumnName + "  " + typeVal + "" + size + " "+defaultVal+" "+indexVal;
		        		           firstEntry = false;
		        		        }else{
		        		           stmtQuery = stmtQuery + " , "+ ColumnName + "  " + typeVal + "" + size + " "+defaultVal+" "+indexVal;
		        		        }
		        		   }  // if
		        	   }  // for i
		        	   
		        	   System.out.println(stmtQuery);
		        	   stmtQuery = stmtQuery + " ) ";
		        	   System.out.println(stmtQuery);
		        	   String CommentsQuery = "COMMENT ON TABLE " +TableNameField.getText()+ " IS '"+table_comments_area.getText()+"'";
		        	   
		        	   ORA_DB connectToCreateTable = new ORA_DB();
		        	   if(connectToCreateTable.createTable(stmtQuery, CommentsQuery) == 0){
		        	        JOptionPane.showMessageDialog(null, "Table Successfuly Created!!!");
		        	        mainFrame.ControlPanel.removeAll(); 
	        				mainFrame.ShowTablesComboBoxInControlPanel();
	        				mainFrame.ControlPanel.setVisible(false);
	        				mainFrame.ControlPanel.setVisible(true);    
		        	   }
		        	   
                     try {
                 	   CreateTableSetUp();
                     }catch (Exception ex){
                     }        
                }
		      }
		     );
		  
		    cancelButton.addActionListener(new java.awt.event.ActionListener()       // Modify this method
	        { 
		        public void actionPerformed(ActionEvent e)
		        {
		 			jpanel.removeAll();
	                try {
	             	   CreateTableSetUp();
	                }catch (Exception ex){       	   
	                }        
	            }
		    }
	       );
		    // Specifying Components, their size, font, background
		     mainFrame.setTitle("Create Table");
		     xYLayout1.setWidth(600);    
		     xYLayout1.setHeight(500);   
		     TableName.setText("Table name: ");
		     TableName.setBackground(new Color(245, 245, 245));
		     TableName.setOpaque(true);
		     Total.setText("Total: ");
		     Total.setBackground(new Color(245, 245, 245));
		     Total.setOpaque(true);
		     
		     TotalField.setText(""+TotalColumnFields+"");
		    
		     columns.setText("column(s)");
		     columns.setBackground(new Color(245, 245, 245));
		     columns.setOpaque(true);   
		     structure.setFont(new Font("Dialog", 1, 13));
		     structure.setBackground(new Color(240, 240, 240));
		     structure.setOpaque(true);
		     structure.setText("                                         " +
		     		"                                   Structure");
		    
		     Name.setFont(new Font("Dialog", 1, 12));
		     Name.setBackground(new Color(220, 220, 220));
		     Name.setOpaque(true);
		     Name.setText("Name");
		     
		     Type.setFont(new Font("Dialog", 1, 12));
		     Type.setBackground(new Color(220, 220, 220));
		     Type.setOpaque(true);
		     Type.setText("Type");
		     
		     Length.setFont(new Font("Dialog", 1, 12));              
		     Length.setBackground(new Color(220, 220, 220));          
		     Length.setOpaque(true);
		     Length.setText("Length/Values");
		     
		     Default.setFont(new Font("Dialog", 1, 12));
		     Default.setBackground(new Color(220, 220, 220));
		     Default.setOpaque(true);
		     Default.setText("Default");
		     
		     Constraints.setFont(new Font("Dialog", 1, 12));
		     Constraints.setBackground(new Color(220, 220, 220));
		     Constraints.setOpaque(true);
		     Constraints.setText("Constraints");
		     
		     table_comments.setFont(new Font("Dialog", 1, 13));
		     table_comments.setBackground(new Color(245, 245, 245));
		     table_comments.setOpaque(true);
		     table_comments.setText("Table comments: ");
		    
		    // Adding Components to Main Frame 
		    jpanel.setLayout(xYLayout1);
		    jpanel.setBackground(SystemColor.control);    
		    jpanel.add(TableName, new XYConstraints(9, 15, 75, 25));            // label TableName
		    jpanel.add(TableNameField, new XYConstraints(84, 15, 250, 25));     // Table Name Text Field
		    jpanel.add(Total, new XYConstraints(360, 15, 40, 25));
		    jpanel.add(TotalField, new XYConstraints(401, 15, 40, 25));            // text field
		    jpanel.add(columns, new XYConstraints(443, 15, 60, 25));
		    jpanel.add(go, new XYConstraints(510, 15, 50, 25));
		    jpanel.add(structure, new XYConstraints(5, 50, 690, 25));
		    jpanel.add(Name, new XYConstraints(5, 75, 130, 25));                // all labels
		    jpanel.add(Type, new XYConstraints(137, 75, 130, 25));
		    jpanel.add(Length, new XYConstraints(269, 75, 130, 25));
		    jpanel.add(Default, new XYConstraints(401, 75, 130, 25));
		    jpanel.add(Constraints, new XYConstraints(533, 75, 162, 25));
		    
		    // Type
	    	String[] types = { "BFILE", "BINARY_DOUBLE", "BINARY_FLOAT", "BLOB", "BOOLEAN", "BINARY_INTEGER", "CHAR", "CLOB",
	    			  "DATE", "DEC", "DECIMAL", "DOUBLE", "FLOAT", "INT", "INTEGER", "LONG RAW", "MLSLABEL",
	    			  "NATURAL", "NATURALN", "NCHAR", "NCLOB", "NUMBER", "NUMERIC", "NVARCHAR2", "PLS_INTEGER", "POSITIVE", "POSITIVEN", 
	                  "RAW", "REAL", "ROWID", "SIGNTYPE", "SMALLINT", "STRING", "TIMESTAMP", "TIMESTAMP WITH TIME ZONE", 
		    		 "TIMESTAMP WITH LOCAL TIME ZONE", "UROWID", "VARCHAR2", "XMLTYPE",   "YEAR" };    
	        
	    	// Default
	    	String[] defaults = { "None", "NOT NULL", "NULL", "CURRENT_TIMESTAMP"};    
	    	//Index
	    	String[] indexes = { "---", "PRIMARY KEY", "FOREIGN KEY", "UNIQUE", "INDEX", "BITMAP"};
		
	    	// Adding & Displaying Specified No. of New Column Creating fiels  to Interface
		    int totalcolumns = nameEntries.length;
		    for(int i=0; i < totalcolumns; i++){
		    	// names
		    	nameEntries[i] = new JTextField(); 
		    	if(i%2!=0)
		    		nameEntries[i].setBackground(new Color(240, 240, 240));
		    	jpanel.add(nameEntries[i], new XYConstraints(10, 75+((i+1)*35), 120, 25));
		    	   	  			       
		    	// Type		      		   
		        type[i] = new JComboBox(types);
		    	type[i].setSelectedIndex(14);
		    	type[i].setBackground(new Color(250, 250, 250));
		    	jpanel.add(type[i], new XYConstraints(142, 75+((i+1)*35), 120, 25));
		    	
		    	// Length 
		    	length[i] = new JTextField(); 
		    	if(i%2!=0)
		    		length[i].setBackground(new Color(240, 240, 240));
		    	jpanel.add(length[i], new XYConstraints(274, 75+((i+1)*35), 120, 25));   
		    	
		    	// Default
		        defaultValue[i] = new JComboBox(defaults);
		    	defaultValue[i].setSelectedIndex(0);
		    	defaultValue[i].setBackground(new Color(250, 250, 250));
		    	jpanel.add(defaultValue[i], new XYConstraints(405, 75+((i+1)*35), 120, 25));
		    	
		    	//Index
		        constraints[i] = new JComboBox(indexes);
		    	constraints[i].setSelectedIndex(0);
		    	constraints[i].setBackground(new Color(250, 250, 250));
		    	jpanel.add(constraints[i], new XYConstraints(537, 75+((i+1)*35), 130, 25));
		    }            
		    
		    jpanel.add(table_comments, new XYConstraints(5, 75+((totalcolumns+1)*35)+33, 250, 40));
		    jpanel.add(table_comments_area, new XYConstraints(5, 75+((totalcolumns+1)*35)+75, 240, 25));
		    JPanel buttonPanel = new JPanel(new XYLayout());             // Panel for Buttons
		    buttonPanel.setPreferredSize(new Dimension(670, 50));
		    buttonPanel.setBackground(new Color(240, 240, 240));
		    jpanel.add(buttonPanel, new XYConstraints(5, 75+((totalcolumns+1)*35)+136, 670, 50));
		    jpanel.setPreferredSize(new Dimension(700, 75+((totalcolumns+1)*35)+136+ 200));
		    buttonPanel.add(createButton, new XYConstraints(400, 10, 120, 20));
		    buttonPanel.add(cancelButton, new XYConstraints(525, 10, 100, 20));
			jpanel.setBackground(new Color(255, 255, 255));      
			mainFrame.setVisible(true);
	   }
	    
 }  //Class CreateTableHandler
