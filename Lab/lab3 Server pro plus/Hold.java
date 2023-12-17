public class Hold extends Event {
    
    public Hold(double time, Customer customer) {
        super(time, customer, true);
    }

    @Override
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        //System.out.println(this.customer.getCustomerNumber() + " " + this.time + " hold ");
        int serverIndex = this.customer.getServerNumber() - 1;
        Server originalServer = serverList.get(serverIndex);
        Server updatedServer = new Server(originalServer.getAvailableTime(), 
                                          originalServer.getServerNumber(), 
                                          originalServer.getWaitingNumber(), 
                                          originalServer.getMaximumQueueNumber());
        ImList<Server> updatedServerList = serverList.set(serverIndex, updatedServer);
        //System.out.print(updatedServer.getWaitingNumber());
        if (this.time == originalServer.getAvailableTime()) {
            Serve nextEvent = new Serve(originalServer.getAvailableTime(),
                                        this.customer);
            return new Pair<Event,ImList<Server>>(nextEvent, updatedServerList);
        } else {
            Hold nextEvent = new Hold(originalServer.getAvailableTime(), 
                                      this.customer);
            return new Pair<Event,ImList<Server>>(nextEvent, updatedServerList);
        }
    }

    @Override
    boolean finishEvent() {
        return false;
    }

    @Override
    public String toString() {
        /*return String.format("%.3f",this.time) + 
               " " +
               this.customer.getCustomerNumber() + 
               " " + 
               "holds at" + 
               " " +
               this.customer.getServerNumber() + 
               "\n";*/
        return "";
    }
}
