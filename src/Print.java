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
				String[] history=new String[0];
				for (HistoryEntry e: printEvent.getHistory()){
					history=new String[history.length+1];
					String newEntry=e.getTime().get(Calendar.MONTH).toString();
				}
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
}