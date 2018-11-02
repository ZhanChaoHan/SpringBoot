package com.sinosoft.service;



import java.util.ArrayList;

import com.sinosoft.normal.po.SysArea;
import com.sinosoft.normal.po.SysCode;

public interface IndexService {
	// 默认查询所属的区域
	ArrayList<SysArea> selectByAreaCode(String area);

	// 默认查询联调信息
	ArrayList<SysCode> selectByMsg();
}
