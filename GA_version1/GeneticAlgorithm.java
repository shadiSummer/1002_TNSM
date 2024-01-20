

import java.util.ArrayList;
import java.util.stream.IntStream;

// represents selection, mutation, crossover and elitism logic
public class GeneticAlgorithm {
	
	
	public static final double MUTATION_RATE = 0.25;
	public static final int TOURNAMENT_SELECTION_SIZE = 3;			
	public static final int POPULATION_SIZE = 57;
	public static final int NUMB_OF_ELITE_ROUTES = 1;
	public static final int NUMB_OF_GENERATION = 30;
	private ArrayList<Paths> initialRoute = null;

	//Constructor
	public GeneticAlgorithm(ArrayList<Paths> initialRoute) {this.initialRoute = initialRoute; }
		
	//get method to return initial route
	public ArrayList<Paths> getInitialRoute(){
		return initialRoute;
	}
	
	//Evolve method will apply crossover and mutation passed in population 
	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));}
	
	Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getRoutes().size(), this);
		IntStream.range(0, NUMB_OF_ELITE_ROUTES).forEach(x -> crossoverPopulation.getRoutes().set(x, population.getRoutes().get(x)));
		IntStream.range(NUMB_OF_ELITE_ROUTES, crossoverPopulation.getRoutes().size()).forEach(x -> {
			Route route1 = selectTournamentPopulation(population).getRoutes().get(0);
			Route route2 = selectTournamentPopulation(population).getRoutes().get(0);
			crossoverPopulation.getRoutes().set(x, crossoverRoute(route1, route2));
		});
		
		return crossoverPopulation;
	}
	Population mutatePopulation(Population population) {
		population.getRoutes().stream().filter(x -> population.getRoutes().indexOf(x) >= NUMB_OF_ELITE_ROUTES).forEach(x -> mutateRoute(x));
		return population;
	}
	
	//called by crossoverPopulation
	//mixes up first half of route1 with second half of route2
	Route crossoverRoute(Route route1, Route route2) {
		Route crossoverRoute = new Route(this);
		Route tempRoute1 = route1;
		Route tempRoute2 = route2;
		if (Math.random() < 0.5) {
			tempRoute1 = route2;
			tempRoute2 = route1;
		}
		for (int x = 0; x < crossoverRoute.getPaths().size()/2; x++)
			crossoverRoute.getPaths().set(x, tempRoute1.getPaths().get(x));
		return fillNullInCrossoverRoute(crossoverRoute, tempRoute2);
	}
	
	private Route fillNullInCrossoverRoute(Route crossoverRoute, Route route) {
		route.getPaths().stream().filter(x -> crossoverRoute.getPaths().contains(x)).forEach(pathX ->{
			for (int y = 0; y < route.getPaths().size(); y++) {
				if (crossoverRoute.getPaths().get(y) == null) {
					crossoverRoute.getPaths().set(y, pathX);
					break;
				}
			}
		});
		return crossoverRoute;
	}
	
	//called by mutatePopulation
	// this method mutates the genes in a generation
	Route mutateRoute(Route route) {
		route.getPaths().stream().filter(x -> Math.random() < MUTATION_RATE).forEach(pathX -> {
			int y = (int) (route.getPaths().size() * Math.random());
			Paths pathY = route.getPaths().get(y);
			route.getPaths().set(route.getPaths().indexOf(pathX), pathY);
			route.getPaths().set(y, pathX);
		});
		return route;
	}
	
	//route1 and route2 are obtained by this method below that returns tournament population
	Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE, this);
		IntStream.range(0, TOURNAMENT_SELECTION_SIZE).forEach(x -> tournamentPopulation.getRoutes().set(
				x, population.getRoutes().get((int) (Math.random() * population.getRoutes().size()))));
		tournamentPopulation.sortRouteByFistness();
		return tournamentPopulation;
	}
	
}
