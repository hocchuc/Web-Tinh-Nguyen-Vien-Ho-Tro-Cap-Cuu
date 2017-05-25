package com.emc.emergency.web;

public class FlashMessage {
    private Type_Mess type;
    private String message;
    private Status status;

    public FlashMessage(Type_Mess type, String message, Status status) {
        this.type = type;
        this.message = message;
        this.status = status;
    }

    public Type_Mess getType() {
        return type;
    }

    public void setType(Type_Mess type) {
        this.type = type;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public static enum Status {
        SUCCESS, FAILURE
    }
    public static enum Type_Mess {
        SYSTEM, LOGIN, REGISTER,CREATED
    }

    @Override
    public String toString() {
        return "FlashMessage{" +
                "type=" + type +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
