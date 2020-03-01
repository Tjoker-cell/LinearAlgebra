<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
	<meta charset="utf-8">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>我要提问</title>
	<link rel="stylesheet" href="css/base.css">
	<link rel="stylesheet" href="css/page.css">
	

	</head>

<body style="background: rgb(246, 245, 250);">
	<!--content S-->
	<div class="super-content">
		
		<div class="superCtab">
			<div class="publishArt">
				<h4>发布问题</h4>
				<div class="pubMain">
					<a href="javascript:history.go(-1)" class="backlistBtn"><i class="ico-back"></i>返回列表</a>
					<form action="" method="post">
						
						<h5 class="pubtitle">问题标题</h5>
						<div class="pub-txt-bar">
							<input type="text" style="width:350px;height:26px"placeholder="请输入你要提问的问题标题" id="quetitle" >
						</div>
						
						<h5 class="pubtitle">问题内容</h5>
						<div class="pub-area-bar">
							<textarea name="" rows="" cols="3"placeholder="请输入的问题具体内容" id="quecontext"></textarea>
						</div>
			
						<div class="pub-btn">
							<input type="button" onclick=" return fabu()" value="发布" class="saveBtn">
						</div>
					</form>
				</div>
			</div>
		
		</div>
	</div>
	<script type="text/javascript">
	function fabu(){
	var title=document.getElementById("quetitle").value;
	var context=document.getElementById("quecontext").value;
	alert(title+";"+context);
	if(title==""){
		alert("标题不能为空");
		return false;
	}
	if(context==""){
		alert("内容不能为空");
		return false;
	}//验证session是否失效
	<c:if test="${user==null}">
		alert("用户登录失效请重新登录");
		window.location.href = '${pageContext.request.contextPath}/client/login.jsp';
	</c:if>
	//ajax实现数据跟新
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/QuestionServlet",
		type:"post",
		data:{title:title,context:context},
		success:function(result){
		if(result=="true"){
			 alert("发表成功");
			document.getElementById("quetitle").valu="";
			document.getElementById("quecontext").value="";
		}else{
			alert("发表错误");
		}
	}
	});
	
	}
	</script>
	<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 
	
</body></html>
