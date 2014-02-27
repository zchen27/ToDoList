
import javax.swing.*;
public abstract class AbstractFileMenu extends JMenu{
	AbstractStateInformation information;
	JMenuItem createBackup,restoreBackup,print;
	
	public abstract void createBackup();
	
	public abstract void restoreBackup();
	
	public abstract void print();
}
