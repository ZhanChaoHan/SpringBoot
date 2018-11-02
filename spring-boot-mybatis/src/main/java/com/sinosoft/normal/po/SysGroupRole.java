package com.sinosoft.normal.po;

import java.io.Serializable;
import java.util.Date;

public class SysGroupRole extends SysGroupRoleKey implements Serializable {
    private String validStatus;

    private String createUserCode;

    private Date createTime;

    private String reverse1;

    private String reverse2;

    private String reverse3;

    private static final long serialVersionUID = 1L;

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", validStatus=").append(validStatus);
        sb.append(", createUserCode=").append(createUserCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", reverse1=").append(reverse1);
        sb.append(", reverse2=").append(reverse2);
        sb.append(", reverse3=").append(reverse3);
        sb.append("]");
        return sb.toString();
    }
}