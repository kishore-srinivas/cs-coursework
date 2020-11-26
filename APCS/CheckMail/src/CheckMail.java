import java.util.Scanner;

public class CheckMail {
	
	public static void main(String[] args) {
		System.out.println("Enter the three dimensions and weight separated by commas: ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		double dim1 = Double.valueOf(input.split(",")[0]);
		double dim2 = Double.valueOf(input.split(",")[1]);
		double dim3 = Double.valueOf(input.split(",")[2]);
		double weight = Double.valueOf(input.split(",")[3]);
		Package mailPackage = new Package(dim1, dim2, dim3, weight);
		System.out.println(mailPackage.checkStatus());
	}

}
