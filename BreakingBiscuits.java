import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point;

// Problem B: "Breaking Biscuits"
public class BreakingBiscuits {
	
	Scanner scanner;
	int numOfVertices;
	double minDistance, greatestDistance, minMugDiameter;
	Point edgeMidpoint;
	ArrayList<Point> vertexList, pointsOnEdge;
	ArrayList<Edge> edgeList;
	ArrayList<Double> distanceList;

	public BreakingBiscuits() {
		scanner = new Scanner(System.in);
		vertexList = new ArrayList<Point>();
		pointsOnEdge = new ArrayList<Point>();
		edgeList = new ArrayList<Edge>();
		distanceList = new ArrayList<Double>();
	}
	
	// represents an edge between two vertices
	public class Edge {
		
		// edge endpoints 
		Point a, b;
		
		public Edge(Point a, Point b) {
			this.a = a;
			this.b = b;
		}
		
		// endpoint 1 getter
		public Point getA() {
			return a;
		}
		
		// endpoint 2 getter
		public Point getB() {
			return b;
		}
		
		// midpoint getter
		public Point getMidPoint() {
			double x = (a.getX() + b.getX())/2;
			double y = (a.getY() + b.getY())/2;
			
			return new Point((int)x, (int)y);
		}
	}
	
	// calculate the minimum mug diameter required for the input biscuit
	public void minMugDiameter() {
		
		// set numOfVertices int from input
		numOfVertices = scanner.nextInt();
		
		// create vertices from input and add to vertexList
		for(int i = 0; i < numOfVertices; i++) {
			vertexList.add(new Point(scanner.nextInt(), scanner.nextInt()));
		}

		// create edges from vertices and add to edgeList
		for(int i = 0; i < numOfVertices; i++) {
			if(i+1 == numOfVertices) {
				edgeList.add(new Edge(vertexList.get(i), vertexList.get(0)));
			}
			else edgeList.add(new Edge(vertexList.get(i), vertexList.get(i+1)));
		}
		
		// for each edge, find greatest distance between it and a vertex, and add this to distanceList
		for(Edge e : edgeList) {
			greatestDistance = 0; 
			
			for(Point p : vertexList) {
				if(minDistanceBetween(e, p) > greatestDistance) {
					greatestDistance = minDistanceBetween(e, p);
				}
			}
			distanceList.add(greatestDistance);
		}
		
		// minimum mug diameter (the solution) is = to lowest distance in distanceList
		minMugDiameter = getMin(distanceList);
		System.out.println(minMugDiameter);
	}
	
	// calculate minimum distance between an edge and a point
	public double minDistanceBetween(Edge e, Point p) {
		minDistance = Integer.MAX_VALUE;
		
		if(distance(e.getA(), p) < minDistance) {
			minDistance = distance(e.getA(), p);
		}
		if(distance(e.getMidPoint(), p) < minDistance) {
			minDistance = distance(e.getMidPoint(), p);
		}
		if(distance(e.getB(), p) < minDistance) {
			minDistance = distance(e.getB(), p);
		}
		return minDistance;
	}
	
	// calculate distance between two points
	public double distance(Point a, Point b) {
		double x = Math.abs(a.getX() - b.getX());
	    double y = Math.abs(a.getY() - b.getY());
	    return Math.sqrt(x*x + y*y);
	}
	
	// get minimum double from ArrayList
	public double getMin(ArrayList<Double> doubleList) {
		double min = Integer.MAX_VALUE;
		
		for(double i : doubleList) {
			if(i < min) {
				min = i;
			}
		}
		return min;
	}
}