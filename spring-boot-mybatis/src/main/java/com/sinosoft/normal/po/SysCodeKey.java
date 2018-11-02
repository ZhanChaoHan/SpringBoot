package com.sinosoft.normal.po;

import java.io.Serializable;

public class SysCodeKey implements Serializable {
    private String CodeType;

    private String codeCode;

    private static final long serialVersionUID = 1L;

    public String getCodeType() {
        return CodeType;
    }

    public void setCodeType(String CodeType) {
        this.CodeType = CodeType;
    }

    public String getCodeCode() {
        return codeCode;
    }

    public void setCodeCode(String codeCode) {
        this.codeCode = codeCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", CodeType=").append(CodeType);
        sb.append(", codeCode=").append(codeCode);
        sb.append("]");
        return sb.toString();
    }
}