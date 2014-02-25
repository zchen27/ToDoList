package abstract_classes;

import java.util.Calendar;

import javax.swing.JFrame;

public abstract class AbstractHistoryEntry extends JFrame {
	public Calendar time;
	public String comment;
	public byte oldPriority;
	public byte newPriority;
	public StateInformation info;
	HistoryEntry(Calendar time, byte oldPriority, byte newPriority){
		
	}
	HistoryEntry(Calendar time, String initalComment){
		
	}
	public abstract String getComment();
	public abstract Calendar getTime();
	public abstract byte getOldPriority();
	public abstract byte getNewPriority();

}
