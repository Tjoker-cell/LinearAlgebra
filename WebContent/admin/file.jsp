<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/page.css">
	<!--[if lte IE 8]>
	<link href="${pageContext.request.contextPath }/admin/ css/ie8.css" rel="stylesheet" type="text/css"/>
	<![endif]-->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/modernizr.js"></script>
	<!--[if IE]>
	<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->
	<TITLE>
	文件管理
	</TITLE>
</head>

<body style="background: #f6f5fa;">

	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
		
		<!--header-->
		<div class="superCtab">
			<div class="ctab-title clearfix"><h3>文件管理</h3></div>
			
			<div class="ctab-Main">
				
				
				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">
						<div class="operateBtn">
							<a href="fileUpload.jsp" class="greenbtn publish">文件上传</a>
						</div>
						<div class="searchBar">
							<input type="text" id="searchFile"  class="form-control srhTxt" placeholder="输入文件名称关键字搜索">
							<input type="button" class="srhBtn" onclick="getFile(0)">
						</div>
					</div>
					<div class="Mian-cont-wrap">
						<div class="defaultTab-T">
							<table border="0" cellspacing="0" cellpadding="0" class="defaultTable">
								<tbody>
								
										<tr><th class="t_1">文件ID</th>
										<th class="t_2">文件名称</th>
										<th class="t_3">上传时间</th>
										<th class="t_4">操作</th></tr>
								
							</tbody></table>
						</div>
						<table border="0"id="tf" cellspacing="0" cellpadding="0" class="defaultTable defaultTable2">
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
		<!--main-->
		
	</div>
	

<script type="text/javascript" src="js/zxxFile.js"></script>
<SCRIPT >
window.onload=function(){
	getFile(0);
	
}
//删除当前行	
function delet(h) {
var con=confirm("确定要删除id为【"+h+"】的文件");

if(con){
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
		type:"post",
		data:{id:h,action:"deletFile"},
	success:function(result){
		if(result=="true"){
			$("#tr"+h+"").remove();
			 alert("删除成功");
		}else{
			alert("删除错误");
		}
	}
	});
	
}
}
function editFile(id){
	var a=$("#tr"+id).children();
	a[1].innerHTML="<td class='t_2_1'><input type='text' id='input"+id+"' value='"+$("#tr"+id).children().siblings().eq(1).text()+"'/></td>";
	//点击修改后将编辑改为保存和取消
	a[4].innerHTML="<div class='btn'><a href='#' class='modify' onclick='return save("+id+")'>保存</a><a href='#' class='delete' onclick='resert()'>取消</a></div></td>";
}
//编辑保存操作
function save(id){//未定义是可能就是id重复了
	var content=document.getElementById("input"+id).value;
	if(content==""){
		alert("修改值不能为空");
		return false;
	}
	
$.ajax({
	url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
	type:"post",
	data:{id:id,content:content,action:"editFile"},
success:function(result){
	if(result=="true"){
		resert();
		 alert("修改成功");
	}else{
		alert("修改错误");
	}
}
});

}


//编辑取消操作
function resert(){
//直接刷新页面
	location.reload();
}
function getFile(currentPage){
	var search=document.getElementById("searchFile").value;//获取查询框关键字
	alert(currentPage);
	alert("search="+search);
	$.getJSON(
		"http://localhost:8080/LinearAlgebra2.0/PageServlet",
		{currentPage:currentPage,search:search,action:"file"},
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
				temp+='<tr class="wd" id='+'tr'+file[j].id+'>'+
				'<td class="t_1">'+file[j].id +'</td>'+
				'<td class="t_2_1">'+file[j].filename+'</td>'+
				'<td class="t_4">'+file[j].uploadtime+'</td>'+
				'<td class="alcenter"><a href="${pageContext.request.contextPath }/DownloadServlet?id='+file[j].id+'" class="export-a">导出数据</a></td>'+
				'<td class="t_4"><div class="btn"><a class="Top">置顶</a><a href="#" class="modify" onclick="editFile('+file[j].id+')">修改</a><a href="#" class="delete" onclick="delet('+file[j].id+')">删除</a></div></td>'+
				'</tr>';
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
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 
<!-- ajax必须要引入jquery -->
</body></html>