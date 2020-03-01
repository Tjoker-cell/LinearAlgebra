<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <link href="${pageContext.request.contextPath }/client/css/basic.css" rel="stylesheet" type="text/css">

<title>top</title>


</head>

<body style="background:url(/admin/images/topbg.gif) repeat-x;">
      <ul class="header_tip">
        <li> <a href="${pageContext.request.contextPath }/AdminServlet?action=login" class="header_tip_a htip_icon1">登录</a> </li>
        <li> <a href="#" class="header_tip_a htip_icon2">系统帮助</a> </li>
        <li> <a href="${pageContext.request.contextPath }/AdminServlet?action=exit" class="header_tip_a htip_icon4" >退出</a> </li>
      </ul>
      
      
</body>
<script type="text/javascript">
function exit(){
	 sessionStorage.clear()  ; //清除所有session值
	 window.location.href = '${pageContext.request.contextPath}/client/login.jsp';
}
</script>
</html>