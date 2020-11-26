import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Statistics stats = new Statistics(1000);
		stats.readData("numbers2.txt");
//		stats.printData();
		
		System.out.println("Average: " + String.format("%.2f", stats.getAverage()));
		System.out.println("Modes: " + Arrays.toString(stats.getMode()));
		System.out.println("Standard Deviation: " + String.format("%.2f", stats.getStandardDev()));
		System.out.println("============================");
		
		
		stats.compact();
		System.out.print("Compacted: ");
		stats.printData();
		System.out.println("Average: " + String.format("%.2f", stats.getAverage()));
		System.out.println("Modes: " + Arrays.toString(stats.getMode()));
		System.out.println("Standard Deviation: " + String.format("%.2f", stats.getStandardDev()));
	}
}
