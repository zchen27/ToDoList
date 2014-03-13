import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class MenuBar extends AbstractMenuBar implements ActionListener,PopupMenuListener{
	MainScreen mainWindow;
	
	public void draw() {
		mainWindow.repaint();
		repaint();
		setVisible(true);
	}
	
	public void update(Point mousePoint, boolean rightMouseClicked,	boolean leftMouseClicked) {
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(closedItems)){
			AbstractClosedActionWindow completed=new ClosedActionWindow();
			JFrame closedWindow=new JFrame("Completed Action Items");
			closedWindow.setContentPane(completed);
			closedWindow.setVisible(true);
		} else if (event.getSource().equals(quit)){
			System.exit(0);
		} else if (event.getSource().equals(fileItem)){
			file.show(fileItem,fileItem.getX(),fileItem.getY());
		}
	}

	public MenuBar(MainScreen window){
		mainWindow=window;
		draw();
		fileItem=new JButton("File");
		fileItem.addActionListener(this);
		add(fileItem);
		file=new FileMenu(new MainScreen());
		add(file);
		fileItem.setComponentPopupMenu(file);
		closedItems=new JButton("Closed Action Items");
		closedItems.addActionListener(this);
		add(closedItems);
		quit=new JButton("Quit");
		quit.addActionListener(this);
		add(quit);
	}
	

	public void popupMenuCanceled(PopupMenuEvent e) {
		
	}
	
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		
	}

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		
	}
}
