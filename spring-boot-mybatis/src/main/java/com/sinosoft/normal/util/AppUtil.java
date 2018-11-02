package com.sinosoft.normal.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.sinosoft.normal.po.OperatorLogs;



/**
 * 工具方法
 * LvWeisheng 2012-01-12 v1.0.0 增加新版本
 */
public class AppUtil {
    /**
     * 把字符串数组的每个元素链接起来，中间用传入的字符分割
     * @param stringList  要被链接的字符串数组
     * @param sign  分割字符
     * @return 数据库需要的险种字符串
     */
    public static String getListToString(String[] stringList, String sign) {
        StringBuffer sbList = new StringBuffer(100);// 返回的险种集合字符串
        // 字符串数组 有值
        if (null != stringList && 0 < stringList.length) {
            // 把字符串数组元素用sign连接
            for (String striInsureList : stringList) {
            	sbList.append(striInsureList);// 字符串数组元素
            	sbList.append(sign);
            }
            // 返回(去掉最后的sign)
            return sbList.substring(0, sbList.length() - sign.length()).toString();
        }
        // 返回null
        return null;
    }
    
    /**
     * 功能：将字符串进行MD5加密转换
     * @param plainText：明文字符串
     * @return
     */
    public static String md5s(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0){
					i += 256;
				}
				if (i < 16){
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			String str = buf.toString().toUpperCase();
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		return plainText;
	}

    /**
     * 生成随机ID，经常在添加数据到数据库中使用
     * @return
     */
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
      }
    
    

	/**
	 * 返回轨迹对象
	 * @param userCode 用户代码
	 * @param operationDate 操作时间
	 * @param roleCode 操作模块代码
	 * @param roleName 操作模块名称
	 * @param areaCode 地区代码
	 * @param companyCode 机构代码
	 * @param findContent 查询内容
	 * @param dataId 业务数据ID
	 * @return
	 */
	public static OperatorLogs setOneOperatorlogs(String userCode,Date operationDate,String roleCode,String roleName,String areaCode,String companyCode,String findContent,String dataId){
		
		OperatorLogs one = new OperatorLogs(); 
		one.setId(getUUID());
		one.setUserCode(userCode); 
		one.setOperationDate(operationDate);
		one.setRoleCode(roleCode); 
		one.setRoleName(roleName); 
		one.setAreaCode(areaCode); 
		one.setCompanyCode(companyCode); 
		one.setFindContent(findContent); 
		one.setDateId(dataId); 
		return one; 
	}
	
	
	/**
	 * 通用的转换json转码方式
	 * @param obj
	 * @param response
	 * @throws Exception
	 */
	public static void sendJson(Object obj,HttpServletResponse response){
		try {	
		// JSONObject-lib包是一个beans,collections,maps,java arrays和xml和JSON互相转换的包。
	    	response.setContentType("text/html;charset=UTF-8");
	    	// 将book对象转换成json写出到客户端
	    	
			response.getWriter().print(obj);;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	
}
