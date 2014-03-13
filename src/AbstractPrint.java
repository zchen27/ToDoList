import java.awt.Graphics;
import java.awt.print.*;


public abstract class AbstractPrint implements Printable{
	
	public abstract int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException;

}
