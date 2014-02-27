
public abstract class AbstractStateInformation
{
	private AbstractEventList eventlist;
	
	public abstract void updateEventList();
	
	public abstract AbstractEventList getEventList();
}
