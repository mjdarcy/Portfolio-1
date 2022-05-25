package lab_2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Lab_2 {
	
	public static void main(String[] args) {
		
		//Print rands:
		
		int[] arr = new int[20];
		Random rand = new Random();
		int upperBound = 99;
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < arr.length; j++){
				if(i < 1){
					arr[j] = rand.nextInt(upperBound);
				}
				if(j != arr.length-1){
					System.out.print(arr[j] + ", ");
				} else{
					System.out.print(arr[j] + "\n");
				}
				
			}
			Arrays.sort(arr);
		}
		System.out.println();
	
		//Print strings:
		
		String[] strArr = {"Egypt", "Switzerland", "Argentina", "Spain", "Portugal", "Luxemburg"};
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < strArr.length; j++){
				if(j != strArr.length-1){
					System.out.print(strArr[j] + ", ");
				} else{
					System.out.print(strArr[j] + "\n");
				}
			}
			Arrays.sort(strArr);
		}
		System.out.println();
		
		//Print ArrayList rands:
		
		ArrayList<Integer> arr2 = new ArrayList<Integer>(20);
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 20; j++){
				if(i < 1){
					arr2.add(rand.nextInt(upperBound));
				}
				if(j != 19){
					System.out.print(arr2.get(j) + ", ");
				} else{
					System.out.print(arr2.get(j) + "\n");
				}
				
			}
			Collections.sort(arr2);
		}
		System.out.println();
		
		//Print ArrayList strings:
	
		ArrayList<String> strArr2 = new ArrayList<String>(Arrays.asList("Egypt", "Switzerland", "Argentina", "Spain", "Portugal", "Luxemburg"));
		
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < strArr2.size(); j++){
				if(j != strArr2.size()-1){
					System.out.print(strArr2.get(j) + ", ");
				} else{
					System.out.print(strArr2.get(j) + "\n");
				}
			}
			Collections.sort(strArr2);
		}
		System.out.println();
		
	}
}