package io.vendhan.social.service.impl;

import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.StatusDao;
import io.vendhan.social.dao.SubscriptionDao;
import io.vendhan.social.dao.constant.StatusEnum;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.entity.Status;
import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.entity.SubscriptionId;
import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.SubscriberDto;
import io.vendhan.social.model.SubscriptionDto;
import io.vendhan.social.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private StatusDao statusDao;

    @Override
    public boolean subscribe(SubscriptionDto subscriptionDto) throws Exception {
        String subscriberEmail = subscriptionDto.getRequestor();
        String publisherEmail = subscriptionDto.getTarget();
        return subscribe(subscriberEmail, publisherEmail);
    }

    @Override
    public boolean block(SubscriptionDto subscriptionDto) throws Exception {
        return false;
    }

    @Override
    public SubscriberDto getSubscribers(BroadcastDto broadcastDto) throws Exception {
        return null;
    }

    @Override
    public boolean isBlocked(
            String subscriberEmail, String publisherEmail) throws Exception {
        return subscriptionDao.isBlocked(subscriberEmail, publisherEmail);
    }

    private boolean subscribe(
            String subscriberEmail, String publisherEmail) throws Exception {
        Person subscriber = personDao.findByEmail(subscriberEmail);
        Person publisher = personDao.findByEmail(publisherEmail);
        Optional<Subscription> existingSubscription =
                subscriptionDao.getSubscription(subscriberEmail, publisherEmail);
        if(existingSubscription.isPresent()) {
            //Unblock
            if(StatusEnum.BLOCKED.getLabel().equals(
                    existingSubscription.get().getStatus().getLabel())) {
                reactivateSubscription(existingSubscription);
            }
        } else {
            activateSubscription(subscriber, publisher);
        }
        return true;
    }

    private void activateSubscription(Person subscriber, Person publisher) {
        Status status =
                statusDao.getJpaRepository().findByLabel(
                        StatusEnum.ACTIVE.getLabel()).get(0);
        Subscription subscription =
                new Subscription(publisher.getId(),
                        subscriber.getId(), status);
        subscriptionDao.getJpaRepository().saveAndFlush(subscription);
    }

    private void reactivateSubscription(Optional<Subscription> existingSubscription) {
        Status status =
                statusDao.getJpaRepository().findByLabel(
                        StatusEnum.ACTIVE.getLabel()).get(0);
        existingSubscription.get().setStatus(status);
        subscriptionDao.getJpaRepository().saveAndFlush(existingSubscription.get());
    }

}
