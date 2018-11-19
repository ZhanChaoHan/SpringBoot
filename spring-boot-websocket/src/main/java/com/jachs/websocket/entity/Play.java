package com.jachs.websocket.entity;

import java.io.Serializable;

public class Play implements Serializable {
    private String ids;

    private String gameid;

    private String playuser;

    private String timeconsuming;

    private String statustype;

    private String mess;

    private static final long serialVersionUID = 1L;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids == null ? null : ids.trim();
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid == null ? null : gameid.trim();
    }

    public String getPlayuser() {
        return playuser;
    }

    public void setPlayuser(String playuser) {
        this.playuser = playuser == null ? null : playuser.trim();
    }

    public String getTimeconsuming() {
        return timeconsuming;
    }

    public void setTimeconsuming(String timeconsuming) {
        this.timeconsuming = timeconsuming == null ? null : timeconsuming.trim();
    }

    public String getStatustype() {
        return statustype;
    }

    public void setStatustype(String statustype) {
        this.statustype = statustype == null ? null : statustype.trim();
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess == null ? null : mess.trim();
    }
}