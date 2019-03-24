import java.util.Scanner;
import java.util.ArrayList;

// Problem A: "Alien Sunset"
public class AlienSunset {
	
	Scanner scanner;
	int numOfSettlements, maxSolarDayLength;
	ArrayList<Planet> planets;
	boolean possible;

	public AlienSunset() {
		scanner = new Scanner(System.in);
		planets = new ArrayList<Planet>();
		maxSolarDayLength = 0;
	}
	
	/* representing a planet with a solar day length,
	   as well as sunrise and sunset times */
	public class Planet {
		int solarDayLength;
		int sunriseHour;
		int sunsetHour;
		
		public Planet(int solarDayLength, int sunriseHour, int sunsetHour) {
			this.solarDayLength = solarDayLength;
			this.sunriseHour = sunriseHour;
			this.sunsetHour = sunsetHour;
		}
		
		// getter for solar day length
		public int getSolarDayLength() {
			return solarDayLength;
		}
		
		// calculate if the planet is in daylight at a given hour
		public boolean isInDaylight(int hour) {
			while(hour > solarDayLength) {
				hour -= solarDayLength;
			}
			
			if(sunriseHour < sunsetHour) {
				if(hour > sunriseHour && hour < sunsetHour) {
					return true;
				}
			}
			
			if(sunriseHour > sunsetHour) {
				if(hour > sunriseHour || hour < sunsetHour) {
					return true;
				}
			}
			
			return false;
		}
	}
	
	/* calculate the number of hours (if possible) that must pass in
	   order for all of the planets to be in darkness for the event */
	public void numberOfHours() {
		
		numOfSettlements = scanner.nextInt();
		
		for(int i = 0; i < numOfSettlements; i++) {
			planets.add(new Planet(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
		}
		
		for(Planet p : planets) {
			if(p.getSolarDayLength() > maxSolarDayLength) {
				maxSolarDayLength = p.getSolarDayLength();
			}
		}
		
		for(int hour = 1; hour <= maxSolarDayLength*1825; hour++) {
			
			possible = true;
			
			for(Planet p : planets) {
				if(p.isInDaylight(hour)) {
					possible = false;
				}
			}
            
			if(possible) {
				System.out.println(hour);
				break;
			}
		}
		
		if(!possible) {
			System.out.println("impossible");
		}
	}
}