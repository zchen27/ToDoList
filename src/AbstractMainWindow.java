
import java.awt.event.*;
import javax.swing.*;


public abstract class AbstractMainWindow extends JFrame implements MouseListener, KeyListener{
	AbstractMainWindow(String title){
		super(title);
	}
	public abstract void run();
	public abstract void load();
	public abstract void load(String fileAddress);
	public abstract void update();
	public abstract void draw();
	public abstract void close();
	public abstract void addComponent(JComponent c);
	public abstract void removeComponent(JComponent c);

}
