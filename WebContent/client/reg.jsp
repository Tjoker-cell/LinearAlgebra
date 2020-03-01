<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<STYLE type="text/css">
body { background: #00FFCC;}
.superloginA {background: url(${pageContext.request.contextPath}/client/images/bg_loginA.jpg) center 0 no-repeat #313541; min-width: 1000px; height: 450px;}
.superlogin {background: url(${pageContext.request.contextPath}/client/images/bg_login.jpg) center 0 no-repeat #f23e47; min-width: 1000px; height: 450px;}
.loginBox { margin-top: -307px;}
.loginBox .logo { width: 232px; margin: 0 auto;}
</STYLE>
<title>新会员注册</title>
</head>
<body>
	<div class="logo"><img src="${pageContext.request.contextPath }/client/images/logo_login.png"/></div>

<form action="${pageContext.request.contextPath}/RegServlet" method="post" onsubmit="return checkForm();"  >
			<table width="850px" border="0" cellspacing="0">
				<tr>
					<td style="padding: 30px"><h1>新会员注册</h1>
						<table width="70%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align: right; width: 20%">会员邮箱：</td>
								<td style="width: 40%">
								<input type="text" class="textinput"  id="email"required="required"  name="email" onkeyup="checkEmail();"/>
								</td>
								<td colspan="2"><span id="emailMsg"></span><font color="#999999">请输入有效的邮箱地址</font></td>
							</tr>
							<tr>
								<td style="text-align: right">会员名：</td>
								<td><input type="text" class="textinput"  id="username" name="username" onkeyup="checkUsername();"/>
								</td>
								<td colspan="2"><span id="usernameMsg"></span><font color="#999999">字母数字下划线1到10位, 不能是数字开头</font></td>
							</tr>
							<tr>
								<td style="text-align: right">密码：</td>
								<td><input type="password" class="textinput"  id="password" name="password" onkeyup="checkPassword();"/></td>
								<td><span id="passwordMsg"></span><font color="#999999">密码请设置6-16位字符</font></td>
							</tr>
							<tr>
								<td style="text-align: right">重复密码：</td>
								<td>
								<input type="password" class="textinput"  id="repassword" name="repassword" onkeyup="checkConfirm();"/>
								</td>
								<td><span id="confirmMsg"></span>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align: right">性别：</td>
								<td colspan="2">&nbsp;&nbsp;
                                <input type="radio" name="gender" value="男" checked="checked" /> 男
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="gender" value="女" /> 女
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align: right">联系电话：</td>
								<td colspan="2">
								<input type="text" class="textinput"
									style="width: 350px" name="telephone" />
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td style="text-align: right">个人介绍：</td>
								<td colspan="2">
								<textarea class="textarea" name="introduce"></textarea>
								</td>
								<td>&nbsp;</td>
							</tr>
						</table>
						
					
						<h1>注册校验</h1>
						
						<table width="80%" border="0" cellspacing="2" class="upline">
							<tr>
								<td style="text-align: right; width: 20%">输入校验码：</td>
								<td style="width: 50%">
								<input type="text" class="textinput" id="emailCode" placeholder="请输入验证码" />
								<button onclick="return sendCode()"  id="submit">发送验证码</button>
								</td>
								<td>&nbsp;</td>
							</tr>
							
						

						<table width="70%" border="0" cellspacing="0">
							<tr>
								<td style="padding-top: 20px; text-align: center">
									<input type="image" src="${pageContext.request.contextPath}/client/images/signup.gif" name="submit" border="0"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
	</form>
		
<script type="text/javascript" src="${pageContext.request.contextPath}/client/Scripts/form.js"></script>
<script src=" ${pageContext.request.contextPath}/client/Scripts/jquery-1.9.1.min.js"></script>

</body>
</html>