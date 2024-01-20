package GA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import GA.GeneticAlgorithm;


class PopulationUtils {
    public static final int POPULATION_SIZE = 50;

    public static Chromosome selectTournamentParent(Population population) {
        List<Chromosome> tournament = new ArrayList<>();
        for (int i = 0; i < GeneticAlgorithm.getTournamentSize(); i++) { 
            tournament.add(population.getPopulation().get(new Random().nextInt(population.getPopulation().size())));
        }
        return PopulationUtils.getFittestChromosome(tournament);
    }


    public static Chromosome getFittestChromosome(List<Chromosome> chromosomes) {
        Chromosome fittest = chromosomes.get(0);
        for (Chromosome chromosome : chromosomes) {
            if (chromosome.fitness > fittest.fitness) {
                fittest = chromosome;
            }
        }
        return fittest;
    }

    public static Chromosome crossover(Chromosome parent1, Chromosome parent2, Map<Integer, PathInfo> pathInfoMap) {
        Set<Integer> childGenesSet = new HashSet<>();
        Random rand = new Random();

        while (childGenesSet.size() < parent1.genes.length) {
            childGenesSet.add(parent1.genes[rand.nextInt(parent1.genes.length)]);
            if (childGenesSet.size() < parent1.genes.length) {
                childGenesSet.add(parent2.genes[rand.nextInt(parent2.genes.length)]);
            }
        }

        int[] childGenes = childGenesSet.stream().mapToInt(i -> i).toArray();

        double bandwidthSum = 0.0;
        double maxDelay = Double.MIN_VALUE;
        double minDelay = Double.MAX_VALUE;
/*
        for (int pathID : childGenes) {
            PathInfo info = pathInfoMap.get(pathID);
            System.out.println("Path ID: " + pathID + ", Bandwidth: " + info.getBandwidth() + ", Delay: " + info.getDelay());
            bandwidthSum += info.getBandwidth();
            maxDelay = Math.max(maxDelay, info.getDelay());
            minDelay = Math.min(minDelay, info.getDelay());
        }*/
        /*
        double totalBandwidth = 0.0;
        for (int id : childGenes) {
            PathInfo info = pathInfoMap.get(id);
            totalBandwidth += info.getBandwidth();
            System.err.println("ID: " + id + ", Bandwidth: " + info.getBandwidth() + ", Total: " + totalBandwidth);
        }*/

        /*
        for (int pathID : childGenes) {  // Use childGenes here
            PathInfo info = pathInfoMap.get(pathID);
            bandwidthSum += info.getBandwidth();
            maxDelay = Math.max(maxDelay, info.getDelay());
            minDelay = Math.min(minDelay, info.getDelay());
        }*/

        double delayDifference = maxDelay - minDelay;

        return new Chromosome(childGenes, bandwidthSum, delayDifference); // Use bandwidthSum here
    }


    public static void mutate(Chromosome chromosome, Map<Integer, PathInfo> pathInfoMap) {
        Random rand = new Random();
        Set<Integer> currentGenes = new HashSet<>();
        for (int gene : chromosome.genes) {
            currentGenes.add(gene);
        }

        for (int i = 0; i < chromosome.genes.length; i++) {
            if (Math.random() < GeneticAlgorithm.getMutationRate()) {
                int newGene;
                do {
                    newGene = rand.nextInt(250) + 1; 
                } while (currentGenes.contains(newGene));
                currentGenes.remove(chromosome.genes[i]);
                chromosome.genes[i] = newGene;
                currentGenes.add(newGene);
            }
        }

        
        double totalBandwidth = 0.0;
        double maxDelay = Double.MIN_VALUE;
        double minDelay = Double.MAX_VALUE;
        for (int gene : chromosome.genes) {
            PathInfo info = pathInfoMap.get(gene);
            totalBandwidth += info.getBandwidth();
            maxDelay = Math.max(maxDelay, info.getDelay());
            minDelay = Math.min(minDelay, info.getDelay());
        }
        chromosome.bandwidthSum = totalBandwidth;
        chromosome.delayDifference = maxDelay - minDelay;

        chromosome.recalculateFitness();
        /*
        System.err.println("Chromosome Path IDs: " + Arrays.toString(chromosome.genes));
        System.err.println("Bandwidth Sum: " + chromosome.bandwidthSum);
        System.err.println("Max Delay: " + maxDelay);
        System.err.println("Min Delay: " + minDelay);
        System.err.println("Delay Difference: " + chromosome.delayDifference);*/
    }
   
}
