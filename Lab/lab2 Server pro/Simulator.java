
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
    
    /**
     * Simulate a customer server log.
     *
     * @return the log of all activity
     */
    public String simulate() {
        ImList<Server> serverList = new ImList<>();
        ImList<Customer> customerList = new ImList<>();
        PQ<Event> eventQueue = new PQ<>(new EventComparator());
        String outputString = "";
        int servedCustomer = 0;
        //Create serverList
        for (int i = 0; i < this.numOfServers; i++) {
            serverList = serverList.add(new Server(0.000, i + 1));
        }

        //Create CustomerList
        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            customerList = customerList.add(new Customer(this.arrivalTimes.get(i),
                                                        this.serviceTimes.get(i), i + 1));
        }

        //Add all arrivals to EventQueue
        for (int i = 0; i < customerList.size(); i++) {
            Arrival currentArrival = new Arrival(customerList.get(i).getArrivalTime(),
                                                    customerList.get(i),
                                                 serverList); 
            eventQueue = eventQueue.add(currentArrival); //add the new arrival to the PQ
            if (currentArrival.getAvailableServerIndex().first() != -1) {
                servedCustomer++;
            }
            serverList = currentArrival.updateServers(); //update the server available
        }

        while (!eventQueue.isEmpty()) {
            Pair<Event, PQ<Event>> outputPair = eventQueue.poll(); //sort the first event out
            Event currentEvent = outputPair.first(); //save the first event
            eventQueue = outputPair.second(); //update the event queue
            outputString += currentEvent.toString() + "\n"; //log the event
            if (currentEvent.isIntermediateEvent()) { //if the current event "Arrival" or "Serve"
                eventQueue = eventQueue.add(currentEvent.execute()); //add the new event
            }
        }
        outputString += "[" + servedCustomer + " " + (customerList.size() - servedCustomer) + "]";
        return outputString;
    }    
}
