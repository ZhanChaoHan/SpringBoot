package com.jachs.mybatis.demo.properties.entity;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/****
 * InitializingBean结合FactoryBean使用
 * @author zhanchaohan
 *InitializingBean:初始化配置参数
 *FactoryBean：返回给定类型对象
 */
public class FirstFactoryBean implements InitializingBean, FactoryBean<FirstBean> {
    private FirstBean firstBean;
    private Properties properties;

    public void setProperties ( Properties properties ) {
        this.properties = properties;
    }

    @Override
    public FirstBean getObject () throws Exception {
        return firstBean;
    }

    @Override
    public Class<?> getObjectType () {
        return firstBean.getClass ();
    }

    @Override
    public void afterPropertiesSet () throws Exception {
        if ( properties != null ) {
            Enumeration<?> props = properties.propertyNames ();
            while ( props.hasMoreElements () ) {
                firstBean = new FirstBean ();
                                String key = props.nextElement().toString();
                //                configuration.set(key, properties.getProperty(key));
                firstBean.setName ( properties.getProperty ( "name" ) );
                firstBean.setPwd ( properties.getProperty ( "pwd" ) );
            }
        }
    }
    /****
     * 是否单例
     * @Title: isSingleton
     * @Description: TODO
     * @return 
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    public boolean isSingleton () {
        return true;
    }
}
