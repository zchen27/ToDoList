import java.awt.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
public class Print extends AbstractPrint {
	private PrinterJob printWindow;
	public MainScreen mainWindow;
	public EventPanel panel;
	
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (printWindow.printDialog()){
			Graphics2D printGraph=(Graphics2D)graphics;
			printGraph.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
			if (!(panel.equals(null))){
				panel.printAll(printGraph);
				return PAGE_EXISTS;
			} else {
				return NO_SUCH_PAGE;
			}
		}	
		return NO_SUCH_PAGE;
	}
	
	public void addPanel(EventPanel event){
		panel=event;
	}
	
	public Print(MainScreen window){
		mainWindow=window;
		printWindow=PrinterJob.getPrinterJob();
	}
}
