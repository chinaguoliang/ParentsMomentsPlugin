package com.jgkj.plugin.domain;

/**
 * Created by chen on 16/10/21.
 */

public class ResponseObj {
    String msg = "";
    boolean success;
    Object obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}