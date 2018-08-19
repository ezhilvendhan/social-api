package io.vendhan.social.service;

import io.vendhan.social.BaseTest;
import io.vendhan.social.dao.SubscriptionDao;
import io.vendhan.social.dao.entity.Subscription;
import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.SubscriberDto;
import io.vendhan.social.model.SubscriptionDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubscriptionServiceTest extends BaseTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private SubscriptionDao subscriptionDao;

    @After
    public void tearDown() {
        subscriptionDao.deleteAll();
    }

    @Test
    public void testSubscribe() throws Exception {
        SubscriptionDto subscriptionDto =
                new SubscriptionDto(
                        "lisa@example.com", "john@example.com");

        Assert.assertTrue("Subscription failed",
                subscriptionService.subscribe(subscriptionDto));

        Optional<Subscription> subscription =
                subscriptionDao.getSubscription(
                        subscriptionDto.getRequestor(),
                        subscriptionDto.getTarget());

        Assert.assertTrue("Subscription must not be null",
                subscription.isPresent());
        Assert.assertEquals("lisa@example.com must be subscriber",
                "lisa@example.com",
                subscription.get().getSubscriber().getEmail());
        Assert.assertEquals("john@example.com must be publisher",
                "john@example.com",
                subscription.get().getPublisher().getEmail());
        Assert.assertEquals("Subscription status must be ACTIVE",
                "ACTIVE",
                subscription.get().getStatus().getLabel());
    }

    @Test
    public void testBlock() throws Exception {
        SubscriptionDto subscriptionDto =
                new SubscriptionDto(
                        "lisa@example.com", "john@example.com");
        Assert.assertTrue("Subscription failed",
                subscriptionService.subscribe(subscriptionDto));

        subscriptionDto = new SubscriptionDto(
                        "lisa@example.com", "john@example.com");
        Assert.assertTrue("Block failed",
                subscriptionService.block(subscriptionDto));

        Optional<Subscription> subscription =
                subscriptionDao.getSubscription(
                        subscriptionDto.getRequestor(),
                        subscriptionDto.getTarget());

        Assert.assertTrue("Subscription must not be null",
                subscription.isPresent());
        Assert.assertEquals("lisa@example.com must be subscriber",
                "lisa@example.com",
                subscription.get().getSubscriber().getEmail());
        Assert.assertEquals("john@example.com must be publisher",
                "john@example.com",
                subscription.get().getPublisher().getEmail());
        Assert.assertEquals("Subscription status must be BLOCKED",
                "BLOCKED",
                subscription.get().getStatus().getLabel());
    }

    @Test
    public void testGetSubscribers() throws Exception {
        testBlock();

        SubscriptionDto subscriptionDto =
                new SubscriptionDto(
                        "user2@example.com", "john@example.com");
        Assert.assertTrue("Subscription failed",
                subscriptionService.subscribe(subscriptionDto));

        BroadcastDto broadcastDto =
                new BroadcastDto(
                        "john@example.com",
                        "Hi kate@example.com, lisa@example.com");

        SubscriberDto subscriberDto =
                subscriptionService.getSubscribers(broadcastDto);

        Assert.assertNotNull(
                "SubscriberDto must not be null", subscriberDto);
        Assert.assertEquals("No. of subscribers not equal to 2",
                2, subscriberDto.getRecepients().size());
        Assert.assertEquals(
                "user2@example.com must be a subscriber",
                "user2@example.com", subscriberDto.getRecepients().get(0));
        Assert.assertEquals(
                "kate@example.com must be a subscriber",
                "kate@example.com", subscriberDto.getRecepients().get(1));
    }
}
