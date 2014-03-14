import java.text.*;
import java.util.*;


public class FileIOTester
{
	public static void main(String[] args)
	{
		MainScreen ms = new MainScreen();
		EventList e = new EventList();
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
		
		EventList list = new EventList();
		list.add(event0);
		list.add(event1);

		ms.si.updateEventList(list);
		
		FileBackup f = new FileBackup(ms);
		f.makeBackup("C:\\file.txt");
	}
}
