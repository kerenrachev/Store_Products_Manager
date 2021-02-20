package observer;

public abstract class Observer {
	
	protected StoreUpdates storeUpdates;
	protected abstract String replyToMSG();
}
