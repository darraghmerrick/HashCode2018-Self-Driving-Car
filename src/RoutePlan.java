import java.util.ArrayList;

public class RoutePlan {

	int a=0; //the row of the start intersection (0 ≤ a < R)
	int b=0; // the column of the start intersection (0 ≤ b < C)
	int x=0; //the row of the finish intersection (0 ≤ x < R)
	int y=0; //the column of the finish intersection (0 ≤ y < C)
	int s=0; // the earliest start (0 ≤ s < T)
	int f=0; //the latest finish (0 ≤ f ≤ T) , (f ≥ s + |x − a| + |y − b|)
	int d=0;
	int v = RouteModel.getVehicles();
	int[] allDistances = new int[v]; //array to store all distance from vehicle to start point
	int[] stepsRemaining = new int[v]; //an array to store the remaining steps of each vehicle
	
	ArrayList<Integer> journeyStats = new ArrayList<Integer>(); //Jou
	
	//public char[][] cityMatrix;
	ArrayList<int[]> routesData = RouteModel.getRouteData(); //Stores all routes
	//Each element of allVehicleTracker is a vehicleTracker array.
	int[][] allVehicleTracker = new int[v][3];
	//vehicleTracker contains current location and ID of a vehicle
	int[] vehicleTracker = new int[3];
	
	public void routePlanner() {
		
		//initialize all vehicles at the starting point (0,0)
		for(int i = 0; i < v; i++) { 
			vehicleTracker = new int[4];
			vehicleTracker[0] =0; //Current a coordinate
			vehicleTracker[1] =0; //Current b coordinate
			vehicleTracker[2] =i;//Vehicle ID
			vehicleTracker[3] =RouteModel.getSteps();
			this.allVehicleTracker[i] = vehicleTracker;
			
		}
		//initialize ride count for each vehicle to zero
		for(int i = 0; i < v; i++) { 
			int start = 0;
			ArrayList<Integer> initialRoute = new ArrayList<Integer>();
			initialRoute.add(start);
			RouteModel.setSubmission(initialRoute, i);
			
		}
		
		//For a single route extract start, finish, earliesr start, latest finish and distance.
		for(int i = 0; i < routesData.size(); i++) {   
			   int[] routeData = routesData.get(i);
			   for(int c = 0; c <
					  routeData.length; c++) {   
				    //System.out.print(routeData[c]);
				    if (c==0){
				    this.a=routeData[c]; //start point a
				    }
				    if (c==1){
				    this.b=routeData[c]; //start point b
				    }
				    if (c==2){
				    this.x=routeData[c]; // finish point x
				    }
				    if (c==3){
				    this.y=routeData[c]; // finish point y
				    }
				    if (c==4){
				    this.s=routeData[c]; // min steps
				    }
				    if (c==5){
				    this.f=routeData[c]; //max steps
				    }
				    if (c==6){
					    this.d=routeData[c]; //distance (steps) of the journey
					    }
			   }
				    //Do this for each route
			        // Find the vehicle closest to the start point of the journey
				    int closestVehicle =  findClosestVehicle(this.a,this.b);
				    //select the winning vehicle
				    checkIsRouteValid(closestVehicle,i);
					            }
						    }
		             	
    //this method returns the vehicle closest to the start point of the route
	private int findClosestVehicle(int destx, int desty) { // x,y is the start point of the journey,
		int x =destx;  
		int y =desty;// but destination in this method
		int a=0; //vehicle start point a
		int b=0; //vehicle start point b
		int s=0; //steps remaining for the closest vehicle
		
		
		//this loop calculates the distance from all vehicles to the start point
		for(int i = 0; i < v; i++) {
			int[] currentVehicle = allVehicleTracker[i];
			a= currentVehicle[0];
			b= currentVehicle[1];
			//All vehicles will be dispatched from (0,0) start point regardless of distance
			//Or some vehicles might never get used.*** All vehicles must be used ***
			if((a==0)&&(b==0)){
				return i;
			}
				
			s=currentVehicle[3];
			// distance = |a − x| + |b − y| 
			int d = Math.abs(a - x) + Math.abs(b - y);
			allDistances[i]=d;
			//store the steps remaining for the current vehicle
			stepsRemaining[i]=s;
			
		}
		// Calculate the shortest distance
		 int shortestDistance = allDistances[0];
		 int steps= stepsRemaining[0];
		 int vehicle =0; 
	       for(int i=1; i<allDistances.length; i++)
	       {
	           if(allDistances[i] < shortestDistance)
	           {
	        	   shortestDistance = allDistances[i];
	        	   vehicle = i;
	        	   steps = stepsRemaining[i];
	        	   
	        // if distances are the same size, use the vehicle with the most steps remaining	  
	           }
	           if(allDistances[i] == shortestDistance){
	        	   if(stepsRemaining[i]< steps){
	        		   shortestDistance=allDistances[i];
	        		   }
	           }
	           
	       }
		return vehicle;
		
	}
	
	private void checkIsRouteValid(int closestVehicle, int i) {
		vehicleTracker = allVehicleTracker[closestVehicle];
		  // Location where vehicle is starting from;
		  int startA=vehicleTracker[0];
		  int startB=vehicleTracker[1];
		  int distanceLeft =vehicleTracker[3];
		  int d1 = Math.abs(startA - a) + Math.abs(startB - b);
		  int d2 = Math.abs(a - x) + Math.abs(b - y);
		  int total = d1+d2;
		  //The vehicle can be used if the journey is within boundaries and
		  //if it has enough remaining steps to complete the journey
		  if((total < f) && (total <= distanceLeft)){
			  //(total > s) && 
			  //If the vehicle can complete the journey, do this;
			  ArrayList<Integer> completedRoute = RouteModel.getCompletedRoutes(closestVehicle);
			  int rides=completedRoute.get(0);
			  //increment rides completed
			  rides++;
			  //add the ride number completed
			  completedRoute.set(0, rides);
			  completedRoute.add(i);
			  RouteModel.setCompletedRoutes(completedRoute,closestVehicle);
			  //Set new location of vehicle
			  vehicleTracker = new int[4];
				vehicleTracker[0] =this.x; //Current a coordinate
				vehicleTracker[1] =this.y; //Current b coordinate
				vehicleTracker[2] =closestVehicle;//Vehicle ID
				vehicleTracker[3]=(distanceLeft-total);
				this.allVehicleTracker[closestVehicle] = vehicleTracker;
				} 
		  else {
			 int validVehicle = findValidRoute(distanceLeft);
			 if (validVehicle!=0){
			 checkIsRouteValid(validVehicle,i);
			 }
		  }
		  }
	
		  
	private int findValidRoute(int distance){
		int validVehicle=0;
		 for(int i=1; i<allDistances.length; i++)
	       {
	           if(allDistances[i] < distance)
	           {
	        	   return validVehicle;
  
	           }    
	       }
		return 0;
		
	}
	
	
	}
		
		