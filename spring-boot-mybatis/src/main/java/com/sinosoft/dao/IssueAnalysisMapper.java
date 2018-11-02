package com.sinosoft.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sinosoft.normal.po.SysCompany;

/**
 * 
 * 创建者姓名：曹龙 日期：2018-6-20 出单分析
 *
 */
@Mapper
public interface IssueAnalysisMapper {

	/**
	 * 出单/签单高峰期统计
	 * 
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return
	 */
	public List<SysCompany> censusIssueAndBillPeak(String startTime, String endTime);

	/**
	 * 门店代理集中出单统计
	 * 
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @param name
	 *            区域
	 * @param region
	 *            地区
	 * @return
	 */
	public List<SysCompany> censusStoreProxyCentralizeIssue(String startTime, String endTime, String name,
			String region);

	/**
	 * 出单业务量统计
	 * 
	 * @param startTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @param region
	 *            地区
	 * @param insuranceType
	 *            险种
	 * @return
	 */

	public List<SysCompany> censusBillVolumeOfBusiness(String startTime, String endTime, String region,
			String insuranceType);
}