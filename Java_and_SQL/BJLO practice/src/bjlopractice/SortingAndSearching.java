package bjlopractice;

import java.util.ArrayList;
import java.util.Scanner;

import bjlopractice.Helper.Stopwatch;

public class SortingAndSearching {
	//P14.1 Modify the selection sort algorithm to sort an array of integers in descending order.
	
	SortingAndSearching()
	{
		int[] selectDescInt = Helper.randArr(10, 100);
		String[] selectDescCoin = Helper.randArrString(10, new String[] {"quarter", "dime", "nickel", "penny"});
		
		selectDesc(selectDescInt);
		System.out.print("Select sort descending: ");
		Helper.printArr(selectDescInt);
		
		selectDesc(selectDescCoin);
		System.out.print("\nSelect sort coin descending: ");
		Helper.printArr(selectDescCoin);
		
		System.out.print("\nSelect Sort runtimes: ");
		int[] runtimeTable = selectSortTrials(10, 20000);
		Helper.printArr(runtimeTable);
		
		System.out.print("\nSelect Sort coin runtimes: ");
		int[] runtimeTableCoin = selectSortStringTrials(10, 20000);
		Helper.printArr(runtimeTableCoin);
		
		System.out.print("\nMerge sort: ");
		String[] mergesortStr = Helper.randArrString(10, new String[] {"apple", "banana", "pear", "orange"});
		MergeSorter.sort(mergesortStr);
		Helper.printArr(mergesortStr);
		
		System.out.print("\nMerge sort loop: ");
		int[] loopArr = Helper.randArr(8, 100);
		MergeSorter.sortLoop(loopArr);
		Helper.printArr(loopArr);
		
		System.out.print("\nBinary search:  " + binarySearch(loopArr, 11));
		
		System.out.print("\nMerge sort countries: ");
		Country[] countries = new Country[] {new Country("b", 2), new Country("a", 1), new Country("c", 3), new Country("d", 4)};
		MergeSorter.sortLoop(countries);
		for(Country c : countries) { System.out.print(c.name + " "); }
		
		/*
		Scanner scan = new Scanner(System.in);
		Person[] people = new Person[8];
		for(int i = 0; i < people.length; i++)
		{
			people[i] = new Person(scan.nextLine());
		}
		for(Person p : people) { System.out.print(p.name + " "); }
		MergeSorter.sortLoop(people);
		for(Person p : people) { System.out.print(p.name + " "); }
		scan.close();
		*/
		
		System.out.print("\nString length comparator: ");
		ArrayList<String> arrList = new ArrayList<String>();
		for(String str : mergesortStr) { arrList.add(str); }
		//MergeSorter.sortArrayList(arrList);
		for(String str : arrList) { System.out.print(str + " "); }
	}
	
	void selectDesc(int[] selectDescInt)
	{
		for(int i = 0; i < selectDescInt.length; i++)
		{
			int indexOfLargest = i;
			for(int j = i + 1; j < selectDescInt.length; j++)
			{
				//find index of lowest value from i
				if(selectDescInt[j] > selectDescInt[indexOfLargest]) { indexOfLargest = j; }
			}
			//swap
			int temp = selectDescInt[i];
			selectDescInt[i] = selectDescInt[indexOfLargest];
			selectDescInt[indexOfLargest] = temp;
		}
	}
	//P14.2 Modify the selection sort algorithm to sort an array of coins by their value.
	
	void selectDesc(String[] arr)
	{
		//could have made my own compareTo function here.
		int[] coinToInt = new int[arr.length];
		
		for(int i = 0; i < coinToInt.length; i++)
		{
			if(arr[i].equals("quarter"))
			{
				coinToInt[i] = 4;
			} else if(arr[i].equals("dime"))
			{
				coinToInt[i] = 3;
			} else if(arr[i].equals("nickel"))
			{
				coinToInt[i] = 2;
			} else if(arr[i].equals("penny"))
			{
				coinToInt[i] = 1;
			}
		}
		
		selectDesc(coinToInt);
		
		for(int i = 0; i < coinToInt.length; i++)
		{
			if(coinToInt[i] == 4)
			{
				arr[i] = "quarter";
			} else if(coinToInt[i] == 3)
			{
				arr[i] = "dime";
			} else if(coinToInt[i] == 2)
			{
				arr[i] = "nickel";
			} else if(coinToInt[i] == 1)
			{
				arr[i] = "penny";
			}
		}
	}
	
	/*
	P14.3 Write a program that automatically generates the table of sample run times for the
	selection sort algorithm. The program should ask for the smallest and largest value
	of n and the number of measurements and then make all sample runs.*/
	int[] selectSortTrials(int trials, int n)
	{
		Stopwatch timer = new Stopwatch();
		int[] runtimeTable = new int[trials];
		int difference = n / trials;
		
		while(trials-- > 1)
		{
			int[] arr = Helper.randArr(trials * difference, 100);
			
			timer.start();
			selectDesc(arr);
			timer.stop();
			runtimeTable[trials] = timer.timeElapsed;
			timer.restart();
		}
		
		return runtimeTable;
	}
	
	/*
	P14.6 Implement a program that measures the performance of the insertion sort algorithm
	described in Special Topic 14.2.*/
	int[] selectSortStringTrials(int trials, int n)
	{
		Stopwatch timer = new Stopwatch();
		int[] runtimeTable = new int[trials];
		int difference = n / trials;
		
		while(trials-- > 1)
		{
			String[] arr = Helper.randArrString(trials * difference, new String[] {"quarter", "dime", "nickel", "penny"});
			
			timer.start();
			selectDesc(arr);
			timer.stop();
			runtimeTable[trials] = timer.timeElapsed;
			timer.restart();
		}
		
		return runtimeTable;
	}
	/*
	P14.4 Modify the merge sort algorithm to sort an array of strings in lexicographic order.*/
	class MergeSorter
	{
		//Obviously could have made this class a bit cleaner. Using generics would have helped, but I ran into an issue involving generics and static methods.
		static void sort(String[] a)
		{
			if(a.length <= 1) { return; }
			String[] first = new String[a.length / 2];
			String[] second = new String[a.length - first.length];
			int i = 0;
			while(i < first.length) { first[i] = a[i]; i++; }
			while(i < a.length) { second[i - first.length] = a[i]; i++; }

			sort(first);
			sort(second);
			mergeStr(first, second, a);
		}
		
		private static void mergeStr(String[] first, String[] second, String[] a)
		{
			int iFirst = 0;
			int iSecond = 0;
			int i = 0;
			while(iFirst < first.length && iSecond < second.length)
			{
				if(first[iFirst].compareTo(second[iSecond]) > 0)
				{
					a[i] = first[iFirst];
					iFirst++;
				} else
				{
					a[i] = second[iSecond];
					iSecond++;
				}
				i++;
			}
			while(iFirst < first.length)
			{
				a[i] = first[iFirst];
				iFirst++; i++;
			} while(iSecond < second.length)
			{
				a[i] = second[iSecond];
				iSecond++; i++;
			}
		}
		
		private static void mergeInt(int[] first, int[] second, int[] a)
		{
			int iFirst = 0;
			int iSecond = 0;
			int i = 0;
			while(iFirst < first.length && iSecond < second.length)
			{
				if(first[iFirst] < second[iSecond])
				{
					a[i] = first[iFirst];
					iFirst++;
				} else
				{
					a[i] = second[iSecond];
					iSecond++;
				}
				i++;
			}
			while(iFirst < first.length)
			{
				a[i] = first[iFirst];
				iFirst++; i++;
			} while(iSecond < second.length)
			{
				a[i] = second[iSecond];
				iSecond++; i++;
			}
		}
		
		private static void mergeCountry(Country[] first, Country[] second, Country[] a)
		{
			int iFirst = 0;
			int iSecond = 0;
			int i = 0;
			while(iFirst < first.length && iSecond < second.length)
			{
				if(first[iFirst].compareTo(second[iSecond]) < 0)
				{
					a[i] = first[iFirst];
					iFirst++;
				} else
				{
					a[i] = second[iSecond];
					iSecond++;
				}
				i++;
			}
			while(iFirst < first.length)
			{
				a[i] = first[iFirst];
				iFirst++; i++;
			} while(iSecond < second.length)
			{
				a[i] = second[iSecond];
				iSecond++; i++;
			}
		}
		
		private static void mergePerson(Person[] first, Person[] second, Person[] a)
		{
			int iFirst = 0;
			int iSecond = 0;
			int i = 0;
			while(iFirst < first.length && iSecond < second.length)
			{
				if(first[iFirst].compareTo(second[iSecond]) < 0)
				{
					a[i] = first[iFirst];
					iFirst++;
				} else
				{
					a[i] = second[iSecond];
					iSecond++;
				}
				i++;
			}
			while(iFirst < first.length)
			{
				a[i] = first[iFirst];
				iFirst++; i++;
			} while(iSecond < second.length)
			{
				a[i] = second[iSecond];
				iSecond++; i++;
			}
		}
		/*
		P14.16 Implement the sort method of the merge sort algorithm without recursion, where
		the length of the array is a power of 2. First merge adjacent regions of size 1, then
		adjacent regions of size 2, then adjacent regions of size 4, and so on.*/
		static void sortLoop(int[] arr)
		{
			//doesn't work with odd numbers.
			int[][] temp = new int[arr.length][1];
			for(int i = 0; i < arr.length; i++) { temp[i][0] = arr[i]; };
			int[][] result = new int[arr.length / 2][2];
			
			for(int i = 0; i < Math.log(arr.length); i++) 
			{
				result = new int[temp.length / 2][temp[0].length * 2];
				for(int j = 0; j < temp.length - 1; j += 2)
				{
					mergeInt(temp[j], temp[j + 1], result[j / 2]);
				}
				System.arraycopy(result, 0, temp, 0, result.length);
			}
				
			System.arraycopy(result[0], 0, arr, 0, arr.length);
		}
		
		static void sortLoop(Country[] arr)
		{
			//doesn't work with odd numbers.
			Country[][] temp = new Country[arr.length][1];
			for(int i = 0; i < arr.length; i++) { temp[i][0] = arr[i]; };
			Country[][] result = new Country[arr.length / 2][2];
			
			for(int i = 0; i < Math.log(arr.length); i++) 
			{
				result = new Country[temp.length / 2][temp[0].length * 2];
				for(int j = 0; j < temp.length - 1; j += 2)
				{
					mergeCountry(temp[j], temp[j + 1], result[j / 2]);
				}
				System.arraycopy(result, 0, temp, 0, result.length);
			}
				
			System.arraycopy(result[0], 0, arr, 0, arr.length);
		}
		
		static void sortLoop(Person[] arr)
		{
			//doesn't work with odd numbers.
			Person[][] temp = new Person[arr.length][1];
			for(int i = 0; i < arr.length; i++) { temp[i][0] = arr[i]; };
			Person[][] result = new Person[arr.length / 2][2];
			
			for(int i = 0; i < Math.log(arr.length); i++) 
			{
				result = new Person[temp.length / 2][temp[0].length * 2];
				for(int j = 0; j < temp.length - 1; j += 2)
				{
					mergePerson(temp[j], temp[j + 1], result[j / 2]);
				}
				System.arraycopy(result, 0, temp, 0, result.length);
			}
				
			System.arraycopy(result[0], 0, arr, 0, arr.length);
		}
		
		static void sortArrayList(ArrayList<String> a)
		{
			if(a.size() <= 1) { return; }
			ArrayList<String>  first = new ArrayList<String> (a.size() / 2);
			ArrayList<String>  second = new ArrayList<String> (a.size() - a.size());
			int i = 0;
			while(i < first.size()) { first.set(i, a.get(i)); i++; }
			while(i < a.size()) { second.set(i - first.size()-1, a.get(i)); i++; }

			sortArrayList(first);
			sortArrayList(second);
			mergeArrayList(first, second, a);
		}
		
		private static void mergeArrayList(ArrayList<String>  first, ArrayList<String>  second, ArrayList<String> a)
		{
			int iFirst = 0;
			int iSecond = 0;
			int i = 0;
			while(iFirst < first.size() && iSecond < second.size())
			{
				if(first.get(iFirst).length() > second.get(iSecond).length())
				{
					a.set(i, first.get(iFirst));
					iFirst++;
				} else
				{
					a.set(i, second.get(iSecond));
					iSecond++;
				}
				i++;
			}
			while(iFirst < first.size())
			{
				a.set(i, first.get(iFirst));
				iFirst++; i++;
			} while(iSecond < second.size())
			{
				a.set(i, second.get(iSecond));
				iSecond++; i++;
			}
		}
	}
	
	/*
	P14.15 Consider the binary search algorithm in Section 14.8. If no match is found, the search
	method returns -1. Modify the method so that if a is not found, the method returns
	-k - 1, where k is the position before which the element should be inserted. (This is
	the same behavior as Arrays.binarySearch.)*/
	int	binarySearch(int[] arr, int element)
	{
		int low = 0;
		int high = arr.length - 1;
		int i = low + high / 2;
		
		while(low <= high )
		{
			if(arr[i] == element) return i;
			if(arr[i] < element)
			{
				low = ++i;
			} else
			{
				high = --i;
			}
			i = (low + high) / 2;
		}
		return low;
	}
	/*
	P14.18 Use insertion sort and the binary search from Exercise P14.15 to sort an array
	as described in Exercise R14.18. Implement this algorithm and measure its
	performance.*/

	
	/*
	P14.8 Implement the algorithm described in Section 14.7.4, but only remember the value
	with the highest frequency so far:
	int mostFrequent = 0;
	int highestFrequency = -1;
	for (int i = 0; i < a.length; i++)
	Count how often a[i] occurs in a[i + 1]...a[n - 1]
	If it occurs more often than highestFrequency
	highestFrequency = that count
	mostFrequent = a[i]*/
	
	/*
	P14.14 Write a program that sorts an ArrayList<Country> in decreasing order so that the most
	largest country is at the beginning of the array. Use a Comparator.*/
	class Country implements Comparable<Country>
	{
		String name;
		int size;
	
		Country(String name, int size)
		{
			this.name = name;
			this.size = size;
		}
		
		@Override
		public int compareTo(Country o) {
			//shouldn't be a need for returning 0 when sorting.
			return this.size > o.size ? 1 : -1;
		}
		
	}
	
	/*
	P14.19 Supply a class Person that implements the Comparable interface. Compare persons by
	their names. Ask the user to input ten names and generate ten Person objects. Using
	the compareTo method, determine and the first and last person among them and print
	them.*/
	class Person implements Comparable<Person>
	{
		String name;
		
		Person(String name)
		{
			this.name = name;
		}
		
		public int compareTo(Person p)
		{
			return name.compareTo(p.name);
		}
	}
	
	/*
	P14.20 Sort an array list of strings by increasing length. Hint: Supply a Comparator.*/
	//I compared them directly, but that code could be pasted into a comparator method. this.compare(String a, String b).
}