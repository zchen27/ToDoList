import java.awt.*;
import java.awt.print.*;
import javax.print.*;
import javax.print.attribute.*;
public class Print extends AbstractPrint {
	private PrinterJob printWindow;
	public MainScreen mainWindow;
	public Object printThing;
	
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		try {
			if (printWindow.printDialog()){
				PrintService printer=printWindow.getPrintService();
				DocPrintJob docJob=printer.createPrintJob();
				DocFlavor flavor=new DocFlavor("text/plain","java.io.InputStream");
				printWindow.print();
				SimpleDoc paper=new SimpleDoc(printThing,flavor,new HashDocAttributeSet());
				docJob.print(paper,new HashPrintRequestAttributeSet());
			}	
		} catch(PrinterException | PrintException p){
			p.printStackTrace();
			return NO_SUCH_PAGE;
		}
		return PAGE_EXISTS;
	}
	
	public Print(MainScreen window,Object subject){
		mainWindow=window;
		printThing=subject;
		printWindow=PrinterJob.getPrinterJob();
	}
}
