import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.TextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class EventPanel extends JScrollPane implements MouseListener,ActionListener {
	private MainScreen mainWindow;
	private ArrayList<String> events;
	private ArrayList<SubEventPanel> eventPanels;
	private JPanel panel;
	private Point mouse;
	private JPopupMenu popup;
	private SubEventPanel mover;
	private JMenuItem deleteItem;
	private JMenuItem completeItem;
	private JMenuItem editItem;

	// JScrollPane
	EventPanel(MainScreen mw) {
		mainWindow = mw;
		panel = new JPanel();
		eventPanels = new ArrayList<SubEventPanel>();
		panel.setLayout(new GridLayout(50, 1));
		addMouseListener(this);
		popup = new JPopupMenu();
		deleteItem = new JMenuItem("Delete Item");
		deleteItem.addActionListener(this);
		popup.add(deleteItem);
		completeItem = new JMenuItem("Complete Item");
		completeItem.addActionListener(this);
		popup.add(completeItem);
		editItem = new JMenuItem("Edit Item");
		editItem.addActionListener(this);
		popup.add(editItem);
		MouseListener popupListener = new PopupListener();
		addMouseListener(popupListener);
		setPreferredSize(new Dimension(mainWindow.getHeight(),
				mainWindow.getWidth()));

		for (int i = 0; i < mw.si.getEventList().size(); i++) {
			eventPanels.add(new SubEventPanel(mw.si.getEventList().get(i)));
			panel.add(eventPanels.get(i));
		}
		// for (int i = 0; i < 200; i++) {
		// eventPanels.add(new SubEventPanel(new Event("Event "+i)));
		// panel.add(eventPanels.get(i));
		// }
		setViewportView(panel);

	}

	public void update(Point mousePoint, boolean rightMouseClicked,
			boolean leftMouseClicked) {
		SubEventPanel mover = null;
		if (leftMouseClicked && mover == null) {
			SubEventPanel test = eventDragged(mousePoint);
			if (test != null) {
				mover = test;
			}
		} else if (leftMouseClicked) {
			movePanel(mover, mousePoint);
		}
		if (!leftMouseClicked) {
			mover = null;
		}

	}

	private SubEventPanel eventDragged(Point mousePoint) {
		for (int i = 0; i < eventPanels.size(); i++) {
			SubEventPanel test = eventPanels.get(i);
			if (test.getY() <= mousePoint.y
					&& test.getY() + test.getHeight() >= mousePoint.y
					&& test.getX() <= mousePoint.x
					&& test.getX() + test.getWidth() >= mousePoint.x) {
				return test;
			}
		}
		return null;
	}

	private void movePanel(SubEventPanel mover, Point mousePoint) {
		Point actualPoint = actualPoint(mousePoint);
		for (int i = 0; i < eventPanels.size(); i++) {
			SubEventPanel test = eventPanels.get(i);
			SubEventPanel test2 = eventPanels.get(i);
			if (i != eventPanels.size() - 1) {
				test2 = eventPanels.get(i + 1);
			}
			if (i == 0
					&& actualPoint.getY() < test.getY() + test.getHeight() / 2) {
				sort(mover, -1);
			} else if (i == eventPanels.size() - 1
					&& actualPoint.getY() > test2.getY() + test2.getHeight()
							/ 2) {
				sort(mover, i);
			} else if (actualPoint.getY() > test.getY() + test.getHeight() / 2
					&& actualPoint.getY() < test2.getY() + test2.getHeight()
							/ 2) {
				sort(mover, i);
			}
		}
	}

	private void sort(SubEventPanel mover, int newSpot) {
		// System.out.println("New Spot " + newSpot);
		ArrayList<SubEventPanel> newList = new ArrayList<SubEventPanel>();
		for (int i = 0; i <= newSpot; i++) {
			if (eventPanels.get(i) != mover) {
				newList.add(eventPanels.get(i));
			}
		}
		newList.add(mover);
		// System.out.println(newList.get(0).getText());
		for (int i = newSpot + 1; i < eventPanels.size(); i++) {
			if (eventPanels.get(i) != mover) {
				newList.add(eventPanels.get(i));
			}
		}
		eventPanels = newList;
		// mainWindow.si.updateEventList()
		panel.removeAll();
		for (int i = 0; i < eventPanels.size(); i++) {
			panel.add(eventPanels.get(i));
		}
	}

	public void draw() {
		repaint();
	}

	private class SubEventPanel extends JLabel {
		private Event event;
		SubEventPanel(Event e) {
			event=e;
			setText(e.getName());
			setHorizontalAlignment(SwingConstants.CENTER);
			if (e.getPriority() == 0) {
			} else if (e.getPriority() == 1) {
				setFont(this.getFont().deriveFont(Font.ITALIC));
			} else if (e.getPriority() == 2) {
				setFont(this.getFont().deriveFont(Font.ITALIC));
			} else if (e.getPriority() == 3) {
			} else if (e.getPriority() == 4) {
				setFont(this.getFont().deriveFont(Font.BOLD));
			} else {
				JFrame error = new JFrame("ERROR");
				error.add(new JLabel(
						"Priority Error, input priority does not match any"));
				error.setSize(new Dimension(300, 300));
				error.setVisible(true);
				error.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		}
		private Event getEvent(){
			return event;
		}

		SubEventPanel(String s) {
			setText(s);
			setHorizontalAlignment(SwingConstants.CENTER);
		}

	}

	public void mouseClicked(MouseEvent arg0) {

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

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		Point mouse = arg0.getPoint();
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (eventPanels.size() > 0) {
				Point actualPoint = actualPoint(mouse);
				mover = (SubEventPanel) panel.getComponentAt(eventPanels.get(0)
						.getX() + 5, (int) actualPoint.getY());
			}
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		if (mover != null) {
			movePanel(mover, arg0.getPoint());
		}
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {
		Point actualPoint=actualPoint(mouse);
		Event e =((SubEventPanel) panel.getComponentAt(actualPoint)).getEvent();
		if (event.getSource().equals(deleteItem)){
			mainWindow.si.getEventList().remove(e);
			refresh();
		} else if (event.getSource().equals(completeItem)){
			e.setPriority(Event.CLOSED);
			mainWindow.si.getEventList().remove(e);
			mainWindow.si.getClosedList().add(e);
			refresh();
		} else if (event.getSource().equals(editItem)){
			new EditActionItem1(mainWindow,e);
		}
		
	}

	public void refresh() {
		eventPanels.removeAll(eventPanels);

		panel.removeAll();
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
	private Point actualPoint(Point mouse){
		double scrollBar = getVerticalScrollBar().getValue();
		double eventPanelsHeight = eventPanels.get(0).getHeight();
		getVerticalScrollBar().setValue(
				getVerticalScrollBar().getMaximum());
		double maxScroll = getVerticalScrollBar().getValue();
		getVerticalScrollBar().setValue((int) scrollBar);
		double verticalShiftPercent = (scrollBar / maxScroll);
		int verticalShift = (int) (verticalShiftPercent * (eventPanels
				.size() - 48));
		Point actualPoint = new Point(
				(int) mouse.getX(),
				(int) (mouse.getY() + verticalShift * eventPanelsHeight));
		return actualPoint;
	}

}