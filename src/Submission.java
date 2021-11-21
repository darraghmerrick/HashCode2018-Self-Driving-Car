import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Submission {
	public static void printResults() {
		//PizzaModel pizzaModel = new PizzaModel();
		ArrayList<ArrayList<Integer>> submission = RouteModel.getSubmission();
			
				FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter("routeSubmission.txt");
				} catch (IOException e) {
					//  catch block
					e.printStackTrace();
				}
				PrintWriter printWriter = new PrintWriter(fileWriter);
				System.out.println("Output file Results");
				int j = 0;
				while (submission.size() > j) {
					ArrayList<Integer> rides = submission.get(j);
					//printWriter.println(Arrays.toString(slice));
					for (int i = 0; i < rides.size(); i++) {
						System.out.print(rides.get(i));
						System.out.print(" ");
						printWriter.print(rides.get(i));
						printWriter.print(" ");
						}
					printWriter.print("\n");
					System.out.print("\n");
					j++;
				}
				System.out.println("------------------------------------");
				printWriter.close();
				
				
		
	
	}
}

