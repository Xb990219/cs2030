public class Hold extends Event {
    
    public Hold(double time, Customer customer) {
        super(time, customer, true);
    }

    @Override
    public Pair<Event,ImList<Server>> execute(ImList<Server> serverList) {
        //System.out.println(this.customer.getCustomerNumber() + " " + this.time + " hold ");
        int serverIndex = this.customer.getServer().getServerNumber() - 1;
        Server originalServer = serverList.get(serverIndex);
        //System.out.print(updatedServer.getWaitingNumber());
        if (!originalServer.isSelfCheck()) {
            if (this.time == originalServer.getAvailableTime()) {
                Serve nextEvent = new Serve(originalServer.getAvailableTime(),
                                            this.customer);
                return new Pair<Event,ImList<Server>>(nextEvent, serverList);
            } else {
                Hold nextEvent = new Hold(originalServer.getAvailableTime(), 
                                        this.customer);
                return new Pair<Event,ImList<Server>>(nextEvent, serverList);
            }
        } else {
            ImList<Server> selfcheckServerList = new ImList<>();
            for (int i = serverIndex; i < serverList.size(); i++) {
                selfcheckServerList = selfcheckServerList.add(serverList.get(i));
            }
            for (Server selfCheckServer : selfcheckServerList) {
                if (selfCheckServer.getAvailableTime() == this.time) {
                    Customer newCustomer = new Customer(this.customer.getArrivalTime(),
                                                        this.customer.getCustomerNumber(),
                                                        this.customer.getServiceTimeSupplier(),
                                                        selfCheckServer);
                    Serve nextEvent = new Serve(this.time, newCustomer);
                    return new Pair<Event,ImList<Server>>(nextEvent, serverList);
                }
            }
            double earliestAvailableTime = Double.MAX_VALUE;
            for (int i = 0; i < selfcheckServerList.size(); i++) {
                if (selfcheckServerList.get(i).getAvailableTime() < earliestAvailableTime) {
                    earliestAvailableTime = selfcheckServerList.get(i).getAvailableTime();
                }
            }
            Hold nextEvent = new Hold(earliestAvailableTime, 
                                      this.customer);
            return new Pair<Event,ImList<Server>>(nextEvent, serverList);
        }
    }

    @Override
    boolean finishEvent() {
        return false;
    }

    @Override
    public String toString() {
        // return String.format("%.3f",this.time) + 
        //        " " +
        //        this.customer.getCustomerNumber() + 
        //        " " + 
        //        "holds at" + 
        //        " " +
        //        this.customer.getServer().toString() + 
        //        "\n";
        return "";
    }
}
