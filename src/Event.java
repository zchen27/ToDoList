import java.util.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.*;


@XmlAccessorType(XmlAccessType.FIELD)
public class Event extends AbstractEvent
{
	@XmlElement(name = "date_eventual")
	private XMLGregorianCalendar dateE;
	
	@XmlElement(name = "date_current")
	private XMLGregorianCalendar dateC;
	
	@XmlElement(name = "date_urgent")
	private XMLGregorianCalendar dateU;
	
	@XmlElement(name = "date_completed")
	private XMLGregorianCalendar dateCompleted;
	
	private History history;
	private String comment;
	private String name;
	private int priority;
	
	
	public Event(String n)
	{
		name = n;
		comment = "";
		history = new History();
		priority = URGENT;
	}
	
	@Override
	public void setDates(GregorianCalendar dateEventual, GregorianCalendar dateCurrent, GregorianCalendar dateUrgent)
	{
		// TODO Auto-generated method stub
		try
		{
			dateE = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEventual);
			dateC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateCurrent);
			dateU = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateUrgent);
			Calendar ds = Calendar.getInstance();
			ds.setTimeInMillis(System.currentTimeMillis());
			if(ds.compareTo(dateE.toGregorianCalendar()) < 0)
			{
				priority = Event.INACTIVE;
			}
		} 
		catch (DatatypeConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		GregorianCalendar ds = new GregorianCalendar();
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
	public GregorianCalendar[] getDates()
	{
		// TODO Auto-generated method stub
		GregorianCalendar[] dates = new GregorianCalendar[3];
		dates[0] = dateE.toGregorianCalendar();
		dates[1] = dateC.toGregorianCalendar();
		dates[2] = dateU.toGregorianCalendar();
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
	
	@Override
	public String getComment()
	{
		return comment;
	}
	
	@Override
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
		GregorianCalendar ds = new GregorianCalendar();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, priority, newPriority));
		priority = newPriority;
	}
	
	@Override
	public void complete()
	{
		GregorianCalendar ds = (GregorianCalendar) Calendar.getInstance();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, priority, Event.CLOSED));
		try
		{
			dateCompleted = DatatypeFactory.newInstance().newXMLGregorianCalendar(ds);
		}
		catch (DatatypeConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		priority = Event.CLOSED;
	}
	
	public GregorianCalendar dateCompleted()
	{
		return dateCompleted.toGregorianCalendar();
	}

}
