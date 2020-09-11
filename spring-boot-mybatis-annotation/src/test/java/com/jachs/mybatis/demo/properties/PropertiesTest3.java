package com.jachs.mybatis.demo.properties;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.jachs.mybatis.demo.properties.entity.First;
import com.jachs.mybatis.demo.properties.entity.Second;


/****
 * @author zhanchaohan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.jachs.mybatis.demo.properties.entity",""},basePackageClasses = {First.class,Second.class})
public class PropertiesTest3 {
	@Autowired
	private Second second;
	
	@Test
	public void testPro() {
		System.out.println(second.getN ());
		System.out.println(second.getFn ());
	}
}
