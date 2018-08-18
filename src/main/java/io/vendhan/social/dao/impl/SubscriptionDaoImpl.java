package io.vendhan.social.dao.impl;

import io.vendhan.social.dao.PersonDao;
import io.vendhan.social.dao.SubscriptionDao;
import io.vendhan.social.dao.entity.Person;
import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.dao.entity.SubscriptionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        Person publisher = personDao.findByEmail(publisherEmail);
        Person subscriber = personDao.findByEmail(subscriberEmail);
        SubscriptionId subscriptionId =
                new SubscriptionId(publisher.getId(), subscriber.getId());
        return this.getJpaRepository().findById(subscriptionId);
    }
}
