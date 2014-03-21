import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class ClosedActionWindow extends JFrame implements MouseListener{
	MainScreen mw;
	JPanel contentPane;
	JScrollPane scrollPane;
	JPanel subPanel;
	ClosedActionWindow(MainScreen mainScreen){
		mw=mainScreen;
		contentPane=new JPanel();
		scrollPane=new JScrollPane();
		subPanel=new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		subPanel.setLayout(new GridLayout(50, 1));
		setSize(400,600);
		setLocation(50,50);
		setResizable(false);
		addMouseListener(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		for(int i=mw.si.getClosedList().size()-1;i>=0;i--){
			System.out.println("test");
			subPanel.add(new SubEventPanel(mw.si.getClosedList().get(i)));
		}
		contentPane.add(scrollPane);
		scrollPane.setViewportView(subPanel);
		setVisible(true);
	
	}
	private class SubEventPanel extends JLabel {
		private Event event;
		SubEventPanel(Event e) {
			event=e;
			setText(e.getName());
			setHorizontalAlignment(SwingConstants.CENTER);
			setPreferredSize(getMinimumSize());
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args){
		new ClosedActionWindow(new MainScreen());
	}

}
