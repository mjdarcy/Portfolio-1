package bjlopractice;

import java.util.ArrayList;

public class Recursion {
	
	Recursion()
	{
		System.out.println("2 to the power of 10: " + pow2(10, 2));
		System.out.print("Rectangle areas: ");
		new Rectangle(5, 5).getArea();
		System.out.println(
		"\nReversed String: " + reverse("Test String") + "\nReversed (true): " +
		find("abcdefghi", "def") + "\nReversed (false): " +
		find("abcdefghi", "dfe") + "\n" +
		"Index of (true): " + indexOf("Mississippi", "sip") + "\n" +
		"Index of (false): " + indexOf("Mississippi", "hello") + "\n" +
		"Reverse iteration: " + reverseIteration("Test String") + "\n" + 
		"Fib: " + fib(10)
		
		);
	}

	int pow2(int pow, int n)
	{
		return pow-- > 1 ? pow2(pow, n*2) : n;
	}
	/*
	Given a class Rectangle with instance variables width and height, provide a recursive
	getArea method. Construct a rectangle whose width is one less than the original and
	call its getArea method.*/
	class Rectangle
	{
		int width, height;
		
		Rectangle(int width, int height)
		{
			this.width = width;
			this.height = height;
		}
		
		int getArea()
		{
			System.out.print(width * height + " ");
			return width * height > 0 ? new Rectangle(width - 1, height - 1).getArea() : 1;
		}
	}
	/*
	Given a class Square with instance variable width, provide a recursive getArea method.
	Construct a square whose width is one less than the original and call its getArea
	method.*/
	class Square
	{
		int width;
		
		Square(int width)
		{
			
		}
		
		int getArea()
		{
			System.out.print(width * width + " ");
			return width * width > 0 ? new Square(width - 1).getArea() : 1;
		}
	}
	/*
	Using recursion, compute the area of a polygon. Cut off a
	triangle and use the fact that a triangle with corners (x1, y1),
	(x2, y2), (x3, y3) has area
	x y x y x y y x y x y x 1 2 2 3 3 1 1 2 2 3 3 1
	2
	+ + − − − */
	class Polygon
	{
		
	}
	/*
	Write a recursive method String reverse(String text) that reverses a string. For
	example, reverse("Hello!") returns the string "!olleH". Implement a recursive solution
	by removing the first character, reversing the remaining text, and combining the two.*/
	String reverse(String text)
	{
		return text.length() > 0 ? reverse(text.substring(1)) + text.charAt(0) : text;
	}
	/*
	P13.6 Use recursion to implement a method
	public static boolean find(String text, String str)
	that tests whether a given text contains a string. For example, find("Mississippi",
	"sip") returns true.
	Hint: If the text starts with the string you want to match, then you are done. If not,
	consider the text that you obtain by removing the first character.*/
	boolean find(String text, String str)
	{
		if(text.length() < str.length()){ return false; }
		else if(str.equals(text.substring(0, str.length())))
		{
			return true;
		}
		return find(text.substring(1), str);
	}
	/*
	Redo Exercise P13.3 with a recursive helper method that reverses a substring of the
	message text.
	*/
	String reverseSub(String substring)
	{
		return reverse(substring);
	}
	/*
	Use recursion to implement a method
	public static int indexOf(String text, String str)
	that returns the starting position of the first substring of the text that matches str.
	Return –1 if str is not a substring of the text.
	For example, s.indexOf("Mississippi", "sip") returns 6.
	Hint: This is a bit trickier than Exercise P13.6, because you must keep track of how
	far the match is from the beginning of the text. Make that value a parameter variable
	of a helper method.*/
	int indexOf(String text, String str)
	{
		return indexOf(text, str, 0);
	} int indexOf(String text, String str, int index)
	{
		if(text.length() < str.length() + index) { return -1; }
		else if(text.substring(index, index + str.length()).equals(str))
		{
			return index;
		} else
		{
			return indexOf(text, str, index+1);
		}
	}
	/*
	The following method was known to the ancient Greeks for computing square
	roots. Given a value x > 0 and a guess g for the square root, a better guess is
	(x + g/x) / 2. Write a recursive helper method public static squareRootGuess(double x,
	double g). If g^2
	is approximately equal to x, return g, otherwise, return squareRootGuess
	with the better guess. Then write a method public static squareRoot(double x) that
	uses the helper method.*/
	double squareRootGuess(double x)
	{
		return squareRootGuess(x, x);
	} double squareRootGuess(double x, double g)
	{
		if(g * g - x < 0.01) { return g; }
		//this doesn't work. Stack over-flow.
		return squareRootGuess(x, (x + g/x) / 2);	
	}
	/*
	P13.5 Implement the reverse method of Exercise P13.3 as an iteration.*/
	String reverseIteration(String str)
	{
		String result = "";
		
		for(int i = 1; i < str.length() + 1; i++)
		{
			result += str.charAt(str.length() - i);
		}
		
		return result;
	}
	/*
	The recursive computation of Fibonacci numbers can be speeded up significantly
	by keeping track of the values that have already been computed. Provide an implementation of the fib method that uses this strategy. 
	Whenever you return a new value, also store it in an auxiliary array. 
	However, before embarking on a computation, consult the array to find whether the result has already been computed. 
	Compare the running time of your improved implementation with that of the original
	recursive implementation and the loop implementation*/
	ArrayList<Integer> fib(int size)
	{
		ArrayList<Integer> values = new ArrayList<Integer>();
		fib(values, size);
		return values;
	} void fib(ArrayList<Integer> values, int size)
	{
		if(values.size() < 2)
		{
			values.add(1);
			fib(values, size);
		} else
		{
			values.add(values.get(values.size() - 1) + values.get(values.size() - 2));
			if(size-- > 0)
			{
				fib(values, size);
			}
		}

	}
	
	/*Implement a SubstringGenerator that generates all substrings of a string. For example,
	the substrings of the string "rum" are the seven strings
	"r", "ru", "rum", "u", "um", "m", ""
	Hint: First enumerate all substrings that start with the first character. There are n of
	them if the string has length n. Then enumerate the substrings of the string that you
	obtain by removing the first character.*/
	
	/*
	ArrayList<String> substringGenerator(String str)
	{
		ArrayList<String> result = new ArrayList<String>();
		return result;
	} ArrayList<String> substringGenerator(String str)
	{
		
	}
	*/
	
	/*
	Implement a SubsetGenerator that generates all subsets of the characters of a string.
	For example, the subsets of the characters of the string "rum" are the eight strings
	"rum", "ru", "rm", "r", "um", "u", "m", ""
	Note that the subsets don’t have to be substrings—for example, "rm" isn’t a substring
	of "rum".*/
}