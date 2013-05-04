package draynorChopper.strategies;

import org.powerbuddy.api.input.Mouse;
import org.powerbuddy.api.methods.Calculations;
import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.methods.Npcs;
import org.powerbuddy.api.methods.Players;
import org.powerbuddy.api.util.Random;
import org.powerbuddy.api.wrapper.Npc;

import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Strategy;

public class AntiBan implements Strategy {

	private final int[] entIds = {1736, 1737, 1738};
	
	@Override
	public boolean isValid() {
		
		return DraynorChopper.state.equals(State.CHOPPING);
	}

	@Override
	public void execute() {
		Npc ent = Npcs.getNearest(entIds);
		
		//This needs a bit more work...
		if ((ent != null && Calculations.distanceTo(ent.getLocation()) < 2) || (Players.getLocal().getAnimation() == -1) ||  (!DraynorChopper.treeExists())) {
			DraynorChopper.state = State.NOT_CHOPPING;
		}
		
		switch(Random.nextInt(0, 230)) { //Increse the second number to lower the frequency of anti-ban
		
		case 1: case 3: case 130:
			Mouse.move((int)(Mouse.getLocation().getX() + Random.nextInt(-250, 250)), 
					(int)(Mouse.getLocation().getY() + Random.nextInt(-250, 250)));
			break;
			
		case 4: case 5: case 10:
			Camera.setCameraRotation(Camera.getAngle() + Random.nextInt(-90, 90));
			break;
			
		case 150:
			
			break;
			
		default:
			break;
		}
		
	}

}
