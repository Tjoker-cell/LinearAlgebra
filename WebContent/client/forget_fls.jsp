<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>忘记密码－完成B </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/login.css">
</head>
<body>
<div class="superlogin"></div>
<div class="loginBox">
	<div class="resetpsw">密码重置</div>
	<div class="stepBar">
		<img src="${pageContext.request.contextPath }/client/images/login_step_2.png">
	</div>
	<div class="loginMain loginMain2">
		<div class="tabwrap">
		<table border="0" cellspacing="0" cellpadding="0">
			<tbody ><tr ><td class="title2">密码：</td><td><input type="password" id="forget_fls_pwd1"placeholder="请输入新密码"class="form-control txt" ></td></tr>
			<tr ><td class="title2">确认密码：</td><td><input type="password" id="forget_fls_pwd2"placeholder="请再次输入"class="form-control txt"></td></tr>
			<tr><td>&nbsp;</td><td><input type="button" class="loginbtn" value="提交" onclick="setNewPwd()"><input type="button" class="resetbtn" value="返回" onclick="window.location='${pageContext.request.contextPath}/client/login.jsp'"></td></tr>		
		</tbody></table>
		</div>
	</div>
</div>
</div>
<div class="footer">Copyright © 2015-2016 uimaker  All Rights Reserved.</div>
<script src=" ${pageContext.request.contextPath}/client/Scripts/jquery-1.9.1.min.js"></script>
<SCRIPT src="${pageContext.request.contextPath}/client/Scripts/common.js"></SCRIPT>
<SCRIPT src="${pageContext.request.contextPath}/client/Scripts/main.js"></SCRIPT>

</body>
</html>