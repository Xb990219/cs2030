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
        int serverIndex = this.customer.getServer().serverNumber - 1;
        double currentServiceTime = this.customer.getServiceTime();
        Server originalServer = serverList.get(serverIndex);
        if (!originalServer.isSelfCheck()) {
            Server updatedServer = originalServer;
            if (originalServer.getWaitingNumber() != 0) {
                updatedServer = originalServer.waitOneLess(this.time + currentServiceTime);
            } else {
                updatedServer = originalServer.waitNoChange(this.time + currentServiceTime);
            }
            ImList<Server> updatedServerList = serverList.set(serverIndex, updatedServer);
            Done nextEvent = new Done(this.time + currentServiceTime,
                                    this.customer);
            return new Pair<Event,ImList<Server>>(nextEvent, updatedServerList);
        } else {
            //change root selfCheck
            Server updatedServerRootSelf = originalServer;
            ImList<Server> updatedServerList = serverList;
            int rootServerIndex = 0;
            for (int i = 0; i < serverList.size(); i++) {
                if (serverList.get(i).isSelfCheck()) {
                    updatedServerRootSelf = serverList.get(i);
                    rootServerIndex = i;
                    break;
                }
            }
            if (rootServerIndex == serverIndex) {
                if (updatedServerRootSelf.getWaitingNumber() != 0) {
                    updatedServerRootSelf = updatedServerRootSelf
                                            .waitOneLess(this.time + currentServiceTime);
                } else {
                    updatedServerRootSelf = updatedServerRootSelf
                                            .waitNoChange(this.time + currentServiceTime);
                }
                updatedServerList = updatedServerList.set(rootServerIndex, updatedServerRootSelf);
            } else {
                if (updatedServerRootSelf.getWaitingNumber() != 0) {
                    updatedServerRootSelf = updatedServerRootSelf
                                            .waitOneLess(updatedServerRootSelf
                                                         .getAvailableTime());
                    updatedServerList = updatedServerList.set(rootServerIndex, 
                                                              updatedServerRootSelf);
                }
                //change itself
                Server updatedServerItself = originalServer
                                             .waitNoChange(this.time + currentServiceTime);
                updatedServerList = updatedServerList.set(serverIndex, updatedServerItself);
            }
            Done nextEvent = new Done(this.time + currentServiceTime, this.customer);
            return new Pair<Event,ImList<Server>>(nextEvent, updatedServerList);
        }
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
               this.customer.getServer().toString() + 
               "\n";
    }
}
