import javax.xml.bind.JAXBException;


public abstract class AbstractFileBackup
{
	public abstract void makeBackup(String location) throws JAXBException;
	
<<<<<<< HEAD
<<<<<<< HEAD
	public abstract void loadBackup(String location) throws JAXBException;
=======
	public abstract EventList loadBackup(String location);
>>>>>>> 08aa127a42a5418b22f8717247594838c88cfdda
=======
	public abstract EventList loadBackup(String location);
>>>>>>> 08aa127a42a5418b22f8717247594838c88cfdda
}