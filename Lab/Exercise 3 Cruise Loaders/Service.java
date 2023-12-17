public class Service {
    private final Loader loader;
    private final Cruise cruise;

    public Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    int getServiceStartTime() {
        return this.cruise.getArrivalTime();
    }

    int getServiceEndTime() {
        return this.cruise.getArrivalTime() + 
               this.cruise.getServiceTime();
    }

    Loader getLoader() {
        return this.loader;
    }

    @Override
    public String toString() {
        return this.loader.toString() +
               " serving " + 
               this.cruise.toString();
    }
}
