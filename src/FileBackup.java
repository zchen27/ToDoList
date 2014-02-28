import java.io.*;
import java.lang.reflect.*;
import javax.xml.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.*;
import org.xml.sax.*;

public class FileBackup extends AbstractFileBackup
{

	/*
	 * Creates backup at the location indicated with the list
	 * @Pre: None
	 * @Post: Current EventList is created at the indicated directory with a name
	 */
	@Override
	public void makeBackup(String location)
	{
		// TODO FINISH THIS GOD DAMNED THING ALREADY
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		try
		{
			Document doc = db.parse(new File(location));
		}
		catch (SAXException | IOException e) {
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
