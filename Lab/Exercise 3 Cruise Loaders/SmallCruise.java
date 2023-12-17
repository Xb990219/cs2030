public class SmallCruise extends Cruise {
    private static final int NUMBER_OF_LOADER = 1;
    private static final int SERVICE_TIME = 30;

    public SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, NUMBER_OF_LOADER, SERVICE_TIME);
    }
}
