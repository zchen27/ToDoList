
import java.util.GregorianCalendar;

public abstract class AbstractEvent
{
	public static final int CLOSED = 0;
	public static final int INACTIVE = 1;
	public static final int EVENTUAL = 2;
	public static final int CURRENT = 3;
	public static final int URGENT = 4;
	
	public abstract void setDates(GregorianCalendar dateEventual, GregorianCalendar dateCurrent, GregorianCalendar dateUrgent);
	
	public abstract void setComment(String newcomment);
	
	public abstract int getPriority();
	
	public abstract GregorianCalendar[] getDates();
	
	public abstract History getHistory();
	
	public abstract String getName();
	
	public abstract String getComment();
	
	public abstract void setName(String name);
	
	public abstract void setHistory(History history);
	
	public abstract void setPriority(int newPriority);
	
	public abstract void complete();
	
	
}
