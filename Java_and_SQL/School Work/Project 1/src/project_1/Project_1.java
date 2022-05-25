package project_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Project_1{
	
	private static class Flight{
		
		String flightNumber;
		String airline;
		String origin;
		String destination;
		AircraftType aircraftType;
		Date scheduledDeparture;
		Date scheduledArrival;
		String gate;
		
		public Flight(String flightNumber, String airline, String origin,
					String destination, AircraftType aircraftType,
					Date scheduledDeparture, Date scheduledArrival, String gate){
			super();
			this.flightNumber = flightNumber;
			this.airline = airline;
			this.origin = origin;
			this.destination = destination;
			this.aircraftType = aircraftType;
			this.scheduledDeparture = scheduledDeparture;
			this.scheduledArrival = scheduledArrival;
			this.gate = gate;
		}

		@Override
		public String toString() {
			return airline+" "+flightNumber+" "+origin+" to "+destination+" | Departs: "+scheduledDeparture+" | Arrives: "+scheduledArrival + " | "+aircraftType;
		}

		public String getFlightNumber() {
			return flightNumber;
		}

		public void setFlightNumber(String flightNumber) {
			this.flightNumber = flightNumber;
		}

		public String getAirline() {
			return airline;
		}

		public void setAirline(String airline) {
			this.airline = airline;
		}

		public String getOrigin() {
			return origin;
		}

		public void setOrigin(String origin) {
			this.origin = origin;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public AircraftType getAircraftType() {
			return aircraftType;
		}

		public void setAircraftType(AircraftType aircraftType) {
			this.aircraftType = aircraftType;
		}

		public Date getScheduledDeparture() {
			return scheduledDeparture;
		}

		public void setScheduledDeparture(Date scheduledDeparture) {
			this.scheduledDeparture = scheduledDeparture;
		}

		public Date getScheduledArrival() {
			return scheduledArrival;
		}

		public void setScheduledArrival(Date scheduledArrival) {
			this.scheduledArrival = scheduledArrival;
		}

		public String getGate() {
			return gate;
		}

		public void setGate(String gate) {
			this.gate = gate;
		}
		
	}
	
	private static class AircraftType{
		
		String manufacturer;
		String model;
		String typeDesignator;
		EngineType engineType;
		int engineNumber;
		
		public AircraftType(String manufacturer, String model, String typeDesignator,
				EngineType engineType, int engineNumber) {
			super();
			this.manufacturer = manufacturer;
			this.model = model;
			this.typeDesignator = typeDesignator;
			this.engineType = engineType;
			this.engineNumber = engineNumber;
		}

		@Override
		public String toString() {
			return manufacturer+"-"+model+"-"+typeDesignator+", " + engineType + "-" + engineNumber;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getTypeDesignator() {
			return typeDesignator;
		}

		public void setTypeDesignator(String typeDesignator) {
			this.typeDesignator = typeDesignator;
		}

		public EngineType getEngineType() {
			return engineType;
		}

		public void setEngineType(EngineType engineType) {
			this.engineType = engineType;
		}

		public int getEngineNumber() {
			return engineNumber;
		}

		public void setEngineNumber(int engineNumber) {
			this.engineNumber = engineNumber;
		}
		
	}
	
	private static enum EngineType{
		Jet, Rocket, Electric, TurboProp, Piston;
	
	}

	public static void main(String[] args){
		
		String[][] rawData = {
				{"100", "AA", "PHL","ORD",
				"Boeing, 456, F392, 0, 1",
				"12-10-2020 08:35", "12-11-2020 11:35", "D11"},
				{"235", "UA", "PHL","SFO",
				"Airbus, 732, G391, 1, 1",
				"12-10-2020 09:25", "12-10-2020 16:00", "C32"},
				{"333", "DL", "ARL","CDG",
				"Embraer, 527, C392, 2, 1",
				"12-10-2020 10:15", "12-11-2020 01:50", "A20"},
				{"476", "WN", "PHL","LAX",
				"Bombardier, 175, R776, 3, 1",
				"12-10-2020 07:30", "12-11-2020 01:35", "B43"},
				{"522", "AA", "MCO","FLL",
				"Comac, 274, T493, 4, 1",
				"12-10-2020 08:00", "12-10-2020 10:10", "C12"},
		};
		
		ArrayList<Flight> arr = new ArrayList<Flight>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm");
		Date dateDepart = null;
		Date dateArrive = null;
		String[] typeInfo;
		EngineType[] type = {EngineType.Jet, EngineType.Rocket, EngineType.Electric, EngineType.TurboProp, EngineType.Piston};
		AircraftType at = null;
		
		for(int i = 0; i < rawData.length; i++) {
				try {
					typeInfo = rawData[i][4].split(", ");
					at = new AircraftType(typeInfo[0], typeInfo[1], typeInfo[2], type[Integer.parseInt(typeInfo[3])], Integer.parseInt(typeInfo[4]));
					dateDepart = sdf.parse(rawData[i][5]);
					dateArrive = sdf.parse(rawData[i][6]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				arr.add(new Flight(
						rawData[i][0], rawData[i][1], rawData[i][2],
						rawData[i][3], at, dateDepart,
						dateArrive, rawData[i][7]));
				System.out.println(arr.get(i).toString());
		}
	}
}