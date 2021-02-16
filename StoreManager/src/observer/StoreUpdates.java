package observer;

import java.util.ArrayList;

import Model.Customer;

public class StoreUpdates {
	
    private static volatile StoreUpdates obj  = null;
    
   
    //singleton design pattren
    public static StoreUpdates getInstance()
    {
        if (obj == null)
        {
            // To make thread safe
            synchronized (StoreUpdates.class)
            {
                // check again as multiple threads
                // can reach above step
                if (obj==null)
                    obj = new StoreUpdates();
            }
        }
        return obj;
    }
    
	private ArrayList<Observer> customerList;
	private String state;
	private StringBuilder gettersList;

	private StoreUpdates() {
		customerList = new ArrayList<Observer>();
	}

	public String getState() {
		return state;
	}

	public void setState(String state, String[] observers) {
		this.state = state;
		observers = notifyAllObservers();
	}

	public void attach(Observer observer) {
		customerList.add(observer);
	}

	public String[] notifyAllObservers() {
		String[] observerlist = new String[customerList.size()];
		int counter =0;
		for (Observer observer : customerList) {
			observerlist[counter++] = observer.update();
		}
		return observerlist; // return list of the observers that got the message
		// we need to thing where to show that in the gui
	}

}
