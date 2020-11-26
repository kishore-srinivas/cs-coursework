package bugs;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class ZBug extends BoxBug {

	private int numSides;

	public ZBug(int length) {
		super(length);
		super.turn();
		numSides = 0;
	}

	public void turn() {
		if (numSides == 0) {
			super.turn();
			setDirection(getDirection() + Location.HALF_RIGHT);
			numSides++;			
		}
		else if (numSides == 1) {
			super.turn();
			super.turn();
			setDirection(getDirection() + Location.HALF_RIGHT);
			numSides++;
		}
	}

	public boolean canMove() {
		if (numSides < 3 && super.canMove()) {
			return true;
		}
		return false;
	}

}
