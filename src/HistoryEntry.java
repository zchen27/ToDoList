import java.util.Calendar;


public class HistoryEntry {
	public Calendar time;
	public String comment;
	public int oldPriority;
	public int newPriority;
	public StateInformation info;
	HistoryEntry(Calendar inTime, int priority, int closed){
		time=inTime;
		this.oldPriority = priority;
		this.newPriority = closed;
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
	public int getOldPriority(){
		return oldPriority;
	}
	public int getNewPriority(){
		return newPriority;
	}

}
