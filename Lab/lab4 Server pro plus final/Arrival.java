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
        Pair<Boolean,Customer> servingPair = this.customer.getServingServer(serverList);
        if (servingPair.first() == true) {
            Serve nextEvent = new Serve(this.time, servingPair.second());
            return new Pair<Event,ImList<Server>>(nextEvent, serverList);
        } else {
            Pair<Boolean,Customer> waitingPair = this.customer.getWaitingServer(serverList);
            if (waitingPair.first() == true) {
                Wait nextEvent = new Wait(this.time, waitingPair.second());
                return new Pair<Event,ImList<Server>>(nextEvent, serverList);
            } else {
                Leave nextEvent = new Leave(this.time, this.customer);
                return new Pair<Event,ImList<Server>>(nextEvent,serverList);
            }
        }
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
