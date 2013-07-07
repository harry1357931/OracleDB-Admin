/* ViewInsertDeleteHandler
 * Description:
 *   1) This class lets you View Table
 *   2) This class contains methods to Insert Rows into selected Table
 *   3) This class contains methods to Delete Columns/Rows in a Table
 *   4) This class let you Drop Table
 ************
 * Parameters
 ************
 * @param mainFrame
 * @param mainPanel		  
 * @param NameLabel
 * @param InsertDataPanel
 * @param insert
 * @param numberOfRows
 * @param rows_text
 * @param insertButton 
 * @param DeleteTablePanel
 * @param dropTable
 * @param TableToDrop
 * @param dropButton 
 * @param ColumnNames  
 * @param deleteColumnButtons
 * @param deleteColumnbuttonbackgrounds 
 * @param rows
 * @param deleteRowsButtons 
 * @param deleteRowsbuttonbackgrounds
 *    
 * @author GurpreetSingh 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

public class ViewInsertDeleteHandler implements ActionListener {
		   
      OracleDatabaseAdminMain mainFrame;
	  JPanel mainPanel;
	  JLabel NameLabel;
	  JPanel InsertDataPanel;
	  JLabel insert;
	  JTextField numberOfRows;
	  JLabel rows_text;
	  JButton insertButton;
	  JPanel DeleteTablePanel;
	  JLabel dropTable;
	  JLabel TableToDrop;
	  JButton dropButton;
	  
	  JLabel[] ColumnNames;  
      JButton[] deleteColumnButtons;
      JLabel[] deleteColumnbuttonbackgrounds; 
      JLabel[][]  rows;  
      JButton[] deleteRowsButtons; 
      JLabel[] deleteRowsbuttonbackgrounds;
      
      // Constructor
      public ViewInsertDeleteHandler (OracleDatabaseAdminMain main_frame) {   
	      mainFrame = main_frame;
	      mainPanel = main_frame.MainPanel;
	  }
	   
      public void actionPerformed(ActionEvent event) {
	      String menuName = event.getActionCommand();
          System.out.println("The action you performed is:" +  menuName);  
			  
          if (menuName.equals("comboBoxChanged")){ 
        	  
        	  JComboBox MainComboBoxInPanel = (JComboBox)(event.getSource());
        	  try{
        	    showSelectedTable((String)(MainComboBoxInPanel.getSelectedItem()));   // passing name of the Selected table Name
        	  }catch(Exception ex){
        	  }
          }    
		  else if (menuName.equals("Exit"))
		          System.exit(0);    
        }  // actionPerformed
   
    public void showSelectedTable(String TableName) throws Exception {                  
	    final String table_name = TableName;
	    mainPanel.setVisible(false);
	    mainPanel.setVisible(true);
	    String getTableQuery = "SELECT* FROM "+ TableName;
		System.out.println(getTableQuery);
		
		ORA_DB getSingleTable = new ORA_DB();  
	    final ShowTableData  tableData = getSingleTable.getTable(getTableQuery);               // Getting data from selected Table...
	    
	    // GUI Part for Showing Table Starts here
	    mainPanel.removeAll();
	    mainFrame.setTitle(TableName);      
	    mainPanel.setBackground(new Color(255, 255, 254));   
	    		    
	    NameLabel = new JLabel(TableName, JLabel.CENTER); 
	    NameLabel.setBackground(new Color(35, 90, 150));           // 100, 100, 100
	    NameLabel.setFont(new Font("Dialog", 1, 14)); 
	    NameLabel.setForeground(new Color(250, 250, 250));
	    NameLabel.setOpaque(true);
	    Border border =  BorderFactory.createLineBorder(new Color(170, 170, 170));       //BorderFactory.createLineBorder(Color.blue);
	    NameLabel.setBorder(border);
	    mainPanel.add(NameLabel, new XYConstraints(40, 10, 150, 20)); 

	    InsertDataPanel = new JPanel(new XYLayout());                     // 
	    InsertDataPanel.removeAll();
	    InsertDataPanel.setBackground(new Color(245, 245, 245));
	    mainPanel.add(InsertDataPanel, new XYConstraints(3, 20, 710, 40));
	    
	    insert = new JLabel("INSERT: ");
	    insert.setFont(new Font("Dialog", 1, 13));
	    insert.setForeground(new Color(35, 90, 129));              // 153,0,153
	    InsertDataPanel.add(insert, new XYConstraints(45, 15, 60, 20));
	    
	    numberOfRows = new JTextField(16);
	    numberOfRows.setText(""+1+"");
	    InsertDataPanel.add(numberOfRows, new XYConstraints(105, 15, 40, 24));
	    
	    rows_text = new JLabel(" new row(s) into Table '"+TableName+"'");
	    rows_text.setFont(new Font("Dialog", 1, 13));
	    rows_text.setForeground(new Color(0, 128, 0));
	    InsertDataPanel.add(rows_text, new XYConstraints(147, 15, 250, 20));
	    
	    insertButton = new JButton(new ImageIcon(((new ImageIcon("Images/insert_row.png")).getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
	    insertButton.setToolTipText("Insert new rows");
	    InsertDataPanel.add(insertButton, new XYConstraints(20, 16, 15, 15));
	    
	    final InsertNewRowsHandler idh  = new InsertNewRowsHandler(mainFrame, tableData.column_names, TableName, tableData.numberOfColumns, tableData.column_class_types);   
	    insertButton.addActionListener(idh);   
	    insertButton.addActionListener(new java.awt.event.ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {       
	        	idh.numberOfNewRows = Integer.parseInt(numberOfRows.getText());  
	        }
	     } 
	    );

	    DeleteTablePanel = new JPanel(new XYLayout());                      //
	    DeleteTablePanel.removeAll();
	    DeleteTablePanel.setBackground(new Color(235, 235, 235));
	    mainPanel.add(DeleteTablePanel, new XYConstraints(3, 60, 710, 40));
	    
	    dropTable = new JLabel("DROP TABLE ");
	    dropTable.setFont(new Font("Dialog", 1, 13));
	    dropTable.setForeground(new Color(35, 90, 129));
	    DeleteTablePanel.add(dropTable, new XYConstraints(45, 12, 90, 20));
	    
	    TableToDrop = new JLabel("'"+ TableName + "'");
	    TableToDrop.setFont(new Font("Dialog", 1, 13));
	    TableToDrop.setForeground(new Color(0, 128, 0));
	    DeleteTablePanel.add(TableToDrop, new XYConstraints(135, 12, 100, 20));             
	    
	    dropButton = new JButton(new ImageIcon(((new ImageIcon("Images/DropTable.jpg")).getImage()).getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH)));
	    dropButton.setToolTipText("Drop table '"+TableName+"'");
	    DeleteTablePanel.add(dropButton, new XYConstraints(20, 15, 15, 15)); 
	    
	    dropButton.addActionListener(new java.awt.event.ActionListener()
	     {
	        public void actionPerformed(ActionEvent e)
	        {       
	        	String dropTableQuery = "DROP TABLE "+table_name;
	        	int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to: \n "+ dropTableQuery ,"Are You Sure ?", JOptionPane.YES_OPTION);
	        	
	        	if(dialogResult == JOptionPane.YES_OPTION){
	        		ORA_DB dropTable = new ORA_DB();
	        		boolean dropped = dropTable.SQL_QueryExecuter(dropTableQuery); 
	        		if(dropped){
	        			JOptionPane.showMessageDialog(null, "Table Dropped Successfully!");
	        			mainPanel.removeAll();
	        			try {
	        				
	        				JTextArea message = new JTextArea();
	        				message.append("Table Dropped Successfully !\n\n You can do following things: \n\n    1) Create New Table \n    2) View and Edit Table by selecting it from Left Window ");
	        		        message.setOpaque(true);
	        				mainPanel.add(message, new XYConstraints(30, 30, 800, 800));
	        				mainFrame.ControlPanel.removeAll(); 
	        				mainFrame.ShowTablesComboBoxInControlPanel();
	        				mainFrame.ControlPanel.setVisible(false);
	        				mainFrame.ControlPanel.setVisible(true);
	        				mainPanel.setVisible(false);
	        				mainPanel.setVisible(true);
	        				
	        			}catch(Exception ex){
	        			 } 
	        			
	        		}else{
	        			JOptionPane.showMessageDialog(null, "Unsuccessfull!");
	        		}
	        		
	        	}else{
	        		
	        	}
	        }
	     } 
	    );
   
	    JPanel showingSelectFromTablePanel = new JPanel(new XYLayout());
	    showingSelectFromTablePanel.setOpaque(true);
	    showingSelectFromTablePanel.setBackground(new Color(235, 235, 235));
	    mainPanel.add(showingSelectFromTablePanel, new XYConstraints(2, 150, 710, 30)); 
	    
	    JLabel selectFrom = new JLabel("SELECT* FROM ");
	    selectFrom.setFont(new Font("Dialog", 1, 12));
	    selectFrom.setForeground(new Color(153, 0 ,153));           //(35, 90, 129));              // 153,0,153
	    showingSelectFromTablePanel.add(selectFrom, new XYConstraints(10, 5, 90, 20));
	    
	    JLabel tableName = new JLabel("'"+ TableName + "'");
	    tableName.setFont(new Font("Dialog", 1, 12));
	    tableName.setForeground(new Color(0, 128 ,0));              // 153,0,153
	    showingSelectFromTablePanel.add(tableName, new XYConstraints(100, 5, 100, 20));
	    
	    JLabel leftCorner = new JLabel();
	    leftCorner.setOpaque(true);
	    leftCorner.setBackground(new Color(245, 245, 245));
	    mainPanel.add(leftCorner, new XYConstraints(2, 177+50, 120, 45));
	    
	    ColumnNames = new JLabel[tableData.numberOfColumns];  
	    deleteColumnButtons = new JButton[tableData.numberOfColumns];
	    deleteColumnbuttonbackgrounds = new JLabel[tableData.numberOfColumns]; 
	    rows = new JLabel[tableData.numberOfRows][tableData.numberOfColumns];  
	    deleteRowsButtons = new JButton[tableData.numberOfRows]; 
	    deleteRowsbuttonbackgrounds = new JLabel[tableData.numberOfRows];
	    
	    for(int i=0; i<tableData.numberOfColumns; i++){
        	deleteColumnButtons[i] = new JButton(new ImageIcon(((new ImageIcon("Images/Delete_GarbageCan_LightUp.PNG")).getImage()).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
	    	deleteColumnButtons[i].setToolTipText("Delete Column '"+tableData.column_names[i]+"'");
	    	Border borderDelButton =  BorderFactory.createLineBorder(new Color(220, 220, 220));
	    	deleteColumnButtons[i].setBorder(borderDelButton);
	    	mainPanel.add(deleteColumnButtons[i], new XYConstraints(121*(i+1)+2+50, 177+50, 20, 20));
	    
	    	deleteColumnbuttonbackgrounds[i] = new JLabel();
		    deleteColumnbuttonbackgrounds[i].setBackground(new Color(245, 245, 245));
		    deleteColumnbuttonbackgrounds[i].setOpaque(true);
		    mainPanel.add(deleteColumnbuttonbackgrounds[i], new XYConstraints(121*(i+1)+2, 177+50, 120, 22));
	    	
	    	ColumnNames[i] = new JLabel(tableData.column_names[i], JLabel.CENTER);
	    	ColumnNames[i].setOpaque(true);
	    	ColumnNames[i].setBackground(new Color(235, 235, 235));
	    	ColumnNames[i].setForeground(new Color(35, 90, 129));  
	    	mainPanel.add(ColumnNames[i], new XYConstraints(121*(i+1)+2, 200+50, 120, 22));
	    }
        
       for(int i=0; i<tableData.numberOfRows; i++){
	      for(int j=0; j<tableData.numberOfColumns; j++){	
        	rows[i][j] = new JLabel(tableData.allRowsAsStrings[i][j], JLabel.CENTER);  
        	rows[i][j].setOpaque(true);
        	rows[i][j].setFont(new Font("", Font.PLAIN, 14));
        	
        	if(i%2==0){
        		rows[i][j].setBackground(new Color(250, 250, 250));
        	}else {
        		rows[i][j].setBackground(new Color(240, 240, 240));
        	}
        	mainPanel.add(rows[i][j], new XYConstraints(121*(j+1)+2, 222+(23*i)+1+50, 120, 22));
	      }
	      
	      deleteRowsButtons[i] = new JButton(new ImageIcon(((new ImageIcon("Images/Delete_GarbageCan_LightUp.PNG")).getImage()).getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)));
	      deleteRowsButtons[i].setToolTipText("Delete this row");
	      Border borderDelButton =  BorderFactory.createLineBorder(new Color(220, 220, 220));
	      deleteRowsButtons[i].setBorder(borderDelButton);
	      mainPanel.add(deleteRowsButtons[i], new XYConstraints(50, 222+(23*i)+1+50, 20, 20));
	      
	      deleteRowsbuttonbackgrounds[i] = new JLabel();
	      if(i%2==0){
	          deleteRowsbuttonbackgrounds[i].setBackground(new Color(250, 250, 250));
	      }else {
	    	  deleteRowsbuttonbackgrounds[i].setBackground(new Color(240, 240, 240));
	      }
	      deleteRowsbuttonbackgrounds[i].setOpaque(true);
	      mainPanel.add(deleteRowsbuttonbackgrounds[i], new XYConstraints(2, 222+(23*i)+1+50, 120, 22));
	   }
       
       mainFrame.setVisible(true); 
       for(int i=0; i<deleteColumnButtons.length; i++){            
        	final int id=i;
        	final String column_name = tableData.column_names[id];
            deleteColumnButtons[id].addActionListener(new java.awt.event.ActionListener()
		     {
		        public void actionPerformed(ActionEvent e)
		        {       
		        	String dropColumnQuery = "ALTER TABLE "+table_name+ " DROP COLUMN "+column_name;
		        	int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to: \n "+ dropColumnQuery ,"Are You Sure ?", JOptionPane.YES_OPTION);
		        	
		        	if(dialogResult == JOptionPane.YES_OPTION){
		        		ORA_DB dropColumn = new ORA_DB();
		        		boolean dropped = dropColumn.SQL_QueryExecuter(dropColumnQuery);
		        		if(dropped){
		        			JOptionPane.showMessageDialog(null, "Column Dropped Successfully!");
		        			mainPanel.removeAll();
		        			try {
		        				showSelectedTable(table_name); 
		        			}catch(Exception ex){
		        			}
		        		}else{
		        			JOptionPane.showMessageDialog(null, "Unsuccessfull!");
		        		} //else
		        		
		        	} // if
		        } // actionPerformed
		     } 
		    );
            
        }   // for
        
        for(int i=0; i<deleteRowsButtons.length; i++){            
        	final int id=i;
        	deleteRowsButtons[id].addActionListener(new java.awt.event.ActionListener()
		     {
		        public void actionPerformed(ActionEvent e)
		        {   String addQuery= "";    	
		        	for(int j=0; j<tableData.numberOfColumns; j++){
		        		if(j==0)
		        		   addQuery = addQuery + " "+tableData.column_names[j] +"='"+ tableData.allRowsAsStrings[id][j]+"'"; 
		        		else
		        		   addQuery = addQuery + " AND "+tableData.column_names[j] +"='"+ tableData.allRowsAsStrings[id][j]+"'";
		        	}
		        	String dropRowQuery = "DELETE FROM "+table_name+ " WHERE( "+addQuery+" )";
		        	
		        	int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to: \n "+ dropRowQuery ,"Are You Sure ?", JOptionPane.YES_OPTION);
		        	
		        	if(dialogResult == JOptionPane.YES_OPTION){
		        		ORA_DB dropRow = new ORA_DB();
		        		boolean dropped = dropRow.SQL_QueryExecuter(dropRowQuery);
		        		if(dropped){
		        			JOptionPane.showMessageDialog(null, "Row Dropped Successfully!");
		        			mainPanel.removeAll();
		        			try {
		        				showSelectedTable(table_name); 
		        			}catch(Exception ex){
		        			} 
		        		
		        		}else{
		        			JOptionPane.showMessageDialog(null, "Unsuccessfull!");
		        		}	
		        	} // if
		        } // actionPerformed
		     } 
		    );
        }
   }    
		              
}//  ViewInsertDeleteHandler
