public class Arrival extends Event {

    public Arrival(double time,
                   Customer customer) {
        super(time, customer, true);
    }

    /**
     * Execute this event Arrival and output Serve or Leave based on
     * the availability of the servers.
     *
     * @return a Piar of Event and updatedServer.
     */
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).canServe(this.customer)) {
                Customer updatedCustomer = new Customer(this.customer.getArrivalTime(), 
                                                        this.customer.getCustomerNumber(),
                                                        this.customer.getServiceTimeSupplier(), 
                                                        i + 1);
                Serve nextEvent = new Serve(this.customer.getArrivalTime(), updatedCustomer);
                return new Pair<Event,ImList<Server>>(nextEvent,serverList);
            }
        }
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).canWait(this.customer)) {
                Customer updatedCustomer = new Customer(this.customer.getArrivalTime(), 
                                                        this.customer.getCustomerNumber(),
                                                        this.customer.getServiceTimeSupplier(), 
                                                        i + 1);
                Wait nextEvent = new Wait(this.time, updatedCustomer);
                return new Pair<Event,ImList<Server>>(nextEvent,serverList);
            }
        }
        Leave nextEvent = new Leave(this.time, this.customer);
        return new Pair<Event,ImList<Server>>(nextEvent,serverList);
    }

    @Override
    boolean finishEvent() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%.3f",this.time) + 
               " " + 
               this.customer.getCustomerNumber() + 
               " " + 
               "arrives" + 
               "\n";
    }
}
