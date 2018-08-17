package io.vendhan.social.controller;

import io.vendhan.social.constant.RequestUriConstant;
import io.vendhan.social.model.*;
import io.vendhan.social.service.FriendshipService;
import io.vendhan.social.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RequestUriConstant.USERS_BASE)
@Validated
public class UserController {

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(value = RequestUriConstant.CONNECT, method = RequestMethod.POST)
    public ResponseEntity<Response> connectFriends(
            @RequestBody @Valid FriendshipDto friends,
            BindingResult bindingResult) throws Exception {
        boolean result = friendshipService.connect(friends);
        return new ResponseEntity<>(
                new Response(result), HttpStatus.OK);
    }

    @RequestMapping(value = RequestUriConstant.FRIENDS, method = RequestMethod.POST)
    public ResponseEntity<Response> getDirectFriends(
            @RequestBody @Valid PersonDto user,
            BindingResult bindingResult) throws Exception {
        FriendshipDto friends = friendshipService.getFriends(user);
        return new ResponseEntity<>(
                new FriendshipResponse(friends), HttpStatus.OK);
    }

    @RequestMapping(value = RequestUriConstant.COMMON_FRIENDS, method = RequestMethod.POST)
    public ResponseEntity<Response> getCommonFriends(
            @RequestBody @Valid FriendshipDto friends,
            BindingResult bindingResult) throws Exception {
        FriendshipDto commonFriends = friendshipService.getCommonFriends(friends);
        return new ResponseEntity<>(
                new FriendshipResponse(commonFriends), HttpStatus.OK);
    }

    @RequestMapping(value = RequestUriConstant.SUBSCRIBE, method = RequestMethod.POST)
    public ResponseEntity<Response> subscribe(
            @RequestBody @Valid SubscriptionDto subscription,
            BindingResult bindingResult) throws Exception {
        boolean result = subscriptionService.subscribe(subscription);
        return new ResponseEntity<>(
                new Response(result), HttpStatus.OK);
    }

    @RequestMapping(value = RequestUriConstant.BLOCK, method = RequestMethod.POST)
    public ResponseEntity<Response> block(
            @RequestBody @Valid SubscriptionDto subscription,
            BindingResult bindingResult) throws Exception {
        boolean result = subscriptionService.block(subscription);
        return new ResponseEntity<>(
                new Response(result), HttpStatus.OK);
    }

    @RequestMapping(value = RequestUriConstant.SUBSCRIBERS, method = RequestMethod.POST)
    public ResponseEntity<Response> getSubscribers(
            @RequestBody @Valid BroadcastDto broadcastDto,
            BindingResult bindingResult) throws Exception {
        SubscriberDto subscribers = subscriptionService.getSubscribers(broadcastDto);
        return new ResponseEntity<>(
                new BroadcastResponse(subscribers), HttpStatus.OK);
    }
    
}
