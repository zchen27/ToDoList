import java.util.Calendar;


public class HistoryEntry {
	public Calendar time;
	public String comment;
	public byte oldPriority;
	public byte newPriority;
	public StateInformation info;
	HistoryEntry(Calendar inTime, byte oldPriority, byte newPriority){
		time=inTime;
		this.oldPriority = oldPriority;
		this.newPriority = newPriority;
	}
	HistoryEntry(Calendar inTime, String initalComment){
		time=inTime;
		comment= initalComment;
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
