package barrons_test2;

public class Tester2 {

	public static void main(String[] args) {
		// Problem 1a and 1b and 1c
		Recipients r = new Recipients();
		System.out.println(r.extractCity("Ithaca, NY 14850"));
		System.out.println(r.extractCity("Glendale, CA 91023"));
		System.out.println(r.extractCity("New York, NY 10013"));
		r.printNames();
		System.out.println(r.getAddress("Jack S. Smith"));
		System.out.println("==========================\n");

	}

}
