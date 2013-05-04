package draynorChopper.conditions;

import org.powerbuddy.api.methods.Players;

import draynorChopper.util.Condition;

public class TillAnimating extends Condition{

	@Override
	public boolean isValid() {

		return Players.getLocal().getAnimation() != -1;
	}

}
