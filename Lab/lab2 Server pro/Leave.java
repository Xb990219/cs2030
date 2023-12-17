class Leave extends Event {
    private static final int LEVEL_THREE = 3;

    public Leave(double time, Customer customer) {
        super(time, customer, LEVEL_THREE, false);
    }

    public ImList<Server> updateServers() {
        return new ImList<Server>();
    }

    public Event execute() {
        return this;
    }

    public String toString() {
        return String.format("%.3f", this.time) + 
               " " + 
               this.customer.getCustomerNumber() + 
               " " + 
               "leaves";
    }
}
