package com.jachs.mybatis.demo.properties;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.jachs.mybatis.demo.properties.entity.First;


/****
 * 约定大于配置，测试类所在包必须和src包命名保持一致
 * @author zhanchaohan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.jachs.mybatis.demo.properties.entity",""},basePackageClasses = {First.class})
public class PropertiesTest {
	@Autowired
	private First first;
	
	@Test
	public void testPro() {
		System.out.println(first.getName());
	}
}
