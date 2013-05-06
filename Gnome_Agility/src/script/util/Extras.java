package script.util;

import java.awt.Point;
import java.util.List;

import org.powerbuddy.api.input.Mouse;
import org.powerbuddy.api.methods.Calculations;
import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.methods.Game;
import org.powerbuddy.api.methods.GameObjects;
import org.powerbuddy.api.methods.Menu;
import org.powerbuddy.api.methods.Players;
import org.powerbuddy.api.util.Random;
import org.powerbuddy.api.wrapper.Area;
import org.powerbuddy.api.wrapper.GameObject;
import org.powerbuddy.api.wrapper.Player;
import org.powerbuddy.api.wrapper.Tile;

import script.AgilityScript;


/*
 * This class contains many Static methods I had to rewrite
 * because the API is either broken or does not include.
 */
public abstract class Extras {
	
	private Extras() {
		//Does not allow instantiation of this class.
	}
	
	/*
	 * Returns true if the Player is in area.
	 * Need to add plane check as well, once the API is fixed.
	 */
	public static boolean playerInArea(Area area) {
		Tile playerTile = Players.getLocal().getLocation();
		
		if (area.contains(playerTile.getX(), playerTile.getY())
				&& area.getPlane() == Game.getPlane()) {
			
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Returns True if player is in motion
	 */
	public static boolean playerInMotion() {
		Player myPlayer = Players.getLocal();
		Tile startTile = myPlayer.getLocation();
		
		AgilityScript.sleep(900);
		
		Tile endTile = myPlayer.getLocation();
		
		if (Calculations.distanceBetween(startTile, endTile) == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/*
	 * Better version of findClosest
	 */
	public static GameObject findClosestObj(int... ids) {
		final List<GameObject> allObjs = GameObjects.getLoaded();
		GameObject closestObj = null;
		int dist = 99999999;
		
		for (GameObject obj : allObjs) {
			for (int id : ids) {
				if (id == obj.getId()) {
					int objDist = Calculations.distanceTo(obj.getLocation());
					if (objDist < dist) {
						dist = objDist;
						closestObj = obj;
					}
					break;
				}
			}
		}
		return closestObj;
	}
	
	public static GameObject findClosestObjInArea(Area area, int... ids) {
		final List<GameObject> allObjs = GameObjects.getLoaded();
		GameObject closestObj = null;
		int dist = 99999999;
		
		for (GameObject obj : allObjs) {
			for (int id : ids) {
				if (id == obj.getId() && area.contains(obj.getLocation().getX(), obj.getLocation().getY())) {
					int objDist = Calculations.distanceTo(obj.getLocation());
					if (objDist < dist) {
						dist = objDist;
						closestObj = obj;
					}
					break;
				}
			}
		}
		return closestObj;
	}
	
	/*
	 * This methods moves the mouse to an X and Y cord with some Randomness 
	 * specified by the user.
	 */
	public static void moveMouse(int x, int y, int randX, int randY) {
		Mouse.move(x + Random.nextInt(-randX, randX), y + Random.nextInt(-randY, randY));
	}
	
	public static void moveMouse(Point p, int randX, int randY) {
		Extras.moveMouse((int)p.getX(), (int)p.getY(), randX, randY);
	}
	
	/*
	 * Interacts with an objects whose Id you don't know, but know the tile.
	 * Returns true when successfully interacted.
	 */
	public static boolean interactWithObj(Tile objTile, String action) {
		
		if (Menu.getMenuOptions().contains(action)) {
			Mouse.click(true);
			return true;
			
		} else if (objTile != null && Extras.isOnScreen(objTile)) {
			Extras.moveMouse(Extras.getScreenLocation(objTile), 10, 10);
			
			AgilityScript.sleep(Random.nextInt(50, 200));
			return false;
			
		} else if (objTile != null && !Extras.isOnScreen(objTile)) {
			Camera.turnTo(objTile);
			return false;
			
		} else {
			return false;
		}
	}
	
	/*
	 * Interacts with an objects and returns true if successful.
	 * Overloaded method.
	 */
	public static boolean interactWithObj(GameObject obj, String action) {
		
		if (Menu.getMenuOptions().contains(action)) {
			Mouse.click(true);
			return true;
			
		} else if (obj != null && Extras.isOnScreen(obj)) {
			Extras.moveMouse(Extras.getScreenLocation(obj.getLocation()), 10, 10);
			
			AgilityScript.sleep(Random.nextInt(50, 200));
			return false;
			
		} else if (obj != null && !Extras.isOnScreen(obj)) {
			Camera.turnTo(obj.getLocation());
			return false;
			
		} else {
			return false;
		}
	}
	
	/*
	 * Once again rewriting API, cus its broke xD
	 */
	public static Point getScreenLocation(Tile tile) {
		int x = (int)Calculations.tileToScreen(tile, tile.getPlane() * 234).getX();
		int y = (int)Calculations.tileToScreen(tile, tile.getPlane() * 234).getY();
		
		return new Point(x, y);
	}
	
	public static boolean isOnScreen(Tile tile) {
		return Calculations.getGameScreen().contains(Extras.getScreenLocation(tile));
	}
	
	public static boolean isOnScreen(GameObject obj) {
		return Extras.isOnScreen(obj.getLocation());
	}
	
	/*
	 * Moves the mouse randomly, from where it was.
	 * User after clicking on something.
	 */
	public static void moveMouseRandom() {
		
		switch(Random.nextInt(0, 5)) {
		
		case 1: case 3: case 0:
			Mouse.move((int)(Mouse.getLocation().getX() + Random.nextInt(-90, 90)), 
					(int)(Mouse.getLocation().getY() + Random.nextInt(-90, 90)));
			break;
		
		default:
			break;
		}
	}
}
