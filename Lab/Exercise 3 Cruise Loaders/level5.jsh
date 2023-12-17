ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> serviceQueue = new ImList<>();
    ImList<Service> activeServiceQueue = new ImList<>();
    ImList<Service> expireServiceQueue = new ImList<>();
    int ONE_HOUR = 60;
    int loaderNumber = 1;
    int index = 0;
    
    for (Cruise cruise : cruises) {
        int loaderNeeded = cruise.getNumOfLoadersRequired();
        while (index < activeServiceQueue.size()) {
            //System.out.println(activeServiceQueue.get(index).getServiceEndTime());
            if (cruise.getArrivalTime() >= activeServiceQueue.get(index).getServiceEndTime()) {
                expireServiceQueue = expireServiceQueue.add(activeServiceQueue.get(index));
                activeServiceQueue = activeServiceQueue.remove(index);
            } else {
                index++;
            }
        }

        for (int i = 0; i < loaderNeeded; i++) {
            if (!expireServiceQueue.isEmpty()) {
                if (expireServiceQueue.get(0).getLoader().getIdentifier() % 3 != 0) {
                    Service newService = new Service(expireServiceQueue.get(0).getLoader(), cruise);
                    activeServiceQueue = activeServiceQueue.add(newService);
                    expireServiceQueue = expireServiceQueue.remove(0);
                    serviceQueue = serviceQueue.add(newService);
                } else {
                    Cruise hiddenCruise = new Cruise(cruise.getIdentifier(),
                                                     cruise.getOriginalArrivalTime(),
                                                     cruise.getNumOfLoadersRequired(),
                                                     cruise.getServiceTime() + ONE_HOUR);
                    Service newService = new Service(expireServiceQueue.get(0).getLoader(), hiddenCruise);
                    activeServiceQueue = activeServiceQueue.add(newService);
                    expireServiceQueue = expireServiceQueue.remove(0);
                    serviceQueue = serviceQueue.add(newService);
                }
            } else {
                if (loaderNumber % 3 != 0){
                    Service newService = new Service(new Loader(loaderNumber), cruise);
                    activeServiceQueue = activeServiceQueue.add(newService);
                    serviceQueue = serviceQueue.add(newService);
                    loaderNumber++;
                } else {
                    Cruise hiddenCruise = new Cruise(cruise.getIdentifier(),
                                                     cruise.getOriginalArrivalTime(),
                                                     cruise.getNumOfLoadersRequired(),
                                                     cruise.getServiceTime() + ONE_HOUR);
                    Service newService = new Service(new RecycledLoader(loaderNumber), hiddenCruise);
                    activeServiceQueue = activeServiceQueue.add(newService);
                    serviceQueue = serviceQueue.add(newService);
                    loaderNumber++;
                }
            }
        }
        index = 0;
    }
    return serviceQueue;
}