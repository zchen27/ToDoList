import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
import java.util.*;
public class Print extends AbstractPrint {
	private PrinterJob printWindow;
	public MainScreen mainWindow;
	public EventPanel panel;
	public Event printEvent;
	
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (printWindow.printDialog()){
			Graphics2D printGraph=(Graphics2D)graphics;
			printGraph.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
			if (!(panel.equals(null))){
				mainWindow.printAll(graphics);
				return PAGE_EXISTS;
			} else if (!(printEvent.equals(null))){
				JFrame printFrame=new JFrame();
				JLabel name=new JLabel(printEvent.getName());
				String priority="Priority: ";
				if (printEvent.getPriority()==AbstractEvent.CLOSED){
					priority+="Completed";
				} else if (printEvent.getPriority()==AbstractEvent.INACTIVE){
					priority+="Inactive";
				} else if (printEvent.getPriority()==AbstractEvent.EVENTUAL){
					priority+="Eventual";
				} else if (printEvent.getPriority()==AbstractEvent.CURRENT){
					priority+="Current";
				} else if (printEvent.getPriority()==AbstractEvent.URGENT){
					priority+="Urgent";
				}
				JLabel priorityLabel=new JLabel(priority);
				JLabel[] history=new JLabel[0];
				for (HistoryEntry e: printEvent.getHistory()){
					String newEntry="";
					newEntry+=e.getTime().get(Calendar.MONTH)+"/"+e.getTime().get(Calendar.DATE)+"/"+e.getTime().get(Calendar.YEAR)+": ";
					if (e.getComment()==null){
						newEntry+="Priority Change - ";
						if (e.getOldPriority()==AbstractEvent.CLOSED){
							newEntry+="Completed to ";
						} else if (e.getOldPriority()==AbstractEvent.INACTIVE){
							newEntry+="Inactive to ";
						} else if (e.getOldPriority()==AbstractEvent.EVENTUAL){
							newEntry+="Eventual to ";
						} else if (e.getOldPriority()==AbstractEvent.CURRENT){
							newEntry+="Current to ";
						} else if (e.getOldPriority()==AbstractEvent.URGENT){
							newEntry+="Urgent to ";
						}
						if (e.getNewPriority()==AbstractEvent.CLOSED){
							newEntry+="Completed";
						} else if (e.getNewPriority()==AbstractEvent.INACTIVE){
							newEntry+="Inactive";
						} else if (e.getNewPriority()==AbstractEvent.EVENTUAL){
							newEntry+="Eventual";
						} else if (e.getNewPriority()==AbstractEvent.CURRENT){
							newEntry+="Current";
						} else if (e.getNewPriority()==AbstractEvent.URGENT){
							newEntry+="Urgent";
						}
					} else {
						newEntry="Comment Change - ";
						newEntry+="\""+e.getComment()+"\"";
					}
					newEntry+="\n";
					history=new JLabel[history.length+1];
					history[history.length-1]=new JLabel(newEntry);
				}
				String commentString="Comment: ";
				commentString+=printEvent.getComment();
				JLabel comment=new JLabel(commentString);
				JPanel contentPane=new JPanel();
				contentPane.add(name);
				contentPane.add(priorityLabel);
				contentPane.add(new JLabel("History:"));
				for (JLabel l: history){
					contentPane.add(l);
				}
				contentPane.add(comment);
				printFrame.setContentPane(contentPane);
				printFrame.printAll(graphics);
			}
		}	
		return NO_SUCH_PAGE;
	}
	
	public void addEvent(Event e){
		printEvent=e;
	}
	
	public void addPanel(EventPanel event){
		panel=event;
	}
	
	public Print(MainScreen window){
		mainWindow=window;
		printWindow=PrinterJob.getPrinterJob();
	}
	
	public static void main(String[] args){
		MainScreen window=new MainScreen();
		window.draw();
	}
}