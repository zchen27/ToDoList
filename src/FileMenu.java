import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.print.*;
import javax.print.attribute.*;
public class FileMenu extends AbstractFileMenu implements ActionListener{
	private JFileChooser save,load;
	private PrinterJob printWindow;
	private FileNameExtensionFilter backupFilter,saveFilter;
	private FileBackup fileBackup;
	private JMenuItem printItem,saveItem,loadItem;
	public MainScreen mainWindow;
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(saveItem)){
			createBackup();
		} else if (event.getSource().equals(loadItem)){
			restoreBackup();
		} else if (event.getSource().equals(printItem)){
			try {
				print();
			} catch (PrinterException e) {
				System.out.print(e.getLocalizedMessage());
			}
		}
	}
	
	public void createBackup() {
		int returnVal=save.showSaveDialog(this);
		if (returnVal==JFileChooser.APPROVE_OPTION){
			File backup=save.getSelectedFile();
			fileBackup.makeBackup(backup.getAbsolutePath());
		}
	}

	
	public void restoreBackup() {
		int returnVal=load.showOpenDialog(this);
		if (returnVal==JFileChooser.APPROVE_OPTION){
			File backup=load.getSelectedFile();
			fileBackup.loadBackup(backup.getAbsolutePath());
		}
		mainWindow.getEventPanel().refresh();
	}

	
	public void print() throws PrinterException{
		PrinterJob job=PrinterJob.getPrinterJob();
		Print printer=new Print(mainWindow);
		printer.addPanel(mainWindow.getEventPanel());
		job.setPrintable(printer);
		if (job.printDialog()){
			try {
				job.print();
			} catch (PrinterException e){
				
			}
		}
	}
	
	public FileMenu(MainScreen window){
		mainWindow=window;
		fileBackup=new FileBackup(mainWindow);
		saveItem=new JMenuItem("Create Backup");
		saveItem.addActionListener(this);
		add(saveItem);
		loadItem=new JMenuItem("Restore Backup");
		loadItem.addActionListener(this);
		add(loadItem);
		printItem=new JMenuItem("Print");
		printItem.addActionListener(this);
		add(printItem);
		printWindow=PrinterJob.getPrinterJob();
		saveFilter=new FileNameExtensionFilter("Folders"," ");
		backupFilter=new FileNameExtensionFilter("XML Files","xml");
		save=new JFileChooser("Create Backup");
		save.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		save.setApproveButtonMnemonic(JFileChooser.SAVE_DIALOG);
		save.setApproveButtonText("Save");
		save.setFileFilter(saveFilter);
		load=new JFileChooser("Restore Backup");
		load.setFileSelectionMode(JFileChooser.FILES_ONLY);
		load.setApproveButtonMnemonic(JFileChooser.OPEN_DIALOG);
		load.setApproveButtonText("Load");
		load.setFileFilter(backupFilter);
	}
}