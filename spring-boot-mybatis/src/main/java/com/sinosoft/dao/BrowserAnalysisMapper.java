package com.sinosoft.dao;




import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.normal.po.SysCompany;
/**
 * 
 * 创建者姓名：曹龙   
 * 日期：2018-6-20	 浏览器使用分析
 *
 */
@Mapper
public interface BrowserAnalysisMapper {
	/**
	 * 浏览器类型统计
	 * @param startTime 开始日期
	 * @param endTime	结束日期
	 * @return
	 */
	public List<SysCompany> censusBrowserType (String startTime,String endTime);
	/**
	 * 浏览器版本统计
	 * @param startTime	开始日期
	 * @param endTime	结束日期
	 * @return
	 */
    public List<SysCompany> censusBrowserVersion (String startTime,String endTime);
    
}