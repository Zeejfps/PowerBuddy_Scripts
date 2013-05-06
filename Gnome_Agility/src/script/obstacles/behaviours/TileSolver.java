package script.obstacles.behaviours;

import org.powerbuddy.api.wrapper.Area;
import org.powerbuddy.api.wrapper.Tile;

import script.AgilityScript;
import script.util.Extras;
import script.util.conditions.TillComplete;

public class TileSolver implements SolvingBehaviour{

	//Fields
		private String action;
		private Area startArea;
		private Area endArea;
		private Tile tile;
		private int completeTime;
		
		//Constructor
		public TileSolver(Area startArea, Area endArea, int completeTime, Tile tile, String action) {
			this.startArea = startArea;
			this.endArea = endArea;
			this.completeTime = completeTime;
			this.tile = tile;
			this.action = action;
		}
		
		@Override
		public void solve() {	
			if (Extras.interactWithObj(tile, action)) {
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
		
		public Tile getTile() {
			return tile;
		}

}
