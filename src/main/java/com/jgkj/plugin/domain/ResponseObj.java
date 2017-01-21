package com.jgkj.plugin.domain;

/**
 * Created by chen on 16/10/21.
 */

public class ResponseObj {
    String msg = "";
    boolean success;
    Object obj;
    Object videoInfo;
    Object videoControllList;

    public Object getVideoControllList() {
        return videoControllList;
    }

    public void setVideoControllList(Object videoControllList) {
        this.videoControllList = videoControllList;
    }

    public Object getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(Object videoInfo) {
        this.videoInfo = videoInfo;
    }

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