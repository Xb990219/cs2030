class Done extends Event {
    private static final int LEVEL_ONE = 1;
    private final int serverNumber;

    public Done(double time, Customer customer, int serverNumber) {
        super(time, customer, LEVEL_ONE, false);
        this.serverNumber = serverNumber;
    }

    public ImList<Server> updateServers() {
        return new ImList<Server>();
    }

    public Event execute() {
        return new Done(this.time, this.customer, this.serverNumber);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + 
               " " + 
               this.customer.getCustomerNumber() + 
               " done serving by " + 
               this.serverNumber;
    }
}
