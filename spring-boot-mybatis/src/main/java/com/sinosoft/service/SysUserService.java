package com.sinosoft.service;

import java.util.List;

import com.sinosoft.normal.po.SysUser;
import com.sinosoft.normal.vo.SysUserVo;

public interface SysUserService {
	/**删除**/
    public int deleteByPrimaryKey(String usercode);

    public int insert(SysUser record);

    public int insertSelective(SysUser record);
    
    /**通过主键查询用户**/
    public SysUser selectByPrimaryKey(String usercode,String validStatus);
    /**通过机构代码查询用户信息**/
    public List<SysUser> querySysUserDelete(String ComCode,String ValidStatus);
    /**条件查询**/
    public List<SysUserVo> querySysUser(String userCode,String userName,String comCode,String comCname,String telePhone,String email,String admin,String validStatus);
    
    public int updateByPrimaryKeySelective(SysUser record);

    public int updateByPrimaryKeyWithBLOBs(SysUser record);

    public int updateByPrimaryKey(SysUser record);
}