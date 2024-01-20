package GA;


class Chromosome {
    int[] genes; // Array to hold path IDs
    double bandwidthSum; // Total bandwidth of paths
    double delayDifference; // Delay Difference of paths
    double fitness;

    static final double MAX_BANDWIDTH = 2.2; // maximum possible bandwidth value
    static final double MAX_DELAY = 59; // maximum possible expected delay

    public Chromosome(int[] pathIDs, double bandwidthSum, double delayDifference) {
        this.genes = pathIDs;
        this.bandwidthSum = bandwidthSum;
        this.delayDifference = delayDifference;
        recalculateFitness();
    }

    private double calculateFitness() {
        // Normalize bandwidth and delay
        double normalizedBandwidth = this.bandwidthSum / MAX_BANDWIDTH;
        double normalizedDelay = this.delayDifference / MAX_DELAY;

        // Fitness formula
        return 0.6 * normalizedBandwidth + 0.4 * (1 - normalizedDelay); 
    }

   
    public void recalculateFitness() {
        this.fitness = calculateFitness();
    }
    
    
}
