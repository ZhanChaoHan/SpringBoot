package com.jachs.hibernate.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jachs.hibernate.dao.Tb2Dao;
import com.jachs.hibernate.entity.Tb2;

@Controller
public class Tb2Action {
	@Autowired
	private Tb2Dao tb2Dao;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public void index() {
		try {
			Tb2 ks=new Tb2();
			ks.setAa("啊AAA");
			ks.setBb("吧SSS");
			ks.setCc("超CCCS");
			System.out.println(tb2Dao.save(ks));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
