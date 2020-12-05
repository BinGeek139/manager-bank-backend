package com.ptit.managerbank.common;

public class ResponseData {

    private String errorCode;
    private String message;
    private Object data;
    public ResponseData() {
    }
    public static ResponseData ofSuccess() {
        ResponseData responseData = new ResponseData();
        responseData.setErrorCode(Constants.ERROR_CODE.SUCCESS);
        return responseData;
    }
    public static ResponseData ofNotFound(String  message) {
        ResponseData responseData = new ResponseData();
        responseData.setErrorCode(Constants.ERROR_CODE.NOT_FOUND);
        responseData.setMessage(message);
        return responseData;
    }
    public static ResponseData ofSuccess(String message) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setErrorCode(Constants.ERROR_CODE.SUCCESS);
        return responseData;
    }

    public static ResponseData ofSuccess(String message, Object data) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setData(data);
        responseData.setErrorCode(Constants.ERROR_CODE.SUCCESS);
        return responseData;
    }

    public ResponseData(String errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseData(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResponseData(String errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public static ResponseData ofFail(String message) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setErrorCode(Constants.ERROR_CODE.FAIL);
        return responseData;
    }
    public static ResponseData ofFail(String message,String errorCode) {
        ResponseData responseData = new ResponseData();
        responseData.setMessage(message);
        responseData.setErrorCode(errorCode);
        return responseData;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "ResponseData{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
