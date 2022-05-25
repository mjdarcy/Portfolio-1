package project_8;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Project_8 {

	Random r = new Random(System.currentTimeMillis());
	final int MIN_FLIGHT_SPACING = 10;
	double runwayIdlePercent = 0;
	
	class FlightList {
		LinkedList<Flight> q = new LinkedList<Flight>();
		ArrayList<Flight> pastFlights = new ArrayList<Flight>();
		ArrayList<Flight> cancelled = new ArrayList<Flight>();
		/*minuteInQueue represents the current minute it was added while in q,
		and the total minutes it was in q in pastFlights.*/
		boolean departing;
		
		public FlightList(boolean departing) {
			super();
			this.departing = departing;
		}

		int getAvgWaitTime() {
			double result = 0;
			for(Flight f : pastFlights) {
				result += f.minuteInQueue;
			}
			return (int) (result / pastFlights.size());
		}
		
		void addFlight(int id, int t) {
			q.add(new Flight(id, t));
		}
		
		int getSize() {
			return q.size();
		}
		
		int getPastFlightsSize() {
			return pastFlights.size();
		}
		
		int getCancelledSize() {
			return cancelled.size();
		}
		
		void removeFlight(int currentTime) {
			Flight rm = q.remove();
			rm.printRemove(this.departing, currentTime);
			pastFlights.add(rm);
		}

		public String toString() {
			String[] options = new String[2];
			if(!this.departing) {
				options[0] = "arriving";
				options[1] = "diverted";
			} else {
				options[0] = "departing";
				options[1] = "cancelled";
			}
			return "\nCurrent " + options[0] + ": " + this.getSize() + 
				   "\nTotal " + options[0] + ": " + this.getSize() + this.getPastFlightsSize() + 
					"\nAverage wait time: " + this.getAvgWaitTime()/60 + ":" + String.format("%02d",this.getAvgWaitTime() % 60) + 
					"\nNumber of " + options[1] + " " + options[0] + "s " + this.getCancelledSize();
		}

		void print() {
			System.out.println(this.toString());
		}
		
		void printFlights() {
			Object[] objs = this.q.toArray();
			Flight f;
			String result = "";
			
			for(int i = 0; i < objs.length; i++) {
				f = (Flight) objs[i];
				result += f.toString() + "\n";
			}
			System.out.println(result);
		}
		
	}
	
	class Flight{
		int id;
		int minuteInQueue;

		Flight(int id, int minuteInQueue) {
			super();
			this.id = id;
			this.minuteInQueue = minuteInQueue;
		}
		
		void setMinuteInQueue(int n) {
			this.minuteInQueue = n;
		}
		
		void printRemove(boolean departing, int time) {
			this.minuteInQueue = time - minuteInQueue;
			String str = (departing? "departure" : "arrival");
			System.out.println(
					"Flight " + str + ": Flight number " + this.id + " at " + time/60 + ":" + String.format("%02d",time % 60) + " hours"
					);
		}
		
	}

	public static void main(String[] args) {
		Project_8 p = new Project_8();
		p.driver();
	}
	
	void driver() {
		FlightList arriving = new FlightList(false);
		FlightList departing = new FlightList(true);
		int time = 0;
		double flights = 0;
		int id = 1;
		
		while(time < 1440) {

		flights += r.nextDouble();
		if(flights > 1) {
			boolean moreThanFive = (arriving.getSize() + departing.getSize() > 5);
			if(r.nextDouble() > 0.5) {
				if(moreThanFive) {
					arriving.cancelled.add(new Flight(id, time));
				} else {
					arriving.addFlight(id, time);
				}
			} else {
				if(moreThanFive) {
					departing.cancelled.add(new Flight(id, time));
				} else {
					departing.addFlight(id, time);
				}
			}
			flights--;
			id++;
		}

		if((time % 5 == 0) && (time > 10)) {
			if(arriving.getSize() > 0) {
				arriving.removeFlight(time);
			} else if(departing.getSize() > 0){
				departing.removeFlight(time);
			}
		}
		
		if(arriving.getSize() == 0 && departing.getSize() == 0) {
			runwayIdlePercent++;
		}
		time++;
		}
		
		arriving.print();
		departing.print();
		
		NumberFormat defaultFormat = NumberFormat.getPercentInstance();
		defaultFormat.setMinimumFractionDigits(2);
		
		System.out.println("\nRunway idle percent: " + defaultFormat.format((runwayIdlePercent / 1440)));
	}
	
	public int getPoissonRandom(double mean) {
		 // Math.RandomSeed(num)  // See a good way to seed your randomizer in the discussion above
		 Random r = new Random();
		 double L = Math.exp(-mean);
		 int x = 0;
		 double p = 1.0;
		 do {
		   p = p * r.nextDouble();
		   x++;
		 } while (p > L);
		 return x - 1;
		}
}