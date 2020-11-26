package bugs;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public class SpiralBug extends BoxBug {
	
	public SpiralBug(int length) {
		super(length);
	}
	
	public void turn() {
		super.turn();
		super.incrementSideLength();
	}
	
}
