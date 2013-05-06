package script.obstacles.behaviours;

import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.util.Random;
import org.powerbuddy.api.wrapper.Area;
import org.powerbuddy.api.wrapper.Tile;

public class RopeSolver extends TileSolver{

	public RopeSolver(Area startArea, Area endArea, int completeTime,
			Tile tile, String action) {
		super(startArea, endArea, completeTime, tile, action);
	}
	
	@Override
	public void solve() {
		Camera.setCameraRotation(230 + Random.nextInt(0, 15));
		super.solve();
	}

}
