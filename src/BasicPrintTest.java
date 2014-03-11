import java.awt.print.PrinterException;
public class BasicPrintTest extends FileMenu{

	public void print() throws PrinterException{
		if (printWindow.printDialog()){
			try {
				printWindow.print();
			} catch (PrinterException p){
				p.printStackTrace();
			}
		}	
	}
	
	public BasicPrintTest(AbstractMainWindow window) {
		super(window);
	}

}
