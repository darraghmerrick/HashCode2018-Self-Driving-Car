
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/* Problem statement for the Online Qualification Round of Hash Code 2018
   Self-driving rides by Darragh Merrick, Ireland - March 2018
   The class RouteParser.class reads in all the file parameters;
● R – number of rows of the grid (1 ≤ R ≤ 10000)
● C – number of columns of the grid (1 ≤ C ≤ 10000)
● F – number of vehicles in the fleet (1 ≤ F ≤ 1000)
● N – number of rides (1 ≤ N ≤ 10000)
● B – per-ride bonus for starting the ride on time (1 ≤ B ≤ 10000)
● T – number of steps in the simulation (1 ≤ T ≤ 109)
 Each ride is then stored in an arraylist of int arrays. Each containing;
● a – the row of the start intersection (0 ≤ a < R)
● b – the column of the start intersection (0 ≤ b < C)
● x – the row of the finish intersection (0 ≤ x < R)
● y – the column of the finish intersection (0 ≤ y < C)
● s – the earliest start (0 ≤ s < T)
● f – the latest finish (0 ≤ f ≤ T) , (f ≥ s + |x − a| + |y − b|)
*/
public class RouteParser {

    public RouteModel routeModel = new RouteModel();
    
	//Initialize all Pizza parameters;
	private int r = 0; //rows
	private int c = 0; //columns
	private int v = 0; //vehicles
	private int n = 0; //rides
	private int bonus = 0; // bonus
	private int t = 0; // steps
	ArrayList<int[]> routes = RouteModel.getRouteData();
    
	public RouteParser() {
		// TODO Auto-generated constructor stub
	}

	public void RideScan(String filename) throws IOException {
		routeModel.setRideData(filename);
	    
		try {
			File file = new File( routeModel.getRideData());
			
			try (Scanner scanner = new Scanner(file)) {
				this.r = scanner.nextInt();
				routeModel.setRows(r); 
				this.c = scanner.nextInt();
				routeModel.setColumns(c); 
				this.v= scanner.nextInt();
				routeModel.setVehicles(v); 
				this.n = scanner.nextInt();
				routeModel.setRides(n); 
				this.bonus = scanner.nextInt();
				routeModel.setBonus(bonus); 
				this.t = scanner.nextInt();
				routeModel.setSteps(t); 
				
				
	           
				int i=0;
				int routeValues = 6;
			    int[] values = new int[7]; //Stores an individual route 
			    
			    while (scanner.hasNextInt()) {
			    	int val =scanner.nextInt();
			        if (i < routeValues ) {
			            values[i]=val;
			            i++;
			         } 
			        
			        else {
			        	values[6] = Math.abs(values[0] - values[2]) + Math.abs(values[1] - values[3]);
			            routes.add(values);
			            values = new int[7];
			            i=0;
			            values[i]=val;
			            i++;
			        }  
			    }
			    //d= (a - x) + (b - y)
			    values[6] = Math.abs(values[0] - values[2]) +Math.abs (values[1] - values[3]);
			    routes.add(values);
			   }
			routeModel.setRouteData(routes);
		} catch(Exception e) {}
	}

	public void debug() {
		System.out.println("------------------------------------");
		System.out.println("File Variables - 1st line ");
		System.out.println("Number of Rows: " + RouteModel.getRows());
		System.out.println("Number of Columns: " +  RouteModel.getColumns());
		System.out.println("Number of vehicles: " +  RouteModel.getVehicles());
		System.out.println("Number of Rides " +  routeModel.getRides());
		System.out.println("Number of Bonus " +  routeModel.getBonus());
		System.out.println("Number of Steps " +  RouteModel.getSteps());
		System.out.println("------------------------------------");
		System.out.println("Route Variables - every other line ");
		for(int i = 0; i < routes.size(); i++) {   
		   int[] route = routes.get(i);
		   for(int c = 0; c <
				  route.length; c++) {   
			    System.out.print(route[c]);}
		   System.out.print("\n");
		} 
		System.out.println("------------------------------------");
	}

}