import java.util.Calendar;


public class HistoryEntry extends AbstractHistoryEntry {
	public Calendar time;
	public String comment;
	public byte oldPriority;
	public byte newPriority;
	public StateInformation info;
	
	HistoryEntry(Calendar inTime, byte oldPriority, byte newPriority){
		super(inTime, oldPriority, newPriority);
		time=inTime;
		this.oldPriority = oldPriority;
		this.newPriority = newPriority;
	}
	
	HistoryEntry(Calendar inTime, String initialComment){
		super(inTime, initialComment);
		time=inTime;
		comment= initialComment;
		
	}
	public String getComment(){
		return comment;
	}
	public Calendar getTime(){
		return time;
	}
	public byte getOldPriority(){
		return oldPriority;
	}
	public byte getNewPriority(){
		return newPriority;
	}

}
