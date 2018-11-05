package com.jachs.mybatis.entity;

import java.io.Serializable;

public class Tb2 implements Serializable {
    private String aa;

    private String bb;

    private String cc;

    private static final long serialVersionUID = 1L;

    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa == null ? null : aa.trim();
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb == null ? null : bb.trim();
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc == null ? null : cc.trim();
    }
}