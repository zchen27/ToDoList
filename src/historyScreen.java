import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class historyScreen extends JFrame {
	public history history;
	JPanel CategoryPanel;
	private FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
	private JPanel infoPanel;

	historyScreen(history importedHistory){
		super("History");
		history = importedHistory;
		this.setVisible(true);
		this.setSize(600, 700);				
		this.setResizable(false);
		
		// Cateogory Panel contains labels of the categories
		CategoryPanel = new JPanel();		
		CategoryPanel.setLayout(flow);
		
		JLabel date = new JLabel ("                              Date              ");
		JLabel time = new JLabel ("Time                                                 ");
		JLabel change = new JLabel ("Change");
		
		date.setAlignmentX(date.LEFT_ALIGNMENT);
		time.setAlignmentX(time.LEFT_ALIGNMENT);
		change.setAlignmentX(change.LEFT_ALIGNMENT);
		
		CategoryPanel.add(date);
		CategoryPanel.add(time);
		CategoryPanel.add(change);
		CategoryPanel.setAlignmentX(CategoryPanel.LEFT_ALIGNMENT);

		infoPanel = new JPanel();
		BoxLayout boxLay = new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS);
		infoPanel.setLayout(boxLay);
		infoPanel.add(CategoryPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(infoPanel);
		scrollPane.setHorizontalScrollBarPolicy(scrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(scrollPane);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		this.repaint();
	}

	
	void draw (){		
		for (int counter=history.size()-1;counter>-1;counter--){
			JPanel panelToAdd = new JPanel();
			panelToAdd.setAlignmentX(panelToAdd.LEFT_ALIGNMENT);
			panelToAdd.setLayout(flow);
			int intMonth = history.get(counter).getTime().get(Calendar.MONTH)+1;

			//Months starts off as 0, as in January is represented by 0, which is why 1 is added to it.

			int intDate = history.get(counter).getTime().get(Calendar.DATE);
			int intYear = history.get(counter).getTime().get(Calendar.YEAR);
			int intHour = history.get(counter).getTime().get(Calendar.HOUR);
			int intMinute = history.get(counter).getTime().get(Calendar.MINUTE);

			String stringDate;
			// The if else statements are to add a zero 
			// in front of the date or month if the date/month is not double digits

			if (intMonth>9){
				stringDate = ""+intMonth;
			} else {
				stringDate = "0"+ intMonth;
			}
			stringDate =stringDate + "/";
			if (intDate>9){
				stringDate += ""+intDate;
			} else {
				stringDate += "0"+ intDate;
			}
			stringDate +="/";
			stringDate +=(intYear%1000);

			String stringTime;

			// Same loop for the same thing, just adding the zero in front of the hour instead
			if (intHour>9){
				stringTime = "" + intHour;
			} else {
				stringTime = "0" + intHour;
			}
			stringTime += ":";
			if (intMinute>9){
				stringTime += "" + intMinute;
			} else {
				stringTime += "0" + intMinute;
			}
			//Checks AM or PM and then displays
			if(history.get(0).getTime().AM_PM==history.get(0).getTime().AM){
				stringTime += " AM";
			} else {
				stringTime += " PM";
			}

			JLabel itemDate = new JLabel (stringDate);
			itemDate.setBorder(new EmptyBorder(10,80,10,10));
			JLabel itemTime = new JLabel (stringTime);
			itemTime.setBorder(new EmptyBorder(10,10,10,10));

			panelToAdd.add(itemDate);
			panelToAdd.add(itemTime);

			//Date and Time have been added, now determining priority change or comment
						
			if(history.get(counter).comment==null){
				//comment empty, it is a priority change, interprets bytes to create message

				String oldPriorityString = "";
				String newPriorityString = "";
				if(history.get(counter).oldPriority==0){
					oldPriorityString = "Completed";
				} else if (history.get(counter).oldPriority==1){
					oldPriorityString = "Inactive";
				} else if (history.get(counter).oldPriority==2){
					oldPriorityString = "Eventual";
				} else if (history.get(counter).oldPriority==3){
					oldPriorityString = "Current";
				} else if (history.get(counter).oldPriority==4){
					oldPriorityString = "Urgent";
				} else {
					JFrame error = new JFrame();
					error.add(new JLabel("Crashed, old priority integer does not match any priorities"));
					error.setVisible(true);
					error.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}

				if(history.get(counter).newPriority==0){
					newPriorityString = "Completed";
				} else if (history.get(counter).newPriority==1){
					newPriorityString = "Inactive";
				} else if (history.get(counter).newPriority==2){
					newPriorityString = "Eventual";
				} else if (history.get(counter).newPriority==3){
					newPriorityString = "Current";
				} else if (history.get(counter).newPriority==4){
					newPriorityString = "Urgent";
				} else {
					JFrame error = new JFrame();
					error.add(new JLabel("Crashed, old priority integer does not match any priorities"));
					error.setVisible(true);
					error.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
				JLabel priorityChangeLabel = new JLabel("Priority changed from " + oldPriorityString + 
				" to " + newPriorityString + ".");
				priorityChangeLabel.setBorder(new EmptyBorder(10,40,10,10));
				priorityChangeLabel.setAlignmentX(priorityChangeLabel.LEFT_ALIGNMENT);
				panelToAdd.add(priorityChangeLabel);
			} else {
				//comment change made, add comment
				
				panelToAdd.add(Box.createRigidArea(new Dimension (36,1)));
				JTextArea commentTextArea = new JTextArea(1,21);
				commentTextArea.setAlignmentX(commentTextArea.LEFT_ALIGNMENT);
				commentTextArea.setBorder(new LineBorder(Color.black, 1));
				commentTextArea.setText(history.get(counter).comment);
				commentTextArea.setLineWrap(true);
				commentTextArea.setWrapStyleWord(true);
				commentTextArea.setEditable(false);
				panelToAdd.add(commentTextArea);
			}			
			infoPanel.add(panelToAdd);
		}
		this.setVisible(false);
		this.setVisible(true);
	}
}