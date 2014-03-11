
public class StateInformation extends AbstractStateInformation
{
	private EventList events;
	private EventList completed;
	private String lastBackup;
	
	StateInformation(EventList e, String s)
	{
		events = e;
		lastBackup = s;
	}
	
	
	@Override
	public void updateEventList(AbstractEventList newevents)
	{
		// TODO Auto-generated method stub
		synchronized(this)
		{
			events = (EventList) newevents;
			for(int i = 0; i < events.size(); i++)
			{
				if(events.get(i).)
			}
		}
	}

	@Override
	public EventList getEventList()
	{
		// TODO Auto-generated method stub
		return events;
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
