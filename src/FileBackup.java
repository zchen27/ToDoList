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
	
	/*
	 * Creates backup at the location indicated with the list
	 * Pre: None
	 * Post: Current EventList is created at the indicated directory with a name
	 */
	
	FileBackup()
	{
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see AbstractFileBackup#makeBackup(java.lang.String)
	 */
	@Override
	public void makeBackup(String location) throws JAXBException
	{
		// TODO FINISH THIS GOD DAMNED THING ALREADY
		
		EventList events = MainScreen.si.getEventList();
		File backup = new File(location);
		
		JAXBContext jc = JAXBContext.newInstance(EventList.class);
		Marshaller marshaller = jc.createMarshaller();
			
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(events, backup);

	}

	/*
	 * (non-Javadoc)
	 * @see AbstractFileBackup#loadBackup(java.lang.String)
	 */
	@Override
	public void loadBackup(String location) throws JAXBException
	{
		EventList events = new EventList();
		File backup = new File(location);
		
		JAXBContext jc = JAXBContext.newInstance(EventList.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
			
		unmarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		events = (EventList) unmarshaller.unmarshal(backup);
			
		MainScreen.si.updateEventList(events);

	}
}
