package com.e_commerce_project.commerce.error;

public class PersonErrorResponse {
    private int status;
    private String massage;
    private long timeStamp;

    public PersonErrorResponse(int status, String massage, long timeStamp) {
        this.status = status;
        this.massage = massage;
        this.timeStamp = timeStamp;
    }

    public PersonErrorResponse(){

    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMassage() {
        return massage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
