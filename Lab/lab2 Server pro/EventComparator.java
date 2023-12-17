import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    public int compare(Event i, Event j) {
        if (i.getTime() == j.getTime()) {
            if (i.getPriorityLevel() == j.getPriorityLevel()) {
                return i.getCustomer().getCustomerNumber() - 
                       j.getCustomer().getCustomerNumber();
            }
            return i.getPriorityLevel() - j.getPriorityLevel();
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
