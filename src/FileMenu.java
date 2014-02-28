import java.awt.print.*;
import javax.swing.*;
import javax.print.*;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
public class FileMenu extends AbstractFileMenu{
	JFileChooser save,load;
	PrinterJob printWindow;
	
	public void createBackup() {
		save.setVisible(true);
	}

	
	public void restoreBackup() {
		load.setVisible(true);
		
	}

	
	public void print() throws PrintException{
		if (printWindow.printDialog()){
			try {
				PrintService printer=printWindow.getPrintService();
				DocPrintJob docJob=printer.createPrintJob();
				SimpleDoc paper=new SimpleDoc(information.getEventList(),new DocFlavor("text/plain","java.io.ImputStream"),new HashDocAttributeSet());
				docJob.print(paper,new HashPrintRequestAttributeSet());
			} catch (PrintException p){
				System.out.println(p.getLocalizedMessage());
			}
		}
		
	}

	public FileMenu(){
		printWindow=PrinterJob.getPrinterJob();
		save=new JFileChooser("Create Backup");
		save.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		save.setApproveButtonMnemonic(JFileChooser.SAVE_DIALOG);
		load=new JFileChooser("Restore Backup");
		load.setFileSelectionMode(JFileChooser.FILES_ONLY);
		load.setApproveButtonMnemonic(JFileChooser.OPEN_DIALOG);
	}
	
	public static void main(String[] args){
		FileMenu test=new FileMenu();
		test.createBackup();
	}
	
}
