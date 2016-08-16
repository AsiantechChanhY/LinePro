package com.example.macbookpro.linepro.model;


/**
 * Created by macbookpro on 8/15/16.
 */
public class UploadAvatar {
    private boolean success;
    private String status;
    private String token;



    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
