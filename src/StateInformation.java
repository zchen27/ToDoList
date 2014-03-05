
public class StateInformation extends AbstractStateInformation
{
	private EventList events;
	private String lastBackup;
	
	@Override
	public void updateEventList(AbstractEventList newevents)
	{
		// TODO Auto-generated method stub
		synchronized(this)
		{
			events = (EventList) newevents;
		}
	}

	@Override
	public AbstractEventList getEventList()
	{
		// TODO Auto-generated method stub
		return null;
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
