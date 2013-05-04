package draynorChopper.strategies;

import org.powerbuddy.api.methods.Inventory;

import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Strategy;

public class CheckInv implements Strategy{

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return Inventory.isFull();
	}

	@Override
	public void execute() {
		DraynorChopper.state = State.BANKING;
	}

}
