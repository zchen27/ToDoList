
import java.util.Calendar;

import javax.swing.JFrame;

public abstract class AbstractHistoryEntry extends JFrame {
	public Calendar time;
	public String comment;
	public byte oldPriority;
	public byte newPriority;
	public AbstractStateInformation info;
	AbstractHistoryEntry(Calendar time, byte oldPriority, byte newPriority){
		
	}
	AbstractHistoryEntry(Calendar time, String initalComment){
		
	}
	public abstract String getComment();
	public abstract Calendar getTime();
	public abstract byte getOldPriority();
	public abstract byte getNewPriority();

}
