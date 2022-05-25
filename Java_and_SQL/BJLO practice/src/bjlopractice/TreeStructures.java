package bjlopractice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeStructures {
/*
P17.3 Write a method that counts the number of all leaves in a tree.*/
	TreeStructures()
	{
		Tree tree = new Tree(10);
		System.out.println("Tree size: " + tree.root.size());
		BinaryTree bTree = new BinaryTree();
		System.out.println("Binary tree size: " + bTree.countNodesWithOneChild() + 
							"\nSwapped children." +
							"\nAnimal guessing game: ");
		//new AnimalGuessingGame();
		BinarySearchTree bst = new BinarySearchTree();
		System.out.println("Average of BST: " + bst.totalAverage());
	}
	
	class Tree
	{
		
		Node root;
		
		Tree(int data)
		{
			root = new Node();
			root.data = data;
			root.children = new ArrayList<Node>();

			root.children.add(new Node());
			root.children.add(new Node());
			root.children.get(0).children.add(new Node());
			root.children.get(0).children.add(new Node());
			
			System.out.println(root.size());
			System.out.println(root.leaves());
		}
		
		class Node
		{
			int data;
			List<Node> children = new ArrayList<Node>();
			
			int size()
			{
				int sum = 0;
				for(Node child : children) { sum += child.size(); };
				return sum + 1;
			}
			
			int leaves()
			{
				int sum = 0;
				for(Node child : children)
				{
					if(child.children.size() == 0) sum++;
					else sum += child.leaves();
				}
				return sum;
			}
		}
		
		void addSubTree(Tree subTree)
		{
			root.children.add(subTree.root);
		}
		
	}

	class BinaryTree
	{
		
		Node root;
		
		class Node
		{
			Object data;
			Node left;
			Node right;
			
			Node(Integer n)
			{
				this.data = n;
			}
			
			int height(Node node)
			{
				if(node == null) return 0;
				return 1 + Math.max(height(node.left), height(node.right));
			}
			
			/*
			P17.4 Add a method countNodesWithOneChild to the BinaryTree class.*/
			int countNodesWithOneChild(Node node)
			{
				int sum = 0;
				int count = 0;
				if(node.left != null)
				{
					count++;
					sum += countNodesWithOneChild(node.left);
				}
				if(node.right != null)
				{
					count++;
					sum += countNodesWithOneChild(node.right);
				}
				if(count == 1) sum++;
				return sum;
			}
			/*
			P17.5 Add a method swapChildren that swaps all left and right children to the BinaryTree
			class.*/
			void swapChildren(Node n)
			{
				if(n == null) return;
				Node oldLeft = n.left;
				n.left = n.right;
				n.right = oldLeft;
				swapChildren(n.left);
				swapChildren(n.left);
			}
		}
		
		
		BinaryTree()
		{
			root = new Node(1);
			root.left = new Node(2);
			root.left.left = new Node(3);
			root.right = new Node(4);
			root.right.left = new Node(5);
			root.right.left.left = new Node(6);
			root.right.left.right = new Node(7);
			root.right.left.right.right = new Node(8);
			root.right.left.right.right.right = new Node(9);
			/*
				O
			  O   O
			O    O 
			 	O O
			 	   O
			 	    O
			 */
		}
		
		int getHeight()
		{
			return root.height(root);
		}
		
		int countNodesWithOneChild()
		{
			return root.countNodesWithOneChild(root);
		}
		
		void swapChildren()
		{
			root.swapChildren(root);
		}
	}
	
	/*
	P17.6 Implement the animal guessing game described in Section 17.2.1. Start with the tree
	in Figure 4, but present the leaves as “Is it a(n) X?” If it wasn’t, ask the user what the
	animal was, and ask for a question that is true for that animal but false for X. For
	example,
	Is it a mammal? Y
	Does it have stripes? N
	Is it a pig? N
	I give up. What is it? A hamster
	Please give me a question that is true for a hamster and false for a pig.
	Is it small and cuddly?
	In this way, the program learns additional facts.*/
	class AnimalGuessingGame
	{
		Node root;
		Scanner ask;
		class Node
		{
			String quality;
			Node left;
			Node right;
			Node(String quality)
			{
				this.quality = quality;
			}
		}
		AnimalGuessingGame()
		{
			root = new Node("Is it a mammal?");
			root.left = new Node("Does it have stripes?");
			root.left.left = new Node("Is it a carnivore?");
			root.left.left.left = new Node("It is a tiger.");
			root.left.left.right = new Node("It is a zebra.");
			root.left.right = new Node("It is a pig.");
			root.right = new Node("Does it fly?");
			root.right.left = new Node("It is an eagle.");
			root.right.right = new Node("Does it swim?");
			root.right.right.left = new Node("It is a penguin.");
			root.right.right.right = new Node("It is an ostrich.");
			ask = new Scanner(System.in);
			identify(root);
		}
		
		void identify(Node n)
		{
			String input;
			//other shorter conditions would work too. If there's a left there's a right.
			while(n.left != null && n.right != null)
			{
				System.out.println(n.quality);
				input = ask.nextLine();
				if(input.equals("yes")) n = n.left;
				else n = n.right;
			}
			System.out.println(n.quality);
		}
	}
	
	/*
	P17.10 Write a method of the BinarySearchTree class
	Comparable smallest()
	that returns the smallest element of a tree. You will also need to add a method to the
	Node class.*/
	class BinarySearchTree
	{
		Node root;
		
		class Node
		{
			Integer data;
			Node left;
			Node right;
			Node(int data)
			{
				this.data = data;
			}
			
			Comparable<Integer> smallest(Node curr)
			{
				if(curr.left == null) { return curr.data; }
				return smallest(curr.left);
			}
			
			int total(Node curr)
			{
				int total = 0;
				if(curr == null) return 0;
				total += total(curr.left);
				total += total(curr.right);
				return total + curr.data;
			}
			
			int size(Node curr)
			{
				int size = 0;
				if(curr == null) return 0;
				size += size(curr.left);
				size += size(curr.right);
				return size + 1;
			}
			/*
			P17.16 Using a visitor, compute the average value of the elements in a binary tree filled with
			Integer objects.*/
			int totalAverage(Node curr, Counter v)
			{
				int total = 0;
				if(curr == null) return 0;
				v.visit(v);
				total += totalAverage(curr.left, v);
				total += totalAverage(curr.right, v);
				if(curr == root) return (total + curr.data) / v.counter;
				return total + curr.data;
			}
		}
		
		class Counter
		{
			int counter = 0;
			void visit(Object data)
			{
				//This isn't needed since all nodes are counted and initialized with data, but I'd like to see what a conditional here would look like.
				if(data != null) counter++;
			}
		}
		
		BinarySearchTree()
		{
			root = new Node(10);
			root.left = new Node(5);
			root.right = new Node(15);
			root.left.left = new Node(0);
			root.left.right = new Node(7);
		}
		
		Comparable<Integer> smallest()
		{
			return root.smallest(root);
		}
		
		int totalAverage()
		{
			Counter v = new Counter();
			int result = root.totalAverage(root, v);
			return result;
		}
	}
	/*
	P17.13 Write a method for the RedBlackTree class of Worked Example 17.2 that checks that
	the tree fulfills the rules for a red-black tree.*/
	class RedBlackTree
	{
		
	}
	
	/*
	P17.22 Modify the implementation of the MinHeap class so that the parent and child index
	positions and elements are computed directly, without calling helper methods.*/
	
	/*
	P17.24 Time the results of heapsort and merge sort. Which algorithm behaves better in
	practice?*/
}