<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link href="/static/css/test.css" rel="stylesheet" />
<script src="/static/js/test.js"></script>
</head>
<body>
	<p>当前时间：${.now?string("yyyy-MM-dd HH:mm:ss.sss")}</p>
	<h1>----------------------------------</h1>
	<p>打印对象</p>
	${user}
	<h1>----------------------------------</h1>
	<p>循环集合</p>
	<#list SList as str> 
		<p>${str}</p>
	</#list>
	<h1>----------------------------------</h1>
	<p>循环集合</p>
	<#list SArr as str> 
		<p>${str}</p>
	</#list>
	<h1>----------------------------------</h1>
</body>
</html>