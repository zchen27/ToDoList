import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryEntry {
	
	private XMLGregorianCalendar time;
	private String comment;
	private int oldPriority;
	private int newPriority;
	private StateInformation info;
	
	HistoryEntry(GregorianCalendar inTime, int priority, int closed){
		try {
			this.time=DatatypeFactory.newInstance().newXMLGregorianCalendar(inTime);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.oldPriority = priority;
		this.newPriority = closed;
	}
	
	HistoryEntry(GregorianCalendar inTime, String initalComment){
		try {
			time=DatatypeFactory.newInstance().newXMLGregorianCalendar(inTime);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comment= initalComment;
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
