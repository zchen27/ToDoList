package abstract_classes;

import java.awt.*;
import javax.swing.*;
public abstract class AbstractMenuBar extends JMenuBar{
	AbstractStateInformation information;
	JMenu closedItems,quit;
	AbstractFileMenu file;
	
	public abstract void update(Point mousePoint,boolean rightMouseClicked,boolean leftMouseClicked);
	
	public abstract void draw();
}
