import java.util.Calendar;


public class Event extends AbstractEvent
{
	private Calendar dateE;
	private Calendar dateC;
	private Calendar dateU;
	private Calendar dateCompleted = null;
	private History history;
	private String comment;
	private String name;
	private int priority;
	
	
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
	public int getPriority()
	{
		// TODO Auto-generated method stub
		return priority;
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
	public History getHistory()
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
	
	
	public String getComment()
	{
		return comment;
	}

	@Override
	public void setHistory(History history)
	{
		// TODO Auto-generated method stub
		this.history = history;
	}

	@Override
	public void setPriority(int newPriority)
	{
		priority = newPriority;
		Calendar ct = Calendar.getInstance();
		ct.setTimeInMillis(System.currentTimeMillis());
		
	}
	
	public void complete()
	{
		Calendar ct = Calendar.getInstance();
		ct.setTimeInMillis(System.currentTimeMillis());
		dateCompleted = ct;
	}
	
	public Calendar dateCompleted()
	{
		return dateCompleted;
	}

}
