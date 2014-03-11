import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.print.*;
import javax.print.attribute.*;
public class FileMenu extends AbstractFileMenu implements ActionListener{
	JFileChooser save,load;
	PrinterJob printWindow;
	FileNameExtensionFilter backupFilter,saveFilter;
	AbstractFileBackup fileBackup;
	JMenuItem printItem,saveItem,loadItem;
	AbstractMainWindow window;
	
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
			AbstractEventList list=fileBackup.loadBackup(backup.getAbsolutePath());
			//Find a way to load the EventList onto the main menu
		}
	}

	
	public void print() throws PrinterException{
		if (printWindow.printDialog()){
			try {
				/*PrintService printer=printWindow.getPrintService();
				DocPrintJob docJob=printer.createPrintJob();
				DocFlavor.STRING flavor=new DocFlavor.STRING("RFC6015");
				SimpleDoc paper=new SimpleDoc("",flavor,new HashDocAttributeSet());
				docJob.print(paper,new HashPrintRequestAttributeSet());*/
				printWindow.print(new HashPrintRequestAttributeSet());
			} catch (PrinterException p){
				System.out.println(p.getLocalizedMessage());
			}
		}
		
	}
	
	public FileMenu(AbstractMainWindow window){
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
		backupFilter=new FileNameExtensionFilter("XML Files",".xml");
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
