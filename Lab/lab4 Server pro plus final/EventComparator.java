import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    private static final double THRESHOLD = 0.00000001;

    public int compare(Event i, Event j) {
        //System.out.println(j.getTime() - i.getTime());
        if (j.getTime() - i.getTime() < THRESHOLD && i.getTime() - j.getTime() < THRESHOLD) {
            return i.getCustomer().getCustomerNumber() - 
                   j.getCustomer().getCustomerNumber();
        } else {
            if (i.getTime() < j.getTime()) {
                return -1;
            }
            if (i.getTime() > j.getTime()) {
                return 1;
            }
            return 0;
        }
    }
}
