
public abstract class AbstractFileBackup
{
	public abstract void makeBackup(String location);
	
	public abstract EventList loadBackup(String location);
}