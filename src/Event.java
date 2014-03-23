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
	private MainScreen ms;
	
	public Event(String n, MainScreen mainScreen)
	{
		ms=mainScreen;
		name = n;
		comment = "";
		history = new History();
		priority = URGENT;
	}
	
	
	@Override
	public void setDates(Calendar dateEventual, Calendar dateCurrent, Calendar dateUrgent)
	{
		// TODO Auto-generated method stub
		dateE = dateEventual;
		dateC = dateCurrent;
		dateU = dateUrgent;
		Calendar ds = Calendar.getInstance();
		ds.setTimeInMillis(System.currentTimeMillis());
		if(ds.compareTo(dateE) < 0)
		{
			priority = Event.INACTIVE;
		}
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
		Calendar ds = Calendar.getInstance();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, newcomment));
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
	
	public void setName(String name)
	{
		this.name = name;
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
		Calendar ds = Calendar.getInstance();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, priority, newPriority));
		priority = newPriority;
	}
	
	public void complete()
	{
		Calendar ds = Calendar.getInstance();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, priority, Event.CLOSED));
		dateCompleted = ds;
		priority = Event.CLOSED;
		ms.si.getEventList().remove(this);
		ms.si.getClosedList().add(this);
		
	}
	
	public Calendar dateCompleted()
	{
		return dateCompleted;
	}

}
