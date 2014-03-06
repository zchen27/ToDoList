

import java.util.Calendar;

public abstract class AbstractEvent
{
	public static final byte CLOSED = 0;
	public static final byte INACTIVE = 1;
	public static final byte EVENTUAL = 2;
	public static final byte CURRENT = 3;
	public static final byte URGENT = 4;
	
	public abstract void setDates(Calendar dateEventual, Calendar dateCurrent, Calendar dateUrgent);
	
	public abstract void setComment(String comment);
	
	public abstract String getName();
	
	public abstract byte getPriority();
	
	public abstract void setPriority(byte newPriority);
	
	public abstract Calendar[] getDates();
	
	public abstract History getHistory();
}
