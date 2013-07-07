import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JApplet;
import javax.swing.JFrame;

 /*FileMenuHandler Class--To Handle Events for 'Exit'.
  *Implements ActionListener to Handle Events
  *Open calls- Choose file from JFile Chooser, Read File and Save them in Unsorted and SortedLists 
  * and then Display them
  *SaveAs--Saves the List on a TextFile
  *Exit- Exit From Program
  *@param jframe
  *@author GurpreetSingh   
  */
public class FileMenuHandler implements ActionListener {
	   JFrame jframe;
	 
	   public StringTokenizer myTokens;
	   
	   /* FileMenuHandler-Constructor
 	    * @param jframe  
	    */
 	    public FileMenuHandler (JFrame jf) {
	      jframe = jf;
	    }
	    /* Method-- ActionPerformed 
		 * Calls specific methods according to specific events
		 * Event handles through ActionEvent
		 * @param menuName To Save the name of action event
		 * @param event  the event that user chooses
		 */
	    public void actionPerformed(ActionEvent event) {
	      String menuName = event.getActionCommand();
	      if (menuName.equals("Exit"))
	          System.exit(0);    
	      
	   } //actionPerformed
       
}//FileMenu Class ends here
 
