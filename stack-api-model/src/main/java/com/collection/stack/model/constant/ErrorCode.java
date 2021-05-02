package com.collection.stack.model.constant;

/**
 * Created by umang.mishra
 * on 02/05/21
 **/
public enum ErrorCode {
    STACK_EMPTY("Stack is empty"),
    STACK_FULL("Stack is Full");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return this.message;
    }
}
