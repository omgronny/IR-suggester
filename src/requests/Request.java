package requests;

import java.util.Comparator;

public class Request {
    private String request;
    private Double relevace;

    public static Comparator<Request> comparator = new Comparator<Request>() {
        @Override
        public int compare(Request o1, Request o2) {
            return (-1) * o1.getRelevace().compareTo(o2.getRelevace());
        }
    };

    public Request(String request, Double relevace) {
        this.request = request;
        this.relevace = relevace;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Double getRelevace() {
        return relevace;
    }

    public void setRelevace(Double relevace) {
        this.relevace = relevace;
    }
}
