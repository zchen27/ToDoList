

import java.util.Calendar;

public abstract class AbstractEvent
{
	public static final int CLOSED = 0;
	public static final int INACTIVE = 1;
	public static final int EVENTUAL = 2;
	public static final int CURRENT = 3;
	public static final int URGENT = 4;
	
	public abstract void setDates(Calendar dateEventual, Calendar dateCurrent, Calendar dateUrgent);
	
	public abstract void setComment(String comment);
	
	public abstract String getName();
	
	public abstract int getPriority();
	
	public abstract void setPriority(int newPriority);
	
	public abstract Calendar[] getDates();
	
	public abstract History getHistory();
	
	public abstract void setHistory(History history);
}
