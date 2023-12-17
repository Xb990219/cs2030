import java.lang.Math;

public class BigCruise extends Cruise {
    private static final double LOADER_LENGTH = 40.0;
    private static final double TIME_PASSENGER = 50.0;

    public BigCruise(String identifier, 
                     int arrivalTime, 
                     int lengthOfTheCruise, 
                     int numberOfPassengers) {
        super(identifier, arrivalTime, (int) Math.ceil(lengthOfTheCruise / LOADER_LENGTH),
            (int) Math.ceil(numberOfPassengers / TIME_PASSENGER));
    }
}
