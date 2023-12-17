/*
 * The Customer class
 */

import java.util.function.Supplier;

public class Customer {
    private final double customerArrivalTime;
    private final int customerNumber;
    private final Supplier<Double> serviceTime;
    private final Server server;

    /**
    * Constructor for the Customer class.
    * @param customerNumber The customer number, incremented by 1 each time.
    * @param customerArrivalTime The customer arrival time.
    */
    public Customer(double customerArrivalTime, int customerNumber, Supplier<Double> serviceTime) {
        this.customerArrivalTime = customerArrivalTime;
        this.customerNumber = customerNumber;
        this.serviceTime = serviceTime;
        this.server = new Server(0, 0, 0, 0,serviceTime);
    }

    /**
    * Constructor for the Customer class.
    * @param customerNumber The customer number, incremented by 1 each time.
    * @param customerArrivalTime The customer arrival time.
    * @param serviceTime the servicetime of customer.
    * @param server the server chosen.
    */
    public Customer(double customerArrivalTime, 
                    int customerNumber, 
                    Supplier<Double> serviceTime,
                    Server server) {
        this.customerArrivalTime = customerArrivalTime;
        this.customerNumber = customerNumber;
        this.serviceTime = serviceTime;
        this.server = server;
    }

    public double getArrivalTime() {
        return this.customerArrivalTime;
    }

    public double getServiceTime() {
        return this.serviceTime.get();
    }

    public Supplier<Double> getServiceTimeSupplier() {
        return this.serviceTime;
    }

    public Server getServer() {
        return this.server;
    }

    /**
     * This is the method to find the available servingServer.
     * @param serverList serverlist
     * @return a pair which contians a boolean value and the new customer with server
     */
    public Pair<Boolean,Customer> getServingServer(ImList<Server> serverList) {
        for (Server server : serverList) {
            if (this.customerArrivalTime >= server.getAvailableTime() && 
                server.getWaitingNumber() == 0) {
                return new Pair<Boolean,Customer>(true,new Customer(customerArrivalTime, 
                                                                        customerNumber, 
                                                                        serviceTime, 
                                                                        server));
            }
        }
        return new Pair<Boolean,Customer>(false, this);
    }

    /**
     * This is the method to find the available waiting Server.
     * @param serverList serverlist.
     * @return a pair which contians a boolean value and the new customer with server
     */
    public Pair<Boolean,Customer> getWaitingServer(ImList<Server> serverList) {
        for (Server server : serverList) {
            if (server.getWaitingNumber() != server.getMaximumQueueNumber()) {
                return new Pair<Boolean,Customer>(true, new Customer(customerArrivalTime, 
                                                                       customerNumber, 
                                                                       serviceTime, 
                                                                       server));
            }
            if (server.isSelfCheck()) {
                return new Pair<Boolean,Customer>(false, this);
            }
        }
        return new Pair<Boolean,Customer>(false, this);
    }

    /*
     * to get the customer number
     */
    public int getCustomerNumber() {
        return this.customerNumber;
    }
    
    /*
     * tostring method which shows the arrivaltime and customer number
     */
    @Override
    public String toString() {
        return this.customerArrivalTime + 
               " customer " + 
               this.customerNumber;
    }
}
