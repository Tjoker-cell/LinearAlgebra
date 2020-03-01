<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录 </title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/base.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/login.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/reset.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/common.css" />
</head>
<body>
<div class="superlogin"></div>
<div class="loginBox">
	<div class="logo"><img src="${pageContext.request.contextPath }/client/images/logo_login.png"/></div>
	<div class="login_box">	
					
					<div class="login_form">
						<div class="login_title">
							登录
						</div>
			
					 
 				<div style="text-align:center">
						<span style="font-size:15px;color:red;font-weight:bold;">${message==null?"":message}</span>
				</div>
				<form action="${pageContext.request.contextPath }/login" onsubmit="return login()" method="post">
					<div class="form_text_ipt">
						<input name="username" type="text" id="username">
					</div>
					<div class="form_text_ipt">
						<input name="password" type="password" id="password" >
					</div>
					<div class="form_check_ipt">
						<div class="left check_left">
							<label><input name="" type="checkbox"> 下次自动登录</label>
						</div>
						<div class="right check_right">
							<a href="forget.jsp">忘记密码</a>
						</div>
					</div>
					<div class="form_btn">
						<button type="submit" >登录</button>
					</div>
					<div class="form_reg_btn">
						<span>还没有帐号？</span><a href="reg.jsp">马上注册</a>
					</div>
				</form>
				<div class="other_login">
					<div class="left other_left">
						<span>其它登录方式</span>
					</div>
					<div class="right other_right">
						<a href="#"><i class="fa fa-qq fa-2x"></i></a>
						<a href="#"><i class="fa fa-weixin fa-2x"></i></a>
						<a href="#"><i class="fa fa-weibo fa-2x"></i></a>
					</div>
				</div>
			</div>
		</div>
</div>

<script src=" ${pageContext.request.contextPath}/client/Scripts/common.js"></script>
 <script src=" ${pageContext.request.contextPath}/client/Scripts/main.js"></script>
 
</body>
</html>
<!-- ${pageContext.request.contextPath }绝对路径的使用，用于jsp不同路径下 -->