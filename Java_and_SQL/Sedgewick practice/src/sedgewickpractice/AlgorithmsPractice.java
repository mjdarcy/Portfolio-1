package sedgewickpractice;

import java.util.Date;
import java.util.Iterator;

public class AlgorithmsPractice {

	public static void main(String[] args) {
		
		new Parenthesis("[(])");
		
		//new InfixToPrefix("(((1 + 2 ) * (3 - 4) * (5 - 6 )))");
		
		String[] strs = {"is", "name", "my", "Hello"};
		Stack<String> stack = new Stack<String>();
		LinkedList<String> list = new LinkedList<String>();
		Queue<String> queue = new Queue<String>();
		
		//initalize data structures:
		for(String str : strs)
		{
			stack.push(str);
			list.add(str);
			queue.push(str);
		}
		
		//Stack tests:
		Stack<String> stackCopy = stack.copy(stack);
		
		new ResizingCapacityStackOfString(5);

		for(Iterator<String> it = stackCopy.iterator(); it.hasNext();)
		{
			System.out.print(it.next() + " ");
		}
		
		//test queue
		queue.printElementFromEnd(0);
		
		//misc test
		Date[] date = Dates.readDates(new String[]{"1/24/2020"});
		System.out.println(date[0]);
		
		//List tests
		list.print();
		list.delete(2);
		list.print();
		
		System.out.println(list.find("name"));
	}

}

/*
1.3.7 Add a method peek() to Stack that returns the most recently inserted item on
the stack (without popping it).

1.3.12 Write an iterable Stack client that has a static method copy() that takes a stack
of strings as argument and returns a copy of the stack. Note : This ability is a prime
example of the value of having an iterator, because it allows development of such functionality without changing the basic API.
 */
class Stack<Item> implements Iterable<Item>
{
	
	Node first;
	int N;
	
	Item pop() 
	{
		Item item = first.data;
		first = first.next;
		N--;
		return item;
	}
	
	Item peak()
	{
		Item item = first.data;
		return item;
	}
	
	void push(Item c)
	{
		Node oldFirst = first;
		first = new Node();
		first.data = c;
		first.next = oldFirst;
		N++;
	}
	
	Stack<String> copy(Stack<String> stack) 
	{
		int i = 0;
		String[] copy = new String[N];
		Iterator<String> it = stack.iterator();
		Stack<String> newStack = new Stack<String>();
		
		while(it.hasNext())
		{
			copy[i] = it.next();
			i++;
		} for(i = copy.length-1; i >=0; i--)
		{
			newStack.push(copy[i]);
		}
		return stack;
	}
	
	class Node
	{
		Item data;
		Node next;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>
	{
		Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.data;
			current = current.next;
			return item;
		}
		
	}
}

/*
1.3.15 Write a Queue client that takes a command-line argument k and prints the kth
from the last string found on standard input (assuming that standard input has k or
more strings).
*/
class Queue<Item>
{
	int N;
	Node first;
	Node last;
	
	class Node
	{
		Item item;
		Node next;
	}
	
	Item pop()
	{
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	void push(Item item)
	{
		Node oldLast = last;
		last = new Node();
		last.item = item;
		if(first == null) first = last;
		else 			  oldLast.next = last;
		N++;
	}
	
	void printElementFromEnd(int k)
	{
		if(k < N)
		{
			Node current = first;
			while(N - (k+1) > 0)
			{
				current = current.next;
				k++;
			}
			System.out.println(current.item);
		} else
		{
			System.out.println("DNE");
		}
	}
	
}

/*
1.3.1 Add a method isFull() to FixedCapacityStackOfStrings.
1.3.14 Develop a class ResizingArrayQueueOfStrings that implements the queue
abstraction with a fixed-size array, and then extend your implementation to use array
resizing to remove the size restriction. 
*/
class FixedCapacityStackOfString
{
	String[] a;
	int N;
	
	FixedCapacityStackOfString(int cap)
	{
		a = new String[cap];
	}
	
	boolean isEmpty() { return N == 0; }
	int size() { return N; }
	void push(String item) { a[N++] = item; }
	String pop() { return a[--N]; }
	boolean isFull() { return N == a.length; }
}

class ResizingCapacityStackOfString extends FixedCapacityStackOfString
{

	ResizingCapacityStackOfString(int cap) {
		super(cap);
	}
	
	void push(String item)
	{
		if(N == a.length) { resize(N*2); }
		a[N++] = item;
	}
	
	String pop()
	{
		if(N == a.length/4) { resize(N/2); }
		return a[--N];
	}
	
	void resize(int newSize)
	{
		String[] newArray = new String[newSize];
		for(int i = 0; i < a.length; i++)
		{
			newArray[i] = a[i];
		}
		a = newArray;
	}
}
/*
1.3.2 Give the output printed by java Stack for the input
it was - the best - of times - - - it was - the - - 

it
was it
it
the it
the it best
it best
of it best
times of it best
best
it best
was it best
it best
the it best
it best
best

*/

/*
note: I have no idea what this question below is asking.

1.3.3 Suppose that a client performs an intermixed sequence of (stack) push and pop
operations. The push operations put the integers 0 through 9 in order onto the stack;
the pop operations print out the return values. Which of the following sequence(s)
could not occur?
a. 4 3 2 1 0 9 8 7 6 5
b. 4 6 8 7 5 3 2 9 0 1
c. 2 5 6 7 4 8 9 3 1 0
d. 4 3 2 1 0 5 6 7 8 9
e. 1 2 3 4 5 6 9 8 7 0
f. 0 4 6 5 3 8 1 7 2 9
g. 1 4 7 9 8 6 5 3 0 2
h. 2 1 4 3 6 5 8 7 9 0
*/

/*
1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
and uses a stack to determine whether its parentheses are properly balanced.
For example, your program should print true for [()]{}{[()()]()} and false for [(]).
*/
class Parenthesis
{
	Parenthesis(String data)
	{
		Stack<Character> stack = new Stack<Character>();
		boolean proper = true;
		
		for(char c : data.toCharArray())
		{
			if(c == '(' || c == '[' || c == '{')
			{
				stack.push(c);
			} else
			{
				char leftC = (char) stack.pop();
				if( (leftC == '(' && c != ')') ||
					(leftC == '[' && c != ']') ||
					(leftC == '{' && c != '}'))
				{
					proper = false;
					break;
				}
			}
		}
		System.out.println(proper);
	}
}
/*
1.3.5 What does the following code fragment print when N is 50?
Give a high-level description of what it does when presented with a positive integer N.

Stack<Integer> stack = new Stack<Integer>();
while (N > 0)
{
 stack.push(N % 2);
 N = N / 2;
}
for (int d : stack) StdOut.print(d);
StdOut.println();

50, 25, 12, 6, 3, 1
0, 1, 0, 0, 1, 1
reversed due to stack: 110010
Binary representation of 50.
*/

/*1.3.6 What does the following code fragment do to the queue q?
Stack<String> stack = new Stack<String>();
while (!q.isEmpty())
 stack.push(q.dequeue());
while (!stack.isEmpty())
 q.enqueue(stack.pop());
 
It reverses it.
*/


/*
1.3.8 Give the contents and size of the array for DoublingStackOfStrings with the
input
it was - the best - of times - - - it was - the - -

9 words - 7 = 3 words
it, 1
it was, 2
it, 2
it the, 2
it the best, 4
it the, 4
it the of, 4
it the of times, 4
it the of, 4
it the, 4
it, 2
it it, 2
it it was, 4
it it, 4
it it the, 4
it it, 4
it, 2
*/

/*
1.3.9 Write a program that takes from standard input an expression without left parentheses and prints the equivalent infix expression with the parentheses inserted.
For example, given the input:
1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
your program should print
( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
1.3.10 Write a filter InfixToPostfix that converts an arithmetic expression from infix to postfix.
1.3.11 Write a program EvaluatePostfix that takes a postfix expression from standard input, evaluates it, and prints the value. (Piping the output of your program from
the previous exercise to this program gives equivalent behavior to Evaluate.
*/

/*
1.3.13 - Same type of problem as before. No clue what this is asking.
*/

/*
1.3.16 Using readInts() on page 126 as a model, write a static method readDates() for
Date that reads dates from standard input in the format specified in the table on page 119
and returns an array containing them.
1.3.17 Do Exercise 1.3.16 for Transaction.
*/
class Dates
{
	Dates()
	{
		
	}
	
	static Date[] readDates(String[] dateStrs)
	{
		
		int month, day, year;
		Date[] dates = new Date[dateStrs.length];
		
		for(int i = 0; i < dateStrs.length; i++)
		{
			String[] fields = dateStrs[i].split("/");
			month = Integer.parseInt(fields[0]);
			day = Integer.parseInt(fields[1]);
			year = Integer.parseInt(fields[2]);
			dates[i] = new Date(year-1900, month-1, day);
			//Why is the Date initializer like this?
		}
		return dates;
	}
}


/*
LINKED-LIST EXERCISES
This list of exercises is intended to give you experience in working with linked lists. Suggestion: make drawings using the visual representation described in the text.
1.3.18 Suppose x is a linked-list node and not the last node on the list. What is the effect of the following code fragment?
x.next = x.next.next;

Removes x.next from the list.

1.3.19 Give a code fragment that removes the last node in a linked list whose first node
is first.

1.3.20 Write a method delete() that takes an int argument k and deletes the kth element in a linked list, if it exists.

1.3.21 Write a method find() that takes a linked list and a string key as arguments
and returns true if some node in the list has key as its item field, false otherwise.

1.3.24 Write a method removeAfter() that takes a linked-list Node as argument and
removes the node following the given one (and does nothing if the argument or the next
field in the argument node is null).

1.3.25 Write a method insertAfter() that takes two linked-list Node arguments and
inserts the second after the first on its list (and does nothing if either argument is null).

1.3.26 Write a method remove() that takes a linked list and a string key as arguments
and removes all of the nodes in the list that have key as its item field.

1.3.27 Write a method max() that takes a reference to the first node in a linked list as
argument and returns the value of the maximum key in the list. Assume that all keys are
positive integers, and return 0 if the list is empty.

1.3.28 Develop a recursive solution to the previous question.
*/
class LinkedList<Item>
{
	Node first;
	Node last;
	int N;
	
	class Node
	{
		Item item;
		Node next;
	}
	
	int max()
	{
		int max = 0;
		Node current = first;
		while(current != null)
		{
			if((Integer)current.item > max)
			{
				max = (Integer)current.item;
			}
			current = current.next;
		}
		return max;
	}
	
	int maxRecursive(Node current, int max)
	{
		if(current != null) maxRecursive(current.next, Math.max((int) current.item, max));
		return max;
	}
	
	void print()
	{
		Node current = first;
		while(current != null)
		{
			System.out.print(current.item + " ");
			current = current.next;
		}
		System.out.println();
	}
	
	void add(Item item)
	{
		Node oldLast = last;
		last = new Node();
		last.item = item;
		if(oldLast == null) first = last;
		else				oldLast.next = last;
		N++;
	}
	
	void insertAfter(Node a, Node b)
	{
		Node aNext = a.next;
		a.next = b;
		b.next = aNext;
	}
	
	boolean find(String item) 
	{
		Node current = first;
		while(current != null)
		{
			if(current.item.equals(item)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	void delete(int k)
	{
		Node current = first;
		if(k == 1) first = first.next;
		else
		{
			for(int i = 0; i < k-2; i++)
			{
				current = current.next;
			}
			current.next = current.next.next;
		}
	}
	
	void removeLastNode()
	{
		Node current = first;
		for(int i = 0; i < N-2; i++) {
			current = current.next;
		}
		current.next = null;
	}
	
	void removeAfter(Node node)
	{
		node.next = node.next.next;
	}
	
	void remove(LinkedList list, String str)
	{
		Node current = list.first;

		while(current != null)
		{
			if(current.item.equals(str))
			{
				first = current.next;
			} else if(current.next.item.equals(str))
			{
				current.next = current.next.next;
			}
		}
	}
}


/*
1.3.22 Suppose that x is a linked list Node. What does the following code fragment do?
t.next = x.next;
x.next = t;

Inserts T after x

1.3.23 Why does the following code fragment not do the same thing as in the previous
question?
x.next = t;
t.next = x.next;

x.next is set to t, so t.next will be set to t as well.


1.3.29 Write a Queue implementation that uses a circular linked list, which is the same
as a linked list except that no links are null and the value of last.next
 is first whenever the list is not empty. Keep only one Node instance variable (last).
*/

class QueueCircle<Item>
{
	Node first;
	Node last;
	int N;
	
	class Node
	{
		Item item;
		Node next;
	}
	
	void add(Item item)
	{
		Node oldLast = last;
		last = new Node();
		last.item = item;
		if(oldLast != null) oldLast.next = last;
		else first = last;
		last.next = first;
		N++;
	}
		
}

/*
1.3.30 Write a function that takes the first Node in a linked list as argument and (destructively) reverses the list, returning the first Node in the result.
Iterative solution : To accomplish this task, we maintain references to three consecutive
nodes in the linked list, reverse, first, and second. At each iteration, we extract the
node first from the original linked list and insert it at the beginning of the reversed
list. We maintain the invariant that first is the first node of what’s left of the original
list, second is the second node of what’s left of the original list, and reverse is the first
node of the resulting reversed list.
public Node reverse(Node x)
{
 Node first = x;
 Node reverse = null;
 while (first != null)
 {
 Node second = first.next;
 first.next = reverse;
 reverse = first;
 first = second;
 }
 return reverse;
}
When writing code involving linked lists, we must always be careful to properly handle
the exceptional cases (when the linked list is empty, when the list has only one or two
nodes) and the boundary cases (dealing with the first or last items). This is usually
much trickier than handling the normal cases.
Recursive solution : Assuming the linked list has N nodes, we recursively reverse the last
N –1 nodes, and then carefully append the first node to the end.
public Node reverse(Node first)
{
 if (first == null) return null;
 if (first.next == null) return first;
 Node second = first.next;
 Node rest = reverse(second);
 second.next = first;
 first.next = null;
 return rest;
}

1.3.31 Implement a nested class DoubleNode for building doubly-linked lists, where
each node contains a reference to the item preceding it and the item following it in the
list (null if there is no such item). Then implement static methods for the following
tasks: insert at the beginning, insert at the end, remove from the beginning, remove
from the end, insert before a given node, insert after a given node, and remove a given
node
*/