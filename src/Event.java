import java.util.Calendar;


public class Event extends AbstractEvent
{
	private Calendar dateE;
	private Calendar dateC;
	private Calendar dateU;
	private String comment;
	private String name;
	private byte priority = URGENT;
	
	
	public Event(String n)
	{
		name = n;
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
	public void setComment(String nextcomment)
	{
		// TODO Auto-generated method stub
		if(nextcomment.length() == 0)
		{
			comment = "THIS COMMENT HAS BEEN DELETED";
		}
		comment = nextcomment;
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
		return null;
	}

	@Override
	public AbstractHistory getHistory()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
