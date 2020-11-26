package testers;

import java.util.Arrays;

import resizableArray.ResizableArray;

public class Tester {

	public static void main(String[] args) {
		
		ResizableArray data = new ResizableArray();		
		data.add(1);
		data.add(2);
		data.add(3);
		data.add(4);
		data.add(5);
		data.insert(4, 6);
		data.remove(3);
		data.replace(2, 7);
		data.reverse();
		data.swap(1, 4);
		data.replace(6, 60);
		System.out.println(data.toString());
		System.out.println(data.findAll(3).toString());
		System.out.println(data.subArray(2, 4).toString());
		System.out.println("---------------------");
		System.out.println(data.toString());
		data.removeAll(3);
		System.out.println(data.toString());
		System.out.println(data.size());
		System.out.println("---------------------");
		data.sort();
		System.out.println(data.toString());
		System.out.println(data.indexOf(5));
		
		ResizableArray data2 = new ResizableArray();
		data2.add(1);
		data2.add(2);
		data2.add(3);
		data2.add(4);
		data2.add(5);
		data2.insert(4, 6);		
		data2.remove(3);
		data2.reverse();
		data2.swap(1, 4);
		data2.replace(6, 60);
		System.out.println(data2.toString());		
		System.out.println(data.equals(data2));
		
		ResizableArray data3 = new ResizableArray();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				data3.add(10*i + j);
			}
		}
		System.out.println(data3.toString());
		ResizableArray sub3 = new ResizableArray();
		sub3 = data3.subArray(0, 10);
		ResizableArray data4 = new ResizableArray(new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		System.out.println(sub3.equals(data4));
		for (int i = 0; i < data3.size(); i++) {
			if (i % 5 == 0) {
				data3.replace(data3.get(i), 12345);
			}
		}
		System.out.println(data3.toString());
		data3.replaceAll(12345, 54321);
		System.out.println(data3.toString());
		data3.removeAll(54321);
		System.out.println(data3.toString());
		data3.swap(50, 20);
		System.out.println(data3.toString());
		data3.addAll(new int[] {1000, 2000, 3000, 4000, 5000});
		System.out.println(data3.toString());
		data3.setAll(987);
		System.out.println(data3.toString());
		data3.clear();
		System.out.println(data3.toString());
	}

}
