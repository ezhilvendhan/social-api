package io.vendhan.social.dao.impl;

import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.SubscriptionDao;
import io.vendhan.social.dao.constant.QueryConstant;
import io.vendhan.social.dao.constant.StatusEnum;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.entity.SubscriptionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class SubscriptionDaoImpl extends SubscriptionDao {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Subscription> getSubscriptions(String publisherEmail) throws Exception {
        Person publisher = personDao.findByEmail(publisherEmail);
        return this.getJpaRepository().findByPublisher(publisher.getId());
    }

    @Override
    public Optional<Subscription> getSubscription(
            String subscriberEmail, String publisherEmail) throws Exception {
        SubscriptionId subscriptionId = getSubscriptionId(
                subscriberEmail, publisherEmail);
        return this.getJpaRepository().findById(subscriptionId);
    }

    @Override
    public boolean isBlocked(
            String subscriberEmail, String publisherEmail) throws Exception {
        SubscriptionId subscriptionId = getSubscriptionId(
                subscriberEmail, publisherEmail);
        Optional<Subscription> subscription =
                this.getJpaRepository().findById(subscriptionId);
        if(subscription.isPresent()) {
            return !StatusEnum.ACTIVE.getLabel().equals(
                        subscription.get().getStatus().getLabel());
        }
        return false;

    }

    @Override
    public List<String> getSubscribers(String publisherEmail) throws Exception {
        Query query = entityManager.createNativeQuery(QueryConstant.GET_SUBSCRIBERS);
        query.setParameter("publisher", publisherEmail);

        return ((List<String>)query.getResultList());
    }

    private SubscriptionId getSubscriptionId(String subscriberEmail, String publisherEmail) {
        Person publisher = personDao.findByEmail(publisherEmail);
        Person subscriber = personDao.findByEmail(subscriberEmail);
        return new SubscriptionId(publisher.getId(), subscriber.getId());
    }
}
