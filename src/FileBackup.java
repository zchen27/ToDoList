import java.io.*;
import java.lang.reflect.*;
import java.text.*;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class FileBackup extends AbstractFileBackup
{
	private MainScreen window;
	
	/*
	 * Creates backup at the location indicated with the list
	 * Pre: None
	 * Post: Current EventList is created at the indicated directory with a name
	 */
	
	FileBackup(MainScreen window)
	{
		this.window = window;
	}
	
	/*
	 * (non-Javadoc)
	 * @see AbstractFileBackup#makeBackup(java.lang.String)
	 */
	@Override
	public void makeBackup(String location)
	{
		File file = new File(location);
		
		try
		{
			JAXBContext jc = JAXBContext.newInstance(EventList.class);
			EventList events = MainScreen.si.getEventList();
			
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(events, file);
		}
		catch (JAXBException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * @see AbstractFileBackup#loadBackup(java.lang.String)
	 */
	@Override
	public void loadBackup(String location) 
	{
		File file = new File(location);
		
		try
		{
			JAXBContext jc = JAXBContext.newInstance(EventList.class);
			
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			EventList events = (EventList) unmarshaller.unmarshal(file);
			MainScreen.si.updateEventList(events);
		}
		catch (JAXBException e)
		{
			//TODO Auto-generated catch block
			
		}
		
	}

}
