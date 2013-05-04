package draynorChopper.strategies;

import org.powerbuddy.api.methods.Inventory;
import org.powerbuddy.api.methods.Players;
import org.powerbuddy.api.wrapper.Player;

import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Strategy;

/*
 * This Strategy checks what state the Script should be in.
 * I need to add player.isMoving() so it doesn't spam click the tree when it gets there.
 */
public class ChangeState implements Strategy {

	@Override
	public boolean isValid() {
		return !DraynorChopper.state.equals(State.IN_BANK);
	}

	@Override
	public void execute() {
		Player player = Players.getLocal();
			
		if (Inventory.isFull()) {
			DraynorChopper.state = State.BANKING;
			
		}else if ((player.getAnimation() == -1)) { //Need to fix this!!!
			/*
			 * Need to add isMoving, maybe possibly a little wait here.
			 */
			DraynorChopper.state = State.NOT_CHOPPING;
			
		} else {
			DraynorChopper.state = State.CHOPPING;
		}
	}

}
