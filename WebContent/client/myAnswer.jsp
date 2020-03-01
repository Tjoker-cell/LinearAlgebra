<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>我的解答</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/client/css/page.css">
</head>

<body style="background: #f6f5fa;">

	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
		
		<!--header-->
		<div class="superCtab">
			<div class="ctab-title clearfix"><h3>我的解答</h3></div>
			
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li class="cur"><a href="wenzhang_pinshang.html">我的解答</a></li>
						<li ><a href="${pageContext.request.contextPath }/QuestionServlet?name=myQuestion&user_id=${user.id } ">我的提问</a></li>
						<li><a href="${pageContext.request.contextPath }/client/wenda.jsp">问答论坛</a></li>
						
					</ul>
				</div>
				
				<div class="ctab-Mian-cont">
				
					<div class="Mian-cont-wrap">
						<div class="defaultTab-T">
							<table border="0" cellspacing="0" cellpadding="0" class="defaultTable">
								<tbody><tr><th class="t_1">提问者</th><th class="t_2">我的回答</th><th class="t_3">提问时间</th><th class="t_4">操作</th></tr>
							</tbody></table>
						</div>
						<table border="0" cellspacing="0" cellpadding="0" class="defaultTable defaultTable2">
							<tbody>
							
							<c:forEach begin="0" step="1" end="${myanswer.size()}" items="${myanswer }" var="answer"> 
							<tr id="${answer.id }">
								<td class="t_1">${answer.user_id }${answer.user_name }</td>
								<td class="t_2"><a href="#">${answer.content }</a></td>
								<td class="t_3">${answer.explain_time }</td>
							<td class="t_4"><div class="btn"><a href="${pageContext.request.contextPath }/AnswerServlet?id=${answer.question_id }" class="modify">详情</a><a href="#" class="delete" onclick="delet(${answer.id })">删除</a></div></td>
							</tr>
							</c:forEach>
						</tbody></table>
						<!--pages S-->
						<div class="pageSelect">
							<span>共 <b>${myanswer.size() }</b> 条 每页 <b></b>条 </span>
							<div class="pageWrap">
								<a class="pagePre"><i class="ico-pre">&nbsp;</i></a>
								<a href="#" class="pagenumb cur">1</a>
								<a href="#" class="pagenumb">2</a>
								<a href="#" class="pagenumb">3</a>
								<a href="#" class="pagenext"><i class="ico-next">&nbsp;</i></a>
							</div>
						</div>
						<!--pages E-->
					</div>
				
				</div>
			</div>
		</div>
		<!--main-->
		
	</div>
<script type="text/javascript">
//删除当前行	
function delet(h) {
var con=confirm("确定要删除id为【"+h+"】的解答吗？");
if(con){
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/AnswerServlet",
		type:"post",
		data:{id:h,action:"deletAnswer"},
	success:function(result){
		if(result=="true"){
			 $("#"+h+"").remove();
			 alert("删除成功");
		}else{
			alert("删除错误");
		}
	}
	});
	
}
}



</script>

<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 


</body></html>
