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
		try
		{
			eventual0.setTime(format.parse("09"));
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		event0.setPriority(Event.EVENTUAL);
		event0.setComment("comment_0");
		
		EventList list = new EventList();
		list.add(event0);
		list.add(event1);

		ms.si.updateEventList(list);
	}
}
