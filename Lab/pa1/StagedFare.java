import java.util.Iterator;

class StagedFare implements Fare {
    private final ImList<Integer> distanceList;
    private final ImList<Integer> fareList;

    public StagedFare(int distance, int fare) {
        ImList<Integer> constructDistanceList = new ImList<>();
        ImList<Integer> constructFareList = new ImList<>();
        constructDistanceList = constructDistanceList.add(distance);
        constructFareList = constructFareList.add(fare);
        this.distanceList = constructDistanceList;
        this.fareList = constructFareList;
    }

    private StagedFare(ImList<Integer> distanceList, 
                       ImList<Integer> fareList) {
        this.distanceList = distanceList;
        this.fareList = fareList;
    }

    public StagedFare add(int distance, int fare) {
        ImList<Integer> updatedDistanceList = this.distanceList;
        ImList<Integer> updatedFareList = this.fareList;
        updatedDistanceList = updatedDistanceList.add(distance);
        updatedFareList = updatedFareList.add(fare);
        return new StagedFare(updatedDistanceList, updatedFareList);
    }

    public int computeFare(Iterable<Ride> trip) {
        int currentDistance = 0;
        int distanceAndFareCommonIndex = 0;
        int currentFareDistance = 0;
        int nextFareDistance = Integer.MAX_VALUE;
        int totalPrice = 0;
        Iterator<Ride> itr = trip.iterator();
        while (itr.hasNext()) {
            currentDistance = itr.next().distance();
            for (int i = 0; i < this.distanceList.size(); i++) {
                if (currentDistance >= this.distanceList.get(i) && 
                    this.distanceList.get(i) > currentFareDistance) {
                    currentFareDistance = this.distanceList.get(i);
                    distanceAndFareCommonIndex = i;
                }
            }
            if (currentFareDistance == currentDistance) {
                totalPrice += this.fareList.get(distanceAndFareCommonIndex);
            } else {
                for (int i = 0; i < this.distanceList.size(); i++) {
                    if (this.distanceList.get(i) > currentFareDistance &&
                        this.distanceList.get(i) < nextFareDistance) {
                        nextFareDistance = this.distanceList.get(i);
                        distanceAndFareCommonIndex = i;
                    }
                }
                totalPrice += this.fareList.get(distanceAndFareCommonIndex);
            }
            nextFareDistance = Integer.MAX_VALUE;
            currentFareDistance = this.fareList.get(0);
            distanceAndFareCommonIndex = 0;
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        int initialFare = this.fareList.get(0);
        int lastFare = initialFare;
        for (Integer fare : fareList) {
            if (fare > lastFare) {
                lastFare = fare;
            }
            if (fare < initialFare) {
                initialFare = fare;
            }
        }
        return "Staged fare from " + initialFare + " to " + lastFare;
    }
}
