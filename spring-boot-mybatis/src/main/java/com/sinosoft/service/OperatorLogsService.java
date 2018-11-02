package com.sinosoft.service;

import java.util.List;
import java.util.Map;

import com.sinosoft.normal.po.OperatorLogs;
import com.sinosoft.normal.vo.OperaLogVo;

public interface OperatorLogsService {
    public int deleteByPrimaryKey(String id);

    public int insert(OperatorLogs record);

    public int insertSelective(OperatorLogs record);

    public OperatorLogs selectByPrimaryKey(String id);

    public int updateByPrimaryKeySelective(OperatorLogs record);

    public int updateByPrimaryKey(OperatorLogs record);
    
    /**获取所有的日志列表通过过滤条件 **/
    public List<OperaLogVo> queryLogsByFilter(Map<String, Object> map);
}