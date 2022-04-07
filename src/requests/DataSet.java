package requests;

import java.util.*;

public class DataSet {

    private static SortedSet<Request> requests = new TreeSet<>(Request.comparator);
    private static SortedSet<Request> userRequests = new TreeSet<>(Request.comparator);

    public static SortedSet<Request> getRequests() {
        return requests;
    }

    public static SortedSet<Request> getUserRequests() {
        return userRequests;
    }

    public static void insertUser(String request) {

        for (Request r : userRequests) {
            if (r.getRequest().equals(request)) {
                userRequests.add(new Request(r.getRequest(), r.getRelevace() + 1));
                userRequests.remove(r);
                return;
            }
        }

        userRequests.add(new Request(request, 1d));
    }

    public static void insertGeneral(String request, Double relevance) {
        requests.add(new Request(request, relevance));
    }


}
