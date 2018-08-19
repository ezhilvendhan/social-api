package io.vendhan.social.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.vendhan.social.constant.RequestUriConstant;
import io.vendhan.social.model.*;
import io.vendhan.social.service.FriendshipService;
import io.vendhan.social.service.SubscriptionService;
import io.vendhan.social.util.ValidationUtil;
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

    @ApiOperation(value="Create a friend connection between two email addresses",
        response = Response.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=422, message="Unprocessable Entity"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = RequestUriConstant.CONNECT, method = RequestMethod.POST)
    public ResponseEntity<Response> connectFriends(
            @RequestBody @Valid FriendshipDto friends,
            BindingResult bindingResult) throws Exception {
        ValidationUtil.isValidFriendshipDto(friends);
        boolean result = friendshipService.connect(friends);
        return new ResponseEntity<>(
                new Response(result), HttpStatus.OK);
    }


    @ApiOperation(value = "Retrieve the friends list for an email address",
            response = FriendshipResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=422, message="Unprocessable Entity"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = RequestUriConstant.FRIENDS, method = RequestMethod.POST)
    public ResponseEntity<Response> getDirectFriends(
            @RequestBody @Valid PersonDto user,
            BindingResult bindingResult) throws Exception {
        ValidationUtil.isValidPersonDto(user);
        FriendshipDto friends = friendshipService.getFriends(user);
        return new ResponseEntity<>(
                new FriendshipResponse(friends), HttpStatus.OK);
    }


    @ApiOperation(value = "Retrieve the common friends list between two email addresses",
        response = FriendshipResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=422, message="Unprocessable Entity"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = RequestUriConstant.COMMON_FRIENDS, method = RequestMethod.POST)
    public ResponseEntity<Response> getCommonFriends(
            @RequestBody @Valid FriendshipDto friends,
            BindingResult bindingResult) throws Exception {
        ValidationUtil.isValidFriendshipDto(friends);
        FriendshipDto commonFriends = friendshipService.getCommonFriends(friends);
        return new ResponseEntity<>(
                new FriendshipResponse(commonFriends), HttpStatus.OK);
    }


    @ApiOperation(value = "Subscribe to updates from an email address",
        response = Response.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=422, message="Unprocessable Entity"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = RequestUriConstant.SUBSCRIBE, method = RequestMethod.POST)
    public ResponseEntity<Response> subscribe(
            @RequestBody @Valid SubscriptionDto subscription,
            BindingResult bindingResult) throws Exception {
        ValidationUtil.isValidSubscriptionDto(subscription);
        boolean result = subscriptionService.subscribe(subscription);
        return new ResponseEntity<>(
                new Response(result), HttpStatus.OK);
    }


    @ApiOperation(value = "Block updates from an email address",
        response = Response.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=422, message="Unprocessable Entity"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = RequestUriConstant.BLOCK, method = RequestMethod.POST)
    public ResponseEntity<Response> block(
            @RequestBody @Valid SubscriptionDto subscription,
            BindingResult bindingResult) throws Exception {
        ValidationUtil.isValidSubscriptionDto(subscription);
        boolean result = subscriptionService.block(subscription);
        return new ResponseEntity<>(
                new Response(result), HttpStatus.OK);
    }
    

    @ApiOperation(value = "Retrieve all email addresses that can receive updates from an email address",
        response = BroadcastResponse.class)
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=404, message="Not Found"),
            @ApiResponse(code=422, message="Unprocessable Entity"),
            @ApiResponse(code=500, message="Internal Server Error")
    })
    @RequestMapping(value = RequestUriConstant.SUBSCRIBERS, method = RequestMethod.POST)
    public ResponseEntity<Response> getSubscribers(
            @RequestBody @Valid BroadcastDto broadcastDto,
            BindingResult bindingResult) throws Exception {
        ValidationUtil.isValidBroadcastDto(broadcastDto);
        SubscriberDto subscribers = subscriptionService.getSubscribers(broadcastDto);
        return new ResponseEntity<>(
                new BroadcastResponse(subscribers), HttpStatus.OK);
    }
    
}
