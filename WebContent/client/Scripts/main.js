//功能js
//功能js
//功能js

//页面加载时，生成随机验证码
window.onload=function(){
 //createCode(4);  
//对forgetPwd中验证码进行隐藏
	$("#email_3").hide();
	
}
//main.jsp左菜单右内容
$(function(){
		$(".left_nav").on("click","dd",function(){
			var sId=$(this).data("id");//获取data-id的值
			window.location.hash=sId;//设置锚点
			loadInner(sId);
		});
		function loadInner(sId){
			var sId=window.location.hash;
			var pathn ,i;
		switch(sId){
			case "#zstp":
				pathn="zstp.jsp";
				i=0;
			break;
			case "#hls":
				pathn="hls.jsp";
				i=1;
			break;
			case "#jzjc":
				pathn="jzjc.jsp";
				i=2;
			break;
			case "#cdbh":
				pathn="cdbh.jsp";
				i=3;
			break;
			case "#xlz":
				pathn="xlz.jsp";
				i=4;
			break;
			case "#xsjz":
				pathn="xsjz.jsp";
				i=5;
			break;
			case "#xzzl":
				pathn="xzzl.jsp";
				
				i=6;
			break;
			
			}
			$("#right1").load(pathn);//加载响应内容
			$(".left_nav dd").eq(i).addClass("currentl").siblings().removeClass("currentl");
			
		}
		var sId=window.location.hash;
		loadInner(sId);
		
	})
//login.jsp判断账号，密码是否为空
function login(){
	//得到name,password文本框对象
	var name=document.getElementById("username");
	var password=document.getElementById("password");
	//判断文本框是否有内容
	if(name.value.length==0){
		confirm("用户名不能为空");
		return false;
	}
	if(password.value.length==0){
		confirm("密码不能为空");
		return false;
	}
	return validateCode();

}


//生成验证码的方法
function createCode(length) {
    var code = "";
    var codeLength = parseInt(length); //验证码的长度
    //var checkCode = document.getElementById("checkCode");
    ////所有候选组成验证码的字符，当然也可以用中文的
    var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
    'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); 
    //循环组成验证码的字符串
    for (var i = 0; i < codeLength; i++)
    {
        //获取随机验证码下标
        var charNum = Math.floor(Math.random() * 62);
        //组合成指定字符验证码
        code += codeChars[charNum];
    }
    return code;
}

//验证验证码是否正确
	function checkCode_1(){
	//获取文本框输入的内容，随机生成的验证码
	var email_3=document.getElementById("email_3");
	var inputCode=document.getElementById("code").value;	
	var check_email=document.getElementById("check_email");
	//var checkCode=document.getElementById("checkCode").innerHTML;
	if(inputCode.length<=0){
		errorword.innerHTML="验证码不能为空";
		$("#check_email").show();//错误提示框显示
		return false;
	}else if(inputCode!=sixcode){
		errorword.innerHTML="验证码错误";
		$("#check_email").show();//错误提示框显示
		//reset();
	//	createCode(4);
		return false;
	}else{
		window.location="http://localhost:8080/LinearAlgebra2.0/client/forget_fls.jsp";
		
	}
	

}
	
	
	//重置
function reset(){
		//重置
		document.getElementById("username").value="";
		document.getElementById("password").value="";
		document.getElementById("inputCode").value="";
		
	}
//forgetPwd中
	//效验邮箱
function checkEmail(){
		var check_email=document.getElementById("check_email");
		var errorword=document.getElementById("errorword");
		var email=document.getElementById("email").value;
		var email_2=document.getElementById("email_2");
		var email_3=document.getElementById("email_3");
		//判断邮箱格式是否正确
		if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(email))){
			errorword.innerHTML="邮箱格式不正确";
			$("#check_email").show();//错误提示框显示
			 return false;
	}else{
		//利用ajax进行判断邮箱是否存在
		$.ajax({
			url:"http://localhost:8080/LinearAlgebra2.0/forgetPwd" ,	
			type:"post",
			data:"email="+email,
			success:function(result){
				if(result=="true"){
					//如果正确，则修改页面内容
					sendCode();//调用发送验证码
				//隐藏邮箱tr
					$("#email_2").hide();
					//将验证码款显示出来
					$("#email_3").show();
					$("#check_email").hide();//错误提示框显示
					errorword.innerHTML="验证码已发送至邮箱";

				}else if(result=="false"){
					errorword.innerHTML="用户不存在";//向div中插入内容
					
				}
				
				}
		});
		//不能用于判断
		// $(".errorword").load(
		//		"${pageContext.request.contextPath}/forgetPwd" ,
		//		"email="+email
		// );
		
	}
	}	
//申明一个全局变量生成一个6位数的验证码
var sixcode=createCode(6);
//发送邮箱验证码
function sendCode(){
	var email=document.getElementById("email").value;
	var check_email=document.getElementById("check_email");
	//随机生成6位验证码
		//利用ajax异步操作进行验证码效验
	alert("哈哈哈哈哈："+sixcode);
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/SendCode",
		type:"post",
		data:"code="+sixcode+"&eadress="+email+"&pore=forgetPwd",
		success:function(result){
			if(result=="true"){
				alert("验证码以发送，请注意查收");
				errorword.innerHTML="验证码已发送至邮箱";
				return true;
				
			}else{
				var errorword=document.getElementById("errorword");
				$("#check_email").hide();//错误提示框显示
				window.location.href="http://localhost:8080/LinearAlgebra2.0/client/forget.jsp";
				confirm("验证码发送错误，请稍后再试");
				return false;
			}
			
		}
	});
}
//forget_fls
	//密码效验
function setNewPwd(){
	var newPwd1=document.getElementById("forget_fls_pwd1").value;
	var newPwd2=document.getElementById("forget_fls_pwd2").value;
	if(newPwd1.length<=0||newPwd2.length<=0){
		alert("密码或新密码不能为空");
		return false;
	}else if(newPwd1!=newPwd2){
		alert("两次输入密码不一致");
	}else{
		$.ajax({
			url:"http://localhost:8080/LinearAlgebra2.0/ChangePwdServlet",
			type:"post",
			data:"newPwd="+newPwd2,
			success:function(result){
				if(result=="true"){
				alert("修改成功，正在跳转到登录界面");
				window.location.href="http://localhost:8080/LinearAlgebra2.0/client/login.jsp";
				}else{
					alert("修改失败，请重新修改");
					window.location.href="http://localhost:8080/LinearAlgebra2.0/client/forget.jsp";

				}
			}
		});
		
	}
	
}
//file分页
function get(){
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
	
