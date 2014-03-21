import java.util.*;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Event.class})
public class EventList extends AbstractEventList
{
	@XmlElement(name = "event")
	public List<Event> getEventList()
	{
		return this;
	}
}
