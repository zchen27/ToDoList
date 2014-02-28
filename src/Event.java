import java.util.Calendar;


public class Event extends AbstractEvent
{
	private Calendar dateE;
	private Calendar dateC;
	private Calendar dateU;
	private AbstractHistory history;
	private String comment;
	private String name;
	private byte priority;
	
	
	public Event(String n)
	{
		name = n;
		comment = "";
		priority = URGENT;
	}
	
	@Override
	public void setDates(Calendar dateEventual, Calendar dateCurrent, Calendar dateUrgent)
	{
		// TODO Auto-generated method stub
		dateE = dateEventual;
		dateC = dateCurrent;
		dateU = dateUrgent;
	}

	@Override
	public void setComment(String newcomment)
	{
		// TODO Auto-generated method stub
		if(newcomment.length() == 0)
		{
			comment = "THIS COMMENT HAS BEEN DELETED";
		}
		comment = newcomment;
	}

	@Override
	public byte getPriority()
	{
		// TODO Auto-generated method stub
		return priority;
	}

	@Override
	public void setPriority(byte newPriority)
	{
		// TODO Auto-generated method stub
		priority = newPriority;
	}

	@Override
	public Calendar[] getDates()
	{
		// TODO Auto-generated method stub
		Calendar[] dates = new Calendar[3];
		dates[0] = dateE;
		dates[1] = dateC;
		dates[2] = dateU;
		return dates;
	}

	@Override
	public AbstractHistory getHistory()
	{
		// TODO Auto-generated method stub
		return history;
	}

	@Override
	public String getName()
	{
		// TODO Auto-generated method stub
		return name;
	}

}
