package com.sinosoft.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sinosoft.normal.po.SysArea;
import com.sinosoft.normal.vo.SysAreaVo;

public interface SysAreaService {
    public int deleteByPrimaryKey(String areaCode);

    public int insert(SysArea record);

    public int insertSelective(SysArea record);

    public SysArea selectByPrimaryKey(String areaCode);

    public int updateByPrimaryKeySelective(SysArea record);

    public int updateByPrimaryKey(SysArea record);
    
    public ArrayList<SysArea> getSysAreaList();
    
    public List<SysArea> selectList(String areaCode);
    
    
    /**
     * 过滤查询地区列表
     * @param map
     * @return
     */
    public List<SysAreaVo> querySysAreaListByFilter(Map<String, Object> map);
    
    
    /**
     * 查询省级地区集合方法
     * @return
     */
    public List<SysArea> queryProvinceAreaList(String supAreaCode);
}