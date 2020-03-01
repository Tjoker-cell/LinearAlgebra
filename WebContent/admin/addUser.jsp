<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<head>
 <title>用户添加</title>
    <meta name="description" content="">
    <meta name="author" content="">

   
    <link href="${pageContext.request.contextPath }/admin/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/admin/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/admin/css/stylesheet.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/admin/css/index.css" rel="stylesheet">
    <link href="/admin/icon/font-awesome.css" rel="stylesheet">
    
  </head>

  <body>

    
    <div id="content"> <!-- Content start -->
      <div class="inner_content">
          <div class="widgets_area">
                <div class="row-fluid">
                
                    <div class="span12">
                         <div  class="daohanglink"style="">
                           <span class="daohang"></span>
                           <a href="javascript:history.go(-1)" >  <span>首页</span><span>></span></a>
                        <a href="javascript:history.go(-1)" > <span >用户管理</span><span>></span></a>  
                           <span>添加用户</span>
                           
                         </div>
                        <div class="well brown">
                         <form action="#">
                            <div class="form_list"><label class="lable_title">用户名</label><input  id="username" class="form_input" type="text"/></div>
                            <div class="form_list"><label class="lable_title">密   码</label><input id="password" class="form_input" type="text"/></div>
                            <div class="form_list"><label class="lable_title" >用户类型</label>
                            <select style="width:150px; margin-left:15px;" id="role">
                              <option>普通用户</option>
                              <option>超级用户</option>
                            </select>
                            
                            </div>
                            <div class="form_list"><label class="lable_title">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label><input class="form_input" id="email" type="text"/></div>
                            <div class="form_list"><label class="lable_title" >性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"id="sex">&nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="sex">&nbsp;&nbsp;女</div>
                            <div class="form_list"><label class="lable_title" >手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机</label><input class="form_input"id="telephone" type="text"/></div>
                            <div class="form_list"><label class="lable_title" >状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" id="state">&nbsp;&nbsp;启用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"id="state">&nbsp;&nbsp;禁用</div>
                            <div class="form_list"><input type="submit" class="submit" onclick ="return addUser()"value="&nbsp;&nbsp;提&nbsp;&nbsp;交&nbsp;&nbsp;"></div>
                         </form>   
                           
                          
                        </div>
                    </div>
                </div>

            
            </div>
        </div>
    </div>
  <script type="text/javascript">
  	function addUser(){
  		
  		var state=document.getElementById("state").checked==false?"停用":"启用";//单选按钮
  		var role=$("#role option:selected").text();//下拉宽的值
  		var sex=document.getElementById("sex").checked==false?"女":"男";//单选按钮
  		var username=document.getElementById("username").value;
  		var password=document.getElementById("password").value;
  		var email=document.getElementById("email").value;
  		var telephone=document.getElementById("telephone").value;
  		if(username==""&&password==""&&email==""){
  			alert("不能为空");
  			return false;
  		}
  		var list={
  				role:role,
  				sex:sex,
  				username:username,
  				password:password,
  				email:email,
  				telephone:telephone,
  				state:state
  		};
  		alert(list);
  		
  	
  $.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/AdminServlet",
		type:"post",
		data:{data:JSON.stringify(list),action:"addUser"},
	success:function(result){
		if(result=="true"){
			alert("天加成功");
		}else{
			alert("添加失败");
		}
	}
	});
  
	}
  
  
  </script>
    <script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.8.3.min.js"></script> 
    
  </body>
</html>