package io.vendhan.social.model;

public class Response {
    private boolean success = true;

    public Response() {
    }

    public Response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
