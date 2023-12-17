import java.util.Iterator;

class DistanceFare implements Fare {
    private final int baseFare;
    private final int baseDistance;
    private final int bandFarePerUnitDistance;
    private final int unitDistance;
    private final int maximumDistance;
    private final int maximumPrice;

    public DistanceFare(int baseFare, 
                        int baseDistance, 
                        int bandFarePerUnitDistance, 
                        int unitDistance, 
                        int maximumDistance) {
        this.baseFare = baseFare;
        this.baseDistance = baseDistance;
        this.bandFarePerUnitDistance = bandFarePerUnitDistance;
        this.unitDistance = unitDistance;
        this.maximumDistance = maximumDistance;
        if (maximumDistance % unitDistance == 0) {
            this.maximumPrice = baseFare +
                                (maximumDistance / 
                                unitDistance) * bandFarePerUnitDistance;
        } else {
            this.maximumPrice = baseFare +
                                (maximumDistance / 
                                unitDistance + 1) * bandFarePerUnitDistance;
        }
    }

    public int computeFare(Iterable<Ride> trip) {
        Iterator<Ride> itr = trip.iterator();
        int totalPrice = 0;
        int totalDistance = 0;
        while (itr.hasNext()) {
            totalDistance += itr.next().distance();
        }
        if (totalDistance == 0) {
            return 0;
        }
        if (totalDistance <= this.baseDistance) {
            return this.baseFare;
        } else if (totalDistance >= this.maximumDistance + 
                                    this.baseDistance) {
            return this.maximumPrice;
        } else {
            if ((totalDistance - baseDistance) % unitDistance == 0) {
                totalPrice = baseFare + 
                              ((totalDistance - baseDistance) / 
                              unitDistance) * bandFarePerUnitDistance;
            } else {
                totalPrice = baseFare + 
                              ((totalDistance - baseDistance) / 
                              unitDistance + 1) * bandFarePerUnitDistance;
            }
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Distance fare: " +
               this.baseFare + 
               " up to " +
               this.baseDistance +
               "; " +
               this.bandFarePerUnitDistance +
               " every " +
               this.unitDistance +
               " up to " +
               this.maximumDistance;
    }


}
