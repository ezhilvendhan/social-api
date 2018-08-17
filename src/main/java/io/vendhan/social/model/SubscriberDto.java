package io.vendhan.social.model;

import io.vendhan.social.constant.ValidationConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SubscriberDto {

    @NotNull(message = ValidationConstant.NO_EMAIL)
    private List<@Email(message = ValidationConstant.EMAIL) String> recepients;

    public SubscriberDto() {}

    public SubscriberDto(
            @NotNull(message = ValidationConstant.NO_EMAIL)
                    List<@Email(message = ValidationConstant.EMAIL) String> friends) {
        this.recepients = friends;
    }

    public List<String> getRecepients() {
        return recepients;
    }

    public void setRecepients(List<String> recepients) {
        this.recepients = recepients;
    }
}
