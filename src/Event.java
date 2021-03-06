import java.util.*;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Event extends AbstractEvent {
	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "priority")
	private int priority;

	@XmlElement(name = "comment")
	private String comment;

	@XmlElement(name = "date_eventual")
	private XMLGregorianCalendar dateE;

	@XmlElement(name = "date_current")
	private XMLGregorianCalendar dateC;

	@XmlElement(name = "date_urgent")
	private XMLGregorianCalendar dateU;

	@XmlElement(name = "date_completed")
	private XMLGregorianCalendar dateCompleted;

	@XmlElement(name = "history")
	private History history = new History();

	public Event() {

	}

	public Event(String n) {
		name = n;
		comment = "";
		history = new History();
		priority = URGENT;
	}

	@Override
	public void setDates(GregorianCalendar dateEventual,
			GregorianCalendar dateCurrent, GregorianCalendar dateUrgent) {
		// TODO Auto-generated method stub
		GregorianCalendar ds = new GregorianCalendar();
		ds.setTimeInMillis(System.currentTimeMillis());
		try {
			if (dateEventual != null) {
				dateE = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						dateEventual);
			} else {
				dateE = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						ds);
			}

			if (dateCurrent != null) {
				dateC = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						dateCurrent);
			} else {
				dateC = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						ds);
			}

			if (dateUrgent != null) {
				dateU = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						dateUrgent);
			} else {
				dateU = DatatypeFactory.newInstance().newXMLGregorianCalendar(
						ds);
			}

		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (dateE != null && ds.compareTo(dateE.toGregorianCalendar()) < 0) {
			priority = Event.INACTIVE;
		}

	}

	@Override
	public void setComment(String newcomment) {
		// TODO Auto-generated method stub
		if (newcomment.length() == 0) {
			comment = "THIS COMMENT HAS BEEN DELETED";
		}
		comment = newcomment;
		GregorianCalendar ds = new GregorianCalendar();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, newcomment));
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

	@Override
	public GregorianCalendar[] getDates() {
		// TODO Auto-generated method stub
		GregorianCalendar[] dates = new GregorianCalendar[3];
		dates[0] = dateE.toGregorianCalendar();
		dates[1] = dateC.toGregorianCalendar();
		dates[2] = dateU.toGregorianCalendar();
		return dates;
	}

	@Override
	public History getHistory() {
		// TODO Auto-generated method stub
		return history;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setName(String newName) {
		GregorianCalendar ds = new GregorianCalendar();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, name, newName));
		this.name = newName;

	}

	@Override
	public void setHistory(History history) {
		// TODO Auto-generated method stub
		this.history = history;
	}

	@Override
	public void setPriority(int newPriority) {
		GregorianCalendar ds = new GregorianCalendar();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, priority, newPriority));
		priority = newPriority;
		MainScreen.si.getEventList().remove(this);
		MainScreen.si.getClosedList().remove(this);
		MainScreen.si.getEventList().add(this);
	}

	@Override
	public void complete() {
		MainScreen.si.getEventList().remove(this);
		MainScreen.si.getClosedList().add(this);
		GregorianCalendar ds = (GregorianCalendar) Calendar.getInstance();
		ds.setTimeInMillis(System.currentTimeMillis());
		history.add(new HistoryEntry(ds, priority, Event.CLOSED));
		try {
			dateCompleted = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(ds);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		priority = Event.CLOSED;
	}

	public GregorianCalendar dateCompleted() {
		return dateCompleted.toGregorianCalendar();
		
	}

}
