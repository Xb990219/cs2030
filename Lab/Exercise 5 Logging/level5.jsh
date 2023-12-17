public Log<Integer> sum(int n) {
    return sumRecursive(n, 1, Log.<Integer>of(0, "hit base case!"));
}

public Log<Integer> sumRecursive(int n, int i, Log<Integer> currentLog) {
    if (i > n) {
        return currentLog;
    } else {
        return sumRecursive(n, 
                            i + 1, 
                            currentLog.flatMap(x -> Log.<Integer>of(x + i, 
                                                                    String.format("adding %d",
                                                                                  i))));
    }
}


Log<Integer> f(int n) {
    return fRecursive(Log.<Integer>of(n));
}

Log<Integer> fRecursive(Log<Integer> log) {
    if (log.getItem() == 1) {
        return log.flatMap(x -> Log.of(x, "1"));
    } else if (log.getItem() % 2 == 0) {
        int currentNumber = log.getItem();
        return fRecursive(log.flatMap(x -> Log.<Integer>of(currentNumber / 2, 
                                                           String.format("%d / 2", x))));
    } else {
        int currentNumber = log.getItem();
        return fRecursive(log.flatMap(x -> Log.<Integer>of(currentNumber * 3 + 1, 
                                                           String.format("3(%d) + 1", x))));
    }
}

