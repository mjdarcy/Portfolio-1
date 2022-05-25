package bjlopractice;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Set;

public class DataStructures {
/*
P16.2 Consider a version of the LinkedList class of Section 16.1 in which the addFirst
method has been replaced with the following faulty version:

752 Chapter 16 Basic Data Structures
public void addFirst(Object element)
{
 Node newNode = new Node();
 first = newNode;
 newNode.data = element;
 newNode.next = first;
}
Develop a program ListTest with a test case that shows the error. That is, the
program should print a failure message with this implementation but not with the
correct implementation.

Answer:
If the list contains more than 0 elements, the list will not contain those elements, since the new nodes next isn't linked to the old first.
*/
	
/*
P16.4 Add a method size to our implementation of the LinkedList class that computes the
number of elements in the list by following links and counting the elements until the
end of the list is reached.*/
/*
P16.6 Add an instance variable currentSize to our implementation of the LinkedList class.
Modify the add, addLast, and remove methods of both the linked list and the list iterator
to update the currentSize variable so that it always contains the correct size. Change
the size method of Exercise P16.4 so that it simply returns the value of this instance
variable.*/

class LinkedList
{
	Node first;
	Node last;
	int currentSize;
	
	class Node
	{
		int data = 0;
		Node next = null;
		
		Node(int data)
		{
			this.data = data;
		}
	}
	
	//only adding what's required to solve these problems. Otherwise I'd create an iterator.
	void add(int data)
	{
		Node current = first;
		Node newNode = new Node(data);
		
		if(first == null) first = current;
		else
		{
			while(current.next != null)
			{
				current = current.next;
			}
			current.next = newNode;
			currentSize++;
		}
	}
	
	void remove()
	{
		if(first == null) return;
		else if(first.next == null) first = null;
		else first = first.next;
		currentSize--;
	}
	
	/*
	int size()
	{
		Node current = first;
		int n = 0;
		while(first != null)
		{
			n++;
			current = current.next;
		}
		return n;
	}*/
	
	int size()
	{
		return currentSize;
	}
	
}
	/*P16.23 Complete the implementation of a stack in Section 16.3.2, using an array for storing
	the elements.*/
	class ArrayStack
	{
		int[] buffer;
		int size;
		
		ArrayStack()
		{
			buffer = new int[10];
			size = 0;
		}
		
		ArrayStack(int n)
		{
			buffer = new int[n];
			size = 0;
		}
		
		void resize()
		{
			int[] arr = new int[buffer.length * 2];
			for(int i = 0; i < buffer.length; i++)
			{
				arr[i] = buffer[i];
			}
			buffer = arr;
		}
		
		void push(int n)
		{
			if(size == buffer.length) resize();
			buffer[size] = n;
			size++;
		}
		
		int pop()
		{
			return buffer[size--];
		}
	}
	/*P16.24 Complete the implementation of a queue in Section 16.3.3, using a sequence of nodes
	for storing the elements.*/
	class QueueLinkedList
	{
		Node first;
		Node last;
		int size;
		
		class Node
		{
			int data;
			Node next;
		}
		//Elements added to end and removed from front best because removing last element would require setting previous next node to null.
		void add(int data)
		{
			Node newNode = new Node();
			newNode.data = data;
			if(first != null)
			{
				last.next = newNode;
				last = newNode;
			} else
			{
				first = newNode;
				last = newNode;
			}
			size++;
		}
		
		int remove()
		{
			int data = 0;
			if(first == null)
			{
				System.out.println("Element DNE. Returning 0.");
			}
			else
			{
				data = first.data;
				first = first.next;
				size--;
			}
			return data;
		}
	}
	
	/*
	P16.30 Implement the hash set in Section 16.4, using the “MAD (multiply-add-divide)
	method” for hash code compression. For that method, you choose a prime number p
	larger than the length L of the hash table and two values a and b between 1 and p – 1.
	Then reduce h to ((a h + b) % p) % L.*/
	class HashSet
	{
		
		int size, p, a, b, reducedHash;
		
		HashSet(int size) throws FileNotFoundException
		{
			Collections c = new Collections();
			this.size = size;
			
			for(Iterator<Integer> it = c.seiveSet(size * 2).iterator(); it.hasNext();)
			{
				int n = it.next();
				if(n > size)
				{
					p = n;
					a = p / 2;
					b = a / 2;
				}
			}
		}
		
		int reducedHashCode(int hash)
		{
			return ((a * hash + b) % p) % size;
		}
		
	}
	/*
	P16.31 Add methods to count collisions to the hash set in Section 16.4 and the one in
	Exercise P16.30. Insert all words from a dictionary (which you can find in /usr/
	share/dict/words on a UNIX or Linux computer) into both hash set implementations.
	Does the MAD method reduce collisions? (Use a table size that equals the number
	of words in the file. Choose p to be the next prime greater than L, a = 3, and b = 5.)*/
}