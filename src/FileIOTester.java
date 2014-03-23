import java.text.*;
import java.util.*;

import javax.xml.bind.JAXBException;


public class FileIOTester
{
	public static void main(String[] args)
	{
		MainScreen ms = new MainScreen();
		
		Event event0 = new Event("event0");
		Event event1 = new Event("event1");
		
		DateFormat format = new SimpleDateFormat("MM/DD/YYYY G 'at' HH:mm:ss z");
		Calendar eventual0 = Calendar.getInstance();
		Calendar current0 = Calendar.getInstance();
		Calendar urgent0 = Calendar.getInstance();
		Calendar eventual1 = Calendar.getInstance();
		Calendar current1 = Calendar.getInstance();
		Calendar urgent1 = Calendar.getInstance();
		try
		{
			eventual0.setTime(format.parse("09/02/1996 AD at 16:16:04 EST"));
			current0.setTime(format.parse("10/01/2000 AD at 23:43:38 EST"));
			urgent0.setTime(format.parse("12/31/2012 AD at 23:59:59 EST"));
			eventual1.setTime(format.parse("02/01/1997 AD at 00:05:23 EST"));
			current1.setTime(format.parse("04/03/2016 AD at 08:05:36 EST"));
			urgent1.setTime(format.parse("05/01/2016 AD at 00:00:00 EST"));
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Calendar[] dates0 = {eventual0, current0, urgent0};
		Calendar[] dates1 = {eventual1, current1, urgent1};
		
		event0.setPriority(Event.EVENTUAL);
		event0.setComment("comment_0");
		event0.setDates(dates0[0], dates0[1], dates0[2]);
		
		event1.setPriority(Event.EVENTUAL);
		event1.setComment("commen_1");
		event1.setDates(dates1[0], dates1[1], dates1[2]);
		
		event1.complete();
		
		EventList list = new EventList();
		list.add(event0);
		list.add(event1);

		MainScreen.si.updateEventList(list);
		
		FileBackup f = new FileBackup();
		f.makeBackup("file.xml");
		
<<<<<<< HEAD
<<<<<<< HEAD
		for (Event e: MainScreen.si.getEventList())
=======
		EventList list2 = f.loadBackup("file.xml");
		System.out.println(list2.size());
		for(Event e: list2)
>>>>>>> 08aa127a42a5418b22f8717247594838c88cfdda
=======
		EventList list2 = f.loadBackup("file.xml");
		System.out.println(list2.size());
		for(Event e: list2)
>>>>>>> 08aa127a42a5418b22f8717247594838c88cfdda
		{
			System.out.println("");
			System.out.println("NAME: " + e.getName());
			System.out.println("PRIORITY: " + e.getPriority());
			System.out.println("COMMENT: " + e.getComment());
			System.out.println("DATE_E: " + format.format(e.getDates()[0].getTime()));
			System.out.println("DATE_C: " + format.format(e.getDates()[1].getTime()));
			System.out.println("DATE_U: " + format.format(e.getDates()[2].getTime()));
		}
	}
}
