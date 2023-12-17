import java.util.Iterator;

public class RebateFare implements Fare {
    private final int rebate;
    private final StagedFare sfare;

    public RebateFare(StagedFare sfare, int rebate) {
        this.sfare = sfare;
        this.rebate = rebate;
    }

    @Override
    public int computeFare(Iterable<Ride> trip) {
        int initialPrice = this.sfare.computeFare(trip);
        int numberOfTrip = 0;
        Iterator<Ride> itr = trip.iterator();
        while (itr.hasNext()) {
            numberOfTrip++;
            itr.next();
        }
        return initialPrice - (numberOfTrip - 1) * this.rebate;
    }

    @Override
    public String toString() {
        return this.sfare.toString() + " (with rebate " + this.rebate + ")";
    }
}
