import java.awt.*;
import javax.swing.*;

public abstract class AbstractMenuBar extends JMenuBar{
	AbstractStateInformation information;
	JMenuItem closedItems,quit,fileItem;
	AbstractFileMenu file;
	
	public abstract void update(Point mousePoint,boolean rightMouseClicked,boolean leftMouseClicked);
	
	public abstract void draw();
}
