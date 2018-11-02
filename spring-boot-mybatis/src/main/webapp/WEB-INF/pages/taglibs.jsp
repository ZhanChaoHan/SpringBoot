<%@page import="com.sinosoft.normal.util.AppConst"%>
<%@page import="com.sinosoft.normal.po.SysRole"%>
<%@page import="java.util.Map"%>
<%@page import="com.sinosoft.normal.vo.UserSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<fmt:setLocale value="${header['accept-language']}"/>
<%
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	UserSession userSession = (UserSession)session.getAttribute(AppConst.USER_SESSION_ID);
	if(userSession == null){
		userSession = new UserSession();
	}
	Map<String, SysRole> set = userSession.getFuncPowers();
%>
<%!
	private boolean isRoleContained(String task, Map<String, SysRole> set){
		if (null == set.get(task)) {
			return false;
		}
		return true;
	}
%>