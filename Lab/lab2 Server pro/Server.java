
public class Server {
    private final double availableTime;
    private final int serverNumber;
    
    /*
     * Constructor
     * @param availableTime The available time
     * @param serverNumber The server number
     */
    public Server(double availableTime, int serverNumber) {
        this.availableTime = availableTime;
        this.serverNumber = serverNumber;
    }

    public int getServerNumber() {
        return this.serverNumber;
    }
    
    /**
    * Checks whether the server can serve the given customer.
    *
    * @param customer The customer to be served.
    * @return {@code true} if the server can serve the customer, otherwise {@code false}.
    */
    public boolean canServe(Customer customer) {
        if (customer.getArrivalTime() >= this.availableTime) {
            return true;
        }
        return false;
    }

    /*
     * tostring
     */
    @Override
    public String toString() {
        return "server " + this.serverNumber;
    }
}
