
public class StateInformation extends AbstractStateInformation
{
	private EventList events;
	private EventList completed;
	private String lastBackup;
	
	StateInformation()
	{
		events = new EventList();
		completed = new EventList();
		lastBackup = new String();
	}
	
	StateInformation(EventList e, String s)
	{
		events = e;
		lastBackup = s;
	}
	
	
	@Override
	public void updateEventList(EventList newevents)
	{
		// TODO Auto-generated method stub
		synchronized(this)
		{
			events = (EventList) newevents;
			for(int i = 0; i < events.size(); i++)
			{
				if(events.get(i).dateCompleted() != null)
				{
					completed.add(events.remove(i));
				}
			}
			
			completed = sort(completed);
		}
	}
	
	private EventList sort(EventList list)
	{
		if (list.size() < 1)
		{
			return list;
		}
		
		Event pivot = list.remove(list.size() / 2);
		EventList before = new EventList();
		EventList after = new EventList();
		
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).dateCompleted().compareTo(pivot.dateCompleted()) < 0)
			{              
				before.add(list.remove(i));
			}
			else if (list.get(i).dateCompleted().compareTo(pivot.dateCompleted()) > 0)
			{
				after.add(list.remove(i));
			}
		}
		
		EventList sorted = new EventList();
		sorted.addAll(0, sort(after));
		sorted.add(pivot);
		sorted.addAll(sorted.size() - 1, before);
		
		return sorted;
	}

	@Override
	public EventList getEventList()
	{
		// TODO Auto-generated method stub
		return events;
	}
	
	public EventList getClosedList()
	{
		return completed;
	}
	
	@Override
	public void setBackUp(String newBackup)
	{
		synchronized(this)
		{
			lastBackup = newBackup;
		}
	}
	
	public String lastBackup()
	{
		return lastBackup;
	}
}
