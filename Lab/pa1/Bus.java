public class Bus implements Ride {
    private final String busNumber;
    private final int boardingStage;
    private final int alightingStage;
    private static final int STAGE_DISTANCE = 800;

    /**
     * This is the constructor.
     * @param busNumber the busNumber
     * @param boardingStage the boarding station
     * @param alightingStage the alighting station
     */
    public Bus(String busNumber, int boardingStage, int alightingStage) {
        this.busNumber = busNumber;
        this.boardingStage = boardingStage;
        this.alightingStage = alightingStage;
    }

    public int distance() {
        return (this.alightingStage - this.boardingStage) * STAGE_DISTANCE;
    }

    @Override
    public boolean isSameRide(Ride anotherRide) {
        if (anotherRide instanceof Bus anotherBus) {
            if (this.busNumber == anotherBus.busNumber) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[Bus " + 
               this.busNumber + 
               ": " + 
               this.boardingStage + 
               " -- " + 
               this.alightingStage + 
               "]";
    }

}
