package project_6;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Project_6 {  
	  
    //Represent a node of the doubly linked list  
  
    class Node {
        PhonebookData data;
        Node previous;
        Node next;
  
        public Node(PhonebookData data2) {  
            this.data = data2;  
        }  
    }  
    
    class PhonebookData implements Comparable<PhonebookData> {
        String name;
        String mobilePhone;

        public PhonebookData(String name, String mobilePhone) {
            this.name = name;
            this.mobilePhone = mobilePhone;
        }

        public String getName() {
            return name;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }


        public String toString() {
            return name + " " + mobilePhone;
        }

        public int compare(String str1, String str2) {

            int l1 = str1.length();
            int l2 = str2.length();
            int lmin = Math.min(l1, l2);

            for (int i = 0; i < lmin; i++) {
                int str1_ch = (int) str1.charAt(i);
                int str2_ch = (int) str2.charAt(i);

                if (str1_ch != str2_ch) {
                    return str1_ch - str2_ch;
                }
            }

            // Edge case for strings like
            // String 1="Geeks" and String 2="Geeksforgeeks"
            if (l1 != l2) {
                return l1 - l2;
            }

            // If none of the above conditions is true,
            // it implies both the strings are equal
            else {
                return 0;
            }
        }

        @Override
        public int compareTo(PhonebookData o) {
            PhonebookData pd = o;
            return  compare(this.name,pd.name);
        }
    }
  
    //Represent the head and tail of the doubly linked list  
    Node head, tail = null;  
  
    //addNode() will add a node to the list  
    public void addNode(PhonebookData data) {  
        //Create a new node  
        Node newNode = new Node(data);  
  
        //If list is empty  
        if(head == null) {  
            //Both head and tail will point to newNode  
            head = tail = newNode;  
            //head's previous will point to null  
            head.previous = null;  
            //tail's next will point to null, as it is the last node of the list  
            tail.next = null;  
        }  
        else {  
            //newNode will be added after tail such that tail's next will point to newNode  
            tail.next = newNode;  
            //newNode's previous will point to tail  
            newNode.previous = tail;  
            //newNode will become new tail  
            tail = newNode;  
            //As it is last node, tail's next will point to null  
            tail.next = null;  
        }  
    }  
    //display() will print out the nodes of the list  
    public void display() {  
        //Node current will point to head  
        Node current = head;  
        if(head == null) {  
            System.out.println("List is empty");  
            return;  
        }
        System.out.println("Nodes of doubly linked list: ");  
        while(current != null) {  
            //Prints each node by incrementing the pointer.  
  
            System.out.print(current.data + " \n");  
            current = current.next;  
        }  
    }
    
    public SortedSet<PhonebookData> search(String searchItem) {
    	SortedSet<PhonebookData> sortedSet = new TreeSet<PhonebookData>();
    	
    	Node current = this.head;
    	
    	while (current != null) {
    	     //Checks each node by incrementing the pointer.
    		String cleanName = searchItem.toLowerCase().replaceAll("[^a-z]", "");
    	    String cleanNumber = searchItem.replaceAll("\\D", "");
    		if ((current.data.name.toLowerCase().replaceAll("[^a-z]", "").contains(cleanName) && (cleanName != "")) || (current.data.mobilePhone.replaceAll("\\D", "").contains(cleanNumber) && (cleanNumber != ""))) {
    	    	sortedSet.add((PhonebookData) current.data);
    	    }
    	    current = current.next;
    	}
    	return sortedSet;
    }
    
    public SortedSet<PhonebookData> searchTailFirst(String searchItem) {
        SortedSet<PhonebookData> sortedSet = new TreeSet<PhonebookData>();
        Node current = tail;
        if (tail == null) {
            System.out.println("List is empty");
            return null;
        }
        //    System.out.println("Nodes of doubly linked list: ");
        while (current != null) {
            //Checks each node by incrementing the pointer.
    		String cleanName = searchItem.toLowerCase().replaceAll("[^a-z]", "");
    	    String cleanNumber = searchItem.replaceAll("\\D", "");
    		if ((current.data.name.toLowerCase().replaceAll("[^a-z]", "").contains(cleanName) && (cleanName != "")) || (current.data.mobilePhone.replaceAll("\\D", "").contains(cleanNumber) && (cleanNumber != ""))) {
    			sortedSet.add((PhonebookData) current.data);
    		}
    		current = current.previous;
        }
        return sortedSet;
    }
  
    public static void main(String[] args) {  
    	Project_6 p = new Project_6();
        p.driver(p);
    } 
    
    public void driver(Project_6 dList) {
        dList.addNode(new PhonebookData("Jack Sparrow", "555-736-1346"));
        dList.addNode(new PhonebookData("Hector Barbossa", "555-516-2846"));
        dList.addNode(new PhonebookData("Joshamee Gibbs", "555-357-8967"));
        dList.addNode(new PhonebookData("Will Turner", "555-123-2634"));
        dList.addNode(new PhonebookData("Elizabeth Swann", "555-622-7423"));
        dList.addNode(new PhonebookData("Beth Swann", "555-622-4756")); 
        //Displays the nodes present in the list
        dList.display();
        System.out.print("\nEnter search item (or q to quit):\n");
        
		Scanner scan = new Scanner(System.in);
        String searchItem = scan.nextLine();
        Boolean searchDir = true;
        while (!searchItem.equals("q")) {
        	SortedSet<PhonebookData> sortedSet;
        	
        	if(searchDir) {
        		System.out.print("Searching forwards...\n");
        		sortedSet = dList.search(searchItem);
        	} else {
        		System.out.print("Searching backwards...\n");
        		sortedSet = dList.searchTailFirst(searchItem);
        	}
        	searchDir = !searchDir;
        	
            if (sortedSet.size() != 0) {
                for (PhonebookData node : sortedSet) {
                    System.out.print(node.toString()+"\n");
                }
            } else {
                System.out.print("\nNo search results found...");
            }

            System.out.print("\nEnter search item (or q to quit):\n");
            searchItem = scan.nextLine();
        }
        scan.close();
    }
}