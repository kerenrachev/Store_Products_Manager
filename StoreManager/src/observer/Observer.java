package observer;

public abstract class Observer {
	
	protected StoreUpdates storeUpdates;

	public abstract String update();
}
