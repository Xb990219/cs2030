public class Train implements Ride {
    private final int boardingStation;
    private final int alightingStation;
    private static final int STATION_1 = 0;
    private static final int STATION_2 = 1500;
    private static final int STATION_3 = 3900;
    private static final int STATION_4 = 5100;
    private static final int STATION_5 = 6000;

    public Train(int boardingStation, int alightingStation) {
        this.boardingStation = boardingStation;
        this.alightingStation = alightingStation;
    }

    /**
     * Find the distance of the current train.
     * @return the distance;
     */
    public int distance() {
        int boardingPosition;
        int alightingPosition;
        ImList<Integer> stationPosition = new ImList<>();
        stationPosition = stationPosition.add(STATION_1);
        stationPosition = stationPosition.add(STATION_2);
        stationPosition = stationPosition.add(STATION_3);
        stationPosition = stationPosition.add(STATION_4);
        stationPosition = stationPosition.add(STATION_5);
        boardingPosition = this.boardingStation - 1;
        alightingPosition = this.alightingStation - 1;
        return stationPosition.get(alightingPosition) - 
               stationPosition.get(boardingPosition);
    }

    /**
    * Whether this two are the same buses or train.
    * @param anotherRide the busNumber
    * @return true if the same, otherwise false
    */
    public boolean isSameRide(Ride anotherRide) {
        if (anotherRide instanceof Train anotherTrain) {
            if (this.alightingStation == anotherTrain.boardingStation) {
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
        return "[Train: " + this.boardingStation + " -- " + this.alightingStation + "]";    
    }
}
