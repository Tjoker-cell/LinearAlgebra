<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta http-equiv="refresh" content="5">页面自动刷新  5秒 -->
<title>用户管理</title>
  
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">
    <link href="css/stylesheet.css" rel="stylesheet">
    <link href="icon/font-awesome.css" rel="stylesheet">
    <style type="text/css">
    .input{ width:40px;
	text-align:center;}
	.daohanglink{
		height:40px; line-height:40px; vertical-align:middle; width:100%;
		background-color:rgb(248,248,248);
		margin-bottom:15px;
		}
	.daohanglink span{
		margin-left:5px;}
	.daohang{
	float: left;
	height: 15px;
	width: 5px;
	border-left-width: 5px;
	border-left-style: solid;
	border-left-color: #036;
	margin-top:12px;
	margin-left:15px;
		}
    </style>
  </head>

  <body>
    <div id="content"> <!-- Content start -->
      <div class="inner_content">
          <div class="widgets_area">
                <div class="row-fluid">
                    <div class="span12">
                         <div  class="daohanglink"style="">
                           <span class="daohang"></span>
                           <span>首页</span><span>></span>
                           <span>管理信息</span><span>></span>
                           <span>用户管理</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <span><input type="text" id="search"style="height: 8px"   placeholder="输入用户名搜索">
							<input type="button" value="搜索" style="height: 18px" onclick="findUser()"></input>
							</span>
                           <a  href="addUser.jsp" class="label label-warning" style="float:right; margin:8px;">添加</a>
                         </div>
                        <div class="well brown">
                            
                           
                            <div class="well-content" style="border:0px;">
                                <table class="table table-striped table-bordered table-hover datatable" id="table">
                                    <thead>
                                        <tr>
                                            <th width="5%">ID</th>
                                            <th width="8%">角色名称</th>
                                            <th width="22%">密码</th>
                                            <th width="8%">用户名</th>
                                            <th width="5%">性别</th>
                                            <th width="10%">邮箱</th>
                                            <th width="5%">状态</th>
                                            <th width="13%">管理操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="user">
                                       
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<SCRIPT type="text/javascript">
var data;
var list;
	window.onload=function(){
		alert("11");
		getMess("allUser","allUser");
	}
//点击编辑在线编辑td中的内容
	function edit(h){
	alert(h);
	//进行判断
	
		 var b =$("#"+h).children();//通过id获取tr子节点td
		//alert($("#"+h).children().siblings().eq(4).text());//通过父节点tr找到其子节点td在顺着找其兄弟节点（siblings()）,eq(位置)。text(获得文本信息)
		for(var i=1;i<=5;i++){
		
		b[i].innerHTML="<input type='text' id='input"+i+"' onchange='return check("+i+")' value='"+$("#"+h).children().siblings().eq(i).text()+"'/>";
	
		}
		//点击修改后将编辑改为保存和取消
		b[7].innerHTML="<input type='button' onclick='return save("+h+")' value='保存'><input type='button' onclick='resert()' value='取消'>";
		}
//编辑保存操作
	function save(h){//未定义是可能就是id重复了
		
		for(var i=1;i<=5;i++){
			if(check(i)==false){
				return false;
			}
		}
	
	
	//获取其子节点的值
	list={
			id:h,role:document.getElementById("input1").value,password:document.getElementById("input2").value,username:document.getElementById("input3").value,sex:document.getElementById("input4").value,email:document.getElementById("input5").value
			};
	
	
	
	alert(list);
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
		type:"post",
		data:{data:JSON.stringify(list),action:"editUser"},
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
//检查用户名：密码等是否规划
	function check(id){
	for(var i=1;i<=5;i++){
		if(document.getElementById("input"+i).value=="" ){
			alert("不嫩为空");
			return false;
		}
	}
	
	if(id==1){
		var role=document.getElementById("input1").value;
		
		if("超级用户"!=role && "普通用户"!=role){
			alert("用户类型只能为【超级用户】或【普通用户】");
			return false;
		}
	}
	if(id==4){
		var sex=document.getElementById("input4").value;
		
		if("男"!=sex && "女"!=sex)
			{alert("性别只能为【男】或【女】");
			return false;
		}
	}
}
//停用用户
	function stop(h){
		var state=document.getElementById("state"+h);
		var t=$("#state"+h+"").text();//获取<td></td>之间的内容
		var state;
		if(t=="停用"){
			state.innerText="启用"
			state=1;
		}else{
			state.innerText="停用";
			state=0;
		}
		$.ajax({
			url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
			type:"post",
			data:{id:h,state:state,action:"stopUser"},
		success:function(result){
			if(result=="true"){
				
			
			}else{
				alert("系统错误");
			}
		}
		});
		

		
	}
//删除当前行	
	function delet(h) {
	var con=confirm("确定要删除id为【"+h+"】的用户");
	if(con){
		$.ajax({
			url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
			type:"post",
			data:{id:h,action:"deletUser"},
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
	function findUser(){
		var username=document.getElementById("search").value;
		var action="findUser";
		getMess(username,action);
	}
function getMess(username,action){
	$.getJSON(
			"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
			{username:username,action:action},
			function(result){
				var temp;
				var v=eval(result);
				var i=0;
				for(i;i<v.length;i++){
					temp+= '<tr id='+v[i].id+'>'+
                    '<td >'+v[i].id+'</td>'+
                    '<td>'+v[i].role+'</td>'+
                    '<td>'+v[i].password+'</td>'+
                    '<td>'+v[i].username+'</td>'+
                    '<td>'+v[i].gender+'</td>'+
                    '<td>'+v[i].email+'</td>'+
                    '<td id='+'state'+v[i].id+' >'+'<div >'+(v[i].state==0?"停用":"启用")+'</div>'+'</td>'+
                    '<td>'+
                  '<a class="btn"  title="修改" onclick="edit('+v[i].id+')">'+'<i class="icon-inbox">'+'</i>'+'</a>' +
                   '  <a class="btn" href="#" onclick="delet('+v[i].id+')" title="删除">'+'<i class="icon-trash">'+'</i>'+'</a>' +
                   '  <a class="btn" href="#" title="停用/启用"onclick="stop('+v[i].id+')">'+'<i class="icon-exclamation">'+'</i>'+'</a>' +
             		'</td>'+ '</tr>' 
				}
				$("#user tr").remove();
				$("#user").append(temp);
			}
	);
	
	
	}
	
</SCRIPT>
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 
  </body>
</html>