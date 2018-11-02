package com.sinosoft.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysCode;
import com.sinosoft.normal.po.SysCodeKey;

@Mapper
public interface SysCodeMapper {
	public int deleteByPrimaryKey(SysCodeKey key);

	public int insert(SysCode record);

	public int insertSelective(SysCode record);

	public SysCode selectByPrimaryKey(SysCodeKey key);

	public int updateByPrimaryKeySelective(SysCode record);

	public int updateByPrimaryKey(SysCode record);

	// 模糊查询
	public ArrayList<SysCode> getSysCodeList(@Param("CodeType") String CodeType);

	public ArrayList<SysCode> getCodeTypeList(@Param("CodeType") String CodeType, @Param("ComCode") String ComCode);

	// 条件查询所有的信息
	public ArrayList<SysCode> selectByPrimaryKeyList(SysCode record);

	// 机构级别的判断大于等于
	public ArrayList<SysCode> muchComLevelList(@Param("CodeType") String CodeType, @Param("ComCode") String ComCode);

	// 机构级别的判断大于
	public ArrayList<SysCode> thanComLevelList(@Param("CodeType") String CodeType, @Param("ComCode") String ComCode);
}