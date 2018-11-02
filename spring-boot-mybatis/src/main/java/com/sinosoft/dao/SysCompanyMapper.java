package com.sinosoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinosoft.normal.po.SysCompany;
import com.sinosoft.normal.vo.SysCompanyVo;

@Mapper
public interface SysCompanyMapper {
	public int deleteByPrimaryKey(String comCode);

	public List<SysCompany> checkSysCompany(String comCode);

	public int insert(SysCompany record);

	public SysCompany getSysCompanyById(String supComd);

	public int insertSelective(SysCompany record);

	/** 机构修改时上级机构判断 */
	public List<SysCompany> findSysCompanyList(@Param("comCode") String comCode, @Param("reverse1") String reverse1);

	/**
	 * 通过主键查询机构
	 * 
	 * @param comCode
	 * @return SysCompany 机构
	 */
	public SysCompany selectByPrimaryKey(String comCode, String validStatus);

	public int updateByPrimaryKeySelective(SysCompany record);

	public int updateByPrimaryKey(SysCompany record);

	public List<SysCompany> selectSysCompany(String admin);

	public List<SysCompany> selectSupSysCompany(@Param("comType") String comType);

	public SysCompany sysCompanyId(@Param("comCode") String comCode);

	public List<SysCompanyVo> querySysCompany(@Param("comCode") String comCode, @Param("comCname") String comCname,
			@Param("areaCode") String areaCode, @Param("comAddress") String comAddress, @Param("admin") String admin,
			@Param("validStatus") String validStatus);
}