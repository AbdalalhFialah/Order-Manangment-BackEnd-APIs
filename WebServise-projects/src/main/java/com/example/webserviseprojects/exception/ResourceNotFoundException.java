package com.example.webserviseprojects.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String feildName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName, String feildName, long fieldValue){
        super(String.format( "%s not found with %s :'%s'",resourceName,feildName,fieldValue)); // Post not found with id : 1
        this.resourceName = resourceName;
        this.feildName = feildName;
        this.fieldValue = fieldValue;

    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFeildName() {
        return feildName;
    }
    public long getFieldValue() {
        return fieldValue;
    }

}
