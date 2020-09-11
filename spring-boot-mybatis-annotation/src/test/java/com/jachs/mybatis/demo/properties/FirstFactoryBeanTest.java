package com.jachs.mybatis.demo.properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jachs.mybatis.demo.properties.entity.FirstFactoryBean;

/**
 * @author zhanchaohan
 * 
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:/first.xml" } )
public class FirstFactoryBeanTest {
    @Autowired
    @Qualifier("firstB")
    private FirstFactoryBean firstFactoryBean;
    
    @Test
    public void TT() throws Exception {
        System.out.println ( firstFactoryBean.getObject ().getName () );
    }
}
