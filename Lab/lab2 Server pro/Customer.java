/*
 * The Customer class
 */

public class Customer {
    private final double customerArrivalTime;
    private final int customerNumber;
    private final double serviceTime;

    /**
    * Constructor for the Customer class.
    * @param serviceTime    The service time of the customer.
    * @param customerNumber The customer number, incremented by 1 each time.
    */
    
    public Customer(double customerArrivalTime, double serviceTime, int customerNumber) {
        this.customerArrivalTime = customerArrivalTime;
        this.serviceTime = serviceTime;
        this.customerNumber = customerNumber;
    }

    public double getArrivalTime() {
        return this.customerArrivalTime;
    }

    /*
     * To get the service time of this customer
     */
    public double getServiceTime() {
        return this.serviceTime;
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
               this.customerNumber + 
               " with service time of " + 
               this.serviceTime;
    }
}
