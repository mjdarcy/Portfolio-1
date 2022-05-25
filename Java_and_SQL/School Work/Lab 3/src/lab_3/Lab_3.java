package lab_3;

import java.util.ArrayList;

public class Lab_3 {
	
	public static class ElectricCarList {

	   ArrayList<String> carModels = new ArrayList<>(); 
	   ArrayList<ElectricCar> carList = new ArrayList<>();
	   
		public void addCars(String[][] str){
			for(int i = 0; i < str.length; i++) {
					carList.add(new ElectricCar(
							str[i][0],
							Double.parseDouble(str[i][1]),
							Integer.parseInt(str[i][2]),
							Double.parseDouble(str[i][3]),
							Integer.parseInt(str[i][4])
							));
			}
		}

		public void printList(){
			for(ElectricCar c : carList) {
				System.out.println(c.toString());
			}
		}
		
	    class ElectricCar {
	    	
	        String modelName;
	        double mfgPrice;
	        int maxRange;
	        double usNewsRating;  // 1.0-10.0
	        int rank;
	        	        
			public ElectricCar(String modelName, double mfgPrice, int maxRange, double usNewsRating, int rank) {
				super();
				this.modelName = modelName;
				this.mfgPrice = mfgPrice;
				this.maxRange = maxRange;
				this.usNewsRating = usNewsRating;
				this.rank = rank;
			}

			@Override
			public String toString() {
				return 
						"Model Name: " + modelName +
						"\nMFG Price: " + mfgPrice +
						"\nMax Range: " + maxRange +
						"\nUS News Rating: " + usNewsRating +
						"\nRank: " + rank +
						"\n";
			}

			public String getModelName() {
				return modelName;
			}
			public void setModelName(String modelName) {
				this.modelName = modelName;
			}
			public double getMfgPrice() {
				return mfgPrice;
			}
			public void setMfgPrice(double mfgPrice) {
				this.mfgPrice = mfgPrice;
			}
			public int getMaxRange() {
				return maxRange;
			}
			public void setMaxRange(int maxRange) {
				this.maxRange = maxRange;
			}
			public double getUsNewsRating() {
				return usNewsRating;
			}
			public void setUsNewsRating(double usNewsRating) {
				this.usNewsRating = usNewsRating;
			}
			public int getRank() {
				return rank;
			}
			public void setRank(int rank) {
				this.rank = rank;
			}
	   }
	}

	public static void main(String[] args) {
		
		String[][] rawData = {
				{"2021 Mini Cooper SE", "29900", "110", "0", "9"},
				{"2021 BMW i3", "44450", "153", "7.2", "8"}, 
				{"2021 Nissan Leaf", "31620", "226", "7.8", "7"},
				{"2021 Hyundai Ioniq Electric", "33245", "170", "8", "6"},
				{"2021 Chevrolet Bolt", "36500", "259", "8.1", "5"},
				{"2021 Porsche Taycan", "79900", "227", "8.7", "4"},
				{"2021 Polestar 2", "59900", "233", "8.8", "3"},
				{"2021 Tesla Model 3", "37990", "353", "8.8", "2"},
				{"2021 Tesla Model S", "79990", "412", "8.9", "1"}
				};
		
		ElectricCarList e = new ElectricCarList();
		e.addCars(rawData);
		e.printList();
	}
}