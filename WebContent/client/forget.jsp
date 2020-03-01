<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<TITLE>忘记密码-完成A</TITLE>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/login.css">
</head>
<body>
<div class="superlogin"></div>
<div class="loginBox">
	<div class="resetpsw">密码重置</div>
	<div class="stepBar">
		<img src="${pageContext.request.contextPath }/client/images/login_step_1.png">
	</div>
	<div class="loginMain loginMain2">
		<div class="tabwrap">
		<table border="0" cellspacing="0" cellpadding="0">
			<tbody ><tr id="email_2" ><td class="title2">邮箱：</td><td><input type="text" id="email"placeholder="请输入注册时邮箱"class="form-control txt" onchange="return checkEmail()"></td></tr>
			<tr id="email_3" ><td class="title2">验证码：</td><td><input type="text" id="code"placeholder="请输入验证码"class="form-control txt" onchange="return checkCode_1()"></td></tr>
			<tr class="errortd"><td>&nbsp;</td><td id="check_email" class="check_email" hidden="hiddened"><i class="ico-error"></i><div class="errorword"id="errorword" style="color:red;font-size: 15px;"></div></td></tr>		
			<tr><td>&nbsp;</td><td><input type="button" class="loginbtn" value="下一步" ><input type="button" class="resetbtn" value="返回" onclick="window.location='${pageContext.request.contextPath}/client/login.jsp'"></td></tr>		
		</tbody></table>
		</div>
	</div>
</div>
<script src=" ${pageContext.request.contextPath}/client/Scripts/jquery-1.9.1.min.js"></script>
<SCRIPT src="${pageContext.request.contextPath}/client/Scripts/common.js"></SCRIPT>
<SCRIPT src="${pageContext.request.contextPath}/client/Scripts/main.js"></SCRIPT>

</body>
</html>