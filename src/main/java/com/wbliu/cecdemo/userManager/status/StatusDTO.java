package com.wbliu.cecdemo.userManager.status;

public class StatusDTO {
    private String status;
    private String message;

    public StatusDTO(){
        status = ReturnCodeEnum.DEFAULT_ERROR.toString();
        message = ReturnCodeEnum.DEFAULT_ERROR_DESC.toString();
    }

    public StatusDTO(String inputStatus, String inputDescription){
        status = inputStatus;
        message = inputDescription;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String description) {
        this.message = description;
    }
}
