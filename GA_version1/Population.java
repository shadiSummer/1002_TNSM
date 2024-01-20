

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {

	private ArrayList<Route> route = new ArrayList<Route>(GeneticAlgorithm.POPULATION_SIZE);
	public ArrayList<Route> getRoutes() {return route;}
	
	//constructor I
	public Population (int populationSize, GeneticAlgorithm geneticAlgorithm) {
		IntStream.range(0, populationSize).forEach(x -> route.add(new Route(geneticAlgorithm.getInitialRoute())));
	}
	
	
	//constructor II
	public Population(int populationSize, ArrayList<Paths> paths) {
		IntStream.range(0, populationSize).forEach(x -> route.add(new Route(paths)));
	}
	
	// get method that returns paths
	public ArrayList<Route> getPaths(){
		return route;
	}
	
	public void sortRouteByFistness() {
		
		route.sort((route1, route2) -> {
			int flag = 0;
			if (route1.getFitness() > route2.getFitness()) flag = -1;
			else if (route1.getFitness() < route2.getFitness()) flag = 1;
			return flag;
		});
	}
}
