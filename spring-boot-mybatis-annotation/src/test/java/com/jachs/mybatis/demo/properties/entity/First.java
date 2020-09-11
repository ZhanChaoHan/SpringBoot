package com.jachs.mybatis.demo.properties.entity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/****
 * 
 * @author zhanchaohan
 *
 */
@Component
@PropertySource(value = {"classpath:properties_first.properties"}, encoding = "utf-8")
public class First {
	@Value("${properties.first.Name}")
	private String Name;
	@Value("${properties.first.Pwd}")
	private String Pwd;
	
	public First() {
		super();
	}
	public First(String name, String pwd) {
		super();
		Name = name;
		Pwd = pwd;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	
}
