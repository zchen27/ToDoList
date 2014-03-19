import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.lang.Object;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.Box.Filler;

public class EditActionItem1 implements ActionListener {

	JFrame frame;
	JPanel contentPane;
	Event myEvent;
	String eventName;
	int eventIndex;
	int numEventualCheck = 0;
	int numCurrentCheck = 0;
	int numUrgentCheck = 0;
	Calendar toUrgentDate;
	Calendar toCurrentDate;
	Calendar toEventualDate;
	MainScreen mainWindow;
	private JTextField toEventualField, toCurrentField, toUrgentField;
	private JCheckBox toEventualBox, toCurrentBox, toUrgentBox;
	private JRadioButton urgent, current, eventual, inactive, completed;
	private JButton comment, history, print;
	private JLabel monthToUrgent, monthToCurrent, monthToEventual;
	static StateInformation information;
	
	
	public EditActionItem1 (MainScreen theMainWindow, int theEventIndex) {
		frame = new JFrame("Edit Action Item");
		frame.setLocationRelativeTo(null);
		frame.setBounds(0,0,0,0);
		Dimension minSize = new Dimension(500, 500);
		frame.setMinimumSize(minSize);
		contentPane = new JPanel();
	    contentPane.setLayout(null);
		contentPane.setBorder((BorderFactory.createEmptyBorder(10,10,20,10)));
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		eventIndex = theEventIndex;
		mainWindow = theMainWindow;
		information = mainWindow.si;
		myEvent = information.getEventList().get(theEventIndex);
		eventName = myEvent.getName();
	
		itemName();
		addJRadioButtons();
		addJCheckBoxes();
		addJButtons();
		addJTextFields();

	}
	
	private void itemName () {
		// show name of the item 
		JLabel theName = new JLabel ("Item Name: ");
		theName.setBounds(50, 30, 100, 30);
		contentPane.add(theName);
	
	}
	
	private void addJRadioButtons() {
		// addRadioButtons to change the priority of the item
		urgent = new JRadioButton ("Urgent");
		urgent.setBounds(45, 90, 100, 30);
		current = new JRadioButton ("Current");
		current.setBounds(45, 120, 100, 30);
		eventual = new JRadioButton ("Eventual");
		eventual.setBounds(45, 150, 100, 30);
		inactive = new JRadioButton ("Inactive");
		inactive.setBounds(45, 180, 100, 30);
		completed = new JRadioButton ("Completed");
		completed.setBounds(45, 210, 100, 30);
		ButtonGroup groupOne = new ButtonGroup();
		groupOne.add(urgent);
		groupOne.add(current);
		groupOne.add(eventual);
		groupOne.add(inactive);
		groupOne.add(completed);
		contentPane.add(urgent);
		contentPane.add(current);
		contentPane.add(eventual);
		contentPane.add(inactive);
		contentPane.add(completed);
	}
	
	private void addJButtons() {
		// add Comment, History, and Print JButtons		
		comment = new JButton ("COMMENT");
		history = new JButton ("HISTORY");
		print = new JButton ("PRINT");
		contentPane.add(comment);
		comment.setBounds(180, 260, 100, 30);
		contentPane.add(history);
		history.setBounds(180, 310, 100, 30);
		contentPane.add(print);
		print.setBounds(180, 360, 100, 30);
		
		comment.addActionListener(this);
		history.addActionListener(this);
		print.addActionListener(this);
	}
	
	private void addJCheckBoxes() {
		// add JCheckBoxes that allow user to set date of future priority changes
		toEventualBox = new JCheckBox("To Eventual: ");
		toEventualBox.setBounds(150, 180, 100, 30);
		toCurrentBox = new JCheckBox ("To Current: ");
		toCurrentBox.setBounds(150, 150, 100, 30);
		toUrgentBox = new JCheckBox ("To Urgent: ");
		toUrgentBox.setBounds(150, 120, 100, 30);
		contentPane.add(toEventualBox);
		contentPane.add(toCurrentBox);
		contentPane.add(toUrgentBox);
		
		toEventualBox.addActionListener(this);
		toCurrentBox.addActionListener(this);
		toUrgentBox.addActionListener(this);
	}
	
	private void addJTextFields() {
		// add JTextFields that allow user to set date of future priority changes
	
		toEventualField = new JTextField();
		toCurrentField = new JTextField();
		toUrgentField = new JTextField();
		contentPane.add(toEventualField);
		toEventualField.setBounds(250, 185, 100, 20);
		contentPane.add(toCurrentField);
		toCurrentField.setBounds(250, 155, 100, 20);
		contentPane.add(toUrgentField);
		toUrgentField.setBounds(250, 125, 100, 20);
		
		toEventualField.setEnabled(false);
		toCurrentField.setEnabled(false);
		toUrgentField.setEnabled(false);
		
		toEventualField.addActionListener(this);
		toCurrentField.addActionListener(this);
		toUrgentField.addActionListener(this);
		
		monthToUrgent = new JLabel ("(MM/DD/YY)");
		monthToCurrent = new JLabel ("(MM/DD/YY)");
		monthToEventual = new JLabel ("(MM/DD/YY)");

		contentPane.add(monthToUrgent);
		monthToUrgent.setBounds(360, 185, 100, 20);
		contentPane.add(monthToCurrent);
		monthToCurrent.setBounds(360, 155, 100, 20);
		contentPane.add(monthToEventual);
		monthToEventual.setBounds(360, 125, 100, 20);
		
	}
	
	
	public void changePriority (String toPriority) {
		if (toPriority.equals("Urgent")) {
			myEvent.setPriority(4);
		} else if (toPriority.equals("Current")) {
			myEvent.setPriority(3);
		} else if (toPriority.equals("Eventual")) {
			myEvent.setPriority(2);
		} else if (toPriority.equals("Inactive")) {
			myEvent.setPriority(1);
		} else if (toPriority.equals("Completed")) {
			myEvent.setPriority(0);
		}
	}

		
	public void actionPerformed(ActionEvent e){
		if ("COMMENT".equals(e.getActionCommand())) {
	    } else if ("HISTORY".equals(e.getActionCommand())) {
	    	viewHistory();
	    } else if ("PRINT".equals(e.getActionCommand())) {
	    	print();
	    }
		
		if ("To Eventual: ".equals(e.getActionCommand())) {
	    	numEventualCheck++;
	    } else if ("To Current: ".equals(e.getActionCommand())) {
	    	numCurrentCheck++;
	    } else if ("To Urgent: ".equals(e.getActionCommand())) {
	    	numUrgentCheck++;
	    } 
		
		if (numEventualCheck%2 == 0) {
			toEventualField.setEnabled(false);
		} else {
			toEventualField.setEnabled(true);
		}
		
		if (numCurrentCheck%2 == 0) {
			toCurrentField.setEnabled(false);
		} else {
			toCurrentField.setEnabled(true);
		}
		
		if (numUrgentCheck%2 == 0) {
			toUrgentField.setEnabled(false);
		} else {
			toUrgentField.setEnabled(true);
		}
		
		if ("Urgent".equals(e.getActionCommand())) {
			changePriority ("Urgent");
		} else if ("Eventual".equals(e.getActionCommand())) {
			changePriority ("Eventual");
		} else if ("Current".equals(e.getActionCommand())) {
			changePriority ("Current");
		} else if ("Inactive".equals(e.getActionCommand())) {
			changePriority ("Inactive");
		} else if ("Completed".equals(e.getActionCommand())) {
			changePriority ("Completed");
		}
			
	}                      
	
	public void viewComment(){
		CommentWindow eventComment = new CommentWindow (mainWindow, eventName, eventIndex);
	}
	
	public void viewHistory(){
		HistoryScreen eventHistory = new HistoryScreen (myEvent.getHistory());
	}

	public void print(){
		Print printEvent = new Print (mainWindow, myEvent);
		printEvent.print(getGraphics(), new PageFormat(), 0);
	}


}

