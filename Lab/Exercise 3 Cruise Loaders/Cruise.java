class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int numberOfLoader;
    private final int serviceTime;   
    private static final int A_HUNDRED = 100;
    private static final int AN_HOUR = 60;


    Cruise(String identifier, int arrivalTime, int numberOfLoader, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numberOfLoader = numberOfLoader;
        this.serviceTime = serviceTime;
    }

    String getIdentifier() {
        return this.identifier;
    }

    int getServiceTime() {
        return this.serviceTime;
    }

    int getOriginalArrivalTime() {
        return this.arrivalTime;
    }

    int getArrivalTime() {
        int arrivalTimeInMinutes = 0;
        if (this.arrivalTime >= A_HUNDRED) {
            arrivalTimeInMinutes = (this.arrivalTime / A_HUNDRED) * AN_HOUR + 
                                    this.arrivalTime % A_HUNDRED;
        } else {
            arrivalTimeInMinutes = this.arrivalTime;
        }
        return arrivalTimeInMinutes;
    }

    int getNumOfLoadersRequired() {
        return this.numberOfLoader;
    }

    @Override
    public String toString() {
        return this.identifier + String.format("@%04d",this.arrivalTime);
    }



}