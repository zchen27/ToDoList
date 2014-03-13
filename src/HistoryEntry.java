import java.util.Calendar;


public class historyEntry {
	public Calendar time;
	public String comment;
	public byte oldPriority;
	public byte newPriority;
	public StateInformation info;
	historyEntry(Calendar inTime, byte oldPriority, byte newPriority){
		time=inTime;
		this.oldPriority = oldPriority;
		this.newPriority = newPriority;
	}
	historyEntry(Calendar inTime, String initalComment){
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
