/*
 * neatly organized, very easy to read 
 * good use of constructors to pass in input
 * All that's missing is an condition to loop, but thats nitpicking 
 *	When you enter a decimal value close to a dollar, you will get a floating point error. 
 *a-4
 *b-6
 *c-3
 *d-7
 */


package ksrinivas.irs;

public class TaxAccount {
	private double taxable, tax;
	private boolean married;
	
	public TaxAccount() {
		this(false, 0);
	}
	
	public TaxAccount(boolean married, double taxable) {
		this.married = married;
		this.taxable = taxable;
		this.tax = 0;
	}
	
	public void calculateTax() {
		if (taxable >= 0) {
			if (!married) {
				if (taxable >= 297350) {
					tax = 93347 + 0.391*(taxable - 297350);
				}
				else if (taxable >= 136750) {
					tax = 36361 + 0.355*(taxable - 136750);
				}
				else if (taxable >= 65550) {
					tax = 14645 + 0.305*(taxable - 65550);
				}
				else if (taxable >= 27050) {
					tax = 4057.5 + 0.275*(taxable - 27050);
				}
				else {
					tax = 0.15*taxable;
				}
			}
			if (married) {
				if (taxable >= 297350) {
					tax = 88306 + 0.391*(taxable - 297350);
				}
				else if (taxable >= 166500) {
					tax = 41885 + 0.355*(taxable - 166500);
				}
				else if (taxable >= 109250) {
					tax = 24393.75 + 0.305*(taxable - 109250);
				}
				else if (taxable >= 45200) {
					tax = 6780 + 0.275*(taxable - 45200);
				}
				else {
					tax = 0.15*taxable;
				}
			}
		} else {
			tax = -1;
		}
	}
	
	public double getTax() {
		calculateTax();
		return tax;
	}
	
	public boolean getMaritalStatus() {
		return married;
	}

}
