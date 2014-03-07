import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EventPanel extends AbstractEventPanel implements AdjustmentListener {
	private JScrollBar scrollBar;
	private	MainScreen mainWindow;
	private EL eventList;
	private ArrayList<String> events;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private ArrayList<SubEventPanel> eventPanels;
	private JPanel panel;
	//JScrollPane
	EventPanel(MainScreen mw) {
		mainWindow=mw;
//		textArea=new JTextArea();
//		textArea.setEditable(false);
//		scrollBar = new JScrollBar();
//		scrollBar.setSize(10, 200);
//		scrollBar.setLocation(590, 0);
//		eventList = new EL();
//		eventList.setSize(800, 590);
//		add(eventList);
//		add(scrollBar);
//		events = new ArrayList<String>();
//		for (int i = 0; i < 500; i++) {
//			events.add("Event "+i);
//		}
//		String eventText=new String("");
//		for(int i=0;i<events.size();i++){
//			eventText+="\t\t\t\t\t"+events.get(i)/**.getName()**/+"\n";
//		}
//		textArea.setText(eventText);
//		scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		add(scrollPane);
//		scrollPane.setPreferredSize(new Dimension(mw.getWidth(),mw.getHeight()-40));
		//scrollBar.addAdjustmentListener(this);
		panel=new JPanel();
		eventPanels=new ArrayList<SubEventPanel>();
		panel.setLayout(new GridLayout(0,1));
//		for(int i=0;i<mw.si.getEventList().size();i++){
//			eventPanels.add(new SubEventPanel(mw.si.getEventList().get(i)));
//		}
		for(int i=0;i<100;i++){
			eventPanels.add(new SubEventPanel("Event "+i));
			panel.add(eventPanels.get(i));
		}
		setViewportView(panel);
		
		
	}

	public void update(Point mousePoint, boolean rightMouseClicked,
			boolean leftMouseClicked) {
		SubEventPanel mover=null;
		if(leftMouseClicked&&mover==null){
			SubEventPanel test=eventDragged(mousePoint);
			if(test!=null){
				mover=test;
			}
		}else if(leftMouseClicked){
			
		}
		if(!leftMouseClicked){
			mover=null;
		}
		
	}
	private SubEventPanel eventDragged(Point mousePoint){
		for(int i=0;i<eventPanels.size();i++){
			SubEventPanel test=eventPanels.get(i);
			if(test.getY()<=mousePoint.y&&
				test.getY()+test.getHeight()>=mousePoint.y&&
				test.getX()<=mousePoint.x&&
				test.getX()+test.getWidth()>=mousePoint.x){
				return test;
			}
		}
		return null;
	}
	private void movePanel(SubEventPanel mover,Point mousePoint){
		
	}
	public void draw() {
		repaint();
	}

	private class EL extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < events.size(); i++) {
				//g.drawString(events.get(i), getWidth() / 2, getY() + (i-scrollBar.getValue())*15);
			}
		}
	}
	public class RightClickMenu extends JPopupMenu{
		RightClickMenu(Point p){
			super();
			setPopupSize(50,50);
			setLocation(p.x,p.y);
			setVisible(true);
		}
	}
	private class SubEventPanel extends JLabel{
		SubEventPanel(AbstractEvent e){
			setText(e.getName());
		}
		SubEventPanel(String s){
			setText(s);
		}
	}
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		eventList.repaint();
	}

}
