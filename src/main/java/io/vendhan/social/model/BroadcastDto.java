package io.vendhan.social.model;

import io.vendhan.social.constant.ValidationConstant;

import javax.validation.constraints.NotBlank;

public class BroadcastDto {
    @NotBlank(message = ValidationConstant.EMAIL)
    private String sender;

    @NotBlank(message = ValidationConstant.TEXT)
    private String text;

    public BroadcastDto() {
    }

    public BroadcastDto(
            @NotBlank(message = ValidationConstant.EMAIL) String sender,
            @NotBlank(message = ValidationConstant.TEXT) String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
