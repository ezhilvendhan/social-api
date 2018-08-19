package io.vendhan.social.util;

import io.vendhan.social.constant.ValidationConstant;
import io.vendhan.social.exception.InvalidInputException;
import io.vendhan.social.model.BroadcastDto;
import io.vendhan.social.model.FriendshipDto;
import io.vendhan.social.model.PersonDto;
import io.vendhan.social.model.SubscriptionDto;

public enum ValidationUtil {
    ;

    public static boolean isValidFriendshipDto(FriendshipDto friendshipDto) {
        if(null != friendshipDto && null != friendshipDto.getFriends()) {
            if(!friendshipDto.getFriends().isEmpty()) {
                if(friendshipDto.getFriends().size() > 1) {
                    return true;
                }
            }
        }
        throw new InvalidInputException(ValidationConstant.INVALID_FRIENDS);
    }

    public static boolean isValidPersonDto(PersonDto personDto) {
        if(null != personDto &&
                StringUtil.isNotBlank(personDto.getEmail())) {
            return true;
        }
        throw new InvalidInputException(ValidationConstant.EMAIL);
    }

    public static boolean isValidSubscriptionDto(SubscriptionDto subscriptionDto) {
        if(null != subscriptionDto) {
            if(StringUtil.isNotBlank(subscriptionDto.getRequestor()) &&
                    StringUtil.isNotBlank(subscriptionDto.getTarget())) {
                return true;
            }
        }
        throw new InvalidInputException(ValidationConstant.INVALID_SUBSCRIPTION_INPUT);
    }

    public static boolean isValidBroadcastDto(BroadcastDto broadcastDto) {
        if(null != broadcastDto) {
            if(StringUtil.isNotBlank(broadcastDto.getSender()) &&
                    StringUtil.isNotBlank(broadcastDto.getText())) {
                return true;
            }
        }
        throw new InvalidInputException(ValidationConstant.INVALID_BROADCAST_INPUT);
    }
}
