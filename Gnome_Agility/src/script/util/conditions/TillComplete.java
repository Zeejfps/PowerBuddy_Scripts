package script.util.conditions;

import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.util.Random;
import org.powerbuddy.api.wrapper.Area;

import script.util.Extras;

public class TillComplete implements Condition{

	private final Area area;
	
	public TillComplete(Area area) {
		this.area = area;
	}
	
	@Override
	public boolean isValid() {
		if (Extras.playerInArea(area) || !Extras.playerInMotion()) {
			return true;
		} else {
			switch(Random.nextInt(0, 10)){
				case 1: case 3:
					Camera.setCameraRotation(Camera.getAngle() + Random.nextInt(-40, 40));
					break;
					
				default:
					break;
			}
			return false;
		}
	}
}
