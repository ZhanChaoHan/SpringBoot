package com.jachs.mybatis.demo.properties;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jachs.mybatis.demo.properties.entity.First;


/****
 * SpringRunner运行环境可以
 * SpringJUnit4ClassRunner运行环境不行
 * @author zhanchaohan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = {"com.jachs.mybatis.demo.properties.entity"},basePackageClasses = {First.class})
public class PropertiesTest2 {
	@Autowired
	private First first;
	
	@Test
	public void testPro() {
		System.out.println(first.getName());
	}
}
