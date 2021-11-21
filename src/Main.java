import java.io.IOException;

public class Main {
	 
	
	public static void main (String [] args) throws IOException {
		String file = "./in/d_metropolis.in";

		RouteParser routeParser =  new RouteParser();
		routeParser.RideScan(file);
		routeParser.debug();
		RoutePlan routePlan = new RoutePlan();
		routePlan.routePlanner();
		Submission.printResults();
	}

}
