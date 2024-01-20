
public class Paths {

	public double bandwidth;
	public double delay;
	public String pathID;
	
	//constructor
	public Paths(String pathID, double bandwidth, double delay) {
		
		this.pathID = pathID;
		this.bandwidth = bandwidth;
		this.delay = delay;
	}
	
	//get methods to return the values
	public String getPathID() {return pathID; }
	public double getBandwidth() {return this.bandwidth;}
	public double getDelay() {return this.delay;}
	//public double getPathID() {return this.pathID;}
	
	public String toString(){ return getPathID();}
	
	public int bandwidthArray() {
		
		return 0;
	}
	
	public int delayArray() {
		
		return 0;
	}	
	
	
	public int delayDifference(Paths paths) {
		return 0;
		
	}
	
}
