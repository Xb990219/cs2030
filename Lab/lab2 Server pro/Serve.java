public class Serve extends Event {
    private static final int LEVEL_FOUR = 4;
    private final int serverNumber;
    
    //constructor
    public Serve(double time,
        Customer customer,
        int serverNumber) {
        super(time, customer, LEVEL_FOUR, true);
        this.serverNumber = serverNumber;
    }

    /**
     * Execute this event Arrival and output Done Event.
     *
     * @return a Done event
     */
    public Done execute() {
        return new Done(this.customer.getArrivalTime() +
                        this.customer.getServiceTime(),
                        this.customer,
                        this.serverNumber);
    }

    public ImList<Server> updateServers() {
        return new ImList<Server>();
    }

    @Override
    public String toString() {
        return String.format("%.3f",this.time) + 
               " " +
               this.customer.getCustomerNumber() + 
               " " + 
               "serves by" + 
               " " +
               this.serverNumber;
    }
}
