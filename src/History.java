import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({HistoryEntry.class})
public class History extends ArrayList <HistoryEntry> {
	
	@XmlElement(name = "history")
	public List<HistoryEntry> getHistory()
	{
		return this;
	}
}
