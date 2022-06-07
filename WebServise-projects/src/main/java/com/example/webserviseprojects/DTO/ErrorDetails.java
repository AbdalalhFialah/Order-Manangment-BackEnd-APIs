package com.example.webserviseprojects.DTO;

import java.util.Date;
//this class helped to specify the error details
// When Error occur can return to user a usefull message that contains time and some details
public class ErrorDetails {
    private Date timestamp;
    private String message ;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
