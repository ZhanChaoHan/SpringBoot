package com.sinosoft.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysArea;
import com.sinosoft.normal.vo.SysAreaVo;
import com.sinosoft.service.SysAreaService;
import com.sinosoft.dao.SysAreaMapper;

@Service
public class SysAreaServiceImpl  implements SysAreaService {

	@Autowired
	public SysAreaMapper sysareamapper;

	@Override
	public int deleteByPrimaryKey(String areaCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SysArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SysArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SysArea selectByPrimaryKey(String areaCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SysArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<SysArea> getSysAreaList() {
		// TODO Auto-generated method stub
		return sysareamapper.getSysAreaList();
	}

	@Override
	public List<SysArea> selectList(String areaCode) {
		// TODO Auto-generated method stub
		return sysareamapper.selectList(areaCode);
	}

	/**
	 * 过滤查询地区列表
	 * 
	 * @param map
	 * @return
	 */
	public List<SysAreaVo> querySysAreaListByFilter(Map<String, Object> map) {
		return sysareamapper.querySysAreaListByFilter(map);
	}

	/**
	 * 查询省级地区集合方法
	 * 
	 * @return
	 */
	@Override
	public List<SysArea> queryProvinceAreaList(String supAreaCode) {
		return sysareamapper.queryProvinceAreaList(supAreaCode);
	}
}
