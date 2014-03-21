import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import EventPanel.SubEventPanel;
public class ClosedActionWindow extends JFrame implements MouseListener, ActionListener{
	MainScreen mw;
	JPanel contentPane;
	JScrollPane scrollPane;
	JPanel subPanel;
	JPopupMenu popup;
	JMenuItem deleteItem;
	JMenuItem completeItem;
	JMenuItem editItem;
	Point mouse;
	ClosedActionWindow(MainScreen mainScreen){
		mw=mainScreen;
		contentPane=new JPanel();
		scrollPane=new JScrollPane();
		subPanel=new JPanel();
		popup = new JPopupMenu();
		deleteItem = new JMenuItem("Delete Item");
		deleteItem.addActionListener(this);
		popup.add(deleteItem);
		completeItem = new JMenuItem("Complete Item");
		completeItem.addActionListener(this);
		completeItem.setBackground(Color.gray);
		popup.add(completeItem);
		editItem = new JMenuItem("Edit Item");
		editItem.addActionListener(this);
		popup.add(editItem);
		MouseListener popupListener = new PopupListener();
		scrollPane.addMouseListener(popupListener);
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
	
	private class PopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(e.getComponent(), e.getX(), e.getY());
				mouse=e.getPoint();
			}
		}
	}
	public void actionPerformed(ActionEvent event) {
		Point actualPoint=actualPoint(mouse);
		Event e =((SubEventPanel) subPanel.getComponentAt(actualPoint)).getEvent();
		if (event.getSource().equals(deleteItem)){
			mw.si.getClosedList().remove(e);
			refresh();
		} else if (event.getSource().equals(completeItem)){
		} else if (event.getSource().equals(editItem)){
			new EditActionItem1(mw,e);
		}
		
	}
	private Point actualPoint(Point mouse){
		double scrollBar = scrollPane.getVerticalScrollBar().getValue();
		double eventPanelsHeight = subPanel.getComponent(0).getHeight();
		scrollPane.getVerticalScrollBar().setValue(
				scrollPane.getVerticalScrollBar().getMaximum());
		double maxScroll = scrollPane.getVerticalScrollBar().getValue();
		scrollPane.getVerticalScrollBar().setValue((int) scrollBar);
		double verticalShiftPercent = (scrollBar / maxScroll);
		int verticalShift = (int) (verticalShiftPercent * (subPanel.getComponentCount() - 48));
		Point actualPoint = new Point(
				(int) mouse.getX(),
				(int) (mouse.getY() + verticalShift * eventPanelsHeight));
		return actualPoint;
	}
	
	public void refresh() {
		subPanel.removeAll();
		for (int i = 0; i < mainWindow.si.getEventList().size(); i++) {
			Event e = mainWindow.si.getEventList().get(i);
			if (e.getPriority() == e.INACTIVE) {
				Calendar[] c = e.getDates();
				DateFormat date = new SimpleDateFormat(
						"MM/DD/YYYY G 'at' HH:mm:ss z");
				String s = date.format(c[0]);
				eventPanels.add(new SubEventPanel(s));
			}
			eventPanels.add(new SubEventPanel(e));
		}
		for (int i = 0; i < eventPanels.size(); i++) {
			panel.add(eventPanels.get(i));
		}
//		if(eventPanels.size()<21){
//			panel.add(Box.createRigidArea(new Dimension(0,800-eventPanels.size()*40)));
//		}
		
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
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
