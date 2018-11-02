package com.sinosoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysUser;
import com.sinosoft.normal.vo.SysUserVo;

@Mapper
public interface SysUserMapper {
	public int deleteByPrimaryKey(@Param("userCode") String userCode);

	public int insert(SysUser record);

	public int insertSelective(SysUser record);

	/** 通过机构代码查询用户信息 **/
	public List<SysUser> querySysUserDelete(@Param("ComCode") String ComCode, @Param("ValidStatus") String ValidStatus);

	/** 通过主键查询用户 **/
	public SysUser selectByPrimaryKey(String userCode, String validStatus);

	/** 条件查询 **/
	public List<SysUserVo> querySysUser(@Param("userCode") String userCode, @Param("userName") String userName,
			@Param("comCode") String comCode, @Param("comCname") String comCname, @Param("telePhone") String telePhone,
			@Param("email") String email, @Param("admin") String admin, @Param("validStatus") String validStatus);

	public int updateByPrimaryKeySelective(SysUser record);

	public int updateByPrimaryKey(SysUser record);
}