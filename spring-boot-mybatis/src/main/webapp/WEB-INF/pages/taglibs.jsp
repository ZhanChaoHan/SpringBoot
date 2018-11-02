<%@page import="com.sinosoft.normal.util.AppConst"%>
<%@page import="com.sinosoft.normal.po.SysRole"%>
<%@page import="java.util.Map"%>
<%@page import="com.sinosoft.normal.vo.UserSession"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${header['accept-language']}"/>
<script type="text/javascript">
/* 	window.history.forward(1);

	window.onload = function () {
		var superA = document.getElementsByTagName("a");
		for (var i = 0; i < superA.length; i++) {
			superA[i].oncontextmenu = function () {return false;};
		}
	}; */
</script>
<input type="hidden" value="${ctx}" id="ctx"/>

<%
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