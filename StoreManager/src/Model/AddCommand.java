package Model;


import View.View;
import interfaces.Command;

public class AddCommand implements Command{

	private View theView;

	
	public AddCommand(View view) {
		theView = view;
	}


	@Override
	public void execute() {
	//	theView.getTheNeededText();
		
		//here we take the relevant information from the view
		// and pass the information to the controller that pass it to the model
		// with match view method
		
	}

}
