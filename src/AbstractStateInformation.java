
public abstract class AbstractStateInformation
{	
	public abstract void updateEventList(EventList newevents);
	
	public abstract EventList getEventList();
	
	public abstract void setBackUp(String newBackup);
	
	public abstract String lastBackup();
}
