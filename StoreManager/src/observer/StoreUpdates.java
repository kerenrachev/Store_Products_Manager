package observer;

import java.util.ArrayList;

import Controller.StoreController;
import Model.Customer;

public class StoreUpdates extends Thread{
	
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
			observerlist[counter++] = observer.replyToMSG();
		}
		return observerlist; 
	}
	public void showCustomerNames(String[] names, StoreController storeController)
	{
			MassageThread MSG = new MassageThread(names,storeController);
			MSG.start();
	}

	public class MassageThread extends Thread
	{
		String[] names;
		StoreController storeController;
		MassageThread (String[] names, StoreController storeController)
		{
			this.names=names;
			this.storeController= storeController;
		}
		@Override
		public void run() {
			for(int i= 0 ; i<customerList.size();i++)
			{
				System.out.println(names[i]);
				storeController.fireLabel(names[i]);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		public void interrupt() {
			super.interrupt();
		}
		
	}

}
