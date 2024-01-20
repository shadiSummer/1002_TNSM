package GA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Main {
	private static final int NUM_GENERATIONS = 30;
	
	    public static void main(String[] args) {
	    	
	        Map<Integer, PathInfo> pathInfoMap = createPathInfoMap(); 

	        
	        List<Chromosome> initialPopulation = readGenesFromFile(pathInfoMap);
	        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(initialPopulation, pathInfoMap);

	        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
	            System.err.println("Current Generation: " + generation);
	            geneticAlgorithm.evolve();

	            // Print the number of the current generation
	            System.out.println("Generation: " + generation);

	            // Print the population for current generation
	            Population currentPopulation = geneticAlgorithm.getPopulation();
	            for (Chromosome chromosome : currentPopulation.getPopulation()) {
	                System.out.println("Chromosome: " + Arrays.toString(chromosome.genes));
	            }

	            // Print the fittest chromosome in the current generation
	            Chromosome fittestChromosome = currentPopulation.getFittestChromosome();
	            System.err.println("Fittest Chromosome: " + Arrays.toString(fittestChromosome.genes));
	        }


	        
	    }
	    
	    private static Map<Integer, PathInfo> createPathInfoMap() {
	        Map<Integer, PathInfo> pathInfoMap = new HashMap<>();
	        return pathInfoMap;
	    }

	    
	    private static List<Chromosome> readGenesFromFile(Map<Integer, PathInfo> pathInfoMap) {
	    	//Map<Integer, PathInfo> pathInfoMap = new HashMap<>();

	    	try (BufferedReader br = new BufferedReader(new FileReader("genes.txt"))) {
	    	    String line;
	    	    while ((line = br.readLine()) != null) {
	    	        line = line.trim();
	    	        if (!line.isEmpty()) {
	    	            String[] values = line.split(",");
	    	            int pathID = Integer.parseInt(values[0].trim());
	    	            double bandwidth = Double.parseDouble(values[1].trim());
	    	            double delay = Double.parseDouble(values[2].trim());
	    	            pathInfoMap.put(pathID, new PathInfo(pathID, bandwidth, delay));
	    	        }
	    	    }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Create initial population of Chromosomes
	        List<Chromosome> initialPopulation = new ArrayList<>();
	        Random rand = new Random();
	        int populationSize = pathInfoMap.size(); 

	        for (int i = 0; i < populationSize; i++) {
	            Set<Integer> selectedIDs = new HashSet<>();
	            while (selectedIDs.size() < 3) {
	                int randomID = rand.nextInt(pathInfoMap.size()) + 1; 
	                selectedIDs.add(randomID);
	            }

	            // Calculate total bandwidth and delay difference of the paths
	            double totalBandwidth = 0.0;
	            double minDelay = Double.MAX_VALUE;
	            double maxDelay = Double.MIN_VALUE;
	            for (int id : selectedIDs) {
	                PathInfo info = pathInfoMap.get(id);
	                totalBandwidth += info.getBandwidth();
	                minDelay = Math.min(minDelay, info.getDelay());
	                maxDelay = Math.max(maxDelay, info.getDelay());
	            }
	            double delayDifference = maxDelay - minDelay;

	            int[] pathIDs = selectedIDs.stream().mapToInt(Integer::intValue).toArray();
	            initialPopulation.add(new Chromosome(pathIDs, totalBandwidth, delayDifference));
	        }

	        return initialPopulation;
	    }

}