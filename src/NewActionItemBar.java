import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//TODO: change height of panel to be a certain distance above the bottom of the mainScreen to the bottom

public class NewActionItemBar extends JPanel implements KeyListener{
	private String eventName="";
	private JLabel newActionItemLbl;
	private JTextField newActionItemBox;
	NewActionItemBar(MainScreen mainScreen){   // will have (MainScreen mainScreen) as parameter
		super(); 
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		//this.setMaximumSize(new Dimension(frame.getWidth(),100)); // change to mainScreen.getWidth()
		
		newActionItemLbl = new JLabel(" New Action Item");
		newActionItemLbl.setAlignmentX(CENTER_ALIGNMENT);
		Dimension d1 = new Dimension(100,50);
		this.add(newActionItemLbl);

		Dimension d2 = new Dimension(mainScreen.getWidth()-newActionItemLbl.getWidth(),newActionItemLbl.getHeight()); 
		//Dimension d2 = new Dimension(frame.getWidth()-newActionItemLbl.getWidth(),newActionItemLbl.getHeight());  // testing version
		newActionItemBox = new JTextField("");
		newActionItemBox.setPreferredSize(d2);
		newActionItemBox.setBackground(Color.white);
		newActionItemBox.addKeyListener(this);
		this.add(newActionItemBox);

	}
	public void update(){
		eventName=newActionItemBox.getText();
		Event e = new Event(eventName);
		mainScreen.stateInformation.getEventList().addEvent(e);
		//System.out.println(eventName);
		newActionItemBox.setText("");
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if ((int)e.getKeyChar()==10){
			this.update();
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
