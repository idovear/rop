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

<script type="text/javascript" src="<%=basePath%>resource/js/jquery-1.8.0.js"></script>
<script type="text/javascript" src="<%=basePath%>resource/js/authorize.js"></script>

<script type="text/javascript">
		window.basepath = '<%=basePath%>';
</script>

</head>
<body>
	<input type="hidden" id="app_key" value="${authorize.app_key}" />
	<input type="hidden" id="response_type" value="${authorize.response_type}" />
	<input type="hidden" id="redirect_uri" value="${authorize.redirect_uri}" />
	<input type="hidden" id="state" value="${authorize.state}" />
	<input type="hidden" id="view" value="${authorize.view}" />
	<input type="hidden" id="sessionId" value="" />

	<h1>云猴网</h1>
	<span style="font-size: 10px">您在使用云猴网用户名访问Oauth2.0</span><br/><br/>
	<span style="font-size: 10px;color: red" id="error"></span>
	<div id="login_span">
		用户名：<input type="text" id="username" />
		<br />
		密码:<input type="text" id="password" />
		<br />
		<input type="button" id="login_submit" value="登录"><a href="#" ><span style="font-size: 10px">忘记密码?</span></a>
	</div>
	
	<div id="authorize_span" style="display: none;">
		<span>授权后，将允许应用Oauth2.0访问或操作以下数据,为您提供更加优质的服务</span><br />
		<input type="button" id="login_authorize" value="授权">
		<input type="button" id="login_cancel" value="取消">
	</div>
	
</body>
</html>