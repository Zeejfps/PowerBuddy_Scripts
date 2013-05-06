package script.courses;

import java.util.LinkedList;

import script.obstacles.Obstacle;

/*
 * This class is a blueprint for Obstacle Course classes, incase you want
 * to create other obstacle courses. 
 */
public abstract class ObstacleCourse {
	
	private final LinkedList<Obstacle> obstacles = new LinkedList<Obstacle>();
	
	public ObstacleCourse() {
		createObstacles();
	}
	
	public LinkedList<Obstacle> getObstacles() {
		return obstacles;
	}
	
	//This method must be overriden.
	public abstract void createObstacles();
	
}
