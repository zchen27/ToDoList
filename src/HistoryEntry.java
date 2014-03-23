import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryEntry {
	
	@XmlElement(name = "time")
	private XMLGregorianCalendar time;
	
	@XmlElement(name = "comment")
	private String comment;
	
	private int oldPriority;
	
	private int newPriority;
	
	HistoryEntry()
	{
		
	}
	
	HistoryEntry(GregorianCalendar inTime, int priority, int closed){
		try {
			this.time=DatatypeFactory.newInstance().newXMLGregorianCalendar(inTime);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.oldPriority = priority;
		this.newPriority = closed;
		
		comment = "Priority changed from " + priorityConvert(priority) + " to " + priorityConvert(closed);
	}
	
	private String priorityConvert(int priority)
	{
		switch(priority)
		{
			case 0:
				return "CLOSED";
			case 1:
				return "INACTIVE";
			case 2:
				return "EVENTUAL";
			case 3:
				return "CURRENT";
			case 4:
				return "URGENT";
			default:
				return null;
		}
	}
	
	HistoryEntry(GregorianCalendar inTime, String initalComment){
		try {
			time=DatatypeFactory.newInstance().newXMLGregorianCalendar(inTime);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comment= "Comment set to " + initalComment;
	}
	
	public String getComment(){
		return comment;
	}
	public GregorianCalendar getTime(){
		return time.toGregorianCalendar();
	}
	public int getOldPriority(){
		return oldPriority;
	}
	public int getNewPriority(){
		return newPriority;
	}

}
