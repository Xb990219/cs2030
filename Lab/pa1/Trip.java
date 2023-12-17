public class Trip {
    private final ImList<Ride> rideList;

    /**
    * This is the constructor.
    * @param ride the ride
    */
    public Trip(Ride ride) {
        ImList<Ride> newRideList = new ImList<>();
        newRideList = newRideList.add(ride);
        this.rideList = newRideList;
    }

    private Trip(ImList<Ride> rideList) {
        this.rideList = rideList;
    }

    /**
    * add the next trip to the current trip.
    * @param newRide the ride
    * @return the new trip
    */
    public Trip next(Ride newRide) {
        ImList<Ride> updatedRideList = this.rideList;
        if (this.rideList.get(this.rideList.size()-1).isSameRide(newRide)) {
            return new Trip(this.rideList);
        }
        updatedRideList = updatedRideList.add(newRide);
        return new Trip(updatedRideList);
    }

    public int fare(Fare sfare) {
        int totalPrice = sfare.computeFare(this.rideList);
        return totalPrice;
    } 

    @Override
    public String toString() {
        String output = "";
        for (Ride ride : rideList) {
            output += ride.toString();
        }
        return output;
    }
}
