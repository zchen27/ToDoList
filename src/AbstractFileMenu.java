import java.awt.print.*;

import javax.print.*;
import javax.swing.*;
public abstract class AbstractFileMenu extends JPopupMenu{
	AbstractStateInformation information;
	JMenuItem createBackup,restoreBackup,print;
	
	public abstract void createBackup();
	
	public abstract void restoreBackup();
	
	public abstract void print() throws PrinterException;
}
