package com.sinosoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.normal.po.SysCompany;

/**
 * 
 * 创建者姓名：曹龙 日期：2018-6-20 接口分析
 *
 */
@Mapper
public interface InterfaceAnalysisMapper {

	/**
	 * 接口的调用次数和平均用时 当天凌晨0点到当前日期
	 * 
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return
	 */
	public List<SysCompany> getNocatfesiotcsoai(String startTime, String endTime);

	/**
	 * 接口排行top10
	 * 
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return
	 */
	public List<SysCompany> censusInterfaceErrorTOP10(String startTime, String endTime);
}