/*
 * The Customer class
 */

public class Customer {
    private final int customerNumber;
    private final double arrivalTime;
    private final double serviceTime;

    /**
    * Constructor for the Customer class.
    * @param arrivalTime    The arrival time of the customer.
    * @param serviceTime    The service time of the customer.
    * @param customerNumber The customer number, incremented by 1 each time.
    */
    
    public Customer(double arrivalTime, double serviceTime, int customerNumber) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.customerNumber = customerNumber;
    }
    
    /*
     * To get the arrival time of this customer
     */

    public double getCustomerArrivalTime() {
        return this.arrivalTime;
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
        return String.format("%.3f", arrivalTime) + " customer " + customerNumber + " arrives";
    }
}
