package io.vendhan.social.model;

import io.vendhan.social.constant.ValidationConstant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonDto {

    @NotBlank(message = ValidationConstant.EMAIL)
    @Email(message = ValidationConstant.EMAIL)
    private String email;

    public PersonDto() {}

    public PersonDto(@NotBlank(message = ValidationConstant.EMAIL)
                     @Email(message = ValidationConstant.EMAIL) String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
