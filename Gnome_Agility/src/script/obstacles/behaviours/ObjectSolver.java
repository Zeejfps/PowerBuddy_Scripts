package script.obstacles.behaviours;

import org.powerbuddy.api.wrapper.Area;
import org.powerbuddy.api.wrapper.GameObject;

import script.AgilityScript;
import script.util.Extras;
import script.util.conditions.TillComplete;

/*
 * This Solving behavior will solve Obstacles that are an
 * actual Object.
 */
public class ObjectSolver implements SolvingBehaviour{
	
	//Fields
	private String action;
	private Area startArea;
	private Area endArea;
	private int[] objIds;
	private int completeTime;
	private GameObject obj;
	
	//Constructor
	public ObjectSolver(Area startArea, Area endArea, int completeTime, int[] objIds, String action) {
		this.startArea = startArea;
		this.endArea = endArea;
		this.completeTime = completeTime;
		this.objIds = objIds;
		this.action = action;
	}
	
	@Override
	public void solve() {
		 obj = Extras.findClosestObj(objIds);
		
		if (Extras.interactWithObj(obj, action)) {
			Extras.moveMouseRandom();
			AgilityScript.sleep(new TillComplete(endArea), completeTime);
		} else {
			return;
		}
	}

	@Override
	public boolean needsSolving() {
		return Extras.playerInArea(startArea);
	}
	
	public GameObject getObj() {
		return obj;
	}

}
