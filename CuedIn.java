import java.util.Scanner;
import java.util.ArrayList;

// Problem C: "Cued In"
public class CuedIn {
	
	Scanner scanner;
	int ballsLeft, maxScore;
	ArrayList<String> ballsList;

	public CuedIn() {
		scanner = new Scanner(System.in);
		ballsList = new ArrayList<String>();
	}
	
	/* calculates the maximum possible score a player can 
	   achieve based on the remaining balls on the table */
	public void maxPossibleScore() {
		
		// set ballsLeft int from input
		ballsLeft = scanner.nextInt();
		
		// add balls from input to list
		for(int i = 0; i < ballsLeft; i++) {
			ballsList.add(scanner.next());
		}
		
		// set max score to 0
		maxScore = 0;
		
		// if no red balls, just add scores of remaining balls
		if(!ballsList.contains("red")) {
			for(String ball : ballsList) {
				maxScore += score(ball);
			}
		}
		
		/* if there are red balls, the highest value ball can be potted before, 
		   between and after each red ball, then the remaining balls can be potted. */
		else {
			int numOfReds = numOfReds(ballsList);
			
			maxScore += numOfReds;
			maxScore += score(highest(ballsList))*(numOfReds+1);
			
			for(String ball : restOfList(ballsList)) {
				maxScore += score(ball);
			}
		}
		System.out.println(maxScore);
	}
	
	// return the score attributed to a given ball
	public int score(String ball) {
		if(ball.equals("red"))
			return 1;
		if(ball.equals("yellow"))
			return 2;
		if(ball.equals("green"))
			return 3;
		if(ball.equals("brown"))
			return 4;
		if(ball.equals("blue"))
			return 5;
		if(ball.equals("pink"))
			return 6;
        if(ball.equals("black"))
			return 7;
        
        return 0;
	}
	
	//return the number of red balls in a list of balls
	public int numOfReds(ArrayList<String> ballsList) {
		int reds = 0;
		
		for(String ball : ballsList) {
			if(ball.equals("red"))
				reds += 1;
		}
		return reds;
	}
	
	//return the highest value ball in a list of balls
	public String highest(ArrayList<String> ballsList) {
		if(ballsList.contains("black"))
		    return "black";
		if(ballsList.contains("pink"))
		    return "pink";
		if(ballsList.contains("blue"))
		    return "blue";
		if(ballsList.contains("brown"))
		    return "brown";
		if(ballsList.contains("green"))
		    return "green";
		if(ballsList.contains("yellow"))
		    return "yellow";
		return "none";
	}
	
	/* remove (one instance of) the highest value ball, and all red 
	   balls, from a list of balls, and return the modified list */
	public ArrayList<String> restOfList(ArrayList<String> ballsList) {
		
		// remove an instance o the highest value ball from balls list
		String highest = highest(ballsList);
		
		// remove all reds from balls list
		for(String ball : ballsList) {
			if(ball.equals("red"))
                ballsList.remove(ball);
		}
		return ballsList;
	}
}