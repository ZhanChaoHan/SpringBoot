package com.sinosoft.service;

import java.util.ArrayList;

import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.po.SysCodeKey;

public interface SysCodeService {
	public int deleteByPrimaryKey(SysCodeKey key);

	public int insert(SysCode record);

	public int insertSelective(SysCode record);

	public SysCode selectByPrimaryKey(SysCodeKey key);

	public int updateByPrimaryKeySelective(SysCode record);

	public int updateByPrimaryKey(SysCode record);
	
	public ArrayList<SysCode> getSysCodeList(String CodeType);
	
	public ArrayList<SysCode> getCodeTypeList(String CodeType,String ComCode);
	//无条件查询
	public  ArrayList<SysCode> selectByPrimaryKeyList(SysCode record);
	//机构级别的判断大于等于
	public ArrayList<SysCode> muchComLevelList(String CodeType,String ComCode);
	//机构级别的判断大于
	public ArrayList<SysCode> thanComLevelList(String CodeType,String ComCode);
}