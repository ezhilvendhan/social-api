package io.vendhan.social.service;

import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.SubscriberDto;
import io.vendhan.social.model.SubscriptionDto;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService extends BaseService {

    boolean subscribe(SubscriptionDto subscriptionDto);

    boolean block(SubscriptionDto subscriptionDto);

    SubscriberDto getSubscribers(BroadcastDto broadcastDto);

}
