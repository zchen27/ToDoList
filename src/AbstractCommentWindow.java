
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public abstract class AbstractCommentWindow extends JFrame{
	public AbstractStateInformation StateInfo;
	private String comment;
	private JButton commit,delete;
	private JTextField txtField;
	AbstractCommentWindow(String title,int eventIndex){}
	public abstract void actionPerformed(ActionEvent e);
	public abstract void commit(String comment);
	public abstract void delete();
}
