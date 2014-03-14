import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
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

public class EventPanel extends AbstractEventPanel implements MouseListener,
		ActionListener {
	private MainScreen mainWindow;
	private ArrayList<String> events;
	private ArrayList<SubEventPanel> eventPanels;
	private JPanel panel;
	private Point mouse;
	private JPopupMenu popup;
	private SubEventPanel mover;

	// JScrollPane
	EventPanel(MainScreen mw) {
		mainWindow = mw;
		panel = new JPanel();
		eventPanels = new ArrayList<SubEventPanel>();
		panel.setLayout(new GridLayout(0, 1));
		addMouseListener(this);
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Delete Item");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		menuItem = new JMenuItem("Complete Item");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		menuItem = new JMenuItem("Edit Item");
		menuItem.addActionListener(this);
		popup.add(menuItem);
		MouseListener popupListener = new PopupListener();
		addMouseListener(popupListener);
		// for(int i=0;i<mw.si.getEventList().size();i++){
		// eventPanels.add(new SubEventPanel(mw.si.getEventList().get(i)));
		// }
		for (int i = 0; i < 200; i++) {
			// panel.add(new JPanel());
			eventPanels.add(new SubEventPanel("Event " + i));
			panel.add(eventPanels.get(i));
		}
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
		double scrollBar = getVerticalScrollBar().getValue();
		double eventPanelsHeight = eventPanels.get(0).getHeight();
		getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
		double maxScroll = getVerticalScrollBar().getValue();
		getVerticalScrollBar().setValue((int) scrollBar);
		double verticalShiftPercent = (scrollBar / maxScroll);
		int verticalShift = (int) (verticalShiftPercent * (eventPanels.size() - 48));
		Point actualPoint = new Point((int) mousePoint.getX(),
				(int) (mousePoint.getY() + verticalShift * eventPanelsHeight));
		for (int i = 0; i < eventPanels.size(); i++) {
			SubEventPanel test = eventPanels.get(i);
			SubEventPanel test2 =eventPanels.get(i);
			if(i!=eventPanels.size()-1){
				test2 = eventPanels.get(i + 1);
			}
			if (i == 0&& actualPoint.getY() < test.getY() + test.getHeight() / 2) {
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
		System.out.println("New Spot " + newSpot);
		ArrayList<SubEventPanel> newList = new ArrayList<SubEventPanel>();
		for (int i = 0; i <= newSpot; i++) {
			if (eventPanels.get(i) != mover) {
				newList.add(eventPanels.get(i));
			}
		}
		newList.add(mover);
		System.out.println(newList.get(0).getText());
		for (int i = newSpot+1; i < eventPanels.size(); i++) {
			if (eventPanels.get(i)!=mover){
				newList.add(eventPanels.get(i));
			}
		}
		eventPanels = newList;
		panel.removeAll();
		for (int i = 0; i < eventPanels.size(); i++) {
			panel.add(eventPanels.get(i));
		}
	}

	public void draw() {
		repaint();
	}

	private class SubEventPanel extends JLabel {
		SubEventPanel(AbstractEvent e) {
			setText(e.getName());
		}

		SubEventPanel(String s) {
			setText(s);
			setHorizontalAlignment(SwingConstants.CENTER);
			// setBorder(BorderFactory.createLineBorder(Color.black));
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
			double scrollBar = getVerticalScrollBar().getValue();
			double eventPanelsHeight = eventPanels.get(0).getHeight();
			getVerticalScrollBar()
					.setValue(getVerticalScrollBar().getMaximum());
			double maxScroll = getVerticalScrollBar().getValue();
			getVerticalScrollBar().setValue((int) scrollBar);
			double verticalShiftPercent = (scrollBar / maxScroll);
			int verticalShift = (int) (verticalShiftPercent * (eventPanels
					.size() - 48));
			Point actualPoint = new Point((int) mouse.getX(),
					(int) (mouse.getY() + verticalShift * eventPanelsHeight));
			mover = (SubEventPanel) panel.getComponentAt(eventPanels.get(0)
					.getX() + 5, (int) actualPoint.getY());
			// ((JLabel) panel.getComponentAt(actualPoint)).setText("Clicked");
			System.out.println(mover.getText());
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// mover.setText("Unclicked");
		movePanel(mover, arg0.getPoint());
		mainWindow.setVisible(false);
		mainWindow.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}