abstract class Event {
    protected final double time;
    protected final Customer customer;
    protected final boolean intermediateEvent;

    //constructor
    public Event(double time, 
                 Customer customer, 
                 boolean intermediateEvent) {
        this.time = time;
        this.customer = customer;
        this.intermediateEvent = intermediateEvent;
    }

    public double getTime() {
        return this.time;
    }

    public boolean isIntermediateEvent() {
        return this.intermediateEvent;
    }

    public Customer getCustomer() {
        return this.customer;
    }
    
    abstract Pair<Event,ImList<Server>> execute(ImList<Server> serverList);

    double getWaitingTime() {
        return 0.0;
    }

    abstract boolean finishEvent();
}
