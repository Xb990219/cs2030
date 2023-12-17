abstract class Event {
    protected final double time;
    protected final Customer customer;
    protected final int priorityLevel;
    protected final boolean intermediateEvent; 

    //constructor
    public Event(double time, Customer customer, int priorityLevel, boolean intermediateEvent) {
        this.time = time;
        this.customer = customer;
        this.priorityLevel = priorityLevel;
        this.intermediateEvent = intermediateEvent;
    }

    public double getTime() {
        return this.time;
    }

    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    public boolean isIntermediateEvent() {
        return this.intermediateEvent;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    abstract ImList<Server> updateServers();
    
    abstract Event execute();
}
