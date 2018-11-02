package com.sinosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.normal.po.SysUser;
import com.sinosoft.normal.vo.SysUserVo;
import com.sinosoft.service.SysUserService;
import com.sinosoft.dao.SysUserMapper;

@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper mapper;

	@Override
	public int deleteByPrimaryKey(String usercode) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(usercode);
	}

	@Override
	public int insert(SysUser record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysUser record) {
		// TODO Auto-generated method stub
		return mapper.insertSelective(record);
	}

	@Override
	public SysUser selectByPrimaryKey(String usercode, String validStatus) {
		// TODO Auto-generated method stub
		return mapper.selectByPrimaryKey(usercode, validStatus);
	}

	@Override
	public List<SysUserVo> querySysUser(String userCode, String userName, String comCode, String comCname,
			String telePhone, String email, String admin, String validStatus) {
		// TODO Auto-generated method stub
		return mapper.querySysUser(userCode, userName, comCode, comCname, telePhone, email, admin, validStatus);
	}

	@Override
	public int updateByPrimaryKeySelective(SysUser record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(SysUser record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(SysUser record) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SysUser> querySysUserDelete(String ComCode, String ValidStatus) {
		// TODO Auto-generated method stub
		return mapper.querySysUserDelete(ComCode, ValidStatus);
	}

}
