package com.jachs.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="group对象",description="群组对象user")
public class Group {
	@ApiModelProperty(value="用户名",name="username",example="xingguo")
	private String name;
	@ApiModelProperty(value="密码",name="pwd",example="123456")
	private String pwd;
	
	public Group() {
		super();
	}
	public Group(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
