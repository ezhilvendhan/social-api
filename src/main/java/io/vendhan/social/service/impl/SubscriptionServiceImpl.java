package io.vendhan.social.service.impl;

import io.vendhan.social.dao.StatusDao;
import io.vendhan.social.dao.SubscriptionDao;
import io.vendhan.social.dao.constant.StatusEnum;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.entity.Status;
import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.SubscriberDto;
import io.vendhan.social.model.SubscriptionDto;
import io.vendhan.social.service.FriendshipService;
import io.vendhan.social.service.PersonService;
import io.vendhan.social.service.SubscriptionService;
import io.vendhan.social.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionDao subscriptionDao;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private PersonService personService;

    @Autowired
    private StatusDao statusDao;

    /**
     * Subscribe notifications using emails of the publisher and subscriber
     * @param subscriptionDto
     * @return
     * @throws Exception
     */
    @Override
    public boolean subscribe(SubscriptionDto subscriptionDto) throws Exception {
        String subscriberEmail = subscriptionDto.getRequestor();
        String publisherEmail = subscriptionDto.getTarget();
        return subscribe(subscriberEmail, publisherEmail);
    }

    /**
     * Blocks notifications from a specific publisher
     * @param subscriptionDto
     * @return
     * @throws Exception
     */
    @Override
    public boolean block(SubscriptionDto subscriptionDto) throws Exception {
        String subscriberEmail = subscriptionDto.getRequestor();
        String publisherEmail = subscriptionDto.getTarget();
        Optional<Subscription> existingSubscription =
                subscriptionDao.getSubscription(subscriberEmail, publisherEmail);
        if(existingSubscription.isPresent()) {
            return changeSubscription(existingSubscription, StatusEnum.BLOCKED);
        } else if(friendshipService.isFriends(subscriberEmail, publisherEmail)) {
            Person subscriber = personService.getPersonFromEmail(subscriberEmail);
            Person publisher = personService.getPersonFromEmail(publisherEmail);
            return createSubscription(
                    subscriber, publisher, StatusEnum.BLOCKED);
        }
        return false;
    }

    /**
     * Get list of subscribers
     *  - Eligibility for receiving updates from i.e. "john@example.com":
            - has not blocked updates from "john@example.com", and
            - at least one of the following:
                - has a friend connection with "john@example.com"
                - has subscribed to updates from "john@example.com"
                - has been @mentioned in the update
     * @param broadcastDto
     * @return
     * @throws Exception
     */
    @Override
    public SubscriberDto getSubscribers(BroadcastDto broadcastDto) throws Exception {
        final List<String> subscribers =
                subscriptionDao.getSubscribers(broadcastDto.getSender());
        StringUtil.getEmailsInText(broadcastDto.getText())
            .stream()
            .forEach(email -> {
                try {
                    if(!isBlocked(email, broadcastDto.getSender())) {
                        subscribers.add(email);
                    }
                } catch(Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
        return new SubscriberDto(subscribers);
    }

    @Override
    public boolean isBlocked(
            String subscriberEmail, String publisherEmail) throws Exception {
        return subscriptionDao.isBlocked(subscriberEmail, publisherEmail);
    }

    private boolean subscribe(
            String subscriberEmail, String publisherEmail) throws Exception {
        Person subscriber = personService.getPersonFromEmail(subscriberEmail);
        Person publisher = personService.getPersonFromEmail(publisherEmail);
        Optional<Subscription> existingSubscription =
                subscriptionDao.getSubscription(subscriberEmail, publisherEmail);
        if(existingSubscription.isPresent()) {
            //Unblock
            if(StatusEnum.BLOCKED.getLabel().equals(
                    existingSubscription.get().getStatus().getLabel())) {
                return changeSubscription(existingSubscription, StatusEnum.ACTIVE);
            }
        } else {
            return createSubscription(subscriber, publisher, StatusEnum.ACTIVE);
        }
        return false;
    }

    private boolean createSubscription(
            Person subscriber, Person publisher, StatusEnum statusType) {
        Status status =
                statusDao.getJpaRepository().findByLabel(
                        statusType.getLabel()).get(0);
        Subscription subscription =
                new Subscription(publisher.getId(),
                        subscriber.getId(), status);
        subscriptionDao.getJpaRepository().saveAndFlush(subscription);
        return true;
    }

    private boolean changeSubscription(
            Optional<Subscription> existingSubscription, StatusEnum type) {
        Status status =
                statusDao.getJpaRepository().findByLabel(type.getLabel()).get(0);
        existingSubscription.get().setStatus(status);
        subscriptionDao.getJpaRepository().saveAndFlush(existingSubscription.get());
        return true;
    }

}
