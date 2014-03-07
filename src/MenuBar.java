import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class MenuBar extends AbstractMenuBar implements ActionListener,PopupMenuListener{
	
	public void draw() {
		
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

	public MenuBar(){
		fileItem=new JButton("File");
		fileItem.addActionListener(this);
		add(fileItem);
		file=new FileMenu(new MainWindow("To Do"));
		add(file);
		closedItems=new JButton("Closed Action Items");
		closedItems.addActionListener(this);
		add(closedItems);
		quit=new JButton("Quit");
		quit.addActionListener(this);
		add(quit);
	}
	
	public static void main(String[] args){
		JFrame frame=new JFrame();
		frame.setMinimumSize(new Dimension(400,100));
		frame.add(new MenuBar());
		frame.setVisible(true);
	}

	public void popupMenuCanceled(PopupMenuEvent e) {
		
	}
	
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		
	}

	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		
	}
}
