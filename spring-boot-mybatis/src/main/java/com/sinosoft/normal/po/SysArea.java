package com.sinosoft.normal.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SysArea implements Serializable {
    private String areaCode;

    private String supAreaCode;

    private String hasSubArea;

    private String areaCname;

    private String validStatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")  
    private Date createTime;

    private String createUserCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")  
    private Date modifyTime;

    private String modifyUserCode;

    private String reverse1;

    private String reverse2;

    private String reverse3;

    private static final long serialVersionUID = 1L;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSupAreaCode() {
        return supAreaCode;
    }

    public void setSupAreaCode(String supAreaCode) {
        this.supAreaCode = supAreaCode;
    }

    public String getHasSubArea() {
        return hasSubArea;
    }

    public void setHasSubArea(String hasSubArea) {
        this.hasSubArea = hasSubArea;
    }

    public String getAreaCname() {
        return areaCname;
    }

    public void setAreaCname(String areaCname) {
        this.areaCname = areaCname;
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
        sb.append(", areaCode=").append(areaCode);
        sb.append(", supAreaCode=").append(supAreaCode);
        sb.append(", hasSubArea=").append(hasSubArea);
        sb.append(", areaCname=").append(areaCname);
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