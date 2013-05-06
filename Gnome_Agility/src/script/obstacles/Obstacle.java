package script.obstacles;

import script.obstacles.behaviours.SolvingBehaviour;

/*
 * You should instantiate this class in order to make your own obstacles.
 */
public class Obstacle {
	
	//Fields
	private final String name;
	private SolvingBehaviour solvingBehaviour;
	
	//Constructor
	public Obstacle(String name, SolvingBehaviour solvingBehaviour) {
		this.name = name;
		this.solvingBehaviour = solvingBehaviour;
	}

//----------------------------------------------Getters and Setters Below-----------------------------------------------------------
	
	public void setSolvingBehaviour(SolvingBehaviour solvingBehaviour) {
		this.solvingBehaviour = solvingBehaviour;
	}
	
	public SolvingBehaviour getSolvingBehaviour() {
		return solvingBehaviour;
	}
	
	public String getName() {
		return name;
	}
//-----------------------------------------------Getters and Setters Above-----------------------------------------------------------
	
	public boolean needsSolving() {
		return solvingBehaviour.needsSolving();
	}
	
	public void performSolve() {
		solvingBehaviour.solve();
	}
}
