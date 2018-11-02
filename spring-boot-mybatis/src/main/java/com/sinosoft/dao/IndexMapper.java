package com.sinosoft.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.normal.po.SysArea;
import com.sinosoft.normal.po.SysCode;

@Mapper
public interface IndexMapper {
	// 默认查询所属的区域
	ArrayList<SysArea> selectByAreaCode(String area);

	// 默认查询联调的信息
	ArrayList<SysCode> selectByMsg();
}
