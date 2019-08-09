package com.jachs.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jachs.mybatis.entity.Tb2;
import com.jachs.mybatis.service.Tb2Service;

@Controller
@RequestMapping(value = "/tb2")
public class Tb2Controller {
	@Autowired
	private Tb2Service tb2Service;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public void index() {
		try {
//			Tb2 tb2= tb2Service.selectByPrimaryKey("a");
//			System.out.println(tb2.getAa());
			
//			Tb2 insert=new Tb2();
//			insert.setAa("aaa");
//			insert.setBb("bb");
//			insert.setCc("cc");
//			tb2Service.insert(insert);
			
//			Tb2 update=new Tb2();
//			update.setAa("aaa");
//			update.setBb("avc");
//			update.setCc("acv");
//			tb2Service.updateByPrimaryKey(update);
			
//			Tb2 b=tb2Service.selectByPrimaryKey("aaa");
//			System.out.println(b.getAa());
			Tb2 bc=tb2Service.select("aaa", "avc", "acv");
			System.out.println(bc.getBb());
			Tb2 b=tb2Service.select(null, "avc", "acv");
			System.out.println(b.getBb());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
