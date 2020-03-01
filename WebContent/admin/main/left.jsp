<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${pageContext.request.contextPath }/admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath }/admin/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	// 三级菜单点击
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('.menuson').slideUp();
		if($ul.is(':visible')){
			$(this).next('.menuson').slideUp();
		}else{
			$(this).next('.menuson').slideDown();
		}
	});
})	
</script>


</head>

    <div style="background: ">
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="${pageContext.request.contextPath }/admin/images/leftico01.png" /></span>个人信息
    </div>
    <!-- 
    	<ul class="menuson">
        <li><cite></cite><a href="user/userInfo.jsp" target="rightFrame">查看个人信息</a><i></i></li>
         <li><cite></cite><a href="user/pwd.jsp" target="rightFrame">修改密码</a><i></i></li>
        </ul>  -->   
    </dd>
	   <dd>
	    <div class="title">
	    <span><img src="${pageContext.request.contextPath }/admin/images/leftico03.png" /></span>管理信息
	    </div>
	    	<ul class="menuson">
	        <li><cite></cite><a href="${pageContext.request.contextPath }/admin/file.jsp" target="rightFrame">文件管理</a><i></i></li>
   	        <li><cite></cite><a href="admin/log.jsp" target="rightFrame">日志管理</a><i></i></li>
   	        <li><cite></cite><a href="admin/usermanager.jsp" target="rightFrame">人员管理</a><i></i></li>
   	        <li><cite></cite><a href="${pageContext.request.contextPath }/AdminServlet?action=adminQuestion" target="rightFrame">问答管理</a><i></i></li>
	       <li><cite></cite><a href="${pageContext.request.contextPath }/admin/notice.jsp" target="rightFrame">公告管理</a><i></i></li>
	       
	        </ul>    
	    </dd>
    </dl>
    </div>
</body>
</html>
 