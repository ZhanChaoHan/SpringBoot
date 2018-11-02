package com.sinosoft.service;

import java.util.List;

import com.sinosoft.normal.po.SysCompany;
import com.sinosoft.normal.vo.SysCompanyVo;

public interface SysCompanyService {
	public int deleteByPrimaryKey(String comCode);

	public int insert(SysCompany record);

	public int insertSelective(SysCompany record);
	
	/**通过主键查询机构**/
	public SysCompany selectByPrimaryKey(String comCode,String validStatus);

	public int updateByPrimaryKeySelective(SysCompany record);

	public int updateByPrimaryKey(SysCompany record);
	
	/**机构修改时上级机构判断*/
	public List<SysCompany> findSysCompanyList(String comCode,String reverse1);
	
	public SysCompany sysCompanyId(String codeCome);
	
	/**判断是否有下级机构*/
	public List<SysCompany> checkSysCompany(String comCode);
	
	public List<SysCompany> selectSysCompany(String admin);
	
	public List<SysCompany> selectSupSysCompany(String comType);
	
	public SysCompany getSysCompanyById(String supComd);
	
	public  List<SysCompanyVo> querySysCompany(String comCode,String comCname,String areaCode,String comAddress,String admin,String validStatus);
}