package com.sinosoft.normal.util;

public class AppConst {
	/*
	 * 
	 * 功能权限
	 * 描述：功能权限控制的常量，主要用在页面功能是否可见
	 */
	
	/** 登录 */
	public static final String ROLE_CODE_0 = "0";
	/** 权限管理 */
	public static final String ROLE_CODE_1 = "1";
	
	/**  (权限)系统管理 - 机构管理 */
	public static final String ROLE_CODE_1_1 = "1_1";
	/** 权限管理-机构管理-机构管理-添加 */
	public static final String ROLE_CODE_1_1_1 = "1_1_1";
	/** 权限管理-机构管理-机构管理-查询 */
	public static final String ROLE_CODE_1_1_2 = "1_1_2";
	/** 权限管理-机构管理-机构管理-修改 */
	public static final String ROLE_CODE_1_1_3 = "1_1_3";
	/** 权限管理-机构管理-机构管理-删除 */
	public static final String ROLE_CODE_1_1_4 = "1_1_4";

	/**  （权限）系统管理 - 用户管理 */
	public static final String ROLE_CODE_1_2 = "1_2";
	/** 权限管理-用户管理-添加 */
	public static final String ROLE_CODE_1_2_1 = "1_2_1";
	/** 权限管理-用户管理-查询 */
	public static final String ROLE_CODE_1_2_2 = "1_2_2";
	/** 权限管理-用户管理-修改 */
	public static final String ROLE_CODE_1_2_3 = "1_2_3";
	/** 权限管理-用户管理-删除 */
	public static final String ROLE_CODE_1_2_4 = "1_2_4";
	/** 权限管理-用户管理-分配岗位 */
	public static final String ROLE_CODE_1_2_5 = "1_2_5";
	
	/** 权限管理-岗位管理 */
	public static final String ROLE_CODE_1_3 = "1_3";
	/** 权限管理-岗位管理-添加 */
	public static final String ROLE_CODE_1_3_1 = "1_3_1";
	/** 权限管理-岗位管理-查询 */
	public static final String ROLE_CODE_1_3_2 = "1_3_2";
	/** 权限管理-岗位管理-修改 */
	public static final String ROLE_CODE_1_3_3 = "1_3_3";
	/** 权限管理-岗位管理-删除 */
	public static final String ROLE_CODE_1_3_4 = "1_3_4";
	/** 权限管理-岗位管理-分配功能 */
	public static final String ROLE_CODE_1_3_5 = "1_3_5";
	
	/** 系统管理-配置管理 */
	public static final String ROLE_CODE_2 = "2";
	/** 权限管理-系统代码配置 */
	public static final String ROLE_CODE_2_1 = "2_1";
	/** 系统代码配置-添加*/
	public static final String ROLE_CODE_2_1_1 = "2_1_1";
	/** 系统代码配置-查询*/
	public static final String ROLE_CODE_2_1_2 = "2_1_2";
	/** 系统代码配置-修改*/
	public static final String ROLE_CODE_2_1_3 = "2_1_3";
	/** 系统代码配置-删除*/
	public static final String ROLE_CODE_2_1_4 = "2_1_4";
	
	/**  系统管理-参数管理 */
	public static final String ROLE_CODE_2_2 = "2_2";
	/** 参数管理-查询*/
	public static final String ROLE_CODE_2_2_1 = "2_2_1";
	/** 参数管理-修改*/
	public static final String ROLE_CODE_2_2_2 = "2_2_2";
	
	/** 权限管理-地区配置 */
	public static final String ROLE_CODE_2_3 = "2_3";
	/** 地区配置-添加*/
	public static final String ROLE_CODE_2_3_1 = "2_3_1";
	/** 地区配置-查询*/
	public static final String ROLE_CODE_2_3_2 = "2_3_2";
	/** 地区配置-修改*/
	public static final String ROLE_CODE_2_3_3 = "2_3_3";
	/** 地区配置-删除*/
	public static final String ROLE_CODE_2_3_4 = "2_3_4";
	
	/**日志管理**/
	public static final String ROLE_CODE_2_4 ="2_4";
	/**日志管理-查询**/
	public static final String ROLE_CODE_2_4_1 ="2_4_1";
	
	/** 超级机构 */
	public static final String SUPER_COM_CODE = "000000";
	/** 超级机构名称 */
	public static final String SUPER_COM_CNAME = "超级机构";
	/** 数据岗位 - 超级管理员岗 */
	public static final String GROUP_CODE_SYS = "sysgroup";
	/** 查询结果列表页面，每页显示的最大行数 */
	public static final int PAGE_SIZE = 10;
	/** 用户登录后的SessionID名称 */
	public static final String USER_SESSION_ID = "USER_SESSION_ID";
	/** 自定义下拉选最大显示数量 */
	public static final int PAGESIZE = 100;
	/** 没有上级（机构、地区等）的默认值 */
	public static final String NOT_SUP = "-";
	
	
	/** 机构类型 - 超级管理机构 */
	public static final String COM_TYPE_ADMIN = "-";
	/** 机构类型*/
	public static final String COM_TYPE = "ComType";
	public static final String COM_TYPE_00 = "00";
	public static final String COM_TYPE_01 = "01";
	/** 机构类型 - 经侦 */
	public static final String COM_TYPE_02 = "02";
	/** 机构类型 -  */
	public static final String COM_TYPE_03 = "03";
	public static final String COM_TYPE_04 = "04";
	public static final String COM_LEVEL = "ComLevel";
	public static final String COM_LEVEL_0 = "00";
	/** 机构级别 - 省级 */
	public static final String COM_LEVEL_1 = "01";
	/** 机构级别 - 市级 */
	public static final String COM_LEVEL_2 = "02";
	/** 机构级别 - 县级 */
	public static final String COM_LEVEL_3 = "03";
	/** 是否有下级机构 - 无 */
	public static final String HASSUBCOMCODE_00 = "00";
	/** 是否有下级机构 - 有 */
	public static final String HASSUBCOMCODE_01 = "01";
	
	/**是否有下级机构枚举**/
	public static final String HASSUBAREA =  "hasSubArea";
	
	/**权限类型枚举**/
	public static final String ROLE_TYPE = "roleType";
	
	/** 权限类型 - 功能权限 */
	public static final String ROLE_TYPE_01 = "01";
	/** 权限类型 - 数据权限 */
	public static final String ROLE_TYPE_02 = "02";
	
	
	/** 数据是否有效- 无效 */
	public static final String VALID_STATUS_00 = "00";
	/** 数据是否有效 - 有效 */
	public static final String VALID_STATUS_01 = "01";
	
	/** 权限类型 - 登录权限 */
	public static final String ROLE_CODE = "0";
	
	
	
	/** 是否可修改 - 可修改 */
	public static final String ISMODIFY_00 = "00";
	/** 是否可修改 - 不可修改 */
	public static final String ISMODIFY_01 = "01";
	
	/** 起始日期时间格式 */
	public static final String START_TIME_FORMAT = " 00:00:00";
	/** 结束日期时间格式 */
	public static final String END_TIME_FORMAT = " 23:59:59";

	/** 输入条件时需要屏蔽的字符串列表 */
	public static final String 	ERROR_CHAR = "|$%@+,*?()<>;!@&[]{}\"'";
	public static final String DRIVER_PERSONAL_SQL= "driverPersonalSql";
	
	
	public static final String allowTypes =".html,.htm,.mht";
	public static final String attachallowTypes = ".zip,.rar";
	public static final String allowTypes_2 =".jpg,.pdf";
	
	/** 时间判断类型- 00:00:00 */
	public static final String TIME_00 = "00";
	/** 时间判断类型- 23:59:59 */
	public static final String TIME_01 = "01";
	
	/** 时间拼接类型- 00:00:00 */
	public static final String TIME_DAYSTART = " 00:00:00";
	/** 时间拼接类型- 23:59:59 */
	public static final String TIME_DAYEND = " 23:59:59";
	
	
	/** 时间格式- 到天 */
	public static final String DATEPATTERN_D  = "yyyy-MM-dd";
	/** 时间格式- 到秒 */
	public static final String DATEPATTERN_S = "yyyy-MM-dd HH24:mi:ss";
	/** 时间格式- 到秒 */
	public static final String DATEPATTERN_S2 = "yyyy-MM-dd hh:mm:ss";
	/** 时间格式- 到秒-24小时 */
	public static final String DATEPATTERN_S3 = "yyyy-MM-dd HH:mm:ss";
	
	/** 机构类型 */
	public static final String COMPANYTYPE = "CompanyType"; 
	
	/** 人员角色 */
	public static final String ROLETYPECODE = "RoleTypeCode";
	
	
	/** 权限类型*/
	public static final String ROLETYPE = "01";

	/** 地区代码  -全国  */
	public static final String AREACODE_COUNTRY = "000000"; 
	
	/** 机构标识  -全国  */
	public static final String COMPANYFLAG_00 = "00"; 
	/** 机构标识  -地方  */
	public static final String COMPANYFLAG_01 = "01"; 
	
	
	/**通讯录类型*/
	public static final String CONTACTTYPE = "ContactType"; 
	
	/**超级机构类别*/
	public static final String SUP_COMPANY = "-";
	/**正则表达式*/
	/** 正则表达式 -为正整数  */
	public static final String REGX_01 = "^[1-9]\\d*$"; 
	/** 正则表达式 -为非负的数值  */
	public static final String REGX_02 = "^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"; 
	/** 正则表达式  -邮箱*/
	public static final String REGEX_EMAIL = "^[0-9a-zA-Z]+@(([0-9a-zA-Z]+)[.])+[a-z]{2,4}$"; 
	/** 正则表达式 -大写英文*/
	public static final String REGEX_COMPANYCODE = "^[A-Z]+$"; 
	/** 手机号以 13、15、18开头     固定电话有区号 0开始  */
	/** 手机号11位 以1开头，     固定电话区号 +“-”+ 号码 */
	public static final String REGEX_TELEPHONE = "^([1][0-9]{10})|[0]{1}[0-9]{2,3}-[0-9]{7,8}$"; 
	/** 超级管理员用户  */
	public static final String ADMIN = "admin"; 
	
	
	/*zhangjun 2016-06-23 增加监控分析模块 start*/
	/**时间判断类型  - 月初 **/
	public static final String MONTHFIRST = "00";
	/**时间判断类型  - 月末 **/
	public static final String MONTHEND = "01";
	
	/**系统上线时间**/
	public static final String OLTIME = "2015-05";
	/*zhangjun 2016-06-23 增加监控分析模块 end*/
	
	/**   添加查询类型常量    InfoQueryType  dongyinhao  2018-05-11   start*/
	/**系统上线时间**/
	public static final String INFO_QUERY_TYPE = "InfoQueryType";
	/**   添加查询类型常量    InfoQueryType  dongyinhao  2018-05-11   start*/
	/**添加直辖市*/
	public static final String BEIJING = "120000";
	public static final String TIANJIN = "110000";
	public static final String SHNGHAI = "310000";
	public static final String CHONGQING = "500000";

}
