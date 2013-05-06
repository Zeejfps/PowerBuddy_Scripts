package script.courses;

import org.powerbuddy.api.wrapper.Tile;

import script.obstacles.Obstacle;
import script.obstacles.behaviours.*;
import script.util.enums.GnomeAreas;

/*
 *  This class contains all of the obstacles used for 
 *  Gnome Agility Course.
 */
public class GnomeCourse extends ObstacleCourse{

	private Obstacle log;
	private Obstacle netOne;
	private Obstacle treeOne;
	private Obstacle rope;
	private Obstacle treeTwo;
	private Obstacle netTwo;
	private Obstacle pipe;
	private Obstacle walk;
	
	@Override
	public void createObstacles() {
		defineObstacles();
		
		getObstacles().add(log);
		getObstacles().add(netOne);
		getObstacles().add(treeOne);
		getObstacles().add(rope);
		getObstacles().add(treeTwo);
		getObstacles().add(netTwo);
		getObstacles().add(pipe);
		getObstacles().add(walk);
	}
	
	/*
	 * THE ORDER OF OBSTACLES MADDERS!!!
	 */
	private void defineObstacles() {
		log = new Obstacle("Log", new TileSolver(
				GnomeAreas.BEFORE_LOG.getArea(),
				GnomeAreas.AFTER_LOG.getArea(),
				7000,
				new Tile(2474, 3435, 0),
				"Walk-across"));

		
		netOne = new Obstacle("Net One", new ObjectSolver(
				GnomeAreas.AFTER_LOG.getArea(),
				GnomeAreas.TREE_ONE.getArea(),
				5000,
				new int[] {2285},
				"Climb-over"));
		
		treeOne = new Obstacle("Tree One", new TileSolver(
				GnomeAreas.TREE_ONE.getArea(),
				GnomeAreas.ROPE.getArea(),
				5000,
				new Tile(2473, 3422, 2),
				"Climb"));
		
		rope = new Obstacle("Rope", new RopeSolver(
				GnomeAreas.ROPE.getArea(),
				GnomeAreas.TREE_TWO.getArea(),
				6000,
				new Tile(2478, 3420, 2),
				"Walk-on"));
		
		treeTwo = new Obstacle("Tree Two", new TileSolver(
				GnomeAreas.TREE_TWO.getArea(),
				GnomeAreas.UNDER_TREE.getArea(),
				5000,
				new Tile(2486, 3419, 2),
				"Climb-down"));
		
		netTwo = new Obstacle("Net Two", new ObjectSolver(
				GnomeAreas.UNDER_TREE.getArea(),
				GnomeAreas.BEFORE_PIPES.getArea(),
				6000,
				new int[] {2286},
				"Climb-over"));
		
		pipe = new Obstacle("Pipe", new ObjectSolver(
				GnomeAreas.BEFORE_PIPES.getArea(),
				GnomeAreas.AFTER_PIPES.getArea(),
				10000,
				new int[] {4058, 154},
				"Squeeze-through"));
		
		walk = new Obstacle("Walking", new WalkingSolver(
				new Tile[] {
						new Tile(2479, 3438, 0),
						new Tile(2474, 3437, 0)},
				GnomeAreas.AFTER_PIPES.getArea(),
				GnomeAreas.BEFORE_LOG.getArea()
				));
	}	
}
