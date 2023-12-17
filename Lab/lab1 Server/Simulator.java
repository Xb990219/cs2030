
public class Simulator {
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;
    
    /**
    * Constructor for the Simulator class.
    * @param numberOfServers The number of total servers.
    * @param arrivalTimes    The arrival times of customers.
    * @param serviceTimes    The service times of customers.
    */
    public Simulator(int numberOfServers, 
                    ImList<Double> arrivalTimes, 
                    ImList<Double> serviceTimes) {
        this.numOfServers = numberOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }
    
    /*
     * I have no idea
     */
    String simulate() {
        ImList<Server> serverList = new ImList<>();
        ImList<Customer> customerList = new ImList<>();
        ImList<String> output = new ImList<>();
        int servedCustomerNumber = 0;
        //Create servers
        for (int i = 0; i < this.numOfServers; i++) {
            serverList = serverList.add(new Server(0.000, i + 1));
        }
        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            customerList = customerList.add(new Customer(this.arrivalTimes.get(i),
                                                        this.serviceTimes.get(i), i + 1));
        }
        for (int i = 0; i < customerList.size(); i++) {
            output = output.add(customerList.get(i).toString());
            for (int j = 0; j < serverList.size(); j++) {
                if (serverList.get(j).canServe(customerList.get(i))) {
                    serverList = serverList.set(j,serverList.get(j).serve(customerList.get(i)));
                    output = output.add(String.format("%.3f", 
                                                        customerList.get(i)
                                                                    .getCustomerArrivalTime()) + 
                                        " customer " + 
                                        customerList.get(i).getCustomerNumber() + 
                                        " served by " + 
                                        serverList.get(j));
                    servedCustomerNumber++;
                    break; //find the server alr, can quit
                } else {
                    if (j == serverList.size() - 1) {
                        output = output.add(String.format("%.3f", 
                                                            customerList.get(i)
                                                                    .getCustomerArrivalTime()) + 
                                            " customer " + 
                                            customerList.get(i).getCustomerNumber() + 
                                            " leaves");
                    }
                }
            }
        }
        output = output.add("[" + servedCustomerNumber + " " + 
                            (customerList.size() - servedCustomerNumber) + "]");
        //System.out.println(output);
        String outputString = String.join("\n",output);
        return outputString;
    }    
}
