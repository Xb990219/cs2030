public class Arrival extends Event {
    private static final int LEVEL_TWO = 2;
    private final ImList<Server> servers;

    public Arrival(double time,
                   Customer customer,
                   ImList<Server> servers) {
        super(time, customer, LEVEL_TWO, true);
        this.servers = servers;
    }

    /**
     * Find the available server in the server list.
     *
     * @return the index in serverlist and server number
     */
    public Pair<Integer,Integer> getAvailableServerIndex() {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).canServe(this.customer)) {
                return new Pair<Integer, Integer>(i, servers.get(i).getServerNumber());
            }
        }
        return new Pair<Integer, Integer>(-1,0);
    }

    /**
     * Update the serverlist after find the correct server.
     *
     * @return a new serverList with updated servers
     */
    public ImList<Server> updateServers() {
        int i = getAvailableServerIndex().first();
        int j = getAvailableServerIndex().second();
        if (i != -1) { //if there is a server to serve the customer, NOT -1.
            ImList<Server> updateServers = this.servers;
            updateServers = updateServers.set(i, 
                                              new Server(this.customer.getArrivalTime() + 
                                                         this.customer.getServiceTime(),
                                              j));
            return updateServers;
        }
        return this.servers;
    }

    /**
     * Execute this event Arrival and output Serve or Leave based on
     * the availability of the servers.
     *
     * @return a Leave Event or a Serve Event.
     */
    public Event execute() {
        if (getAvailableServerIndex().first() == -1) {
            return new Leave(this.time, this.customer);
        } else {
            return new Serve(this.customer.getArrivalTime(),
                             this.customer, 
                             this.getAvailableServerIndex().second());
        }
    }

    @Override
    public String toString() {
        return String.format("%.3f",this.time) + 
               " " + 
               this.customer.getCustomerNumber() + 
               " " + 
               "arrives";
    }
}
