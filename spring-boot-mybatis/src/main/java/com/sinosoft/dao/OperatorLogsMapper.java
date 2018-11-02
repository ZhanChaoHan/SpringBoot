package com.sinosoft.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.normal.po.OperatorLogs;
import com.sinosoft.normal.vo.OperaLogVo;

@Mapper
public interface OperatorLogsMapper {
	public int deleteByPrimaryKey(String id);

	public int insert(OperatorLogs record);

	public int insertSelective(OperatorLogs record);

	public OperatorLogs selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(OperatorLogs record);

	public int updateByPrimaryKey(OperatorLogs record);

	/** 获取所有的日志列表通过过滤条件 **/
	public List<OperaLogVo> queryLogsByFilter(Map<String, Object> map);
}