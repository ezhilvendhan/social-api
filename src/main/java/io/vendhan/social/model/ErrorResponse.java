package io.vendhan.social.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse extends Response {
    private boolean success = false;

    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if(null == this.errors) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

    public void addAll(List errors) {
        if(null == this.errors) {
            this.errors = errors;
        } else {
            this.errors.addAll(errors);
        }
    }
}

