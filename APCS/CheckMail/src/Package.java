
public class Package {

	private double dim1, dim2, dim3, weight, sum;
	private boolean tooHeavy;
	
	public Package() {
		this(0, 0, 0, 0);
	}
	
	public Package(double dim1, double dim2, double dim3, double weight) {
		this.dim1 = dim1;
		this.dim2 = dim2;
		this.dim3 = dim3;
		this.weight = weight;
	}
	
	public String checkStatus() {
		if (weight >= 70) {
			tooHeavy = true;
		} else { 
			tooHeavy = false; 
		}
		
		if (dim1 > dim2) {
			if (dim1 > dim3) {
				sum = dim1 + 2*(dim3 + dim2);
			} else {
				sum = dim3 + 2*(dim1 + dim2);
			}
		} else {
			if (dim2 > dim3) {
				sum = dim2 + 2*(dim1 + dim3);
			} else {
				sum = dim3 + 2*(dim1 + dim2);
			}
		}
		
		if (sum <= 100) {
			if (tooHeavy) {
				return "Package is too heavy";
			} else {
				return "Package is acceptable!";
			}
		} else {
			if (tooHeavy) {
				return "Package is too large and too heavy";
			} else {
				return "Package is too large";
			}
		}
	}
	
}
