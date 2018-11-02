package com.sinosoft.service.impl;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.OperatorLogs;
import com.sinosoft.normal.vo.OperaLogVo;
import com.sinosoft.service.OperatorLogsService;
import com.sinosoft.dao.OperatorLogsMapper;

@Service
public class OperatorLogsServiceImpl  implements OperatorLogsService {
	@Autowired
	private OperatorLogsMapper mapper;
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(OperatorLogs record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(OperatorLogs record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public OperatorLogs selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(OperatorLogs record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(OperatorLogs record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	 /**获取所有的日志列表通过过滤条件 **/
    public List<OperaLogVo> queryLogsByFilter(Map<String, Object> map){
		return mapper.queryLogsByFilter(map);
	}


	
}
