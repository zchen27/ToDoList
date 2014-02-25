package abstract_classes;

public abstract class AbstractFileBackup
{
	public abstract void makeBackup(String location);
	
	public abstract AbstractEventList loadBackup(String location);
}
