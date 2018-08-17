package io.vendhan.social.model;

import java.util.List;

public class BroadcastResponse extends Response {
    private List<String> recepients;

    private int count = 0;

    public BroadcastResponse(SubscriberDto subscriberDto) {
        this.recepients = subscriberDto.getRecepients();
        if(null != this.recepients) {
            this.count = this.recepients.size();
        }
    }

    public List<String> getRecepients() {
        return recepients;
    }

    public void setRecepients(List<String> recepients) {
        this.recepients = recepients;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
