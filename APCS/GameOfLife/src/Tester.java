
public class Tester {

	public static void main(String[] args) {
		Life grid = new Life("griddata\\life100.txt");
		grid.step(5);
		System.out.println(grid);
	}

}
