package draynorChopper.strategies;

import org.powerbuddy.api.methods.Walking;

import draynorChopper.conditions.TillInArea;
import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Strategy;

/*
 * Will execute when Script state equals to INV_FULL.
 * Need better walking methods!
 */
public class WalkToBank implements Strategy{

	
	
	@Override
	public boolean isValid() {
		return DraynorChopper.state.equals(State.BANKING);
	}

	@Override
	public void execute() {

		if(DraynorChopper.inArea(DraynorChopper.BANK_AREA)) {
			DraynorChopper.state = State.IN_BANK;
		} else {
			Walking.walkPath(DraynorChopper.PATH_TO_BANK);
			DraynorChopper.sleep(new TillInArea(DraynorChopper.BANK_AREA), 5000); //Need to replace this badly!
		}
	}
}
