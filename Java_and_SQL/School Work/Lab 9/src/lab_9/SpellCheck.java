package lab_9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
   This program checks which words in a file are not present in a dictionary.
*/
public class SpellCheck
{
   public static void main(String[] args) 
      throws FileNotFoundException
   {


      Set<String> dictionaryWords = readWordsHashSet("resources\\words.txt");
      dictionaryWords.addAll(readWordsHashSet("resources\\words2.txt"));
      
      Set<String> documentWords = readWordsHashSet("resources\\alice30.txt");


      for (String word : documentWords)
      {
         if (!dictionaryWords.contains(word))
         {
            System.out.println(word);
         }
      }
      
      StopWatch timer = new StopWatch();
      System.out.println("Starting Read ArrayList Test"); 
      long arrayListTime;
      timer.start();
      ArrayList<String>  words2 = readWordsArrayList("resources\\War-and-Peace.txt");
      timer.stop();
      arrayListTime = timer.getElapsedTime();
      timer.reset();
      System.out.println("Elapsed time for ArrayList "
      + arrayListTime + " milliseconds.  Number of words: " + words2.size());
      
      System.out.println("Starting Read HashSet Test"); 
      long hashSetTime;  
      timer.start();
      Set<String>  words = readWordsHashSet("resources\\War-and-Peace.txt");
      timer.stop();
      hashSetTime = timer.getElapsedTime();
      timer.reset();
      System.out.println("Elapsed time for HashSet "
      + hashSetTime + " milliseconds.  Number of words: " + words.size());
      
      System.out.println("Starting Read TreeSet Test"); 
      long treeSetTime;
      timer.start();
      TreeSet<String>  words3 = readWordsTreeSet("resources\\War-and-Peace.txt");
      timer.stop();
      treeSetTime = timer.getElapsedTime();
      timer.reset();
      System.out.println("Elapsed time for TreeSet "
      + treeSetTime + " milliseconds.  Number of words: " + words3.size());
   }

   /**
      Reads all words from a file.
      @param filename the name of the file
      @return a set with all lowercased words in the file. Here, a 
      word is a sequence of upper- and lowercase letters.
 * @throws FileNotFoundException 
   */
	public static ArrayList<String> readWordsArrayList(String filename) throws FileNotFoundException{
		  ArrayList<String> words = new ArrayList<>();
	      Scanner in = new Scanner(new File(filename));
	      in.useDelimiter("[^a-zA-Z]+");
	      while (in.hasNext())
	      {
	         words.add(in.next().toLowerCase());
	      }
	      return words;
		
	}
	
	public static TreeSet<String> readWordsTreeSet(String filename) throws FileNotFoundException {
		  TreeSet<String> words = new TreeSet<>();
	      Scanner in = new Scanner(new File(filename));
	      in.useDelimiter("[^a-zA-Z]+");
	      while (in.hasNext())
	      {
	         words.add(in.next().toLowerCase());
	      }
	      return words;
		
	}
   
   public static Set<String> readWordsHashSet(String filename)
      throws FileNotFoundException
   {
      Set<String> words = new HashSet<>();
      Scanner in = new Scanner(new File(filename));
      in.useDelimiter("[^a-zA-Z]+");
      while (in.hasNext())
      {
         words.add(in.next().toLowerCase());        
      }
      return words;
   }
}
