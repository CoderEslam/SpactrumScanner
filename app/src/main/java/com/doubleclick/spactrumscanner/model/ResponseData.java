package com.doubleclick.spactrumscanner.model;

/**
 * Created By Eslam Ghazy on 1/3/2023
 */
public class ResponseData {

    private Response response;
    private String message;

    public ResponseData(Response response, String message) {
        this.response = response;
        this.message = message;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "response=" + response +
                ", message='" + message + '\'' +
                '}';
    }
}
