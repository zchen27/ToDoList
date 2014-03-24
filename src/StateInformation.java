
public class StateInformation extends AbstractStateInformation
{
	private EventList events;
	private EventList completed;
	private String lastBackup;
	
	StateInformation()
	{
		events = new EventList();
		completed = new EventList();
		lastBackup = new String();
	}
	
	StateInformation(EventList e, String s)
	{
		events = e;
		lastBackup = s;
	}
	
	
	@Override
	public void updateEventList(EventList newevents)
	{
		// TODO Auto-generated method stub
		synchronized(this)
		{
			EventList urgent=new EventList();
			EventList current=new EventList();
			EventList eventual=new EventList();
			EventList inactive=new EventList();
			for(int i=0;i<newevents.size();i++){
				if(newevents.get(i).getPriority()==Event.URGENT){
					urgent.add(newevents.get(i));
				}else if(newevents.get(i).getPriority()==Event.CURRENT){
						current.add(newevents.get(i));
				}else if(newevents.get(i).getPriority()==Event.EVENTUAL){
					eventual.add(newevents.get(i));
				}else if(newevents.get(i).getPriority()==Event.INACTIVE){
					inactive.add(newevents.get(i));
				}else if(newevents.get(i).getPriority()==Event.CLOSED){
					completed.add(newevents.get(i));
				}
			}
			urgent = sort(urgent);
			current = sort(current);
			eventual = sort(eventual);
			inactive = sort(inactive);
			completed = sort(completed);
			events=new EventList();
			events.addAll(urgent);
			events.addAll(current);
			events.addAll(eventual);
			events.addAll(inactive);
		}
	}
	
	private EventList sort(EventList list)
	{
		if (list.size() < 1)
		{
			return list;
		}
		
		Event pivot = list.remove(list.size() / 2);
		EventList before = new EventList();
		EventList after = new EventList();
		
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i).dateCompleted().compareTo(pivot.dateCompleted()) < 0)
			{              
				before.add(list.remove(i));
			}
			else if (list.get(i).dateCompleted().compareTo(pivot.dateCompleted()) > 0)
			{
				after.add(list.remove(i));
			}
		}
		
		EventList sorted = new EventList();
		sorted.addAll(0, sort(after));
		sorted.add(pivot);
		sorted.addAll(sorted.size() - 1, before);
		
		return sorted;
	}

	@Override
	public EventList getEventList()
	{
		// TODO Auto-generated method stub
		return events;
	}
	
	public EventList getClosedList()
	{
		return completed;
	}
	
	@Override
	public void setBackUp(String newBackup)
	{
		synchronized(this)
		{
			lastBackup = newBackup;
		}
	}
	
	public String lastBackup()
	{
		return lastBackup;
	}
}
