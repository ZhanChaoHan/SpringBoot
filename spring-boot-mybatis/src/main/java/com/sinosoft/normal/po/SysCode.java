package com.sinosoft.normal.po;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SysCode extends SysCodeKey implements Serializable {
	private String CodeType;

	private String codeCode;
	
    private String codeCname;

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

    public String getCodeType() {
		return CodeType;
	}

	public void setCodeType(String codeType) {
		CodeType = codeType;
	}

	public String getCodeCode() {
		return codeCode;
	}

	public void setCodeCode(String codeCode) {
		this.codeCode = codeCode;
	}

	public String getCodeCname() {
        return codeCname;
    }

    public void setCodeCname(String codeCname) {
        this.codeCname = codeCname;
    }

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Override
	public String toString() {
		return "SysCode [CodeType=" + CodeType + ", codeCode=" + codeCode + ", codeCname=" + codeCname
				+ ", validStatus=" + validStatus + ", createTime=" + createTime + ", createUserCode=" + createUserCode
				+ ", modifyTime=" + modifyTime + ", modifyUserCode=" + modifyUserCode + ", reverse1=" + reverse1
				+ ", reverse2=" + reverse2 + ", reverse3=" + reverse3 + "]";
	}


}