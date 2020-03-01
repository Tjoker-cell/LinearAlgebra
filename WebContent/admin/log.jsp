<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- <meta http-equiv="refresh" content="5">页面自动刷新  5秒 -->
<title>日志管理</title>
  
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
                         </div>
                        <div class="well brown">
                            
                           
                            <div class="well-content" style="border:0px;">
                                <table class="table table-striped table-bordered table-hover datatable" id="table">
                                    <thead>
                                        <tr>
                                            <th width="5%">ID</th>
                                            <th width="8%">用户ID</th>
                                            <th width="8%">用户名</th>
                                            <th width="5%">操作</th>
                                            <th width="10%">用户角色</th>
                                            <th width="5%">状态</th>
                                              <th width="38%">详情</th>
	                                            <th width="18%">操作时间</th>
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
		getMess("allLog","allLog");
	}
//删除当前行	
	function delet(h) {
	var con=confirm("确定要删除id为【"+h+"】的日志");
	if(con){
		$.ajax({
			url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
			type:"post",
			data:{id:h,action:"deletLog"},
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
		var action="findLog";
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
					temp+= '<tr id='+v[i].log_id+'>'+
                    '<td >'+v[i].log_id+'</td>'+
                    '<td>'+v[i].user_id+'</td>'+
                    '<td>'+v[i].user_name+'</td>'+
                    '<td>'+v[i].log_action+'</td>'+
                    '<td>'+v[i].user_role+'</td>'+
                    '<td>'+v[i].user_state+'</td>'+
                    '<td>'+v[i].log_content+'</td>'+
                    '<td  >'+v[i].log_time+'</td>'+
                    '<td>'+
                   '  <a class="btn" href="#" onclick="delet('+v[i].log_id+')" title="删除">'+'<i class="icon-trash">'+'</i>'+'</a>' +
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