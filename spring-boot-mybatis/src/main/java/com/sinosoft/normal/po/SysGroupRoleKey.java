package com.sinosoft.normal.po;

import java.io.Serializable;

public class SysGroupRoleKey implements Serializable {
    private String groupCode;

    private String roleCode;

    private static final long serialVersionUID = 1L;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", groupCode=").append(groupCode);
        sb.append(", roleCode=").append(roleCode);
        sb.append("]");
        return sb.toString();
    }
}