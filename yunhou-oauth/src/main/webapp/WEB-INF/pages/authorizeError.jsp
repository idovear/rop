<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图标</title>

<meta name="renderer" content="webkit">

<link href="resource/image/favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" /> 
<link href="resource/image/favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" /> 
<link href="resource/image/favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" /> 

</head>
<body>
	<h1>很抱歉，出错了！</h1>
	<br />
	<span style="color: red;font-size:10px">${error}</span>
	<br />
	<b>您可以根据错误码文档来查询以下出错信息：</b><br />
	<span>app_key</span><br />
	<span>response_type</span><br />
	<span>redirect_uri</span>	
</body>
</html>