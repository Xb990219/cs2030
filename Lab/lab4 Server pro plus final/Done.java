class Done extends Event {
    
    public Done(double time, Customer customer) {
        super(time, customer, true);
    }

    @Override
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        End nextEvent = new End(this.time, this.customer);
        int indexOfServerOccupied = this.customer.getServer().getServerNumber() - 1;
        Server originalServer = serverList.get(indexOfServerOccupied);
        Server updatedServer = originalServer.restServer();
        ImList<Server> newServerList = serverList.set(indexOfServerOccupied, updatedServer);
        return new Pair<Event,ImList<Server>>(nextEvent,newServerList);
    }

    @Override
    boolean finishEvent() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.time) + 
               " " + 
               this.customer.getCustomerNumber() + 
               " done serving by " +
               this.customer.getServer().toString() + 
               "\n";
    }
}
