package draynorChopper.strategies;

import org.powerbuddy.api.methods.Walking;

import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Strategy;

/*
 * Walks back to trees from bank NEED TO UPDATE WALKING METHODS!!!!
 */
public class WalkToTrees implements Strategy{
	
	@Override
	public boolean isValid() {
		return DraynorChopper.state.equals(State.WALKING_TO_TREES);
	}

	@Override
	public void execute() {
	
		if(DraynorChopper.inArea(DraynorChopper.TREE_AREA)) {
			DraynorChopper.state = State.NOT_CHOPPING;
		} else {
			Walking.walkPath(DraynorChopper.PATH_TO_TREES);
			DraynorChopper.sleep(1000, 2000); //Need to replace this badly!
		}
	}
}
