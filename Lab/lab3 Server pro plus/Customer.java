/*
 * The Customer class
 */

import java.util.function.Supplier;

public class Customer {
    private final double customerArrivalTime;
    private final int customerNumber;
    private final Supplier<Double> serviceTime;
    private final int serverNumber;

    /**
    * Constructor for the Customer class.
    * @param customerNumber The customer number, incremented by 1 each time.
    * @param customerArrivalTime The customer arrival time.
    */
    public Customer(double customerArrivalTime, int customerNumber, Supplier<Double> serviceTime) {
        this.customerArrivalTime = customerArrivalTime;
        this.customerNumber = customerNumber;
        this.serviceTime = serviceTime;
        this.serverNumber = 0;
    }

    /**
    * Constructor for the Customer class.
    * @param customerNumber The customer number, incremented by 1 each time.
    * @param customerArrivalTime The customer arrival time.
    * @param serviceTime the servicetime of customer.
    * @param serverNumber the serverNumber.
    */
    public Customer(double customerArrivalTime, 
                    int customerNumber, 
                    Supplier<Double> serviceTime,
                    int serverNumber) {
        this.customerArrivalTime = customerArrivalTime;
        this.customerNumber = customerNumber;
        this.serviceTime = serviceTime;
        this.serverNumber = serverNumber;
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

    public int getServerNumber() {
        return this.serverNumber;
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
