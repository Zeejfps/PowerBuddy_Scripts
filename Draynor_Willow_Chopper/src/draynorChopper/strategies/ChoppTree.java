package draynorChopper.strategies;

import org.powerbuddy.api.input.Mouse;
import org.powerbuddy.api.methods.Camera;
import org.powerbuddy.api.util.Random;
import org.powerbuddy.api.wrapper.GameObject;

import draynorChopper.conditions.TillAnimating;
import draynorChopper.enums.State;
import draynorChopper.main.DraynorChopper;
import draynorChopper.util.Condition;
import draynorChopper.util.Strategy;

/*
 * This will run when the script is in the NOT_CHOPPING state.
 */
public class ChoppTree implements Strategy{
	
	private static final Condition tillAnimating = new TillAnimating();
	
	@Override
	public boolean isValid() {
		return DraynorChopper.state.equals(State.NOT_CHOPPING);
	}

	@Override
	public void execute() {
		
		if (!DraynorChopper.inArea(DraynorChopper.TREE_AREA)) {
			DraynorChopper.state = State.WALKING_TO_TREES;
		} else {
			GameObject temp = DraynorChopper.findClosest(DraynorChopper.treeIds);
			if (temp != null) {
				DraynorChopper.tree = temp;
				clickOnTree(temp);
				DraynorChopper.state = State.CHOPPING;
			}
		}
	}
	
	/*
	 * This has some redundancy, but I had to do it for bug fixes...
	 */
	private void clickOnTree(final GameObject tree) {
		if (tree != null && tree.getLocation().onScreen()) {
			Mouse.move(tree.getScreenLocation());
			if (Random.nextInt(0, 6) == 1) {
				tree.interact("Chop down");
				moveMouseRandom();
				DraynorChopper.sleep(tillAnimating, Random.nextInt(1500, 3000));
			} else {
				tree.click();
				moveMouseRandom();
				DraynorChopper.sleep(tillAnimating, Random.nextInt(1500, 3000));
			}
		} else if (tree != null && !tree.getLocation().onScreen()){
			Camera.turnTo(tree);
		} else {
			return;
		}
	}
	
	private void moveMouseRandom() {
		
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
