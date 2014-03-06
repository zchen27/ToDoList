import java.io.*;
import java.lang.reflect.*;
import java.text.*;
import java.util.*;

import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;

import org.w3c.dom.*;
import org.xml.*;
import org.xml.sax.*;

public class FileBackup extends AbstractFileBackup
{
	private StateInformation information;
	
	/*
	 * Creates backup at the location indicated with the list
	 * Pre: None
	 * Post: Current EventList is created at the indicated directory with a name
	 */
	
	FileBackup(AbstractMainWindow window)
	{
		information = window.si;
	}
	
	@Override
	public void makeBackup(String location)
	{
		// TODO FINISH THIS GOD DAMNED THING ALREADY
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		EventList list = (EventList) information.getEventList();
		
		//Nested Try-Catch. Ask not why.
		try 
		{
			db = dbf.newDocumentBuilder();
			Document backup = db.parse(new File(location));
			
			Element event_list = backup.createElement("event_list");
			backup.appendChild(event_list);
			
			for(Event e: list)
			{
				String n = e.getName();
				Calendar[] d = e.getDates();
				String c = e.getComment();
				History h = e.getHistory();
				byte p = e.getPriority();
				DateFormat format = new SimpleDateFormat("MMMM/DD/YYYY G 'at' HH:mm:ss z");
				
				Element event = backup.createElement("event");
				event_list.appendChild(event);
				
				Attr name = backup.createAttribute("name");
				name.setValue(n);
				event.appendChild(name);
				
				Attr priority = backup.createAttribute("priority");
				priority.setValue("" + p);
				event.appendChild(priority);
				
				Attr to_eventual = backup.createAttribute("to_eventual");
				to_eventual.setValue(format.format(d[0]));
				event.appendChild(to_eventual);
				
				Attr to_current = backup.createAttribute("to_current");
				to_current.setValue(format.format(d[1]));
				event.appendChild(to_current);
				
				Attr to_urgent = backup.createAttribute("to_urgent");
				to_urgent.setValue(format.format(d[2]));
				event.appendChild(to_urgent);
				
				Element history = backup.createElement("history");
			}
			
			
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}

	}

	@Override
	public AbstractEventList loadBackup(String location) 
	{
		// TODO DO IT
		return null;
	}
	
	

}
