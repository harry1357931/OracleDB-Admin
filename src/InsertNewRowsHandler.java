/* InsertNewRowsHandler
 * Description:
 *   This class handles events related to Insertion of New Rows in existing Tables
 * 
 * @param table_name Table Name in which to insert rows
 * @param num_of_Columns Number of Columns that table have
 * @param ColumnNames Names of those Columns
 * @param ColumnTypes Data Types of Columns like int, string, date etc.
 * @param numberOfNewRows Number of Rows to Insert, default value is 1
 * 
 * @author GurpreetSingh 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

public class InsertNewRowsHandler implements ActionListener {
	   OracleDatabaseAdminMain mainFrame;
	   JPanel mainPanel;
	   String table_name;
	   int num_of_Columns;
	   String[] ColumnNames;
	   String[] ColumnTypes;   
	   int numberOfNewRows = 1;
	   // Constructor
       public InsertNewRowsHandler (OracleDatabaseAdminMain main_frame, String[] colum_names, String tabl_name, int no_Of_columns, String[] column_types) {
	      mainFrame = main_frame;
	      mainPanel = mainFrame.MainPanel;
	      table_name = tabl_name;
		  num_of_Columns = no_Of_columns;
		  ColumnNames = colum_names;
		  ColumnTypes = column_types;
	   }
      
       public void actionPerformed(ActionEvent event) {
	      String menuName = event.getActionCommand();
	      if (menuName.equals("")){                    // for insert data button in View Table--->Option
	           JOptionPane.showMessageDialog(null, "Inserting Rows");
	           InsertNewRows();
	      }     
	   } //actionPerformed
       
       public void InsertNewRows(){
    	   mainPanel.removeAll();
    	   JLabel insertLabel = new JLabel("INSERT Rows in '"+table_name+"'", JLabel.CENTER);
    	   insertLabel.setForeground(new Color(35, 90, 129));
    	   insertLabel.setBackground(new Color(245, 245, 245));
    	   insertLabel.setOpaque(true); 
    	   mainPanel.add(insertLabel, new XYConstraints(2, 5, 715, 22));  
    	   
    	   JPanel[] NewRows = new JPanel[numberOfNewRows];  
    	   final JTextField[][] all_Textfields = new JTextField[numberOfNewRows][num_of_Columns]; 
    	   JLabel[][] labelsAsBackground = new JLabel[numberOfNewRows][num_of_Columns];
    	   JLabel[] columnName= new JLabel[num_of_Columns];
    	   JLabel[] columnType= new JLabel[num_of_Columns];
    	   JButton[] goButtons = new JButton[numberOfNewRows];              
    	   JLabel[] button_backgrounds = new JLabel[numberOfNewRows];           
    	       	   
           for(int i=0; i<numberOfNewRows; i++){
    	           NewRows[i] = new JPanel(new XYLayout());
    	           NewRows[i].setBackground(new Color(255, 255, 255));
		    	   JLabel column_names = new JLabel("Column", JLabel.CENTER); 
		    	   column_names.setOpaque(true);
		    	   JLabel column_type = new JLabel("Type", JLabel.CENTER); 
		    	   column_type.setOpaque(true);
		    	   JLabel value = new JLabel("Value         ", JLabel.CENTER); 
		    	   value.setOpaque(true);
		    	   NewRows[i].add(column_names, new XYConstraints(2, 0, 150, 22));
		    	   NewRows[i].add(column_type, new XYConstraints(153, 0, 150, 22));
		    	   NewRows[i].add(value, new XYConstraints(304, 0, 300, 22));
		    	   
		    	   for(int j=0; j<num_of_Columns; j++){
		    		   columnName[j] = new JLabel(ColumnNames[j], JLabel.CENTER);
		    		   columnName[j].setFont(new Font("", Font.PLAIN, 12)); 
		    		   NewRows[i].add(columnName[j], new XYConstraints(2, 22+3+28*j, 150, 22)); 
		    		   
		    		   columnType[j] = new JLabel(ColumnTypes[j], JLabel.CENTER);
		    		   columnType[j].setFont(new Font("", Font.PLAIN, 12)); 
		    		   NewRows[i].add(columnType[j], new XYConstraints(2+150, 22+3+28*j, 150, 22)); 
		    				   
		    		   all_Textfields[i][j] = new JTextField(25);  
		    		   NewRows[i].add(all_Textfields[i][j], new XYConstraints(375, 22+3+28*j, 150, 22));
		    		   labelsAsBackground[i][j] = new JLabel();
		    		   labelsAsBackground[i][j].setOpaque(true);                
		    		   
		    		   if(j%2==0){
		    			   labelsAsBackground[i][j].setBackground(new Color(255,255,255));
		    		       columnName[j].setBackground(new Color(255,255,255));
		    		       columnType[j].setBackground(new Color(255,255,255));
		    		   }else{
		    			   labelsAsBackground[i][j].setBackground(new Color(245,245,245));
		    			   columnName[j].setBackground(new Color(245,245,245));
		    			   columnType[j].setBackground(new Color(245,245,245));
		    		   }
		    		   NewRows[i].add(labelsAsBackground[i][j], new XYConstraints(2, 22+28*j, 604, 28)); 
		    	   }
		    	   goButtons[i] = new JButton("<html><b>Go</b></html>");
	    	       NewRows[i].add(goButtons[i], new XYConstraints(30, 22+28*num_of_Columns+2, 50, 18));
	    	       button_backgrounds[i] = new JLabel();
	    	       button_backgrounds[i].setOpaque(true);
	    	       button_backgrounds[i].setBackground(new Color(211, 220, 227));
	    	       NewRows[i].add(button_backgrounds[i], new XYConstraints(2, 22+28*num_of_Columns, 604, 22 ));
		    	   mainPanel.add(NewRows[i], new XYConstraints(2, 50+(22+(num_of_Columns*28)+22+50)*i, 604, 22+num_of_Columns*28+22));
           }
                      
    	   for(int i=0; i<goButtons.length; i++){            
             	final int id = i;  	
             	goButtons[id].addActionListener(new java.awt.event.ActionListener()
  			     {
  			        public void actionPerformed(ActionEvent e)  
  			        {   
  			        	String col_names_string = "";
  			        	for(int i=0; i<num_of_Columns; i++){
  			        		if(i==0)
  			        		   col_names_string = col_names_string + " "+ColumnNames[i];
  			        		else
  			        			col_names_string = col_names_string + ", "+ColumnNames[i];
  			        	}
  			        	
  			        	String column_field_values = "";
  			        	for(int j=0; j<num_of_Columns; j++){
  			        		if(j==0)
  			        			column_field_values = column_field_values + " '"+all_Textfields[id][j].getText()+"'";
  			        		else
  			        			column_field_values = column_field_values + ", '"+all_Textfields[id][j].getText()+"'";
  			        	}
  			        	String insertRowQuery = "INSERT INTO "+table_name+" ( "+col_names_string+" ) VALUES ( " + column_field_values + " )";
  			        	int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to: \n "+ insertRowQuery ,"Are You Sure ?", JOptionPane.YES_OPTION);
  			        	if(dialogResult == JOptionPane.YES_OPTION){
  			        		ORA_DB insertRow = new ORA_DB();
  			        		boolean rowAdded = insertRow.SQL_QueryExecuter(insertRowQuery);   
  			        		if(rowAdded){
  			        			JOptionPane.showMessageDialog(null, "Row Added Successfully!");		
  			        		}else{
  			        			JOptionPane.showMessageDialog(null, "Unsuccessful!");
  			        		}
  			        	}
  			        }
  			     } 
  			    );
  	            
            }   // for
           mainPanel.setVisible(false);
    	   mainPanel.setVisible(true);
    	   mainFrame.setTitle("Insert Rows"); 
    	   mainFrame.setVisible(true);
       }  
                      
 }   // InsertNewRowsHandler
