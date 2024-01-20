package GA;

class PathInfo {
    private int id;
    private double bandwidth;
    private double delay;

    public PathInfo(int id, double bandwidth, double delay) {
        this.id = id;
        this.bandwidth = bandwidth;
        this.delay = delay;
    }

    public double getBandwidth() {
        return bandwidth;
    }

    public double getDelay() {
        return delay;
    }
}