import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class encrypts and decrypts text files using one of 3 algorithms:
 * 		Random monoalphabet, Vigenere, or Playfair
 * 
 * 
 * @author
 * @version
 * 
 */
public class Crypt {

	
	/**
	 * 
	 * An integer representing the algorithm chosen.
	 * Set to:
	 * 1 for random monoalphabet
	 * 2 for Vigenere
	 * 3 for Playfair
	 * 
	 */
	public static final int algorithm = 2;
	private ArrayList<String> monoCipher = new ArrayList<String>();
	private int vigenereEncryptIndex = 0, vigenereDecryptIndex = 0;
	private String[][] playfairCipher = new String[5][5];
	private ArrayList<String> play = new ArrayList<String>();
	
	
	/**
	 * Reads from the file specified, and writes out an encrypted version of the file. If the output file already
	 * exists, overwrite it.
	 * 
	 * @param inputFilename The path of the file to be encrypted.
	 * @param outputFilename The path of the encrypted file to be saved.
	 * @param keyword The keyword to be used in the encryption algorithm.
	 * 
	 */
	@SuppressWarnings({ "unused" })
	public void encrypt(String inputFilename, String outputFilename, String keyword) 
	{
		Scanner scan = null;
		FileWriter writer = null;
		BufferedWriter buffWriter = null;
		BufferedReader buffReader = null;
		
		try {
			
			FileReader reader = new FileReader(inputFilename);
			buffReader = new BufferedReader(reader);
			writer = new FileWriter(outputFilename);
			buffWriter = new BufferedWriter(writer);
			
			// Prepare cipher for mono
			for (int i = 0; i < keyword.length(); i++) {
				char c = keyword.toLowerCase().charAt(i);
				if (!monoCipher.contains(c+"")) monoCipher.add(c+"");
			}
			for (int i = 122; i >= 97; i--) {
				char c = (char) i;
				if (!monoCipher.contains(c+"")) monoCipher.add(c+"");
			}
						
			// Prepare cipher for playfair
			for (int i = 0; i < keyword.length(); i++) {
				char c = keyword.toLowerCase().charAt(i);
				if (c == 'i') c = 'j';
				if (!play.contains(c+"") && play.size() < 25) {
					play.add(c+"");
				}
			}
			for (int i = 97; i <= 122; i++) {
				char c = (char) i;
				if (c == 'i') c = 'j';
				if (!play.contains(c+"")) play.add(c+"");
			}
			for (int i = 0; i < play.size(); i++) playfairCipher[i/5][i%5] = play.get(i);
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					System.out.print(playfairCipher[i][j] + " ");
				}
				System.out.println();
			}
			
			int a;
			String x = "";
			StringBuffer line;
			StringBuffer encrypted = new StringBuffer("");
			while ((a = buffReader.read()) != -1) {
				if (algorithm == 1) {
					System.out.println(monoCipher);
					line = new StringBuffer((char) a + "" + buffReader.readLine());
					encrypted = encryptMono(line, keyword);
					buffWriter.write(encrypted + System.getProperty("line.separator"));
				}
				if (algorithm == 2) {
					line = new StringBuffer((char) a + "" + buffReader.readLine());
					if (line.charAt(0) != 13) encrypted = encryptVigenere(line, keyword);
					buffWriter.write(encrypted + System.getProperty("line.separator"));
				}
				if (algorithm == 3) {	
					char first = (char) a;
					char second = (char) buffReader.read();
					char firstSpace = 0, secondSpace = 0;
					line = new StringBuffer("");
					if (first == 32 || first == 13) {
						line.append(first);
						firstSpace = (char) ((first==32) ? 32 : 13);
						first = second;
						second = (char) buffReader.read();
					}
					line.append(first);
					if (second == 32 || second == 13) {
						line.append(second);
						secondSpace = (char) ((second==32) ? 32 : 32);
						second = (char) buffReader.read();
					}
					line.append(second);
					encrypted = encryptPlayfair(line, keyword);
					x += encrypted.toString();
					if (x.contains(System.getProperty("line.separator"))) {
						buffWriter.write(x);
						x = "";
					}
					
				}
			}
		} catch (IOException e) {}
		finally {
			try {
				if (buffWriter != null)	buffWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (scan != null) scan.close();
		}
	}
	
	
	/**
	 * Reads from the (previously encrypted) file specified, and writes out a decrypted version of the file. 
	 * If the output file already exists, overwrite it.
	 * 
	 * @param inputFilename The path of the encrypted file.
	 * @param outputFilename The path of the decrypted file to be saved.
	 * @param keyword The keyword to be used in the decryption algorithm.
	 * 
	 */
	public void decrypt(String inputFilename, String outputFilename, String keyword) 
	{
		FileWriter writer = null;
		BufferedWriter buffWriter = null;
		BufferedReader buffReader = null;
		
		try {
			
			FileReader reader = new FileReader(inputFilename);
			buffReader = new BufferedReader(reader);
			writer = new FileWriter(outputFilename);
			buffWriter = new BufferedWriter(writer);
			int a;
			String x = "";
			StringBuffer line;
			StringBuffer decrypted = new StringBuffer("");
			while ((a = buffReader.read()) != -1) {
				if (algorithm == 1) {
					line = new StringBuffer((char) a + "" + buffReader.readLine());
					decrypted = decryptMono(line, keyword);
					buffWriter.write(decrypted + System.getProperty("line.separator"));
				}
				if (algorithm == 2) {
					line = new StringBuffer((char) a + "" + buffReader.readLine());
					if (line.charAt(0) != 13) decrypted = decryptVigenere(line, keyword);
					buffWriter.write(decrypted + System.getProperty("line.separator"));
				}
				if (algorithm == 3) {	
					char first = (char) a;
					char second = (char) buffReader.read();
					char firstSpace = 0, secondSpace = 0;
					line = new StringBuffer("");
					if (first == 32 || first == 13) {
						line.append(first);
						firstSpace = (char) ((first==32) ? 32 : 13);
						first = second;
						second = (char) buffReader.read();
					}
					line.append(first);
					if (second == 32 || second == 13) {
						line.append(second);
						secondSpace = (char) ((second==32) ? 32 : 32);
						second = (char) buffReader.read();
					}
					line.append(second);
					decrypted = decryptPlayfair(line, keyword);
					x += decrypted.toString();
					if (x.contains(System.getProperty("line.separator"))) {
						buffWriter.write(x);
						x = "";
					}
					
				}
			}
		} catch (IOException e) {

		} finally {
			try {
				if (buffWriter != null)	buffWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	private StringBuffer encryptMono(StringBuffer line, String keyword) {
		for (int i = 0; i < line.length(); i++) {
			char current = line.charAt(i);
			if ((current >= 65 && current <= 90) || (current >= 97 && current <= 122)) {
				boolean capital = false;
				int index = current - 'a';
				if (index < 0) {
					capital = true;
					index = current - 'A';
				}
				if (index > 26)	return line;
				if (capital) line.setCharAt(i, monoCipher.get(index).toUpperCase().charAt(0));
				else line.setCharAt(i, monoCipher.get(index).charAt(0));
			}
		}
		return line;		
	}
	
	private StringBuffer encryptVigenere(StringBuffer line, String keyword) {
		char current;
		for (int j = 0; j < line.length(); j++) {
			current = line.charAt(j);
			if ((current >= 65 && current <= 90) || (current >= 97 && current <= 122)) {
				char n = 0;
				int shift = 0;
				if (current >= 65 && current <= 90) {
					shift = (keyword.toUpperCase().charAt(vigenereEncryptIndex) - 'A');
					n = (char) (current + shift);
					if (n > 'Z') n-=26;
					line.setCharAt(j, n);
					vigenereEncryptIndex = (vigenereEncryptIndex+1)%keyword.length();
				} else if (current >= 97 && current <= 122) {
					shift = (keyword.charAt(vigenereEncryptIndex) - 'a');
					n = (char) (current + shift);
					if (n > 'z') n-=26;
					line.setCharAt(j, n);
					vigenereEncryptIndex = (vigenereEncryptIndex+1)%keyword.length();
				}				
			}
		}
		return line;
	}
	
	private StringBuffer encryptPlayfair(StringBuffer line, String keyword) {
		char first = line.charAt(0);
		char second = line.charAt(line.length()-1);
		int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
		if ((first >= 65 && first <= 90) || (first >= 97 && first <= 122)) {
			if (first == 'j') first = 'i';
			first = Character.toLowerCase(first);
			int index = play.indexOf(first + "");
			r1 = index/5;
			c1 = index%5;
		}
		if ((second >= 65 && second <= 90) || (second >= 97 && second <= 122)) {
			if (second == 'j') second = 'i';
			second = Character.toLowerCase(second);
			int index = play.indexOf(second + "");
			r2 = index/5;
			c2 = index%5;
		}
		System.out.println(first + "[" + r1 + ", "  + c1 + "] and " + second + "[" + r2 + ", "  + c2 + "]");
		
		line.setCharAt(0, playfairCipher[r2][c1].charAt(0));
		line.setCharAt(line.length()-1, playfairCipher[r1][c2].charAt(0));
		System.out.println(line);
		
		return line;	
	}
	
	private StringBuffer decryptMono(StringBuffer line, String keyword) {		
		for (int i = 0; i < line.length(); i++) {
			char current = line.charAt(i);
			if ((current >= 65 && current <= 90) || (current >= 97 && current <= 122)) {
				boolean capital = (current+"").toLowerCase().equals((char) (current+32)+"");		
				int index = monoCipher.indexOf((current+"").toLowerCase());
				if (capital) line.setCharAt(i, monoCipher.get(index).toUpperCase().charAt(0));
				else line.setCharAt(i, monoCipher.get(index+27).charAt(0));
			}
		}
		return line;		
	}
	
	private StringBuffer decryptVigenere(StringBuffer line, String keyword) {
		char current;
		for (int j = 0; j < line.length(); j++) {
			current = line.charAt(j);
			if ((current >= 65 && current <= 90) || (current >= 97 && current <= 122)) {
				char n = 0;
				int shift = 0;
				if (current >= 65 && current <= 90) {
					shift = (keyword.toUpperCase().charAt(vigenereDecryptIndex) - 'A');
					n = (char) (current - shift);
					if (n < 'A') n+=26;
					line.setCharAt(j, n);
					vigenereDecryptIndex = (vigenereDecryptIndex+1)%keyword.length();
				} else if (current >= 97 && current <= 122) {
					shift = (keyword.charAt(vigenereDecryptIndex) - 'a');
					n = (char) (current - shift);
					if (n < 'a') n+=26;
					line.setCharAt(j, n);
					vigenereDecryptIndex = (vigenereDecryptIndex+1)%keyword.length();
				}
				
				
				
			}
		}
		return line;
	}
	
	private StringBuffer decryptPlayfair(StringBuffer line, String keyword) {
		return line;
	}
}
