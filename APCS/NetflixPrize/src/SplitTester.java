

import java.util.ArrayList;
import java.util.HashMap;

public class SplitTester {
	
	public static void main(String args[]) {
		String s = "Drama|Fantasy|Sci-Fi";
		System.out.println(s);
		String[] part = s.split("\\|");
		System.out.println(part[1]);
		
		String string = "004-034556";
		String[] parts = string.split("-");
		String part1 = parts[0]; // 004
		String part2 = parts[1]; // 034556
		System.out.println(part1);
		System.out.println(part2);
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(2, 4);
		System.out.println(map.get(1));
		System.out.println(map.get(4));
		System.out.println(map);
		
		ArrayList<Integer> input = new ArrayList<Integer>();
		input.add(1);
		input.add(2);
		input.add(3);
		input.add(3);
		input.add(3);
		input.add(4);
		input.add(5);
		input.add(5);
		input.add(5);
		input.add(6);
		input.add(7);
		input.add(7);
		input.add(1);
		System.out.println(input);
		ArrayList<Integer> output = new ArrayList<Integer>();
		for (int i : input) {
			if (!output.contains(i)) output.add(i);
		}
		System.out.println(output);
		
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("apple");
		strings.add("banana");
		strings.add("cherry");
		strings.add("dill");
		strings.add("eggplant");
		strings.add("fruit");
		strings.add("grape");
		System.out.println(strings.contains("apple"));
		System.out.println(strings.contains("app"));
		System.out.println(strings.contains("peach"));
	}

}
