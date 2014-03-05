import java.io.*;
import java.lang.reflect.*;
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
			//Element event_list = new Element("event_list");
			int i = 0;
			for(Event e: list)
			{
				
				String name = e.getName();
				Calendar[] dates = e.getDates();
				String comment = e.getComment();
				byte Priority = e.getPriority();
				
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
