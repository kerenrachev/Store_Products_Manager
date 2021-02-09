package interfaces;

import Controller.StoreController;

public interface Store_viewable {
	void registerListener(StoreController controller);
	void notifyToListeners(Command c);

}
