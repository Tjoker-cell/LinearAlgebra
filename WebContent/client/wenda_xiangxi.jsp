<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<meta   name="referrer" content="never" charset="utf-8">

    <title>问题详细</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="//csdnimg.cn/release/download/static_files/pc/css/common-65254fc43a.css" />
	<link rel="stylesheet" href="//csdnimg.cn/release/download/static_files/pc/css/download_detail-2e8422accb.css"/>

</head>
<body>

<div class="news-nav-wrap">
    <div class="news-nav">
        <div class="container clearfix">
            <div id="csdn_tool_otherPlace"></div>
            <div class="nav-bar clearfix">
                <a class="current" href="${pageContext.request.contextPath }/client/main.jsp">首页</a>
                <a  href="${pageContext.request.contextPath }/client/main.jsp" >计算工具</a>
                <a  href="${pageContext.request.contextPath }/client/wenda.jsp" >问答论坛</a>
                <a  href="${pageContext.request.contextPath }/client/main.jsp" >关于我们</a>
            </div>
        </div>
    </div>
</div>
<div class="home_page_wrap">
    <div class="main clearfix">
        <div class="main_l fl right_main_box">

            <div class="resource_box">
                <div class="resource_box_info">
                    <dl class="resource_box_dl">
                        <dt>
                            <img src="//csdnimg.cn/release/download/static_files/pc/images/minetype/zip.svg"/>
                        </dt>
                        <dd>
                            <h3>
                                <span class="resource_title">${question.title}</span>
                            </h3>
					
                            <div class="resource_box_desc">
                                <div class="resource_description">
                                    <p>${question.content }<br></p>
                                </div>
                               
                                <div class="resource_box_b">
                                 <label>
                               	 <span>上传者：</span>
                                <em class="upl_time">${question.user_id }&nbsp;${question.user_name }</em>
                            		</label>
                                    <strong class="size_box">
                                        <span>${question.publish_time } 上传</span>
                                       
                                    </strong>
                                </div>
                            </div>
                        </dd>
                    </dl>
                </div>

                <div class="resource_box_fn clearfix">
                 
                    <div class="right_fn fr">
                        <!-- 回复功能 -->
                        <a href="#ans_content"  id="download_report" class="download_report dl_func">
                            <i class="fa fa-exclamation-triangle"></i>
                            <span>回复</span>
                        </a>
                       
                    </div>

                </div>
            </div>

            <!-- 用户评论 -->
                            <div id="comment" class="dl_comm clearfix">
                    <!--标头-->
                    <h3 class="comment_t">
                        <strong>评论</strong>
                        <span class="comment_t_num">共${answer.size() }条</span>
                    </h3>
                    <!--列表-->
                  
                    <c:forEach begin="0" end="${answer.size()}" step="1" items="${answer }" var="answer">
                    <div class="comm_t">
                             <dl class="comm_list clearfix" id="${answer.id }">
                                <dt class="comm_list_t clearfix">
                                    <div class="comm_list_t_l fl">
                                        <a href="#" target="_blank">
                                            <img class="comm_avatar" src="https://avatar.csdn.net/9/A/A/2_ailiuyv.jpg">
                                        </a>
                                        <a class="dl_user" href="#" target="_blank"><b>${answer.user_id }&nbsp;${answer.user_name }</b>：</a>
                                        <span class="comm_con">${answer.content}                                     
                                                                            </span>
                                                                            
                                    </div>
                                    <div class="comm_list_t_r fr">
                                        <span>${answer.explain_time}</span>
                                        <c:if test="${user.role=='超级用户'}">
                                        <div style="position:fixed;top:60px;right:220px;">	<a href="${pageContext.request.contextPath }/admin/question.jsp" class="backlistBtn"><i class="ico-back"></i>返回列表</a></div>
                                          <a href="#" class="dl_reco_btn dl_reco_btn_new" onclick="delet(${answer.id})">删除</a>
                                    </c:if>
                                </dt>
                               
                                <!--回复内容列表-->
                </dl>
                               
                 </c:forEach>            
                                <!--回复内容列表-->
                                                                                                                                </dl>
                  </div>
                    <!--页码-->
                                            <div class="comm_page_wrap">
                            <a class="pageliststy prev" style="display: none;" href="javascript:;">上一页</a>
                            <strong>1</strong>
                            <a class="pageliststy next" href="javascript:;">下一页</a>
                        </div>
                                        <!-- 评论框 -->
            </div><br/><br/><br/><br/><br/><br/>
			<div id="huifu" style="text-align:center;">
				<textarea id="ans_content" style="width: 560px;height:180px;"placeholder="请输入回复的内容"> </textarea>
			<div style="position:relative;left:-230px;top:6px">
				<input type="button" value="发布" onclick=" fabu()" style="width: 88px;height:28px"></input>
			</div>
			</div>
       
        </div>
    </div>
</div>

<script type="text/javascript">
//管理员删除炒作
	function delet(h){
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
//发表评论
	function fabu(){
		var content=document.getElementById("ans_content").value;
		alert(content);
		if(content==""){
			alert("内容不嫩为空");
			return false;
		}
		<c:if test="${user.id==null}">
			alert("登录失效，请重新登录");
			location.href="${pageContext.request.contextPath}/client/login.jsp";
		return false;
		</c:if>
	var id="${question.id}";
	var user_id="${question.user_id}";
	var user_name="${question.user_name}";
	
	location.href="${pageContext.request.contextPath}/AnswerServlet?action=answer&content="+content+"&que_id="+id+"&user_id="+user_id+"&user_name="+user_name;
	}

</script>
<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 

</body>
</html>
<!-- 在js中使用el表达式一定要使用双引号 -->