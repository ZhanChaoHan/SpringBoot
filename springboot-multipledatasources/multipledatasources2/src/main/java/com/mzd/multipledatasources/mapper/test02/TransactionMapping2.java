package com.mzd.multipledatasources.mapper.test02;

import org.springframework.stereotype.Repository;

import com.mzd.multipledatasources.bean.TeachersBean;

@Repository
public interface TransactionMapping2 {

	void save(TeachersBean t);

}
