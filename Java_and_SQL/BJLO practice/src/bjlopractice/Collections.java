package bjlopractice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import bjlopractice.Helper.Stopwatch;

public class Collections {
	
	final String currentDir = System.getProperty("user.dir");
	
	Collections() throws FileNotFoundException
	{
		LinkedList<String> employees = new LinkedList<>();
		String[] strs = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
		for(String str : strs) { employees.add(str); }
		downsize(employees, 3);
		System.out.print("Del every 3rd element: ");
		Helper.printArr(employees);
		
		Set<String> treeSet = breakDownTree(currentDir + "\\words.txt");
		System.out.println("\nUnique words: " + treeSet.toString() + " with size " + treeSet.size());
	
		System.out.println("Primes up to 100: " + seiveSet(100));
		
		compareTimes(currentDir + "\\words.txt");
		
		System.out.println("HashMap program: ");
		editGrades(new HashMap());
		
		System.out.println("HashMap program, reverse sentence: ");
		reverseWords("Hello my name is Alijah. This is the second sentence. This is the third.");
		
		System.out.println("Reverse int: ");
		reverseNumber(102348);
		
		System.out.println("Properly balanced html: " + properlyBalanced("<p> </p> <div> <div>"));
		
		System.out.println("Maze has exit: " + new Maze().hasExit());
	}
	/*
	P15.1 Write a method
	public static void downsize(LinkedList<String> employeeNames, int n)
	that removes every nth employee from a linked list.*/
	void downsize(LinkedList<String> employeeNames, int n)
	{
		Iterator<String> it = employeeNames.iterator();
		int space = n;
		while(it.hasNext())
		{
			if(space == 0)
			{
				it.remove();
				space = n;
			}
			it.next();
			space--;
		}
	}
	/*
	Write a program that reads text from a file and breaks it up into individual words.
	Insert the words into a tree set. At the end of the input file, print all words, followed
	by the size of the resulting set. This program determines how many unique words atext file has.*/
	Set<String> breakDownTree(String fileName) throws FileNotFoundException
	{
		Scanner in = new Scanner(new File(fileName));
		in.useDelimiter("[^a-zA-Z]+");
		
		Set<String> tree = new TreeSet<String>();
		
		while(in.hasNext())
		{
			tree.add(in.next().toLowerCase());
		}
		
		return tree;
	}
	/*
	Implement the sieve of Eratosthenes: a method for computing
	prime numbers, known to the ancient Greeks. This method
	will compute all prime numbers up to n. Choose an n.
	First insert all numbers from 2 to n into a set. Then erase all
	multiples of 2 (except 2); that is, 4, 6, 8, 10, 12, . . . . Erase
	all multiples of 3; that is, 6, 9, 12, 15, . . . . Go up to n. Then
	print the set.*/
	Set<Integer> seiveSet(int n)
	{
		Set<Integer> raw = new HashSet<Integer>();
		for(int i = 2; i < n + 1; i++)
		{
			raw.add(i);
		}
		
		Set<Integer> result = new HashSet<Integer>();
		
		while(raw.size() > 0)
		{
			Iterator<Integer> it = raw.iterator();
			Integer curr = it.next();
			result.add(curr);
			it.remove();
			
			while(it.hasNext())
			{
				if(it.next() % curr == 0)
				{
					it.remove();
				}
			}
		}
		return result;
	}
	/*
	 P15.13 Insert all words from a large file (such as the novel “War and Peace”, which is available on the Internet) into a hash set and a tree set. Time the results. Which data
	structure is faster?*/
	
	void compareTimes(String fileName) throws FileNotFoundException
	{
		Scanner inHash = new Scanner(new File(currentDir + "\\words.txt"));
		Scanner inTree = new Scanner(new File(currentDir + "\\words.txt"));
		inHash.useDelimiter("[^a-zA-Z]+");
		inTree.useDelimiter("[^a-zA-Z]+");
		
		Set<String> hash = new HashSet<String>();
		Set<String> tree = new TreeSet<String>();
		
		Stopwatch stp = new Stopwatch();
		
		stp.start();
		
		while(inHash.hasNext())
		{
			hash.add(inHash.next());
		}
		
		stp.stop();
		System.out.print("Hash time: " + stp.timeElapsed);
		stp.restart();
		stp.start();
		
		while(inTree.hasNext())
		{
			tree.add(inTree.next());
		}
		
		System.out.println(" Tree time: " + stp.timeElapsed);
	}
	
	/*
	P15.9 Write a program that keeps a map in which both keys and
	values are strings—the names of students and their course
	grades. Prompt the user of the program to add or remove students, to modify grades,
	or to print all grades. The printout should be sorted by name and formatted like this:
	Carl: B+
	Joe: C
	Sarah: A*/
	void editGrades(Map<String, String> st)
	{
		st.put("Carl", "B+");
		st.put("Joe", "C");
		st.put("Sarah", "A");
		
		System.out.print("Map contents: " + st);
		
		st.remove("Joe");
		
		System.out.print(" Map contents after removing Joe: " + st);
		
		st.put("Josh", "B");
		
		System.out.print(" Keys after removing Josh: ");
		
		for(String key : st.keySet())
		{
			System.out.print(st.get(key) + " ");
		}
	}
	/*
	P15.14 Write a program that reads a Java source file and produces an index of all identifiers
	in the file. For each identifier, print all lines in which it occurs. For simplicity, we
	will consider each string consisting only of letters, numbers, and underscores
	an identifer. Declare a Scanner in for reading from the source file and call
	in.useDelimiter("[^A-Za-z0-9_]+"). Then each call to next returns an identifier.*/
	
	/*
	P15.3 Use a stack to reverse the words of a sentence. Keep reading words until you have a
	word that ends in a period, adding them onto a stack. When you have a word with a
	period, pop the words off and print them. Stop when there are no more words in the
	input. For example, you should turn the input
	Mary had a little lamb. Its fleece was white as snow.
	into
	Lamb little a had mary. Snow as white was fleece its.
	Pay attention to capitalization and the placement of the period.
	P r o g r a m m i n g E x e r c i s e s
	706 Chapter 15 The Java Collections Framework*/
	void reverseWords(String str)
	{
		Stack<String> words = new Stack<String>();
		String[] strWords = str.split(" ");
		
		for(String strs : strWords)
		{
			words.add(strs);
			if(strs.charAt(strs.length() - 1) == '.')
			{
				String sentence = "";
				while(words.size() > 0)
				{
					sentence += words.pop() + " ";
				}
				
				sentence = sentence.replace(".", "").toLowerCase();
				sentence = sentence.substring(0, 1).toUpperCase() + sentence.substring(1, sentence.length() - 1) + ".";
				
				System.out.println(sentence);
			}
		}
	}
	/*
	P15.4 Your task is to break a number into its individual digits, for example, to turn 1729
	into 1, 7, 2, and 9. It is easy to get the last digit of a number n as n % 10. But that gets
	the numbers in reverse order. Solve this problem with a stack. Your program should
	ask the user for an integer, then print its digits separated by spaces.*/
	void reverseNumber(int n)
	{
		Stack<Integer> nums = new Stack<Integer>();
		while(n > 0)
		{
			nums.add(n % 10);
			n /= 10;
		}
		
		int result = 0;
		int placeValue = 1;
		
		while(nums.size() > 0)
		{
			result += nums.pop() * placeValue;
			placeValue *= 10;
		}
		
		System.out.println(result);
	}
	/*
	P15.21 Write a program that checks whether a sequence of HTML tags is properly nested.
	For each opening tag, such as <p>, there must be a closing tag </p>. A tag such as <p>
	may have other tags inside, for example
	<p> <ul> <li> </li> </ul> <a> </a> </p>
	708 Chapter 15 The Java Collections Framework
	The inner tags must be closed before the outer ones. Your program should process a
	file containing tags. For simplicity, assume that the tags are separated by spaces, and
	that there is no text inside the tags.*/
	boolean properlyBalanced(String str)
	{
		
		String[] brackets = str.split(" ");
		Stack<String> openingBrackets = new Stack<String>();
		
		for(String bracket : brackets)
		{
			
			if(bracket.matches("<[a-z]+>"))
			{
				openingBrackets.add(bracket);
			} else
			{
				
				String opening = openingBrackets.pop();
				String closing = opening.substring(0, 1) + "/" + opening.substring(1);
				
				if(!bracket.equals(closing))
				{
					return false;
				}
			}
		}
		if(openingBrackets.size() > 0) return false;
		return true;
	}
	/*
	P15.22 Add a % (remainder) operator to the expression calculator of Section 15.6.3.
	
	else if(input.equals("%")
	{
		results.push(results.pop() % results.pop())
	}*/
	
	/*
	P15.25 Modify the maze solver program of Section 15.6.4 to handle mazes with cycles. Keep
	a set of visited intersections. When you have previously seen an intersection, treat it
	as a dead end and do not add paths to the stack.*/
	
	class Maze
	{
		boolean[][] maze;
		int[] exit;
		List<Path> visitedInter;
		
		Maze()
		{
			visitedInter = new ArrayList<Path>();
			maze = genMaze();
			exit = findExit();
		}
		
		boolean[][] genMaze()
		{
			return new boolean[][]
					{{false,false,false,false,false ,false,false,false},
					 {false ,true ,true ,true ,true ,true ,true ,false},
					 {false,false,false,false,true ,false,false,false},
					 {false,true ,true ,true ,true ,true ,true ,false},
					 {false,false,false,false,true ,false,false,false},
					 {true,true ,true ,true ,true ,true ,true ,false},
					 {false,false,false,false,true ,false,false,false},
					 {false,false,false,false,false,false,false,false}};
		}
		
		int[] findExit(){
			
			Stack<Path> stack = new Stack<Path>();
			Path start = new Path("", new int[] {3, 4});
			stack.add(start);
			
			//all positions are y x
			
			while(stack.size() > 0)
			{
				int paths = 0;
				Path currentPath = stack.pop();
				//first goes to y=2, x=4
				if(currentPath.currentPos[0] == 0 || currentPath.currentPos[1] == 0) { return currentPath.currentPos; }
				if(!visitedInter.contains(currentPath)) {
					if(maze[currentPath.currentPos[0] - 1][currentPath.currentPos[1]] && !currentPath.dir.equals("south"))
					{
						stack.add(new Path("north", new int[] {currentPath.currentPos[0] - 1, currentPath.currentPos[1]}));
						paths++;
					} if(maze[currentPath.currentPos[0] + 1][currentPath.currentPos[1]] && !currentPath.dir.equals("north"))
					{
						stack.add(new Path("south", new int[] {currentPath.currentPos[0] + 1, currentPath.currentPos[1]}));
						paths++;
					} if(maze[currentPath.currentPos[0]][currentPath.currentPos[1] + 1] && !currentPath.dir.equals("west"))
					{
						stack.add(new Path("east", new int[] {currentPath.currentPos[0], currentPath.currentPos[1] + 1}));
						paths++;
					} if(maze[currentPath.currentPos[0]][currentPath.currentPos[1] - 1] && !currentPath.dir.equals("east"))
					{
						stack.add(new Path("west", new int[] {currentPath.currentPos[0], currentPath.currentPos[1] - 1}));
						paths++;
					}
					if(paths > 0) visitedInter.add(currentPath);
					//I could have just done the above from the start but I'd think this would make the time complexity much worse.
					//directions aren't needed if visits are logged.
					//I guess I'd only need to log turns.
				}
			}
			return new int[] {-1, -1};
		}
		
		boolean hasExit()
		{
			System.out.println("The exit is at [" + exit[1] + ", " + exit[0] + "]");
			return exit[0] != -1;
		}
		
		class Path
		{
			String dir;
			int[] currentPos;
			
			Path(String dir, int[] currentPos)
			{
				this.dir = dir;
				this.currentPos = currentPos;
			}
			
			public String toString()
			{
				return "going " + dir + " to y=" + currentPos[0] + " x= " + currentPos[1];
			}
		}
	}
	
	/*
	P15.26 In a paint program, a “flood fill” fills all empty pixels of a drawing with a given color,
	stopping when it reaches occupied pixels. In this exercise, you will implement a
	simple variation of this algorithm, flood-filling a 10 × 10 array of integers that are
	initially 0.
	Prompt for the starting row and column.
	Push the (row, column) pair onto a stack.
	You will need to provide a simple Pair class.
	Repeat the following operations until the stack is empty.
	Pop off the (row, column) pair from the top of the stack.
	If it has not yet been filled, fill the corresponding array location with a number 1, 2, 3, and so on
	(to show the order in which the square is filled).
	Push the coordinates of any unfilled neighbors in the north, east, south, or west direction on the stack.
	When you are done, print the entire array.*/
	class FloodFill
	{
		
		Point[][] picture;
		int n;
		Stack<Point> toFill;
		
		FloodFill()
		{
			
			picture = new Point[10][10];
			for(int y = 0; y < picture.length; y++)
			{
				for(int x = 0; x < picture[y].length; x++)
				{
					picture[y][x] = new Point(new int[] {0, 0, 0});
				}
			}
			
			n = 1;
			toFill = new Stack<Point>();
		}
		
		void fillNeighbors(int[] cord)
		{
			toFill.add(picture[cord[0]][cord[1]]);
			picture[cord[0]][cord[1]].rgb = new int[] {n, n, n++};
			if(cord[0] > 0 && picture[cord[0] - 1][cord[1]].rgb[0] < 1)
			{
				picture[cord[0] - 1][cord[1]].rgb[0] = 1;
				fillNeighbors(new int[] {cord[0] - 1, cord[1]});
			} if(cord[0] < picture.length && picture[cord[0] + 1][cord[1]].rgb[0] < 1)
			{
				picture[cord[0] + 1][cord[1]].rgb[0] = 1;
				fillNeighbors(new int[] {cord[0] + 1, cord[1]});
			} if(cord[1] > 0 && picture[cord[0] - 1][cord[1]].rgb[0] < 1)
			{
				picture[cord[0]][cord[1] - 1].rgb[0] = 1;
				fillNeighbors(new int[] {cord[0], cord[1] - 1});
			} if(cord[1] < picture.length && picture[cord[0] - 1][cord[1]].rgb[0] < 1)
			{
				picture[cord[0]][cord[1] + 1].rgb[0] = 1;
				fillNeighbors(new int[] {cord[0], cord[1] + 1});
			}
		}
			
		class Point
		{
			int[] rgb;
			
			Point(int[]	rgb)
			{
				this.rgb = rgb;
			}
		}
	}
}