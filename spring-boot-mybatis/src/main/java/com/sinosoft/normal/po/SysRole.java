package com.sinosoft.normal.po;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    private String roleCode;

	private String upperRoleCode;

	private String hasSubRole;

	private String roleName;

	private String roleType;

	private String validStatus;

	private Date createTime;

	private String createUserCode;

	private Date modifyTime;

	private String modifyUserCode;

	private String url;

	private String reverse1;

	private String reverse2;

	private String reverse3;

	private static final long serialVersionUID = 1L;

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getUpperRoleCode() {
		return upperRoleCode;
	}

	public void setUpperRoleCode(String upperRoleCode) {
		this.upperRoleCode = upperRoleCode;
	}

	public String getHasSubRole() {
		return hasSubRole;
	}

	public void setHasSubRole(String hasSubRole) {
		this.hasSubRole = hasSubRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		sb.append(", roleCode=").append(roleCode);
		sb.append(", upperRoleCode=").append(upperRoleCode);
		sb.append(", hasSubRole=").append(hasSubRole);
		sb.append(", roleName=").append(roleName);
		sb.append(", roleType=").append(roleType);
		sb.append(", validStatus=").append(validStatus);
		sb.append(", createTime=").append(createTime);
		sb.append(", createUserCode=").append(createUserCode);
		sb.append(", modifyTime=").append(modifyTime);
		sb.append(", modifyUserCode=").append(modifyUserCode);
		sb.append(", url=").append(url);
		sb.append(", reverse1=").append(reverse1);
		sb.append(", reverse2=").append(reverse2);
		sb.append(", reverse3=").append(reverse3);
		sb.append("]");
		return sb.toString();
	}

	
}