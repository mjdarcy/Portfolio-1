package lab_7;

import java.util.Scanner;
import java.util.Stack;

public class Lab_7 {
	
	static Scanner scan = new Scanner(System.in);
	
	void stackPrint() {
		
		System.out.println("Enter number to reverse: ");
		Stack<Integer> s = new Stack<Integer>();
		int n = scan.nextInt();
		
		while(n != 0) {
			s.add(n % 10);
			n -= n % 10;
			n /= 10;
		}
		
		int counter = 1;
		
		while(!s.isEmpty()) {
			n += counter * s.pop();
			counter *= 10;
		}
		
		System.out.println(n);
	
	}
	
	void sentenceReverser() {
		
		System.out.println("Enter sentence to reverse: ");
		
		Stack<String> s = new Stack<String>();
		scan = new Scanner(System.in);
		scan = new Scanner(scan.nextLine());
		
		while(scan.hasNext()) {
			String str = scan.next();
			if(str.charAt(str.length() - 1) != '.') {
				s.add(" " + str.toLowerCase());
			} else {
				s.add(str.substring(0, 1).toUpperCase() + str.substring(1, str.length() - 1));
				while(!s.isEmpty()) {
					System.out.print(s.pop());
				}
				System.out.print(". ");
			}
		}
	}

	public static void main(String[] args) {
		Lab_7 m = new Lab_7();
		m.stackPrint();
		m.sentenceReverser();
		scan.close();
	}

}
