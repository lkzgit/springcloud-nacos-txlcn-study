package com.lcn.order.model;

import lombok.Data;

@Data
public class ResponseData {

    public static final int STATUS_OK = 200;
    public static final int STATUS_FAILED = 10001;

    private int statusCode;
    private Object data;
    private String message;

    public ResponseData()
    {
        this.statusCode = STATUS_OK;
        this.data = "";
        this.message = "";
    }

    public ResponseData(int statusCode, Object data, String message)
    {
        this.statusCode = statusCode;
        this.data = data;
        this.message = message;
    }
}
