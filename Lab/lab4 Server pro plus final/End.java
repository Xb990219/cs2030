class End extends Event {
    
    public End(double time, Customer customer) {
        super(time, customer, false);
    }

    @Override
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        End nextEvent = new End(this.time, this.customer);
        return new Pair<Event, ImList<Server>>(nextEvent, serverList);
    }

    @Override
    boolean finishEvent() {
        return true;
    }

    @Override 
    public String toString() {
        return "";
    }
}
