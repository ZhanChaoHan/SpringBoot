package com.jachs.mybatis.demo.properties.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/****
 * 
 * @author zhanchaohan
 *
 */
@Component
@ConfigurationProperties(prefix = "man")
@PropertySource(value = 
{"classpath:properties_first.properties",
"classpath:properties_second.properties"}, encoding = "utf-8")
public class Second {
    @Value("${properties.second.Name}")
    private String n;
    @Value("${properties.second.Pwd}")
    private String w;
    
    @Value("${properties.first.Name}")
    private String fn;
    @Value("${properties.first.Pwd}")
    private String fw;
    
    public Second () {
        super ();
    }

    public Second ( String n, String w, String fn, String fw ) {
        super ();
        this.n = n;
        this.w = w;
        this.fn = fn;
        this.fw = fw;
    }

    public String getN () {
        return n;
    }

    public void setN ( String n ) {
        this.n = n;
    }

    public String getW () {
        return w;
    }

    public void setW ( String w ) {
        this.w = w;
    }

    public String getFn () {
        return fn;
    }

    public void setFn ( String fn ) {
        this.fn = fn;
    }

    public String getFw () {
        return fw;
    }

    public void setFw ( String fw ) {
        this.fw = fw;
    }
    
}
