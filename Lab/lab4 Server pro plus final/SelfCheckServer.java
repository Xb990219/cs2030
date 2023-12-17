import java.util.function.Supplier;

public class SelfCheckServer extends Server {

    public SelfCheckServer(double availableTime,
                  int serverNumber,
                  int waitingNumber,
                  int maximumQueueNumber,
                  Supplier<Double> restTime) {
        super(availableTime, serverNumber, waitingNumber, maximumQueueNumber, restTime);
    }

    @Override
    public SelfCheckServer restServer() {
        return this;
    }

    @Override
    public boolean isSelfCheck() {
        return true;
    }

    @Override
    public SelfCheckServer waitOneLess(double nextAvailableTime) {
        return new SelfCheckServer(nextAvailableTime,
                          this.serverNumber,
                          this.waitingNumber - 1,
                          this.maximumQueueNumber,
                          this.restTime);
    }

    @Override
    public SelfCheckServer waitNoChange(double nextAvailableTime) {
        return new SelfCheckServer(nextAvailableTime,
                          this.serverNumber,
                          this.waitingNumber,
                          this.maximumQueueNumber,
                          this.restTime);
    }

    @Override
    public SelfCheckServer waitOneMore() {
        return new SelfCheckServer(this.availableTime,
                          this.serverNumber,
                          this.waitingNumber + 1,
                          this.maximumQueueNumber,
                          this.restTime);
    }

    @Override
    public String toString() {
        return "self-check " + this.getServerNumber();
    }

}
