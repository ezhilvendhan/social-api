package io.vendhan.social.model;

import io.vendhan.social.constant.ValidationConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SubscriptionDto {

    @NotBlank(message = ValidationConstant.EMAIL)
    @Email(message = ValidationConstant.EMAIL)
    private String requestor;

    @NotBlank(message = ValidationConstant.EMAIL)
    @Email(message = ValidationConstant.EMAIL)
    private String target;

    public SubscriptionDto() {}

    public SubscriptionDto(
            @NotBlank(message = ValidationConstant.EMAIL)
            @Email(message = ValidationConstant.EMAIL) String requestor,
            @NotBlank(message = ValidationConstant.EMAIL)
            @Email(message = ValidationConstant.EMAIL) String target) {
        this.requestor = requestor;
        this.target = target;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
