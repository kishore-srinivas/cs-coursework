package barrons_test2;

import java.util.ArrayList;

public class Recipients {
	
	private ArrayList<String> recipients;
	
	public Recipients(ArrayList<String> recipients) {
		this.recipients = recipients;		
	}
	
	public Recipients() {
		recipients = new ArrayList<String>();
		recipients.add("Mr. J. Adams");
		recipients.add("6 Rose St.");
		recipients.add("Ithaca, NY 14850");
		recipients.add("");
		recipients.add("Jack S. Smith");
		recipients.add("12 Posy Way");
		recipients.add("Suite #201");
		recipients.add("Glendale, CA 91023");
		recipients.add("");
		recipients.add("Ms. M. K. Delgado");
		recipients.add("2 River Dr.");
		recipients.add("New York, NY 10013");
		recipients.add("");
	}
	
	public String extractCity(String cityZip) {
		return cityZip.split(",")[0];
	}
	
	public void printNames() {
		System.out.println(recipients.get(0));
		ArrayList<Integer> counter = new ArrayList<Integer>();
		for (int i = 0; i < recipients.size()-1; i++) {
			if (recipients.get(i) == "") {
				counter.add(i);
			}
		}
		for (int i = 0; i < counter.size(); i++) {
			System.out.println(recipients.get(counter.get(i)+1));
		}
	}
	
	public String getAddress(String name) {
		String str = "";
		for (int i = 0; i < recipients.size(); i++) {
			if (recipients.get(i).equalsIgnoreCase(name)) {
				i++;
				while (!recipients.get(i).equals("")) {
					str += recipients.get(i) + " ";
					i++;
				}
				i = recipients.size();
			}
		}
		return str;
	}

}
