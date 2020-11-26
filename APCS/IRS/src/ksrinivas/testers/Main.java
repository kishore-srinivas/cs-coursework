package ksrinivas.testers;

import java.util.Scanner;

import ksrinivas.irs.TaxAccount;

public class Main {

	public static void main(String[] args) {
		System.out.print("Enter your marital status (1 for single, 2 for married): ");
		Scanner in = new Scanner(System.in);
		boolean married = in.nextInt() % 2 == 0;
		System.out.print("Enter your taxable income: $");
		double taxable = in.nextDouble();
		TaxAccount account = new TaxAccount(married, taxable);
		in.close();

		if (account.getTax() < 0) {
			System.out.println("Invalid income!");
		} else {
			System.out.println("Your federal tax is: $" + account.getTax());
		}

	}

}
