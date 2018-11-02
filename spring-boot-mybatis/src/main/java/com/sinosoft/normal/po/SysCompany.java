package com.sinosoft.normal.po;

import java.io.Serializable;
import java.util.Date;

public class SysCompany implements Serializable {
    private String comCode;

    private String areaCode;

    private String comLevel;

    private String comType;

    private String supComCode;

    private String hasSubCom;

    private String comCname;

    private String comAddress;

    private String comphone;

    private String validStatus;

    private Date createTime;

    private String createUserCode;

    private Date modifyTime;

    private String modifyUserCode;

    private String reverse1;

    private String reverse2;

    private String reverse3;

    private static final long serialVersionUID = 1L;

    public String getComCode() {
        return comCode;
    }

    public void setComCode(String comCode) {
        this.comCode = comCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getComLevel() {
        return comLevel;
    }

    public void setComLevel(String comLevel) {
        this.comLevel = comLevel;
    }

    public String getComType() {
        return comType;
    }

    public void setComType(String comType) {
        this.comType = comType;
    }

    public String getSupComCode() {
        return supComCode;
    }

    public void setSupComCode(String supComCode) {
        this.supComCode = supComCode;
    }

    public String getHasSubCom() {
        return hasSubCom;
    }

    public void setHasSubCom(String hasSubCom) {
        this.hasSubCom = hasSubCom;
    }

    public String getComCname() {
        return comCname;
    }

    public void setComCname(String comCname) {
        this.comCname = comCname;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComphone() {
        return comphone;
    }

    public void setComphone(String comphone) {
        this.comphone = comphone;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserCode() {
        return modifyUserCode;
    }

    public void setModifyUserCode(String modifyUserCode) {
        this.modifyUserCode = modifyUserCode;
    }

    public String getReverse1() {
        return reverse1;
    }

    public void setReverse1(String reverse1) {
        this.reverse1 = reverse1;
    }

    public String getReverse2() {
        return reverse2;
    }

    public void setReverse2(String reverse2) {
        this.reverse2 = reverse2;
    }

    public String getReverse3() {
        return reverse3;
    }

    public void setReverse3(String reverse3) {
        this.reverse3 = reverse3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comCode=").append(comCode);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", comLevel=").append(comLevel);
        sb.append(", comType=").append(comType);
        sb.append(", supComCode=").append(supComCode);
        sb.append(", hasSubCom=").append(hasSubCom);
        sb.append(", comCname=").append(comCname);
        sb.append(", comAddress=").append(comAddress);
        sb.append(", comphone=").append(comphone);
        sb.append(", validStatus=").append(validStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserCode=").append(createUserCode);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifyUserCode=").append(modifyUserCode);
        sb.append(", reverse1=").append(reverse1);
        sb.append(", reverse2=").append(reverse2);
        sb.append(", reverse3=").append(reverse3);
        sb.append("]");
        return sb.toString();
    }
}