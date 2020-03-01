<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<meta   name="referrer" content="never" charset="utf-8">
<title>在线计算工具</title>
<link href="${pageContext.request.contextPath }/client/css/style.css" rel="stylesheet" type="text/css">	<!-- 资料下载css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/jisuan.css">

	
	
</head>

<body>
<div class="container">
  <div class="left" >
    <dl class="left_nav">
      <dt> <span>线代学习</span> </dt>
   
      <dd class="currentl" >
      	<a id="title"style="font-size:15px"> 矩阵初等计算 </a>
      		<ul class="menuson">
			 <li onclick="cometo('zhuangzhi')"id="zhuangzhi" class="currentl"><cite></cite><a >矩阵转置</a><i></i></li>
        <li onclick="cometo('jiajiang')" id="jiajiang"><cite></cite><a >矩阵加法</a><i></i></li>
        <li onclick="cometo('shucheng')"id="shucheng"><cite></cite><a >矩阵乘法</a><i></i></li>
        <li onclick="cometo('nijuzheng')" id="nijuzheng"><cite></cite><a>求逆矩阵</a><i></i></li>
        <li onclick="cometo('tezhengzhi')" id="tezhengzhi"><cite></cite><a>矩阵特征值</a><i></i></li>
        <li onclick="cometo('xianshi')"id="xianshi"><cite></cite><a>判断矩阵相似</a><i></i></li>
      	</ul>
      
       </dd>
      <dd > <a id="title"  style="font-size:15px"onclick="cometo('hangliesi')" id="hangliesi">行列式计算</a> 
      	<ul class="menuson">
      	<li></li>
      	</ul>
      </dd>
      <dd data-id="jzjc"> <a id="title" style="font-size:15px">解线性方程组</a> </dd>
      <dd data-id="cdbh"> <a id="title" style="font-size:15px">线性相关判断</a></dd>
    </dl>
  </div>
   <div style="font-size:15px;width: 460;" class="right" id="right1" >
		
		</div>
<script src="${pageContext.request.contextPath }/client/Scripts/common.js"></script> 
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 
<SCRIPT >
$(function (){

	cometo("zhuangzhi");

	});
$('#title').click(function(){
	var $ul = $(this).next('ul');
	$('dd').find('.menuson').slideUp();
	if($ul.is(':visible')){
		$(this).next('.menuson').slideUp();
	}else{
		$(this).next('.menuson').slideDown();
	}
});
function cometo(mess){
	 var url="${pageContext.request.contextPath }/client/jisuan/"+mess+".jsp";
	alert(mess);
	 $("#right1").load(url);
	 //设置高亮，点击效果
	 $("#"+mess+"").addClass("currentl").siblings().removeClass("currentl");
	
}

</SCRIPT>
</body>
</html>
