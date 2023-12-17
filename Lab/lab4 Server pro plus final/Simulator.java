import java.util.function.Supplier;

public class Simulator {
    private final int numOfServers;
    private final int numberOfSelfChecks;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTime;
    
    /**
    * Constructor for the Simulator class.
    * @param numberOfServers The number of total servers.
    * @param arrivalTimes    The arrival times of customers.
    * @param serviceTime    The service times of customers.
    */
    public Simulator(int numberOfServers,
                     int numberOfSelfChecks,
                     int qmax,
                     ImList<Double> arrivalTimes, 
                     Supplier<Double> serviceTime,
                     Supplier<Double> restTime) {
        this.numOfServers = numberOfServers;
        this.numberOfSelfChecks = numberOfSelfChecks;
        this.qmax = qmax;
        this.arrivalTimes = arrivalTimes;
        this.serviceTime = serviceTime;
        this.restTime = restTime;
    }
    
    /**
     * Simulate a customer server log.
     *
     * @return the log of all Events
     */
    public String simulate() {
        ImList<Server> serverList = new ImList<>();
        ImList<Customer> customerList = new ImList<>();
        PQ<Event> eventQueue = new PQ<>(new EventComparator());
        String outputString = "";
        int servedCustomer = 0;
        double waitingTime = 0;
        //Create serverList
        for (int i = 0; i < this.numOfServers; i++) {
            serverList = serverList.add(new Server(0.000, i + 1,0,this.qmax,this.restTime));
        }
        for (int i = 0; i < this.numberOfSelfChecks; i++) {
            serverList = serverList.add(new SelfCheckServer(0, 
                                                            this.numOfServers + 1 + i,
                                                            0,
                                                            this.qmax,
                                                            restTime));
        }
        //System.out.println(serverList);
        //Create CustomerList
        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            customerList = customerList.add(new Customer(this.arrivalTimes.get(i),
                                                         i + 1, 
                                                         serviceTime));
        }
        //add arrival to eventQueue
        for (int i = 0; i < this.arrivalTimes.size(); i++) {
            Arrival currentArrival = new Arrival(this.arrivalTimes.get(i), customerList.get(i));
            eventQueue = eventQueue.add(currentArrival);          
        }
        while (!eventQueue.isEmpty()) {
            Pair<Event, PQ<Event>> outputPair = eventQueue.poll(); //sort the first event out
            Event currentEvent = outputPair.first(); //save the first event
            // System.out.println(currentEvent);
            // for (Server server: serverList) {
            //     System.out.println(server);
            //     System.out.println(server.getAvailableTime());
            //     System.out.println(server.getWaitingNumber());
            // }
            eventQueue = outputPair.second(); //update the event queue
            outputString += currentEvent.toString(); //log the event
            if (currentEvent.isIntermediateEvent()) { //arrival, wait, serve
                Pair<Event, ImList<Server>> currentEventOutput = currentEvent.execute(serverList);
                Event nextEvent = currentEventOutput.first();
                ImList<Server> updatedServerList = currentEventOutput.second();
                eventQueue = eventQueue.add(nextEvent);
                serverList = updatedServerList;
            }
            if (currentEvent.finishEvent()) {
                servedCustomer++;
            } 
            waitingTime += currentEvent.getWaitingTime();
        }
        double averageWaitingTime = 0;
        if (servedCustomer != 0) {
            averageWaitingTime = waitingTime / (double) servedCustomer;
        } else {
            averageWaitingTime = 0.00;
        }
        outputString += "[" + 
                        String.format("%.3f", averageWaitingTime) + 
                        " " + 
                        servedCustomer + 
                        " " + 
                        (customerList.size() - servedCustomer) + "]";
        return outputString;
    }    
}
