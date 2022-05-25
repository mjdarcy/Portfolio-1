package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class BinarySearch {
	
	interface BinarySearchInt{
		void sort();
		boolean contains(int value);
		int binarySearch(int key);
		void remove(int index);
		void add(int value);
		void initializeArray(); 
		void printElements();

	}
	
	static class BinarySearchArray implements BinarySearchInt{
		
		int[] arr;
		
		BinarySearchArray() {
			
		}
		
		static void testBinarySearchArray(BinarySearchInt targetArray) {
		    System.out.println("\n\nWelcome to the Binary Search Test.\n");
		    int value = 0;
			Scanner scanner = new Scanner(System.in);
		    try {
		        System.out.print("Enter an integer to search (or -1 or <Control> D to quit): ");
		        String ss = scanner.nextLine();
		        value = Integer.parseInt(ss);
		        do {
		        	int index = targetArray.binarySearch(value);
		        	if(index > -1) {
		        		System.out.println("Value "+value+" is found. Do you want to delete? y/n");
		        		if(scanner.nextLine().equals("y")) {
		        			targetArray.remove(index);
		        		}
		        	} else {
		        		System.out.println("Value "+value+" not found.  Do you want to add it? y/n");
		        		if(scanner.nextLine().equals("y")) {
		        			targetArray.add(value);
		        		}
		        	}
		        System.out.print("\nEnter an integer to search (or -1 or <Control> D to quit): ");
	        	ss = scanner.nextLine();
	        	System.out.print(ss+"\n");
	        	value = Integer.parseInt(ss);
		        } while(!ss.equals("-1"));
		    } catch(NoSuchElementException e) {
		        e.printStackTrace();
		        System.out.println("Goodbye");
		    }
		}

		@Override
		public void sort() {
			Arrays.sort(arr);
		}

		@Override
		public int binarySearch(int key) {
			return Arrays.binarySearch(arr, key);
		}

		@Override
		public void remove(int index) {
			
			
			while(index > 0 && this.arr[index-1] != 0) {
				this.arr[index] = this.arr[index-1];
				index--;
			}
			this.arr[index] = 0;
			this.sort();
			this.printElements();
			
			/*
			int[] arrTemp = arr;
			arr = new int[arr.length-1];
			
			int i = 0, j = 0;
			while(i < arrTemp.length) {
				if(i != index) {
					arr[i] = arrTemp[j];
					i++;
					j++;
				} else {
					i++;
				}
			}*/
		}

		@Override
		public boolean contains(int value) {
			int index = Arrays.binarySearch(arr, value);
			if(index > 0) {
				return true;
			} else {
				return false;
			}
		}

		@Override
		public void add(int value) {
			boolean tag = true;
			for(int i = arr.length-1; i >= 0; i--) {
				if(arr[i] == 0) {
					arr[i] = value;
					tag = false;
					break;
				}
			}
			if(tag) {
				System.out.println("No space available.");
			}
			this.sort();
			this.printElements();
		}

		@Override
		public void initializeArray() {
			arr = new int[15];
			this.sort();
			this.printElements();
		}

		@Override
		public void printElements() {
			System.out.print(arr[0]);
			for(int i = 1; i < arr.length; i++) {
				System.out.print(", " + arr[i]);
			}
		}
	}
	
	class BinarySearchArrayList implements BinarySearchInt{
		
		ArrayList<Integer> arrList;
		
		BinarySearchArrayList() {
			
		}

		@Override
		public void sort() {
			Collections.sort(arrList);
		}

		@Override
		public int binarySearch(int key) {
			return Collections.binarySearch(arrList, key);
		}

		@Override
		public void remove(int index) {
			arrList.remove(index);
			this.sort();
			this.printElements();
		}

		@Override
		public void add(int value) {
			arrList.add(value);
			this.sort();
			this.printElements();
		}

		@Override
		public void initializeArray() {
			this.arrList = new ArrayList<Integer>();
			this.sort();
			this.printElements();
		}

		@Override
		public void printElements() {
			if(arrList.size() > 0) {
				System.out.print(arrList.get(0));
			}
			for(int i = 1; i < arrList.size(); i++) {
				System.out.print(", " + arrList.get(i));
			}
		}

		@Override
		public boolean contains(int value) {
			return arrList.contains(value);
		}
		
	}
	
	public static void main(String[] args) {
		BinarySearch b = new BinarySearch();
		b.run();
	}
	
	public void run() {
		BinarySearchInt b = new BinarySearchArrayList();
		b.initializeArray();
		BinarySearchArray.testBinarySearchArray(b);
		
		BinarySearchInt b1 = new BinarySearchArray();
		b1.initializeArray();
		BinarySearchArray.testBinarySearchArray(b1);
	}
}
