

import java.util.ArrayList;
import java.util.Arrays;

// represents route candidate solution from source to destination
public class Route {

	private boolean isFitnessChanged = true;
	private double [] fitness = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
	 		 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			 0, 0, 0, 0, 0, 0, 0};
	private ArrayList<Paths> paths = new ArrayList <Paths>();
	private double arrayBW [] = null;
	private double arrayD [] = {0.4391836734693878,	0.5338775510204081,	0.6848979591836735, 0.3918367346938776,	0.4865306122448979,	0.6375510204081632,	0.30612244897959184,
								0.4008163265306123, 0.3844897959183673, 0.4791836734693877,	0.6302040816326531,	0.0,                0.09469387755102043, 0.24571428571428566,
								0.1412244897959184, 0.28326530612244893, 0.37795918367346937, 0.5289795918367347, 0.42448979591836733, 0.4391836734693878, 0.5338775510204081,
								0.6848979591836735,	0.3918367346938776,	0.4865306122448979, 0.6375510204081632,	0.30612244897959184, 0.4008163265306123, 0.3844897959183673,
								0.4791836734693877,	0.6302040816326531,	0.0, 				0.09469387755102043,0.24571428571428566, 0.1412244897959184, 0.28326530612244893,
								0.37795918367346937, 0.5289795918367347, 0.42448979591836733, 0.4391836734693878, 0.5338775510204081, 0.6848979591836735, 0.3918367346938776,
								0.4865306122448979, 0.6375510204081632, 0.30612244897959184, 0.4008163265306123, 0.3844897959183673, 0.4791836734693877, 0.6302040816326531,
								0.0, 0.09469387755102043, 0.24571428571428566, 0.1412244897959184, 0.28326530612244893, 0.37795918367346937, 0.5289795918367347, 0.42448979591836733};


	
	//constructor for genetic algorithm
	public Route(GeneticAlgorithm geneticAlgorithm) {
		geneticAlgorithm.getInitialRoute().forEach(x -> paths.add(null));
	}
			
	//constructor of the paths
	public Route(ArrayList<Paths> paths) {
		this.paths.addAll(paths);
		//Collection.shuffle(Arrays.asList(paths)); //shuffles the path list
	}
	
	// get method to return the paths
	public ArrayList<Paths> getPaths() {
		isFitnessChanged = true;
		return paths;
	}																									
	

	/**
	 * Calculates a value between 0 and 1, given the precondition that value
	 * is between minimum and maximum. 0 means value = maximum, and 1 means value = minimum.
	 * @return 
	 */

/*
	// here enter the codes to calculate paths delay difference										
	public double normBW(){
		
		double[] arrayBw = {84, 84, 99, 84, 84, 99, 86, 86, 84, 84,
							90, 84, 84, 92, 87, 84, 84, 92, 87, 84,
							84, 99, 84, 84, 99, 86, 86, 84, 84, 90,
							84, 84, 92, 87, 84, 84, 92, 87, 84, 84,
							99, 84, 84, 99, 86, 86, 84, 84, 90, 84,
							84, 92, 87, 84, 84, 92, 87};
		
		double min = 84;
		double max = 99;
		
		for(int i =0; i<arrayBw.length; i++)	{
			
			double a = arrayBw[i] - min;
			double b = max - min;
			double c = a / b;
			arrayBW[i] = 1 - c;
			System.err.println("D is :  " + arrayBW);

			
			// i=0, 0<50, 			bw= 1- ( (3-2) / (5 - 2) ) = 1- (1 / 3) = 
			//BW = 1 - ((arrayBW[i] - min) / (max - min)); // 1/3
			//System.err.println("BWis : " + BWis);
		}
		
		//System.out.println("BW is :  " + BW);

	    return BW;
		
	}
	
	public double normD(){
		
		double[] arrayD = { 771, 655, 470, 829, 713, 528, 934, 818, 838, 722,
							537, 1309, 1193, 1008, 1136, 962, 846, 661, 789, 771, 
							655, 470, 829, 713, 528, 934, 818, 838, 722, 537,
							1309, 1193, 1008, 1136, 962, 846, 661, 789, 771, 655,
							470, 829, 713, 528, 934, 818, 838, 722, 537, 1309,
							1193, 1008, 1136, 962, 846, 661, 789};
	

		
		double min = 470;
		double max = 1309;
		
		for(int i =0; i<arrayD.length; i++){
			double a = arrayD[i] - min;
			
			//System.err.println("Array is :  " + a);
			
			double b = max - min;
			double c = a / b;
			
			D = 1 - c; 
			//System.err.println("D is :  " + D);

		}
				
		//System.err.println("D is :  " + D);

		return D;
		
		}*/

	
	// print out the paths																			
	public String toString() {return Arrays.toString(paths.toArray()); }

	public double getFitness() {
		// TODO Auto-generated method stub
		if (isFitnessChanged == true) {
			
		//	fitness = (D * 0.2) + (BW * 0.8);
			for (int i=0; i < 56; i++){
			//arrayD[i] * 0.3;
			//double b = normBW() * 0.7;
			fitness = (arrayD[i] * 0.3);								// hear write the formula for fitness
			}
		//	System.err.println("D is :  " + a);
		//	System.err.println("BW is :  " + b);
			
			
			isFitnessChanged = false;
		}
		
		//System.out.println(" fitness is :  " + fitness);

		return fitness;
	
	}
}						



/*
 * public double normalizedDelayDifference() {
	
	float value;
	double min = 0.1;
	double max = 0.5;
	
    return 1 - ((value - min) / (max - min));
	}
 */


/*
 * 		for(int i=0; i< arrayBW.length; i++)
			BW += Math.pow(arrayBW[i], 2);
		BW = Math.sqrt(BW);
				
		System.err.println("BW is :  " + BW);

		return BW;
 */


/*	
public double  normalizeBw(double [] bwArray, int maxBw){
     int total=0;
     for(int i =0; i<bwArray.length;i++){
     total+= bwArray[i];
    }
     return total;
}

/*
public double normalizedBandwidth() {
	
	float min = 1; 
	float max = 5;
	
	Paths path = new Paths(max, max, max);
	float theBandwidth = paths.getBandwidth();
	
	bnd = 1 - ((this.ba - min) / (max - min));
	    
	return bnd ;
	}	*/																							
	
	/*
	for (int i = 0; i < paths.getbandwidth.length; ++i) {
		normSq += bandwidth[i] * bandwidth[i];
	}
	float norm = (float) Math.sqrt(normSq);
	for (int i = 0; i < bandwidth.length; ++i ) {
		bandwidth[i] = bandwidth[i] / norm;}
		
	return bandwidth;	
*/																												


