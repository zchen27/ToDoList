import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public abstract class AbstractEditActionItem extends JFrame {

	JFrame frame;
	private JTextField toEventualField, toCurrentField, toUrgentField;
	private JCheckBox toEventualBox, toCurrentBox, toUrgentBox;
	private JRadioButton urgent, current, eventual, inactive, completed;
	private JButton comment, history, print;
	AbstractStateInformation information;
	
	AbstractEditActionItem(int theEventIndex, StateInformation theInformation); // Constructor
	public abstract void priorityChange();
	public abstract void actionPerformed(ActionEvent e);
	public abstract void viewComment();
	public abstract void viewHistory();
	public abstract void print();

}
