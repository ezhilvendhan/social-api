package io.vendhan.social.service;

import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.SubscriberDto;
import io.vendhan.social.model.SubscriptionDto;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService extends BaseService {

    boolean subscribe(SubscriptionDto subscriptionDto) throws Exception;

    boolean block(SubscriptionDto subscriptionDto) throws Exception;

    SubscriberDto getSubscribers(BroadcastDto broadcastDto) throws Exception;

    boolean isBlocked(
            String subscriberEmail, String publisherEmail) throws Exception;

}
