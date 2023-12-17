class Done extends Event {
    
    public Done(double time, Customer customer) {
        super(time, customer, false);
    }

    @Override
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        Leave nextEvent = new Leave(this.time, this.customer);
        return new Pair<Event,ImList<Server>>(nextEvent,serverList);
    }

    @Override
    boolean finishEvent() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + 
               " " + 
               this.customer.getCustomerNumber() + 
               " done serving by " +
               this.customer.getServerNumber() + 
               "\n";
    }
}
