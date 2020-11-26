
public class Main {
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		
		Crypt crypt = new Crypt();
		String input = "data" + System.getProperty("file.separator") + "input.txt";
		String encrypted = "data" + System.getProperty("file.separator") + "encrypted.txt";
		String decrypted = "data" + System.getProperty("file.separator") + "decrypted.txt";
		String keyword = "hippopotamus";
		
		
		crypt.encrypt(input, encrypted, keyword);
		crypt.decrypt(encrypted, decrypted, keyword);
				
		long end = System.currentTimeMillis();
		long time = (end-start);
		long mins = time/60000;
		long secs = time%60000/1000;
		long millis = time % 1000;
		System.out.println("Runtime: " + mins + ":" + secs + "." + millis);
	}
}
