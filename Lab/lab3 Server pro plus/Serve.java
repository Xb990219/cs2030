public class Serve extends Event {

    //constructor
    public Serve(double time,
                 Customer customer) {
        super(time, customer, true);
    }

    /**
     * Execute this event Serve and output Done Event.
     *
     * @return a Done event
     */
    @Override
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        int serverIndex = this.customer.getServerNumber() - 1;
        double currentServiceTime = this.customer.getServiceTime();
        Server originalServer = serverList.get(serverIndex);
        Server updatedServer = new Server(this.time + currentServiceTime, 
                                          originalServer.getServerNumber(), 
                                          originalServer.getWaitingNumber() - 1, 
                                          originalServer.getMaximumQueueNumber());
        if (originalServer.getWaitingNumber() != 0) {
            updatedServer = new Server(this.time + currentServiceTime, 
                                       originalServer.getServerNumber(), 
                                       originalServer.getWaitingNumber() - 1, 
                                       originalServer.getMaximumQueueNumber());
        } else {
            updatedServer = new Server(this.time + currentServiceTime, 
                                       originalServer.getServerNumber(), 
                                       originalServer.getWaitingNumber(), 
                                       originalServer.getMaximumQueueNumber());
        }
        ImList<Server> updatedServerList = serverList.set(serverIndex, updatedServer);
        Done nextEvent = new Done(this.time + currentServiceTime,
                                  this.customer);
        return new Pair<Event,ImList<Server>>(nextEvent, updatedServerList);
    }

    @Override
    double getWaitingTime() {
        return this.time - this.customer.getArrivalTime();
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
               "serves by" + 
               " " +
               this.customer.getServerNumber() + 
               "\n";
    }
}
