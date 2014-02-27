import javax.swing.*;
import java.awt.*;

public abstract class AbstractEventPanel extends JPanel{
	public abstract void update(Point mousePoint, boolean rightMouseClicked, boolean leftMouseClicked);
	public abstract void draw();
	

}