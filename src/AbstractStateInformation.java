
public abstract class AbstractStateInformation
{	
	public abstract void updateEventList(AbstractEventList newevents);
	
	public abstract AbstractEventList getEventList();
	
	public abstract void setBackUp(String newBackup);
	
	public abstract String lastBackup();
}
