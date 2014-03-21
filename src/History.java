import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({HistoryEntry.class})
public class History extends ArrayList <HistoryEntry> {
	
	@XmlElement(name = "event")
	public List<HistoryEntry> getEventList()
	{
		return this;
	}
}
