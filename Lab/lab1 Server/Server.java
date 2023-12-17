
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
    
    /**
    * Checks whether the server can serve the given customer.
    *
    * @param customer The customer to be served.
    * @return {@code true} if the server can serve the customer, otherwise {@code false}.
    */
    public boolean canServe(Customer customer) {
        if (customer.getCustomerArrivalTime() >= availableTime) {
            return true;
        }
        return false;
    }

    /**
    * Serves the customer and returns a new server with updated time.
    *
    * @param customer The customer being served.
    * @return A new server with an updated time.
    */
    public Server serve(Customer customer) {
        double arrivaltime = customer.getCustomerArrivalTime();
        double number = customer.getServiceTime();
        return new Server(arrivaltime + number, this.serverNumber);
    }
    
    /*
     * tostring
     */
    @Override
    public String toString() {
        return "server " + this.serverNumber;
    }
}
