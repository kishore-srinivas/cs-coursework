import java.util.ArrayList;

import javax.swing.JFileChooser;

public class Tester {
	
	public static void main(String[] args) {		
		JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
	    int returnVal = chooser.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName() + "\n============================\n");
	    }
		
		ArrayList<String> fileData = FileIO.readFile(chooser.getSelectedFile().getAbsolutePath());
		for (int i = 0; i < fileData.size(); i++) {
			System.out.println(fileData.get(i));
		}
		
		FileIO.writeFile("test.txt", fileData);
		
	}

}
