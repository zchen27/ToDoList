import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class MenuBar extends AbstractMenuBar implements ActionListener,PopupMenuListener{
	public MainScreen mainWindow;
	
	public void draw() {
		mainWindow.repaint();
		repaint();
		setVisible(true);
	}
	
	public void update(Point mousePoint, boolean rightMouseClicked,	boolean leftMouseClicked) {
		
	}
	
	public void createClosedActionWindow(){
		ClosedActionWindow completed=new ClosedActionWindow(mainWindow);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(closedItems)){
			createClosedActionWindow();
		} else if (event.getSource().equals(quit)){
			System.exit(0);
		} else if (event.getSource().equals(fileItem)){
			file.show(fileItem,fileItem.getX(),fileItem.getY());
		}
	}

	public MenuBar(MainScreen window){
		mainWindow=window;
		fileItem=new JMenuItem("File");
		fileItem.addActionListener(this);
		fileItem.setMinimumSize(new Dimension(100,30));
		add(fileItem);
		file=new FileMenu(window);
		file.addPopupMenuListener(this);
		add(file);
		fileItem.setComponentPopupMenu(file);
		closedItems=new JMenuItem("Closed Action Items");
		closedItems.addActionListener(this);
		closedItems.setMinimumSize(new Dimension(100,30));
		add(closedItems);
		quit=new JMenuItem("Quit");
		quit.addActionListener(this);
		quit.setMinimumSize(new Dimension(100,30));
		add(quit);
		draw();
	}
	

	public void popupMenuCanceled(PopupMenuEvent e) {
		
	}
	
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		
	}

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		
	}
}
