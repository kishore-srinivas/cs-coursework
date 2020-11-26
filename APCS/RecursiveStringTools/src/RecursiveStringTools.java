import java.util.Scanner;


public class RecursiveStringTools {

	// Example
	public static int length(String in) {
		if (in.equals("")) {
			return 0;
		} else {
			return length(in.substring(1)) + 1;
		}
	}	

	// Example
	public static boolean equals(String in1, String in2) {
		if (in1.length() != in2.length()) {
			return false;
		} else if (length(in1) == 0 && length(in2) == 0) {
			return true;
		} else {
			return in1.charAt(0) == in2.charAt(0) && equals(in1.substring(1), in2.substring(1));
		}
	}

	// Exercise #1
	public static boolean matches(String in1, String in2) {
		if (in1.length() != in2.length()) {
			return false;
		} else if (equals(in1, in2)) {
			return true;
		} else {
			return (in1.charAt(0) == in2.charAt(0) || in1.charAt(0) == '?' || in2.charAt(0) == '?') && matches(in1.substring(1), in2.substring(1));
		}
	}



	// Exercise #2
	public static boolean isPalindrome(String in) {
		String str = new String(in).replaceAll("[^A-Za-z0-9]+", "");
		String str1, str2;
		if (str.length() % 2 == 1) {
			str1 = new String(str.substring(0, str.length()/2)).toLowerCase();
			str2 = reverse(new String(str.substring(str.length()/2+1, str.length()))).toLowerCase();
		} else {
			str1 = new String(str.substring(0, str.length()/2)).toLowerCase();
			str2 = reverse(new String(str.substring(str.length()/2, str.length()))).toLowerCase();
		}
		return equals(str1, str2);
	}
	
	private static String reverse(String in) {
		String reversed = "";
		for (int i = in.length()-1; i >= 0; i--) {
			reversed += in.charAt(i);
		}
		return reversed;
	}

	// Exercise #3
	public static void printPermutations(String in) {
		printPermutations(in, "");
	}
	
	private static void printPermutations(String in, String removed) {		
		if (in.length() == 1) {
			System.out.println(removed + "" + in);
		} else {
			forLoop(0, in.length(), in, removed);
		}
	}
	
	private static void forLoop(int i, int n, String in, String removed) {
		if (i >= n) {
			return;
		} else {
			printPermutations(in.substring(0, i) + in.substring(i+1), removed + in.charAt(i));
			i++;
			forLoop(i, n, in, removed);
		}
	}



	public static String piglatinate(String in) {
		return "";
	}



	public static void main(String[] args) {
		Scanner kboard = new Scanner(System.in);
		System.out.println("Please enter a string:");
		String s = kboard.nextLine();

		String out = RecursiveStringTools.length(s) + "";
		System.out.println("\nThe length is --> " + out + "\n\n");
		System.out.println("Please enter another string:");		
		String s2 = kboard.nextLine();
		String out2 = RecursiveStringTools.equals(s, s2) + "";
		System.out.println("The two entered strings are equal: " + out2);
		String out3 = RecursiveStringTools.matches(s, s2) + "";
		System.out.println("The two entered strings match: " + out3);
		String out4 = RecursiveStringTools.isPalindrome(s) + "";
		System.out.println(s + " is a palindrome: " + out4);
		System.out.println("---\n");
		System.out.println("Please enter another string:");		
		String s3 = kboard.nextLine();
		printPermutations(s3);
	}
}
