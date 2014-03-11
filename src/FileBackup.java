import java.io.*;
import java.lang.reflect.*;
import java.text.*;
import java.util.*;

import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import org.xml.*;
import org.xml.sax.*;

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
		// TODO FINISH THIS GOD DAMNED THING ALREADY
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		EventList list = (EventList) window.si.getEventList();
		
		//<MAGIC>
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
				int p = e.getPriority();
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
				
				Attr comment = backup.createAttribute("comment");
				comment.setValue(c);
				event.appendChild(comment);
				
				Element history = backup.createElement("history");
				event.appendChild(history);
				
				for(HistoryEntry he: h)
				{
					Element entry = backup.createElement("entry");
					history.appendChild(entry);
					
					Attr time = backup.createAttribute("time");
					time.setValue(format.format(he.getTime()));
					entry.appendChild(time);
					
					Attr entryComment = backup.createAttribute("entry_comment");
					entryComment.setValue(he.getComment());
					entry.appendChild(entryComment);
				}
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(backup);
			StreamResult result = new StreamResult(new File(location));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//</MAGIC>

	}

	/*
	 * (non-Javadoc)
	 * @see AbstractFileBackup#loadBackup(java.lang.String)
	 */
	@Override
	public EventList loadBackup(String location) 
	{
		// TODO DO IT
		EventList list = new EventList();
		
		try
		{
			File file = new File(location);
			DateFormat format = new SimpleDateFormat("MMMM/DD/YYYY G 'at' HH:mm:ss z");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document backup = db.parse(file);
			
			backup.getDocumentElement().normalize();
			
			NodeList nlist = backup.getElementsByTagName("event");
			
			
			
			for(int i = 0; i < nlist.getLength(); i++)
			{
				Node node = nlist.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE)
				{
					Element element = (Element) node;
					
					String name = element.getAttribute("name");
					int priority = Integer.parseInt(element.getAttribute("priority"));
					Calendar date_eventual = Calendar.getInstance();
					Calendar date_current = Calendar.getInstance();
					Calendar date_urgent = Calendar.getInstance();
					date_eventual.setTime(format.parse(element.getAttribute("date_eventual")));
					date_current.setTime(format.parse(element.getAttribute("date_current")));
					date_urgent.setTime(format.parse(element.getAttribute("date_urgent")));
					String comment = element.getAttribute("comment");
					
					Element historyE = (Element) element.getFirstChild();
					NodeList elist = historyE.getElementsByTagName("entry");
					
					History history = new History();
					
					for(int j = 0; j < elist.getLength(); j++)
					{
						Node enode = elist.item(i);
						
						if(node.getNodeType() == Node.ELEMENT_NODE)
						{
							Element eElement = (Element) enode;
							Calendar time = Calendar.getInstance();
							time.setTime(format.parse(eElement.getAttribute("time")));
							String entry_comment = eElement.getAttribute(("entry_comment"));
							HistoryEntry he = new HistoryEntry(time, entry_comment);
							history.add(he);
						}
					}
					
					Event e = new Event(name);
					e.setPriority(priority);
					e.setComment(comment);
					e.setDates(date_eventual, date_current, date_urgent);
					e.setHistory(history);
					
					list.add(e);
				}
			}
		}
		catch (Exception e)
		{
			
		}
		
		return list;
	}

}
