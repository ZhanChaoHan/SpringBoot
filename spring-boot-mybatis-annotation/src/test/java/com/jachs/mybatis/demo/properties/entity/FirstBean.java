package com.jachs.mybatis.demo.properties.entity;

/****
 * 
 * @author zhanchaohan
 *
 */
public class FirstBean {
    private String Name;
    private String Pwd;
    
    
    public FirstBean () {
        super ();
    }
    public FirstBean ( String name, String pwd ) {
        super ();
        Name = name;
        Pwd = pwd;
    }
    public String getName () {
        return Name;
    }
    public void setName ( String name ) {
        Name = name;
    }
    public String getPwd () {
        return Pwd;
    }
    public void setPwd ( String pwd ) {
        Pwd = pwd;
    }
    
}
