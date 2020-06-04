package com.mzd.multipledatasources.dao.test02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mzd.multipledatasources.bean.TeachersBean;
import com.mzd.multipledatasources.mapper.test02.TransactionMapping2;

@Component
public class TransactionDao2 {
	@Autowired
	private TransactionMapping2 tm2;

	public void save(TeachersBean t) {
		tm2.save(t);
	}

}
