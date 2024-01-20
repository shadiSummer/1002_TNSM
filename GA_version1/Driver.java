import java.util.ArrayList;
import java.util.Arrays;

public class Driver {
	
	public ArrayList<Paths> initialRoute = new ArrayList<Paths>(Arrays.asList(
			
			new Paths("0", 84, 771),	
			new Paths("1", 84, 655),
			new Paths("2", 99, 470),
			new Paths("3", 84, 829),
			new Paths("4", 84, 713),
			new Paths("5", 99, 528),
			new Paths("6", 86, 934),
			new Paths("7", 86, 818),
			new Paths("8", 84, 838),
			new Paths("9", 84, 722),

			new Paths("10", 90, 537),
			new Paths("11", 84, 1309),
			new Paths("12", 84, 1193),
			new Paths("13", 92, 1008),
			new Paths("14", 87, 1136),
			new Paths("15", 84, 962),
			new Paths("16", 84, 846),
			new Paths("17", 92, 661),
			new Paths("18", 87, 789),
			new Paths("19", 84, 771),
			
			new Paths("20", 84, 655),
			new Paths("21", 99, 470),
			new Paths("22", 84, 829),
			new Paths("23", 84, 713),
			new Paths("24", 99, 528),
			new Paths("25", 86, 934),
			new Paths("26", 86, 818),
			new Paths("27", 84, 838),
			new Paths("28", 84, 722),
			new Paths("29", 90, 537),
			
			new Paths("30", 84, 1309),
			new Paths("31", 84, 1193),
			new Paths("32", 92, 1008),
			new Paths("33", 87, 1136),
			new Paths("34", 84, 962),
			new Paths("35", 84, 846),
			new Paths("36", 92, 661),
			new Paths("37", 87, 789),
			new Paths("38", 84, 771),
			new Paths("39", 84, 655),
			
			new Paths("40", 99, 470),
			new Paths("41", 84, 829),
			new Paths("42", 84, 713),
			new Paths("43", 99, 528),
			new Paths("44", 86, 934),
			new Paths("45", 86, 818),
			new Paths("46", 84, 838),
			new Paths("47", 84, 722),
			new Paths("48", 90, 537),	
			new Paths("49", 84, 1309),
			
			new Paths("50", 84, 1193),	
			new Paths("51", 92, 1008),	
			new Paths("52", 87, 1136),	
			new Paths("53", 84, 962),	
			new Paths("54", 84, 846),	
			new Paths("55", 92, 661),	
			new Paths("56", 87, 789)
			));
	
	public static void main(String [] args) {
		
		Driver driver = new Driver();
		Population population = new Population(GeneticAlgorithm.POPULATION_SIZE, driver.initialRoute);
		population.sortRouteByFistness();
		
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.initialRoute);
		
		int generationNumber = 0;
		driver.printHeading(generationNumber++);
		driver.printPopulation(population);
		
		while (generationNumber < GeneticAlgorithm.NUMB_OF_GENERATION){
			driver.printHeading(generationNumber++);
			population = geneticAlgorithm.evolve(population);
			population.sortRouteByFistness();
			driver.printPopulation(population);
		}
		
		System.err.println("Best Route Found so fat:  " + population.getRoutes().get(0));
		System.err.println("w/ a distance of:  " + String.format("%.2f", population.getRoutes().get(0).getFitness()));
		
	}
	public void printPopulation(Population population) {
		population.getRoutes().forEach(x -> {
			
			System.out.println(Arrays.toString(x.getPaths().toArray()) + "  |  " + 
					String.format("%.4f", x.getFitness())); //  + "  |  " + String.format("%.2f", x.normD())
		});
		System.out.println("");
	}
	
	public void printHeading(int generationNumber){
		
		System.out.println("> Generation # " + generationNumber);
		String headingColumn1 = "Route";
		String remainingHeadingColumns = "Fitness     | Distance  ";
		int pathIdLength = 0;
		for (int x = 0; x < initialRoute.size(); x++) pathIdLength += initialRoute.get(x).getPathID().length();
		double arrayLength = pathIdLength + initialRoute.size()+2;
		double partialLength = (arrayLength - headingColumn1.length()) /2;
		
		for (int x = 0; x < partialLength; x++) 	System.out.print("  ");
		
		for (int x = 0; x < partialLength; x++)		System.out.print("  ");
		
		if ((arrayLength % 2) == 0) System.out.println("  ");
		System.out.println("  |  " + remainingHeadingColumns);
		
		pathIdLength += remainingHeadingColumns.length() + 3;
		
		for (int x = 0; x < pathIdLength+initialRoute.size()*2; x++) 	System.out.print("-");

		System.out.println("");
			
		
	}
	
	
	
	
}
