
public abstract class AbstractStateInformation
{
	private AbstractEventList eventlist;
	
	public abstract void updateEventList(AbstractEventList newevents);
	
	public abstract AbstractEventList getEventList();
}
