package bjlopractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class Helper {
	
	static int[] randArr(int size, int bounds)
	{
		int[] result = new int[size];
		Random r = new Random();
		
		for(int i = 0; i < result.length; i++)
		{
			result[i] = r.nextInt(bounds);
		}
		
		return result;
	}
	
	static String[] randArrString(int size, String[] options)
	{
		String[] result = new String[size];
		Random r = new Random();
		for(int i = 0; i < result.length; i++)
		{
			result[i] = options[r.nextInt(options.length)];
		}
		return result;
	}
	
	static<Item> LinkedList<Item> randLinkedList(int size, Item[] options)
	{
		LinkedList<Item> result = new LinkedList<Item>();
		Random r = new Random();
		for(int i = 0; i < size; i++)
		{
			result.add(options[r.nextInt(options.length)]);
		}
		return result;
	}
	
	static<Item> void printArr(Item[] arr)
	{
		for(Item n : arr)
		{
			System.out.print(n + " ");
		}
	} static<Item> void printArr(int[] arr) { printArr(Arrays.stream(arr).boxed().toArray(Integer[]::new)); }

	static<Item> void printArr(LinkedList<Item> ls)
	{
		printArr(ls.toArray());
	}

	static class Stopwatch
	{
		
		boolean running;
		int timeElapsed;
		double startTime;
		
		Stopwatch()
		{
			running = false;
			timeElapsed = 0;
		}
		
		void start()
		{
			startTime = System.currentTimeMillis();
		}
		
		void stop()
		{
			timeElapsed += System.currentTimeMillis() - startTime;
		}
		
		void restart()
		{
			timeElapsed = 0;
		}
	}
}