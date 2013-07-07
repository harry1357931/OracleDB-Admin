/* OracleDatabaseAdmin: Contains Main Class
 * Description: 
 *   A Database admin designed by Gurpreet Singh.
 *   Functionalities:
 *   1) Connects to Remote Database
 *   2) Create new Table, View Old Tables
 *   3) Insert/Delete any number of Rows in Tables
 *   4) Insert/Delete any no. of Columns in Tables
 *   5) Drop Table 
 *
 ************
 * Parameters
 ************
 * @param MainPanelHomeArea 
 * @param MainPanel 
 * @param ControlPanel
 * @param MenuPanel
 *
 *************
 * Author Info
 *************
 * @author Gurpreet Singh
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import oracle.jdeveloper.layout.XYConstraints;
import oracle.jdeveloper.layout.XYLayout;

public class OracleDatabaseAdminMain extends JFrame {   
	 
	private static final long serialVersionUID = 1L;
	JTextArea MainPanelHomeArea;
	JPanel MainPanel, ControlPanel, MenuPanel; 
	       
	public static void main(String[] args) {
		
		 OracleDatabaseAdminMain display = new OracleDatabaseAdminMain("Oracle Database Admin by Gurpreet Singh", 950, 700);  
	}
	
	/*  OracleDatabaseAdmin: Constructor with 3 arguments
	 *  @param title Title of Window
	 *  @param width Width of Opening Window
	 *  @param height Height of Opening Window
	 */
    public OracleDatabaseAdminMain(String title, int width, int height) {     // Constructor
		   
    	   ImageIcon db_icon = new ImageIcon("Images/database_icon.jpg");
    	   setIconImage(db_icon.getImage()) ;
    	  
    	   ControlPanel = new JPanel(new XYLayout());
    	   ControlPanel.setPreferredSize(new Dimension(200, 580));
    	   ControlPanel.setBackground(new Color(250, 250, 250));
    	   ControlPanel.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
    	   
		   MainPanel = new JPanel(new XYLayout());
		   MainPanel.setPreferredSize(new Dimension(700, 1500));
		   MainPanel.setBackground(new Color(255, 255, 255));   
		   
		   MainPanelHomeArea = new JTextArea("\n   Welcome to Gurpreet's Oracle Database Admin!");
		   MainPanelHomeArea.append("\n\n   Here you can Create, Insert and Delete Tables...\n   Use Menu and MenuIcons to do stuff here...");
		   MainPanelHomeArea.setFont(new Font("Dialog", 1, 14));
		   MainPanelHomeArea.setBackground(new Color(250, 250, 250));
		   //MainPanelHomeArea.setBorder(BorderFactory.createLineBorder(Color.black));
		   MainPanel.add(MainPanelHomeArea, new XYConstraints(1, 1, 1050, 700));   
		   
		   MenuPanel = new JPanel(new XYLayout());
		   
	       getContentPane().setLayout(new XYLayout());            // XY Layout
		   getContentPane().add(new JScrollPane(ControlPanel), new XYConstraints(5, 105, 200, 600 ));
	       
	       JScrollPane main = new JScrollPane(MainPanel);
	       main.setPreferredSize(new Dimension(800,650));
	       getContentPane().add(main, new XYConstraints(210, 105, 1065, 600 ));
	       getContentPane().add(MenuPanel, new XYConstraints(0, 0, 1270, 100));
		   
	       // Calling Methods to add the functionalities in the Window
		    pack();
	        setTitle(title);
		    setSize(width, height);
	        setLocation(150,30);
		    createFileMenu();
		    createCreateMenu();
		    createInsertMenu();
		    createDeleteMenu();
		    createTableButtonInMenuPanel();
		    InsertDataButtonInMenuPanel();
		    DeleteDataButtonInMenuPanel();
		    ShowTablesComboBoxInControlPanel();
		    setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setVisible(true);	        
	        
	   } // Constructor
	 
     private void createFileMenu() {
	      JMenuItem   item;
	      JMenuBar    menuBar  = new JMenuBar();
	      JMenu       fileMenu = new JMenu("File");
	      //fileMenu.setFont(new Font("Arial", Font.PLAIN,13));
	      FileMenuHandler fmh  = new FileMenuHandler(this);
          
	      item = new JMenuItem("Exit");      //Quit
	      item.addActionListener( fmh );
	      fileMenu.add( item );

	      setJMenuBar(menuBar);
	      menuBar.add(fileMenu);
	    
	   } //createFileMenu

     public void createCreateMenu() {
	      JMenuItem   item2;
	      JMenuBar    menuBar  = this.getJMenuBar();
	      JMenu       editMenu = new JMenu("Create");
	      //editMenu.setFont(new Font("Dialog", 12,13));
	      CreateTableHandler emh  = new CreateTableHandler(this);

	      item2 = new JMenuItem("Create New Table");    //Open...
	      item2.addActionListener( emh );
	      editMenu.add(item2);
          editMenu.addSeparator();           //add a horizontal separator line
	      
	      setJMenuBar(menuBar);
	      menuBar.add(editMenu);
	    
	   } //createEditMenu
        
     private void createInsertMenu() {
	      JMenuItem   item3;
	      JMenuBar    menuBar  = this.getJMenuBar();
	      JMenu       editMenu = new JMenu("Insert");
	      //editMenu.setFont(new Font("Dialog", 200,13));
	      
	      TransferToShowTableAndInsertRowHandler emh  = new TransferToShowTableAndInsertRowHandler(this);   
	      item3 = new JMenuItem("Insert data Into Table");    //Open...
	      item3.addActionListener(emh);
	      editMenu.add(item3);
          editMenu.addSeparator();           //add a horizontal separator line
	      
	      setJMenuBar(menuBar);
	      menuBar.add(editMenu);
	    
	   } //createEditMenu
  
     private void createDeleteMenu() {
	      JMenuItem   item4;
	      JMenuBar    menuBar  = this.getJMenuBar();
	      JMenu       editMenu = new JMenu("Delete");
	             
	      DeleteTableOrRowHandler emh  = new DeleteTableOrRowHandler(this);
 
	      item4 = new JMenuItem("Delete data from Table");    //Open...
	      item4.addActionListener( emh );
	      
	      editMenu.add(item4);
          editMenu.addSeparator();           //add a horizontal separator line
	      setJMenuBar(menuBar);
	      menuBar.add(editMenu);
	      
	   } //createEditMenu

     
     public void ShowTablesComboBoxInControlPanel(){
    	 
    	 ControlPanel.removeAll();    	     	 
    	  
    	 JButton try_againButton = new JButton();        // refresh button for Internet Connection...
    	 try_againButton.addActionListener(new java.awt.event.ActionListener()
		  {
		        public void actionPerformed(ActionEvent e)
		        {   
		        	ShowTablesComboBoxInControlPanel();
		        }
		   } 
		   );

    	 ORA_DB ToShowTables = new ORA_DB(); 
    	 
    	 if(ToShowTables.connected == false){
    		  JTextArea message2 = new JTextArea(" Unable to list all Tables.\n" +
    		  		           " Please Check your Internet\n Connection:");
    		  ControlPanel.add(message2, new XYConstraints(5, 100, 180, 60)); 
    		  try_againButton.setText("Try Again"); 
    		  ControlPanel.add(try_againButton, new XYConstraints(5, 165, 100, 20));  
    		  ControlPanel.setVisible(false);
    		  ControlPanel.setVisible(true);
    		  return;
    	 }
    	 
    	 // ControlPanel.add(try_againButton, new XYConstraints(5, 165, 100, 20));  
		  
    	 JLabel LabelTables = new JLabel("All Tables:", JLabel.CENTER); 
    	 LabelTables.setBackground(new Color(35, 90, 150));           // 100, 100, 100
    	 LabelTables.setFont(new Font("Dialog", 1, 13)); 
    	 LabelTables.setForeground(new Color(250, 250, 250));
    	 LabelTables.setOpaque(true);
	     Border border =  BorderFactory.createLineBorder(new Color(170, 170, 170));       //BorderFactory.createLineBorder(Color.blue);
	     LabelTables.setBorder(border);
	     ControlPanel.add(LabelTables, new XYConstraints(10, 48, 80, 20)); 
    	 
	     String[] alltables = ToShowTables.showTables("SELECT TABLE_NAME AS NAME FROM USER_TABLES");        // show tables and get tables are two different methods in ORA_DBTest
    	 JComboBox showTables = new JComboBox(alltables); 
    	 showTables.setSelectedIndex(0);
    	 showTables.setBackground(new Color(255, 255, 255));
    	 ControlPanel.add(showTables, new XYConstraints(10, 75, 160, 25));
    	 
    	 ViewInsertDeleteHandler vidh  = new ViewInsertDeleteHandler(this);   
    	 showTables.addActionListener(vidh);
    	 JTextArea message1 = new JTextArea(" You can view and edit any \n table" +
		           " just by selecting it!");
    	 message1.setBackground(new Color(250, 250, 250));
	     ControlPanel.add(message1, new XYConstraints(10, 105, 160, 60)); 
    	 ControlPanel.setVisible(false);
    	 ControlPanel.setVisible(true);
     }
     
     public class TransferToShowTableAndInsertRowHandler implements ActionListener{
    	 
    	 OracleDatabaseAdminMain mainFrame;
    	 JPanel mainPanel;
    	 public TransferToShowTableAndInsertRowHandler (OracleDatabaseAdminMain main_frame) {
		      
    		 mainFrame = main_frame;
    		 mainPanel = main_frame.MainPanel;
		 }
		   
		public void actionPerformed(ActionEvent event) {
		      String menuName = event.getActionCommand();
		      int index = event.getID();		      
		      InsertDataStartupTableSelector();
		} //actionPerformed
		
		public void InsertDataStartupTableSelector(){
			 mainPanel.removeAll();    	     	
			 mainFrame.setTitle("Inserting ROW in Table");      
   		     mainPanel.setBackground(new Color(255, 255, 254));   
		    		    
		     JLabel NameLabel = new JLabel("INSERT", JLabel.CENTER); 
		     NameLabel.setBackground(new Color(35, 90, 150));           // 100, 100, 100
		     NameLabel.setFont(new Font("Dialog", 1, 14)); 
		     NameLabel.setForeground(new Color(250, 250, 250));
		     NameLabel.setOpaque(true);
		     Border border =  BorderFactory.createLineBorder(new Color(170, 170, 170));       //BorderFactory.createLineBorder(Color.blue);
		     NameLabel.setBorder(border);
		     mainPanel.add(NameLabel, new XYConstraints(100, 70, 100, 20)); 

		     JPanel InsertRowMenuPanel = new JPanel(new XYLayout());                     // 
		     InsertRowMenuPanel.removeAll();
		     InsertRowMenuPanel.setBackground(new Color(250, 250, 250));
		     mainPanel.add(InsertRowMenuPanel, new XYConstraints(80, 80, 350, 200));
		     
	    	 JButton try_againButton = new JButton();        // refresh button for Internet Connection...
	    	 try_againButton.addActionListener(new java.awt.event.ActionListener()              // Edit
			  {
			        public void actionPerformed(ActionEvent e)
			        {   
			        	InsertDataStartupTableSelector();
			        }
			   } 
			   );

	    	 ORA_DB ToShowTables = new ORA_DB(); 	 
	    	 if(ToShowTables.connected == false){
	    		  
	    		  JTextArea message2 = new JTextArea(" Unable to list all Tables.\n" +
	    		  		           " Please Check your Internet\n Connection:");
	    		  InsertRowMenuPanel.add(message2, new XYConstraints(20, 20, 180, 60)); 
	    		  try_againButton.setText("Try Again"); 
	    		  InsertRowMenuPanel.add(try_againButton, new XYConstraints(20, 85, 100, 20));  
	    		  mainPanel.setVisible(false);
	 	    	  mainPanel.setVisible(true);
	    		  return;
	    	 }
	    	     	  
	    	 JLabel selectTable = new JLabel(" SELECT Table to INSERT Rows: ");
	    	 selectTable.setOpaque(true); 
	    	 InsertRowMenuPanel.add(selectTable, new XYConstraints(20, 20, 300, 23)); 
	    	 
	    	 String[] alltables = ToShowTables.showTables("SELECT TABLE_NAME AS NAME FROM USER_TABLES");        // show tables and get tables are two different methods in ORA_DBTest    	 
	    	 final JComboBox showTables = new JComboBox(alltables); 
	    	 showTables.setSelectedIndex(0);
	    	 showTables.setBackground(new Color(255, 255, 255));
	    	 InsertRowMenuPanel.add(showTables, new XYConstraints(20, 50, 300, 25));
	    	 
	    	 JLabel insertLabel = new JLabel(" INSERT: ");
	    	 insertLabel.setOpaque(true);
	    	 InsertRowMenuPanel.add(insertLabel, new XYConstraints(21, 90, 53, 23)); 
	    	 
	    	 final JTextField num_rows = new JTextField(16);
	    	 InsertRowMenuPanel.add(num_rows, new XYConstraints(75, 90, 50, 23));
	    	 
	    	 JLabel rowsLabel = new JLabel("new row(s)");
	    	 rowsLabel.setOpaque(true);
	    	 InsertRowMenuPanel.add(rowsLabel, new XYConstraints(125, 90, 190, 23)); 
	    	 
	    	 JButton goButton = new JButton("Go"); 
	    	 InsertRowMenuPanel.add(goButton, new XYConstraints(20, 130, 50, 23));
	    	 
	    	 goButton.addActionListener(new java.awt.event.ActionListener()
			    {
			        public void actionPerformed(ActionEvent e)
			        {    String table_name = (String)(showTables.getSelectedItem());   
			        	 String getTableQuery = "SELECT* FROM "+ table_name;		
						 ORA_DB getSingleTable = new ORA_DB();  
					     ShowTableData  tableData = getSingleTable.getTable(getTableQuery);               // Getting data from selected Table...
		        	     
					     InsertNewRowsHandler idh  = new InsertNewRowsHandler(mainFrame, tableData.column_names, table_name, tableData.numberOfColumns, tableData.column_class_types); 
			        	 idh.numberOfNewRows = Integer.parseInt(num_rows.getText());
			        	 idh.InsertNewRows();
			        }
			     } 
			    );
	    	 mainPanel.setVisible(false);
	    	 mainPanel.setVisible(true);			 
		 }
     }
	 
     public void InsertDataButtonInMenuPanel(){
    	 JButton Insert_dataButton = new JButton(new ImageIcon(((new ImageIcon("Images/Insert_row.png")).getImage()).getScaledInstance(78, 83, java.awt.Image.SCALE_SMOOTH)));
         Insert_dataButton.setBorderPainted(true);  
    	 Insert_dataButton.setFocusPainted(true);  
    	 Insert_dataButton.setContentAreaFilled(true); 
    	 Insert_dataButton.setToolTipText("Insert data into Table");
    	 TransferToShowTableAndInsertRowHandler emh  = new TransferToShowTableAndInsertRowHandler(this);   
         Insert_dataButton.addActionListener(emh);                         // Edit
         MenuPanel.add(Insert_dataButton, new XYConstraints(105, 10, 78, 83));
     }
     
     public void DeleteDataButtonInMenuPanel(){
    	 JButton delete_dataButton = new JButton(new ImageIcon(((new ImageIcon("Images/Delete_Table_icon.png")).getImage()).getScaledInstance(78, 83, java.awt.Image.SCALE_SMOOTH)));
    	 delete_dataButton.setBorderPainted(true);  
    	 delete_dataButton.setFocusPainted(true);  
    	 delete_dataButton.setContentAreaFilled(true); 
    	 delete_dataButton.setToolTipText("Delete data");
    	 DeleteTableOrRowHandler emh  = new DeleteTableOrRowHandler(this);    
         delete_dataButton.addActionListener(emh);                               	 
         MenuPanel.add(delete_dataButton, new XYConstraints(195, 10, 78, 83));   
     }
     
     public class DeleteTableOrRowHandler implements ActionListener{	 
    	 OracleDatabaseAdminMain mainFrame;
    	 JPanel mainPanel;
    	 
    	 public DeleteTableOrRowHandler(OracleDatabaseAdminMain main_frame) {
    		 mainFrame = main_frame;
    		 mainPanel = main_frame.MainPanel;
		 }
		   
		 public void actionPerformed(ActionEvent event) {
		      String menuName = event.getActionCommand();
		      int index = event.getID();     
		      deleteRowOrTableSelector();
		 } //actionPerformed
		
		 public void deleteRowOrTableSelector(){
			 
			 mainPanel.removeAll();    	     		 
			 mainFrame.setTitle("Select Table to DeleteRow, DeleteColumn or Drop Table itself ");      
   		     mainPanel.setBackground(new Color(255, 255, 254));   
		    		    
		     JLabel NameLabel = new JLabel("DELETE", JLabel.CENTER); 
		     NameLabel.setBackground(new Color(35, 90, 150));           
		     NameLabel.setFont(new Font("Dialog", 1, 14)); 
		     NameLabel.setForeground(new Color(250, 250, 250));
		     NameLabel.setOpaque(true);
		     Border border =  BorderFactory.createLineBorder(new Color(170, 170, 170));      
		     NameLabel.setBorder(border);
		     mainPanel.add(NameLabel, new XYConstraints(100, 70, 100, 20)); 

		     JPanel InsertRowMenuPanel = new JPanel(new XYLayout());                    
		     InsertRowMenuPanel.removeAll();
		     InsertRowMenuPanel.setBackground(new Color(250, 250, 250));
		     mainPanel.add(InsertRowMenuPanel, new XYConstraints(80, 80, 350, 200));
		     
	    	 JButton try_againButton = new JButton();        // refresh button for Internet Connection...
	    	 try_againButton.addActionListener(new java.awt.event.ActionListener()              // Edit
			  {
			        public void actionPerformed(ActionEvent e)
			        {   
			        	deleteRowOrTableSelector();
			        }
			   } 
			   );

	    	 ORA_DB ToShowTables = new ORA_DB(); 
	    	 if(ToShowTables.connected == false){
	    		  
	    		  JTextArea message2 = new JTextArea(" Unable to list all Tables.\n" +
	    		  		           " Please Check your Internet\n Connection:");
	    		  InsertRowMenuPanel.add(message2, new XYConstraints(20, 20, 180, 60)); 
	    		  try_againButton.setText("Try Again"); 
	    		  InsertRowMenuPanel.add(try_againButton, new XYConstraints(20, 85, 100, 20));  
	    		  
	    		  mainPanel.setVisible(false);
	 	    	  mainPanel.setVisible(true);
	    		  return;
	    	 }
	    	 
	    	 
	    	 JLabel selectTable = new JLabel(" SELECT Table: ");
	    	 selectTable.setOpaque(true); 
	    	 InsertRowMenuPanel.add(selectTable, new XYConstraints(20, 20, 300, 23)); 
	    	
	    	 String[] alltables = ToShowTables.showTables("SELECT TABLE_NAME AS NAME FROM USER_TABLES");        // show tables and get tables are two different methods in ORA_DBTest     	 
	    	 final JComboBox showTables = new JComboBox(alltables); 
	    	 showTables.setSelectedIndex(0);
	    	 showTables.setBackground(new Color(255, 255, 255));
	    	 InsertRowMenuPanel.add(showTables, new XYConstraints(20, 50, 300, 25));
	    	 
	    	 JTextArea message = new JTextArea("For following options: \n   Delete Row \n   Delete Column  \n   Drop this Table");
	    	 message.setBackground(new Color(250, 250, 250));
	    	 message.setFont(new Font("Dialog", 1, 12));
	    	 message.setForeground(new Color(135, 29 ,26));
	    	 InsertRowMenuPanel.add(message, new XYConstraints(20, 80, 300, 80)); 
	    	 
	    	 JButton goButton = new JButton("Go"); 
	    	 InsertRowMenuPanel.add(goButton, new XYConstraints(20, 160, 50, 23));
	    	 
	    	 final ViewInsertDeleteHandler vidh  = new ViewInsertDeleteHandler(mainFrame);    
	    	 
	    	 goButton.addActionListener(new java.awt.event.ActionListener()
			    {
			        public void actionPerformed(ActionEvent e)
			        {    
			        	try { vidh.showSelectedTable((String)(showTables.getSelectedItem()));
			        	}
			        	catch(Exception ex){
			        		JOptionPane.showMessageDialog(null, ex.getMessage());
			        	}
			        	mainPanel.setVisible(false);
				    	mainPanel.setVisible(true);

			        }
			     } 
			    );
	    	 mainPanel.setVisible(false);
	    	 mainPanel.setVisible(true);
		 }
     }
     
     public void createTableButtonInMenuPanel(){
    	 JButton create_tableButton = new JButton(new ImageIcon(((new ImageIcon("Images/create_tab.png")).getImage()).getScaledInstance(78, 83, java.awt.Image.SCALE_SMOOTH)));
    	 create_tableButton.setBorderPainted(true);  
         create_tableButton.setFocusPainted(true);  
         create_tableButton.setContentAreaFilled(true); 
         create_tableButton.setToolTipText("Create Table");   
         CreateTableHandler cmh  = new CreateTableHandler(this);
         create_tableButton.addActionListener(cmh);    	 
         MenuPanel.add(create_tableButton, new XYConstraints(15, 10, 78, 83)); 
     }
      
   }// Class OracleDatabaseAdmin