<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta   name="referrer" content="never" charset="utf-8">
<title>LinearAlgebra-study</title>
 <link href="${pageContext.request.contextPath }/client/css/basic.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/client/css/style.css" rel="stylesheet" type="text/css">	<!-- 资料下载css -->
	<!-- 下载资料css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/page.css">
<!-- 以下两个css主要是防止页面抛锚 -->
<link rel="stylesheet" href="https://csdnimg.cn/public/sandalstrap/1.4/css/sandalstrap.min.css">
<link rel="stylesheet" href="https://csdnimg.cn/release/phoenix/template/css/ck_htmledit_views-833878f763.css" />
<link rel="stylesheet" href="{pageContext.request.contextPath }/client/css/base.css">
	<link rel="stylesheet" href="{pageContext.request.contextPath }/client/css/page.css">
<!--[if IE]><script src="Scripts/html5.js"></script><![endif]-->
<!-- 问答 -->
<link rel="stylesheet" href="//csdnimg.cn/release/download/static_files/pc/css/download_detail-2e8422accb.css"/>
    <link rel="stylesheet" href="//csdnimg.cn/release/download/static_files/pc/css/common-65254fc43a.css" />
</head>
<body>
<div class="container">
  <header>
    <div class="header_top">
      <ul class="header_tip">
        <li class="header_tipl"></li>
        <li> <a href="${pageContext.request.contextPath }/AdminServlet?action=login" class="header_tip_a htip_icon1">登录</a> </li>
        <li> <a href="#" class="header_tip_a htip_icon2">系统帮助</a> </li>
        <li> <a href="${pageContext.request.contextPath }/AdminServlet?action=exit" class="header_tip_a htip_icon4" >退出</a> </li>
        <li class="header_tipr"></li>
      </ul>
      <div class="clear"></div>
      <a href="#" class="logo"></a>
      <nav> <a class="current" href="${pageContext.request.contextPath }/client/main.jsp" id="firstPage">首 页</a> <a  onclick="domanager('jisuan')" id="jisuan" >计算工具</a> <a onclick="doload()"id="wenda">问答论坛</a> <a  id="suanfa">线代算法</a> <a onclick="aboutus()" id="aboutus">关于我们</a></nav>
    </div>
    <div class="msg"> <span class="floatleft">欢迎您，${user.username}</span> <span class="msg_line"></span> <span class="msg_a">${notice.size()}<span class="gray">条公告</span></span>
      <div class="notice"> <span class="msg_line"></span>
        <div class="notice_ctn_box">
          <ul class="notice_ctn">
          <c:forEach begin="0" end="${notice.size()-1}" items="${notice }" var="notic">
            <li><a href="#">${notic.content}</a></li>
           </c:forEach>
          </ul>
        </div>
        <span class="notice_close"></span> <span class="notice_arrowr"></span> <span class="notice_arrowl"></span> </div>
    </div>
  </header>
    <div id="manger">
  <div class="left" >
    <dl class="left_nav">
      <dt> <span>线代学习</span> </dt>
      <dd class="currentl" data-id="zstp"> <a>知识图谱</a> </dd>
      <dd data-id="hls"> <a  >行列式</a> </dd>
      <dd data-id="jzjc"> <a >矩阵基础</a> </dd>
      <dd data-id="cdbh"> <a>初等变换</a></dd>
      <dd data-id="xlz"> <a>向量组与线性相关</a> </dd>
      <dd data-id="xsjz"> <a>相似矩阵与二次型</a> </dd>
      <dd data-id="xzzl" onclick="get()"> <a>下载完整资料</a> </dd>
      
    </dl>
  </div>
   <div style="font-size:15px" class="right" id="right1" >
   <h1 class="jqplot_title" style="font-size:22px ;color:red"><b>线性代数知识图谱</b></h1>
  <div>
  <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;线性代数是代数学的一个分支，主要处理线性关系问题。线性关系意即数学对象之间的关系是以一次形式来表达的。例如，在解析几何里，<p>平面上直线的方程是二元一次方程；空间平面的方程是三元一次方程，而空间直线视为两个平面相交，由两个三元一次方程所组成的方程组来表示。</p>含有 n个未知量的一次方程称为线性方程。变于关量是一次的函数称为线性函数。线性关系问题简称线性问题。解线性方程组的问题是最简单的线性问题。
</P>
<img  src="https://img-blog.csdn.net/20161127163357400">  </img>
</div>
</div>

</div>
 <SCRIPT type="text/javascript">
 function aboutus(){
	 var message="【Tjoker】：线代学习系统，有问题请联系QQ：**********";
	 alert(message);
	 $("#aboutus").addClass("current").siblings().removeClass("current");
 }
 function domanager(manager){
	 alert(manager);
	 //通过变量传递，避免循环
	 switch (manager) {
	case "jisuan":
		 var url="${pageContext.request.contextPath }/client/jisuan/"+manager+".jsp"
		 alert("fdsg");
		 break;
	default:
		 var url="${pageContext.request.contextPath }/client/"+manager+".jsp"
		break;
	}
	
		 $("#manger").load(url);
		 //设置高亮，点击效果
		 $("#"+manager+"").addClass("current").siblings().removeClass("current");
	
	 
 }

 function doload(){
	 <c:if test="${user==null}">
	 alert("用户未登录，请登录");
	 return;
	 </c:if>
	 window.location.href = '${pageContext.request.contextPath}/client/wenda.jsp';
 }
 </SCRIPT>
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 
<script src="${pageContext.request.contextPath }/client/Scripts/common.js"></script>
<script src=" ${pageContext.request.contextPath}/client/Scripts/main.js"></script>

</body>
</html>
<!-- 
1. meat中属性 referrer防盗链（解决引用网络图片不显示问题）  name="referrer" content="never" 
2.以下两个css主要是防止页面抛锚
<link rel="stylesheet" href="https://csdnimg.cn/public/sandalstrap/1.4/css/sandalstrap.min.css">
<link rel="stylesheet" href="https://csdnimg.cn/release/phoenix/template/css/ck_htmledit_views-833878f763.css" />
 -->