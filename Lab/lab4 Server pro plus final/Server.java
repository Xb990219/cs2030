import java.util.function.Supplier;

public class Server {
    protected final double availableTime;
    protected final int serverNumber;
    protected final int waitingNumber;
    protected final int maximumQueueNumber;
    protected final Supplier<Double> restTime;
    
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
                  int maximumQueueNumber,
                  Supplier<Double> restTime) {
        this.availableTime = availableTime;
        this.serverNumber = serverNumber;
        this.waitingNumber = waitingNumber;
        this.maximumQueueNumber = maximumQueueNumber;
        this.restTime = restTime;
    }

    /**
     * This is the method to rest the server if needed.
     * @return the new rested server.
     */
    public Server restServer() {
        return new Server(this.availableTime + restTime.get(),
                          this.serverNumber,
                          this.waitingNumber,
                          this.maximumQueueNumber,
                          restTime);
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
     * This is the method to update the server.
     * @param nextAvailableTime the next availbable for the server.
     * @return the new server.
     */
    public Server waitOneLess(double nextAvailableTime) {
        return new Server(nextAvailableTime,
                          this.serverNumber,
                          this.waitingNumber - 1,
                          this.maximumQueueNumber,
                          this.restTime);
    }

    /**
     * This is the method to update the server.
     * @param nextAvailableTime the next availbable for the server.
     * @return the new server.
     */
    public Server waitNoChange(double nextAvailableTime) {
        return new Server(nextAvailableTime,
                          this.serverNumber,
                          this.waitingNumber,
                          this.maximumQueueNumber,
                          this.restTime);
    }

    public boolean isSelfCheck() {
        return false;
    }

    /**
     * This is the method to update the server.
     * @return the new server.
     */
    public Server waitOneMore() {
        return new Server(this.availableTime,
                          this.serverNumber,
                          this.waitingNumber + 1,
                          this.maximumQueueNumber,
                          this.restTime);
    }

    /*
     * tostring
     */
    @Override
    public String toString() {
        return "" + this.serverNumber;
    }
}
