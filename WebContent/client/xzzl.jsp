<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>资料下载</title>

</head>

<body style="background: #f6f5fa;">

	<!--content S-->
	<div class="super-content">
		<div class="superCtab">
			
			
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li class="cur">资料下载</li>
					</ul>
				</div>
				
				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">
						<div class="operateBtn">
							<div class="wd-msg" style="color:red">LinearAlgebra-线代学习</div>
						</div>
						<div class="searchBar">
							<input type="text" id="searchFile"  class="form-control srhTxt" placeholder="输入资料名搜索">
							<input type="button" class="srhBtn" onclick="getFile(0)">
						</div>
					</div>
					
					<div class="Mian-cont-wrap">
						<div class="defaultTab-T">
							<table border="0" cellspacing="0" cellpadding="0" class="defaultTable">
								<tbody><tr><th class="t_1">资料ID</th><th class="t_2_1">资料名称</th><th class="t_4">上传时间</th><th>操作</th></tr>
							</tbody></table>
						</div>
						<table border="0" cellspacing="0" cellpadding="0" id="tf"class="defaultTable defaultTable2">
						</table>
						<!--pages S-->
						<div class="pageSelect">
							
							<div class="pageWrap">
							
							</div>
						</div>
						<!--pages E-->
					</div>
				</div>
			</div>
		</div>
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.9.1.min.js"></script> 
<SCRIPT type="text/javascript">
window.onload=function(){
	getFile(0);
}
function getFile(currentPage){
	var search=document.getElementById("searchFile").value;//获取查询框关键字
		alert(currentPage);
		$.getJSON(
			"http://localhost:8080/LinearAlgebra2.0/PageServlet",
			{currentPage:currentPage,search:search},
			function(result){
					var p=eval(result);
					//遍历json对象
					var file=p.f;
					var j=0;
					var temp;//设置一个对象存储html
					//利用json对象来进行赋值（也可以用jstl）,注意用单引号''
					var tem;
					var but;//设置按钮可见
					//添加span属性
					tem='<span id="span">'+'共 <b>'+p.totalnum+'</b> 条'+' 每页 <b>'+p.pageSize+' </b>条'+'<B>'+
					  ' 当前第'+(p.currentPage+1)+'页</B>'+'</span>';
				for(j;j<file.length;j++){
					temp+='<tbody>'+'<tr class="wd">'+
					'<td class="t_1">'+file[j].id +'</td>'+
					'<td class="t_2_1">'+file[j].filename+'</td>'+
					'<td class="t_4">'+file[j].uploadtime+'</td>'+
					'<td class="alcenter"><a href="${pageContext.request.contextPath }/DownloadServlet?id='+file[j].id+'" class="export-a">导出数据</a></td>'+
					'</tr>'+'</tbody>';
				}
				$("#tf tr").remove(); /*  其余内容清空，防止获取重复的数据  */
				$("#span").remove();
				$("#tf").append(temp);
				$(".pageSelect").append(tem);
				//设置按钮可见
				$(".pageWrap a").remove();
				if(p.currentPage==p.totalPage-1 ){
					
					but='<a  class="pagenumb cur" id="first"onclick="getFile(0)">'+'首页</a>'+
					'<a  class="pagenumb" id="pre"onclick="getFile('+(p.currentPage-1)+')">'+'上一页</a>';
					
				}else if(p.currentPage==0){
					but='<a  class="pagenumb cur"id="next"onclick="getFile('+(p.currentPage+1)+')">下一页</a>'+
					'<a  class="pagenumb" id="last"onclick="getFile('+(p.totalPage-1)+')">尾页</a>';
				}else{
					but='<a  class="pagenumb cur"id="first" onclick="getFile(0)">首页</a>'+
					'<a  class="pagenumb" id="pre"onclick="getFile('+(p.currentPage-1)+')">上一页</a>'+
					'<a  class="pagenumb cur"id="next"onclick="getFile('+(p.currentPage+1)+')">下一页</a>'+
					'<a  class="pagenumb" id="last"onclick="getFile('+(p.totalPage-1)+')">尾页</a>';
				}
				
				$(".pageWrap").append(but);
			}
	);
}
	

</SCRIPT>
</body>
</html>