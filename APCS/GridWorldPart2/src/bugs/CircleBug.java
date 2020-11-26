package bugs;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class CircleBug extends BoxBug {
	
	public CircleBug(int length) {
		super(length);
	}
	
	public void turn() {
		super.turn();
		setDirection(getDirection() + Location.HALF_LEFT);
	}

}
