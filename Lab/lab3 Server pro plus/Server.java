
public class Server {
    private final double availableTime;
    private final int serverNumber;
    private final int waitingNumber;
    private final int maximumQueueNumber;
    
    /**
     * This is the constructor.
     * @param availableTime The current availbale time of the server.
     * @param serverNumber The server Number.
     * @param waitingNumber How many users are waiting right now.
     * @param maximumQueueNumber maximum qnumber allowed.
     */
    public Server(double availableTime, 
                  int serverNumber, 
                  int waitingNumber, 
                  int maximumQueueNumber) {
        this.availableTime = availableTime;
        this.serverNumber = serverNumber;
        this.waitingNumber = waitingNumber;
        this.maximumQueueNumber = maximumQueueNumber;
    }

    public double getAvailableTime() {
        return this.availableTime;
    }

    public int getServerNumber() {
        return this.serverNumber;
    }

    public int getWaitingNumber() {
        return this.waitingNumber;
    }

    public int getMaximumQueueNumber() {
        return this.maximumQueueNumber;
    }
    
    /**
    * Checks whether the server can serve the given customer.
    *
    * @param customer The customer to be served.
    * @return {@code true} if the server can serve the customer, otherwise {@code false}.
    */
    public boolean canServe(Customer customer) {
        if (customer.getArrivalTime() >= this.availableTime && this.waitingNumber == 0) {
            return true;
        }
        return false;
    }
    
    /**
     * This is the method to find out whether the server can wait for the customer.
     * @param customer The customer to be served.
     * @return true if can wait, flase otherwise.
     */
    public boolean canWait(Customer customer) {
        if (this.waitingNumber == this.maximumQueueNumber) {
            return false;
        }
        return true;
    }

    /*
     * tostring
     */
    @Override
    public String toString() {
        return this.availableTime + "server " + this.serverNumber;
    }
}
