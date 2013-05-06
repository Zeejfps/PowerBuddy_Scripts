package script.obstacles.behaviours;

import org.powerbuddy.api.methods.Walking;
import org.powerbuddy.api.wrapper.Area;
import org.powerbuddy.api.wrapper.Tile;

import script.AgilityScript;
import script.util.Extras;
import script.util.conditions.TillComplete;

public class WalkingSolver implements SolvingBehaviour{

	private Tile[] path;
	private Area startArea;
	private Area walkToArea;
	
	public WalkingSolver(Tile[] path, Area startArea, Area walkToArea) {
		this.path = path;
		this.startArea = startArea;
		this.walkToArea = walkToArea;
	}
	
	@Override
	public boolean needsSolving() {
		return Extras.playerInArea(startArea);
	}

	@Override
	public void solve() {
		Walking.walkPath(path);
		AgilityScript.sleep(new TillComplete(walkToArea), 5000);
	}

}
