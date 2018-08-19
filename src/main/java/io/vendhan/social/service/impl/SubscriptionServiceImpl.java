package io.vendhan.social.service.impl;

import io.vendhan.social.dao.SubscriptionDao;
import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.SubscriberDto;
import io.vendhan.social.model.SubscriptionDto;
import io.vendhan.social.service.BaseService;
import io.vendhan.social.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Override
    public boolean subscribe(SubscriptionDto subscriptionDto) {

    }

    @Override
    public boolean block(SubscriptionDto subscriptionDto) {
        return false;
    }

    @Override
    public SubscriberDto getSubscribers(BroadcastDto broadcastDto) {
        return null;
    }

    @Override
    public boolean isBlocked(
            String subscriberEmail, String publisherEmail) throws Exception {
        return subscriptionDao.isBlocked(subscriberEmail, publisherEmail);
    }

}
