<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta   name="referrer" content="never" charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>小凡知道</title>
	<link rel="stylesheet" href="css/base.css">
	<link rel="stylesheet" href="css/page.css">
	<link rel="stylesheet" href="//csdnimg.cn/release/download/static_files/pc/css/download_detail-2e8422accb.css"/>
    <link rel="stylesheet" href="//csdnimg.cn/release/download/static_files/pc/css/common-65254fc43a.css" />
</head>

<body style="background: #f6f5fa;">

	<!--content S-->
	<div class="super-content RightMain" id="RightMain">
		
		<!--header-->
		<div class="superCtab">
			<div class="ctab-title clearfix"><h3>问答论坛</h3></div>
			
			<div class="ctab-Main">
				
				
				<div class="ctab-Mian-cont">
					<div class="Mian-cont-btn clearfix">
						<div class="operateBtn">
							<a href="main.jsp" class="greenbtn publish">首页</a>
							<a href="${pageContext.request.contextPath }/QuestionServlet?name=myQuestion&user_id=${user.id }" class="greenbtn publish">我的提问</a>
							<a href="${pageContext.request.contextPath }/AnswerServlet?action=myAnswer&user_id=${user.id } "class="greenbtn publish">我的解答</a>
							<a href="question.jsp" class="greenbtn publish">提问</a>
						</div>
						<div class="searchBar">
							<input type="text" id="" value="" class="form-control srhTxt" placeholder="输入标题关键字搜索">
							<input type="button" class="srhBtn" value="">
						</div>
					</div></div>
<!-- --------------------------------------------------------------------------------------------------- -->
	 <div class="resource_recommend">
         <div class="album_detail_wrap">
                      
	        </div>
	    </div>
				
			</div>
<SCRIPT type="text/javascript">

	window.onload=function(){
		$.ajax({
			url:"http://localhost:8080/LinearAlgebra2.0/QuestionServlet",
			type:"post",
			data:{name:"wenda"},
			success:function(result){
			var que=eval(result);
			var temp="";
				for(var i=0;i<que.length;i++){
					temp+='<dl class="album_detail_list clearfix">'+
                        '<dt>'+
                    '<a class="dl_block_a" target="_blank"href="${pageContext.request.contextPath}/AnswerServlet?id='+que[i].id+'">'+
                   ' <img src="${pageContext.request.contextPath}/client/images/111111.png" title="">'+
                    '</a>'+
               ' </dt>'+
               ' <dd>'+
                    '<a target="_blank" href="${pageContext.request.contextPath}/AnswerServlet?id='+que[i].id+'" class="album_detail_title reco_a">'+que[i].title+'</a>'+
                   ' <p class="download-text">'+
                       ' <a target="_blank"href="${pageContext.request.contextPath}/AnswerServlet?id='+que[i].id+'" class="album_detail_title reco_a">'+que[i].content+'</a>'+
                  '  </p>'+
                    '<a class="dl_block_a"href="${pageContext.request.contextPath}/AnswerServlet?id='+que[i].id+'">'+
                       ' <div class="album_detail_bot clearfix">'+
                           ' <label>'+
                               ' <span>提问者：</span>'+
                                '<em class="upl_time">'+que[i].user_id + '_'+que[i].user_name+'</em>'+
                            '</label>'+
                            '<label>'+
                                '<span>时间：</span>'+
                               ' <em class="upl_time">'+que[i].publish_time+' </em>'+
                           ' </label>'+
                        '</div>'+
                    '</a>'+
                '</dd>'+
           ' </dl>';
           
				}
				$(".album_detail_wrap dl").remove();
				$(".album_detail_wrap").append(temp);
			
		}
		});
	}

</SCRIPT>
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 

</body></html>
