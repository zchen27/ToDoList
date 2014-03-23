import java.text.*;
import java.util.*;


public class FileIOTester
{
	public static void main(String[] args)
	{
		MainScreen ms = new MainScreen();
		Event event0 = new Event("event0");
		Event event1 = new Event("event1");
		
		DateFormat format = new SimpleDateFormat("MM/DD/YYYY G 'at' HH:mm:ss z");
		GregorianCalendar eventual0 = new GregorianCalendar();
		GregorianCalendar current0 = new GregorianCalendar();
		GregorianCalendar urgent0 = new GregorianCalendar();
		GregorianCalendar eventual1 = new GregorianCalendar();
		GregorianCalendar current1 = new GregorianCalendar();
		GregorianCalendar urgent1 = new GregorianCalendar();
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
		GregorianCalendar[] dates0 = {eventual0, current0, urgent0};
		GregorianCalendar[] dates1 = {eventual1, current1, urgent1};
		
		event0.setPriority(Event.EVENTUAL);
		event0.setComment("comment_0");
		event0.setDates(dates0[0], dates0[1], dates0[2]);
		
		event1.setPriority(Event.EVENTUAL);
		event1.setComment("commen_1");
		event1.setDates(dates1[0], dates1[1], dates1[2]);
		
		EventList list = new EventList();
		list.add(event0);
		list.add(event1);

		ms.si.updateEventList(list);
		
		FileBackup f = new FileBackup(ms);
		f.makeBackup("file.xml");
		System.out.println("Backup made");
		f.loadBackup("file.xml");
		System.out.println("Backup loaded");
		
		for (Event e: ms.si.getEventList())
		{
			System.out.println(e.getName());
			System.out.println(e.getPriority());
			System.out.println(e.getComment());
			History h = e.getHistory();
			for (HistoryEntry he: h)
			{
				System.out.println("\t" + he.getComment());
			}
		}
	}
}
