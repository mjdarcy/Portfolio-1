package com.alijah.perscholas;

import java.util.Scanner;

public class PSSimplePrograms {
	
	public static void main(String[] args) { new PSSimplePrograms(); }
	
	PSSimplePrograms()
	{
		SessionFactory s;
		
		new Database();
		/*
		StringBuffer sb = new StringBuffer(10);
		sb.append("hello my name is alijah");
		
		String dir = System.getProperty("user.dir");
		
		try {
			File f = new File(dir + "\\filePractice.txt");
			
			BufferedWriter out = new BufferedWriter(new FileWriter(f));
			out.write(sb.toString() + "\n" + sb.toString());
			out.close();
			
			BufferedReader in = new BufferedReader(new FileReader(f));
			
			
			String line = "a";
			System.out.println(line);
			while((line = in.readLine()) != null) System.out.println(line);
			
			in.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
        StringBuffer sb2 = new StringBuffer("135");

        sb2.replace(0, 5, "6789").insert(0, "12345");

        System.out.println(sb2.reverse().delete(0, 5));
		System.out.println("Complete");
		
		
		interface F
		{
			int calculate(int a, int b, int c);
		}
		
		F avg = (int a, int b, int c) -> (a + b + c) / 3;
		
		int average = avg.calculate(1, 3, 10);
		
		System.out.println(average);
		
		F doubleAvg = (int a, int b, int c) -> avg.calculate(a, b, c) * 2;
		
		int doubleAverage = doubleAvg.calculate(1, 3, 10);
		System.out.println(doubleAverage);
		

		
		@FunctionalInterface
		interface MyFunctionalInterface {
		  public Integer sqr(int a);

		  default String sqr(String a) {
		    return a + a;
		  }
		}

	    MyFunctionalInterface fi = n -> {
		      return n * n;
		    };
		    System.out.println("Anser: " + fi.sqr(5));
		
		
		
		
		/*
		Map<String, String> address = new HashMap<String, String>();
		address.put("13 8th st", "Tom");
		address.put("19 2nd str", "Nancy");
		address.put("17 3rd st", "Joe");
		
		Map<String, String> address2 = new TreeMap<String, String>();
		address2.put("13 8th st", "Tom");
		address2.put("19 2nd str", "Nancy");
		address2.put("17 3rd st", "Joe");
		
		//1.
		Parent p = new Child1();
		Parent p2 = new Child2();
		p.message();
		p2.message();
		
		//2.
		Bank a = new BankA();
		System.out.println("\nBank a balance: $" + a.getBalance());
		
		Bank b = new BankB();
		System.out.println("Bank b balance: $" + b.getBalance());
		
		Bank c = new BankC();
		System.out.println("Bank c balance: $" + c.getBalance());
	
		//3.
		Marks a2 = new A(70, 80, 78);
		System.out.println("\nStudent A percent grade: " + a2.getPercentage() + "%");
		
		Marks b2 = new B(70, 90, 97, 85);
		System.out.println("Student A percent grade: " + b2.getPercentage() + "%\n");
		
		//4.
		SubClass test = new SubClass();
		test.a_method();
		test.printNoneAbs();
		
		System.out.println();
		
		//5.
		Animals cat = new Cats();
		cat.cats();
		Animals dog = new Dogs();
		dog.dogs();
		
		
		//6.
		int n1, n2;
		n1 = 5;
		n2 = 6;
		
		Shape a3 = new Area(n1, n2);
		System.out.println("\nRectangle area of " + n1 + " and " + n2 + " and 6: " + a3.RectangleArea());
		
		Shape b3 = new Area(n1);
		System.out.println("Square area of " + n1 + ": " + b3.SquareArea());
		
		Shape c3 = new Area(n1);
		System.out.println("Circle area of " + n1 + ": " + c3.CircleArea());
		
		//7.
		Random r = new Random();
		Shape[] recs = new Area[4];
		Shape[] sqs = new Area[4];
		Shape[] circs = new Area[5];
		
		for(int i = 0; i < 4; i++) recs[i] = new Area(r.nextInt(10), r.nextInt(10));
		for(int i = 0; i < 4; i++) sqs[i] = new Area(r.nextInt(10));
		for(int i = 0; i < 5; i++) circs[i] = new Area(r.nextInt(10));
		
		System.out.println("\nArea of 4 random rectangles: ");
		for(Shape s : recs) System.out.print("[" + s.getA() + ", " + s.getB() + "] = " + s.RectangleArea() + ", ");
		
		System.out.println("\nArea of 4 random squares: ");
		for(Shape s : sqs) System.out.print("[" + s.getA() + "] = " + s.SquareArea() + ", ");
		
		System.out.println("\nArea of 5 random circles: ");
		for(Shape s : circs) System.out.print("[" + s.getA() + "] = " + s.CircleArea() + ", ");
		
		System.out.println();
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(10);
		arr.add(15);
		for(int n : arr) System.out.println(n);
	
		arr.forEach(n -> {System.out.println(n);});
		
		List<Integer> list = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i = 0; i < 50; i++) list.add(rand.nextInt(100));
		Iterator<Integer> it = list.iterator();
		while(it.hasNext())
		{
			int curr = it.next();
			if(curr % 2 == 0) { System.out.println("Removing: " + curr); it.remove(); }
			else System.out.println("Number is odd: " + curr);
		}
		
		
		class LinkedListC
		{
			Node first;
			Node curr;
			
			class Node
			{
				int data;
				Node next;
			}
		
			boolean hasNext()
			{
				return curr != null;
			}
			
			Node next()
			{
				curr = curr.next;
				return curr;
			}
			
			void add()
			{
				
			}
		}
		
		LinkedList<Integer> ll = new LinkedList<Integer>();
		for(int i = 0; i < 10; i++) ll.add(r.nextInt(10));
		ListIterator<Integer> it2 = ll.listIterator();

		int removed = 0;
		while(it2.hasNext())
		{
			int n = it2.next();
			System.out.println("Current number: " + n);
			if(n % 2 != 0)
			{
				System.out.println("Removing odd number.");
				it2.remove();
				removed++;
			}
		}
		int endIndex = it2.nextIndex();
		System.out.println("Size with odds: " + endIndex + removed);
		while(it2.hasPrevious()) System.out.println(it2.previous());
		System.out.println("Size without odds: " + endIndex);
		*/
	}
}
	
class Practice1
{
	
	Practice1()
	{
		int a = 5, b = 5;
		int result1 = add(a, b);
		System.out.println(result1);
		
		double a2 = 5.5, b2 = 5.6;
		double result2 = addDouble(a2, b2);
		System.out.println(result2);
		
		int a3 = 5; double b3 = 5.5;
		double result3 = addIntDouble(a3, b3);
		System.out.println(result3);
		
		int a4 = 10, b4 = 5;
		double result4 = divide(a4, b4);
		System.out.println(result4);
		
		double a5 = 10.5, b5 = 10.2;
		double result5 = divideDouble(a5, b5);
		System.out.println(result5);
		System.out.println((int) result5);
		
		dividePredefined();
		this.calculationGravity(5);
		this.coffeeShop();
		
		doubleLoopCountdown();
		
		new Calculator();
	}
	
	int add(int a, int b)
	{
		return a + b;
	}

	double addDouble(double a, double b)
	{
		return a + b;
	}
	
	double addIntDouble(int a, double b)
	{
		return a + b;
	}
	
	double divide(int a, int b)
	{
		return Math.max(a, b) / Math.min(a, b);
	}
	
	double divideDouble(double a, double b)
	{
		return Math.max(a, b) / Math.min(a, b);
	}
	
	void dividePredefined()
	{
		int x = 5, y = 6;
		int q = y / x;
		System.out.println(q);
		q = (int) ((double) y / x);
		System.out.println(q);
	}

	void calculationGravity(int time)
	{
		final double EARTHS_GRAVITY = 9.8;
		double acc = time * EARTHS_GRAVITY;
		System.out.println(acc);
	}
	
	void coffeeShop()
	{
		double SALES_TAX = 1.05;
		
		double coffee = 2.4, cappuccino = 3.4, espresso = 3.5, tea = 1.0;
		double subTotal = coffee * 3 + cappuccino * 4 + espresso * 2;
		double totalSale = subTotal * SALES_TAX;
		System.out.println(String.format(String.valueOf(totalSale), "%.2f"));
	}
	
	void doubleLoopCountdown()
	{
		for(int i = 5; i > -1; i--)
		{
			for(int j = 0; j < i; j++)
			{
				System.out.print(i);
			}
			System.out.println();
		}
	}
}

class Loops
{
	
	Loops()
	{
		printTen();
		System.out.println();
		printTens();
		System.out.println();
		printTenWhile();
		System.out.println();
		printForLoop();
		System.out.println();
		printForLoopBreak();
		System.out.println();
		nestedLoop();
		System.out.println();
		isPal();
		System.out.println();
		fib(1, 1, 100);
		System.out.println();
		print2DArr();
		System.out.println();
		printStrPal();
		System.out.println();
		printPrimes();
		System.out.println();
		twoDArr();
	}
	
	void printTen()
	{
		for(int i = 1; i <= 10; i++) System.out.print(i + " ");
	}
	
	void printTens()
	{
		for(int i = 10; i <= 100; i += 10) System.out.print(i + " ");
	}
	
	void printTenWhile()
	{
		int i = 0;
		while(++i <= 10) System.out.print(i);
	}
	
	void printForLoop()
	{
		for(int i = 1; i < 100; i++)
		{
			if(i >= 25 && i <= 75) continue;
			if(i % 5 == 0) System.out.print(i + " ");
		}
	}
	
	void printForLoopBreak()
	{
		for(int i = 1; i < 100; i++)
		{
			if(i > 50) break;
			if(i % 5 == 0) System.out.print(i + " ");
		}
	}
	
	void nestedLoop()
	{
		for(int i = 1; i <= 2; i++) 
		{
			
			System.out.println("Week " + i + ":");
			
			for(int j = 1; j <= 5; j++)
			{
				System.out.println("Day " + j);
			}
		}
	}
	
	void isPal()
	{
		for(int i = 10; i <= 200; i++)
		{
			int orig = i;
			int reversed = 0;
			while(orig > 0)
			{
				reversed *= 10;
				reversed += orig % 10;
				orig /= 10;
			}
			if(i == reversed) System.out.print(i + " ");
		}
	}
	
	void fib(int a, int b, int max)
	{
		System.out.print(a + ", ");
		if(b < max) fib(b, a + b, max);
	}
	
	void print2DArr()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				System.out.print(" [i: " + i + " j: " + j + "], ");
			}
			System.out.println();
		}
		System.out.println();
		
		boolean[] str = new boolean[5];
		System.out.println("-" + str[2] + "-");
	}
	
	void printStrPal()
	{
		String[] strs = {"Racecar", "Alijah"};
		for(String str : strs)
		{
			boolean an = true;
			for(int i = 0; i < str.length() / 2; i++)
			{
				str = str.toLowerCase();
				if(str.charAt(i) != str.charAt(str.length() - (i + 1)))
				{
					an = false;
					break;
				}
			}
			if(an) System.out.println(str + " is an anagram.");
		}
	}
	
	void printPrimes()
	{
		int[] n = {7, 10, 11, 17, 22, 42, 55, 60, 61, 89};
		boolean[] notPrime = new boolean[n[n.length-1] + 1];
		for(int i = 2; i < notPrime.length; i++)
			if(!notPrime[i])
				for(int j = i * i; j < notPrime.length; j += i)
					notPrime[j] = true;
		for(int i = 0; i < n.length; i++)
			if(!notPrime[n[i]])
				System.out.println(n[i] + " is prime.");
		else System.out.println(n[i] + " is not prime.");
	}
	
	void twoDArr()
	{
		Scanner scan = new Scanner(System.in);
		int[][] twoDArr = new int[3][4];
		System.out.println("Enter the number to get the multiples of, then enter the max value you'd like to add: ");
		
		int n = scan.nextInt();
		int max = scan.nextInt();
		
		for(int i = 0; i < twoDArr.length; i++)
		{
			for(int j = 0; j < twoDArr[i].length; j++)
			{
				int cellValue = ((i * 4) + j + 1) * n;
				if(cellValue > max) break;
				twoDArr[i][j] = cellValue;
				System.out.println(cellValue);
			}
		}
		
		for(int[] arr : twoDArr)
		{
			int rowValue = 0;
			for(int cell : arr) rowValue += cell;
			System.out.println(rowValue);
		}
		scan.close();
	}
}

class Calculator
{
	
	final static String test = "test";
	
	Calculator()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("1. Add\n2. Subtract\n3. Multiply\n4. Divide\n5. Average\n6. Exit");
		int input = scan.nextInt();
		if(input == 6) System.exit(0);
		System.out.println("Enter two numbers seperated by enter: ");
		int a = scan.nextInt();
		int b = scan.nextInt();
		while(true)
		{
			int result = 0;
			switch(input)
			{
				case 1: result = add(a, b);
				break;
				case 2: result = subtract(a, b);
				break;
				case 3: result = multiply(a, b);
				break;
				case 4: result = divide(a, b);
				break;
				case 5: result = average(a, b);
				break;
				default: System.out.println("Invalid option"); continue;
			}
			System.out.println(result);
			scan.close();
			break;
		}
	}
	
	int add(int a, int b)
	{
		return a + b;
	}
	
	int subtract(int a, int b)
	{
		return a - b;
	}
	
	int multiply(int a, int b)
	{
		return a * b;
	}
	
	int divide(int a, int b)
	{
		return a / b;
	}
	
	int average(int a, int b)
	{
		return (a + b) / 2;
	}
}

interface MonsterI {
	void attack();
}

class Monster implements MonsterI
{
	String name;
	
	Monster(String name)
	{
		this.name = name;
	}

	public void attack() {
		System.out.println("Monster attacks");
	}
	
}

class FireMonster extends Monster
{
	FireMonster(String name)
	{
		super(name);
	}
	
	public void attack()
	{
		System.out.println("Fire monster attacks.");
	}
}

class WaterMonster extends Monster
{
	WaterMonster(String name)
	{
		super(name);
	}
	
	public void attack()
	{
		System.out.println("Water monster attacks.");
	}
}

class StoneMonster extends Monster
{
	StoneMonster(String name)
	{
		super(name);
	}
	
	public void attack()
	{
		System.out.println("Stone monster attacks.");
	}
}

interface Mobile
{
	void walk();
	void run();
	void sit();
}

class Person implements Mobile
{
    static String COURSE="TEK RD JAVA EAST";
    static double raise=5;
    
    String fname;
    String lname;
    int age;
    String phone;
    char gender;
    double salary;
    double netincome;
    boolean active;
    
    public Person(){}
    
    public Person(boolean act)
    {
        active = act;
    }
    
    public Person (String fname, String lname, int age, String phone, double salary, char gender, boolean active)
    {
        this.fname=fname;
        this.lname=lname;
        this.age=age;
        this.phone=phone;
        this.salary=salary;
        this.gender=gender;
        this.active= active;
        
    }
     
     void calculateNetIncome()
      {
          double bonus;
          bonus = (this.salary * .05);
          this.netincome= this.salary+bonus;
          
      }
    
     void printFullName()
     {
    	 System.out.println(this.fname + " " + this.lname);
     }

	public static String getCourse() {
		return COURSE;
	}

	public static void setCourse(String course) {
		COURSE = course;
	}

	public static double getRaise() {
		return raise;
	}

	public static void setRaise(double raise) {
		Person.raise = raise;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getNetincome() {
		return netincome;
	}

	public void setNetincome(double netincome) {
		this.netincome = netincome;
	}

	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public void walk() {
		System.out.println("Person walked.");
	}

	@Override
	public void run() {
		System.out.println("Person ran.");
	}

	@Override
	public void sit() {
		System.out.println("Person sat down.");
	}

}

class Student extends Person
{
	Address add;
	
	Student(){ add = new Address(); }
	
	Student(String fname, String lname, int age, String phone, double salary, char gender, boolean active)
	{
		super(fname, lname, age, phone, salary, gender, active);
		add = new Address();
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}
	
}

class Address
{
	private String street;
	private String town;
	private String state;
	private int zip;
	
	Address()
	{
		
	}
	
	Address(String st, String tw, String state, int zip)
	{
		this.street = st;
		this.town = tw;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
}

interface LibraryUser
{
	void registerAccount();
	void requestBook();
}

class User
{
	int age;
	String bookType;
	
	User(){ }
	
	User(int age, String bookType)
	{
		this.age = age;
		this.bookType = bookType;
	}
}

class AdultUser extends User implements LibraryUser
{
	AdultUser(){ }

	AdultUser(int age, String bookType)
	{
		super(age, bookType);
	}
	
	@Override
	public void registerAccount()
	{
		if(age > 12) System.out.println("You have successfully registered under an Adult Account");
		else System.out.println("Sorry, Age must be greater than 12 to register as an adult");
	}

	@Override
	public void requestBook() 
	{
		if(age < 12) System.out.println("Book Issued successfully, please return the book within 7 days");
		else System.out.println("Oops, you are allowed to take only adult Fiction books");
	}
	
}

class ChildUser extends User implements LibraryUser
{
	
	ChildUser(){ }
	
	ChildUser(int age, String bookType)
	{
		super(age, bookType);
	}

	@Override
	public void registerAccount() 
	{
		if(age < 12) System.out.println("You have successfully reistered under a Child Account.");
		else System.out.println("Sorry, Age must be less than 12 to register as a kid.");
	}

	@Override
	public void requestBook() 
	{
		if(age < 12) System.out.println("Book Issued successfully, please return the book within 10 days");
		else System.out.println("Oops, you are allowed to take only kids books");
	}
}


interface Iterable
{
	void sort();
}

class Peasent implements Iterable
{
	String name;

	@Override
	public void sort() { System.out.println("Sorting peasent."); }
	
}

class King implements Iterable
{
	String name;

	@Override
	public void sort() { System.out.println("Sorting king."); }
}

class Knight implements Iterable
{
	String name;

	public void sort() { System.out.println("Sorting knight."); }
}

class main
{
	void functionThatSortsOrSomething(Iterable it)
	{
		//Any object that implements Iterable can be used as a parameter
		//because it implements Iterable, the following function can be used within this function.
		it.sort();
	}
}

abstract class Parent
{
	abstract void message();
}

class Child1 extends Parent
{

	@Override
	void message() {
		System.out.println("This is first subclass.");
	}
	
}

class Child2 extends Parent
{

	@Override
	void message() {
		System.out.println("This is second subclass.");
	}
	
}

abstract class Bank
{
	abstract int getBalance();
}

class BankA extends Bank
{
	@Override
	int getBalance() { return 100; }
}

class BankB extends Bank
{
	@Override
	int getBalance() { return 150; }
}

class BankC extends Bank
{
	@Override
	int getBalance() { return 200; }
}

abstract class Marks
{
	abstract double getPercentage();
}

class A extends Marks
{
	int a, b, c;
	
	A(int a, int b, int c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	double getPercentage() { return (a + b + c) / 3; }
}

class B extends Marks
{
	int a, b, c, d;
	
	B(int a, int b, int c, int d)
	{
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	
	@Override
	double getPercentage() { return (a + b + c) / 3; }
}

abstract class AbsConst
{
	AbsConst()
	{
		System.out.println("This is a constructor of abstract class");
	}
	
	abstract void a_method();
	
	void printNoneAbs()
	{
		System.out.println("This is a normal method of abstract class");
	}
}

class SubClass extends AbsConst
{

	@Override
	void a_method()
	{
		System.out.println("This is abstract method");
	}
	
}

abstract class Animals
{
	abstract void cats();
	abstract void dogs();
}

class Cats extends Animals
{
	
	void cats() { System.out.println("Cats meow."); }

	@Override
	void dogs() {
		System.out.println("Cats don't bark.");
	}
}

class Dogs extends Animals
{
	void dogs() { System.out.println("Dogs bark."); }

	@Override
	void cats() {
		System.out.println("Dogs don't meow.");
	}
}

abstract class Shape
{
	abstract int RectangleArea();
	abstract int SquareArea();
	abstract int CircleArea();
    abstract int getA();
	abstract int getB();
}

class Area extends Shape
{
	int a, b;
	
	Area(int a)
	{
		this.a = a;
	}
	
	Area(int a, int b)
	{
		this(a);
		this.b = b;
	}
	@Override
	int RectangleArea() { return a * b; }

	@Override
	int SquareArea() { return a * a; }

	@Override
	int CircleArea() { return (int) (a * Math.PI); }
	
	int getA() { return this.a; }
	
	int getB() { return this.b; }
}

