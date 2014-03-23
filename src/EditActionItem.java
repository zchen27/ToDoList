import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.Box.Filler;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditActionItem implements ActionListener, DocumentListener {

    JFrame frame;
    JPanel contentPane;
    Event myEvent;
    String eventName;
    int eventIndex;
    int numEventualCheck = 0;
    int numCurrentCheck = 0;
    int numUrgentCheck = 0;
    int currentPriority;
    GregorianCalendar dateToUrgent = null;
    GregorianCalendar dateToCurrent = null;
    GregorianCalendar dateToEventual = null;
    MainScreen mainWindow;
    private JTextField toEventualField, toCurrentField, toUrgentField, theNameField;
    private JCheckBox toEventualBox, toCurrentBox, toUrgentBox;
    private JRadioButton urgent, current, eventual, inactive, completed;
    private JButton comment, history, print, save;
    private JLabel monthToUrgent, monthToCurrent, monthToEventual;
    static StateInformation information;
    String inToEventualField = "";
    String inToCurrentField = "";
    String inToUrgentField = "";

    public EditActionItem (MainScreen theMainWindow, Event theEvent) {
        frame = new JFrame("Edit Item");
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
        frame.setAlwaysOnTop(true);

        mainWindow = theMainWindow;
        information = mainWindow.si;
        myEvent = theEvent;
        eventName = theEvent.getName();
        currentPriority = theEvent.getPriority();
        itemName();
        addJRadioButtons();
        addJCheckBoxes();
        addJButtons();
        addJTextFields();
        
        toUrgentField.setText(inToUrgentField);
        toEventualField.setText(inToEventualField);
        toCurrentField.setText(inToCurrentField);
        
        save = new JButton ("SAVE CHANGES");
        save.addActionListener(this);
        save.setBounds(130, 425, 200, 30);
        contentPane.add(save);

    }
    
    private void itemName () {
        // show name of the item 
        JLabel theName = new JLabel ("Item Name: ");
        theNameField = new JTextField (eventName);
        theNameField.getDocument().addDocumentListener(this);
        contentPane.add(theName);
        theName.setBounds(50, 30, 100, 20);
        contentPane.add(theNameField);
        theNameField.setBounds(150, 30, 200, 20);
        mainWindow.getEventPanel().refresh();
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
        contentPane.add(urgent);
        contentPane.add(current);
        contentPane.add(eventual);
        contentPane.add(inactive);
        contentPane.add(completed);
        urgent.setActionCommand("Urgent");
        // checks the radio button for the existing priority
        if (currentPriority == 0) {
            completed.doClick();
        } else if (currentPriority == 1) {
            inactive.doClick();
        } else if (currentPriority == 2) {
            eventual.doClick();
        } else if (currentPriority == 3) {
            current.doClick();
        } else if (currentPriority == 4) {
            urgent.doClick();
        }

        ButtonGroup groupOne = new ButtonGroup();
        groupOne.add(urgent);
        groupOne.add(current);
        groupOne.add(eventual);
        groupOne.add(inactive);
        groupOne.add(completed);
        urgent.addActionListener(this);
        current.addActionListener(this);
        eventual.addActionListener(this);
        inactive.addActionListener(this);
        completed.addActionListener(this);
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
        toEventualField = new JTextField(inToEventualField);
        toCurrentField = new JTextField(inToCurrentField);
        toUrgentField = new JTextField(inToUrgentField);
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
            myEvent.complete();
        }
    }

    public void actionPerformed(ActionEvent e){
        if ("SAVE CHANGES".equals(e.getActionCommand())) {
            frame.dispose();
            mainWindow.getEventPanel().refresh();
        }
        if ("COMMENT".equals(e.getActionCommand())) {
            viewComment();
        } else if ("HISTORY".equals(e.getActionCommand())) {
            viewHistory();
        } else if ("PRINT".equals(e.getActionCommand())) {
            try {
                print();
            } catch (PrinterException e1) {
                e1.printStackTrace();
            }
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
        
        DateFormat format = new SimpleDateFormat("mm/dd/yyyy");
        String toUrgentFieldText = toUrgentField.getText();
        if(toUrgentFieldText.length() != 0)
        {
        	
        	try {
				dateToUrgent.setTime(format.parse(toUrgentFieldText));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            inToUrgentField = toUrgentFieldText;
            toUrgentField.setText(inToUrgentField);
        }

        String toCurrentFieldText = toCurrentField.getText();
        if(toUrgentFieldText.length() != 0)
        {
        	try {
				dateToCurrent.setTime(format.parse(toCurrentFieldText));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            inToCurrentField = toCurrentFieldText;
            toCurrentField.setText(inToCurrentField);
        }
        
        String toEventualFieldText = toEventualField.getText();
        if(toUrgentFieldText.length() != 0)
        {
        	try {
				dateToEventual.setTime(format.parse(toEventualFieldText));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            inToEventualField = toEventualFieldText;
            toEventualField.setText(inToEventualField);
        }
        
        myEvent.setDates(dateToEventual, dateToCurrent, dateToUrgent);
        
        // don't let dates be earlier than another, no downgrade a priority...
        
    }                      
    public void viewComment(){
        CommentWindow eventComment = new CommentWindow (mainWindow, eventName, eventIndex);
    }
    public void viewHistory(){
        HistoryScreen eventHistory = new HistoryScreen (myEvent.getHistory());
        eventHistory.draw();
    }

    public void print() throws PrinterException {
        PrinterJob printEvent = PrinterJob.getPrinterJob();
        Print event = new Print (mainWindow);
        event.addEvent(myEvent);
        if (printEvent.printDialog()) {
            try {
                printEvent.print();
            } catch (PrinterException exceptionOne) {
            
            }
        }
        
    }

    public void changedUpdate(DocumentEvent arg0) {
        eventName = theNameField.getText();
        myEvent.setName(eventName);    
    }

    public void insertUpdate(DocumentEvent arg0) {
        eventName = theNameField.getText();
        myEvent.setName(eventName);
    }

    public void removeUpdate(DocumentEvent arg0) {
        eventName = theNameField.getText();
        myEvent.setName(eventName);
    }

}