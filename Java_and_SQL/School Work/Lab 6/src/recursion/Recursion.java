package recursion;

public class Recursion {
	
	public int sum(int n) {
		if(n == 1) {
			return 1;
		}
		return n + sum(n - 1);
	}
	
	public int factorial(int n) {
		if(n == 1) {
			return 1;
		}
		return n * sum(n - 1);
	}
	
	public int powerOf10(int n) {
		if(n == 1) {
			return 10;
		}
		return 10 * powerOf10(n - 1);
	}
	
	public int powerOfN(int x, int p) {
		if(p == 1) {
			return 10;
		}
		return x * powerOf10(p - 1);
	}
	
	public int bunnyEars(int n) {
		if(n == 1) {
			return 2;
		}
		return 2 + bunnyEars(n - 1);
	}

	public static void main(String[] args) {
		Recursion r = new Recursion();
		System.out.println("Sum: "+r.sum(5));
		System.out.println("Factorial: "+r.factorial(5));
		System.out.println("Power of 10: "+r.powerOf10(5));
		System.out.println("Power of N: "+r.powerOfN(10, 5));
		System.out.println("Bunny ears: "+r.bunnyEars(10));
		
		Flower.start();
	}
}