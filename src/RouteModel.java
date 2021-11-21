import java.util.ArrayList;
import java.util.List;

/* Google Hash Code 2018 Sample Problem
   Pizza Cutter by Darragh Merrick, Ireland - February 2018
   The class PizzaModel.class creates data objects which can be referenced from any class
 */

public class RouteModel {
	
	String filename = null;
	private static int r = 0; //rows
	private static int c = 0; //columns
	private static int v = 0; //vehicles
	private int n = 0; //rides
	private int b = 0; // bonus
	private static int t = 0; // steps
	public char[][] cityMatrix;
	static ArrayList<int[]> routeData = new ArrayList<int[]>();
	public static List<int[]> RidesComplete = new ArrayList<int[]>();
	//This is the final submission. Each element of Arraylist contains the car ride stats
	static ArrayList<ArrayList<Integer>> submission = new ArrayList<ArrayList<Integer>>(); 
	
	public void setRideData(String filename) {
		this.filename = filename; }

	public String getRideData() {
		return filename;}
	
	// Getter and setter for columns
	public void setColumns(int c) {
		RouteModel.c = c; }

	public static int getColumns() {
		return c;}
	
	// Getter and setter for rows
	public void setRows(int r) {
	RouteModel.r = r;}

	public static int getRows() {
		return r;}
	
	// Getter and setter for vehicles
	public void setVehicles(int f) {
		RouteModel.v = f;	}

	public static int getVehicles() {
		return v;}
	
	// Getter and setter for Rides
	public void setRides(int n) {
		this.n = n;}

	public int getRides() {
		return n;}
	// Getter and setter for Bonus
		public void setBonus(int b) {
			this.b = b;}

		public int getBonus() {
			return b;}

		// Getter and setter for steps
		public void setSteps(int t) {
			this.t = t;}

		public static int getSteps() {
			return t;}
	
	// Getter and setter for Routes
		public void setRouteData(ArrayList<int[]> routeData) {
			RouteModel.routeData= routeData;}
		
		public static ArrayList<int[]> getRouteData() {
			return routeData;}
	
     // Getter and setter for adding routes
	 public static void setCompletedRoutes(ArrayList<Integer> completedRoute, int i) {
     RouteModel.submission.set(i, completedRoute);}
				
	 public static ArrayList<Integer> getCompletedRoutes(int i) {

	 return submission.get(i);}
	
	 //Getter and Setter for final Submission
	 public static ArrayList<ArrayList<Integer>> getSubmission() {
			return submission;}
	 
	 public static void setSubmission(ArrayList<Integer> initialRoute, int i) {
		 RouteModel.submission.add(initialRoute);}
}




