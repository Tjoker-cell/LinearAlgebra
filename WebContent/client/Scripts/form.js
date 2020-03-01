var emailObj;
var usernameObj;
var passwordObj;
var confirmObj;

var emailMsg;
var usernameMsg;
var passwordMsg;
var confirmMsg;
var regetSixCode=createCode(6);
var emailCode;
window.onload = function() {	// 页面加载之后, 获取页面中的对象
	emailObj = document.getElementById("email");
	usernameObj = document.getElementById("username");
	passwordObj = document.getElementById("password");
	confirmObj = document.getElementById("repassword");

	emailMsg = document.getElementById("emailMsg");
	usernameMsg = document.getElementById("usernameMsg");
	passwordMsg = document.getElementById("passwordMsg");
	confirmMsg = document.getElementById("confirmMsg");
	emailCode=document.getElementById("emailCode");
	$("#emailCode").hide();
};

function checkForm() {			// 验证整个表单
	var bEmail = checkEmail();
	var bUsername = checkUsername();
	var bPassword = checkPassword();
	var bConfirm = checkConfirm();
	if(emailCode.value==""){
		alert("请点击发送验证码按钮,发送验证码");
		return false;
	}else if(emailCode.value!=regetSixCode){
		alert("验证码错误,请重新输入");
		return false;
	}
	return bUsername && bPassword && bConfirm && bEmail ;	// return false后, 事件将被取消
}

function checkEmail() {			// 验证邮箱
	var regex = /^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/;
	var value =emailObj.value;
	var msg = "";
	if (!value)
		msg = "邮箱必须填写：";
	else if (!regex.test(value))
		msg = "邮箱格式不合法：";
	emailMsg.innerHTML = msg;
	emailObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";
	return msg == "";
}

function checkUsername() {		// 验证用户名
	var regex = /^[a-zA-Z_]\w{0,9}$/;	// 字母数字下划线1到10位, 不能是数字开头
	var value = usernameObj.value;// 获取usernameObj中的文本
	var msg = "";						// 最后的提示消息, 默认为空
	if (!value)							// 如果用户名没填, 填了就是一个字符串可以当作true, 没填的话不论null或者""都是false
		msg = "用户名必须填写：";			// 改变提示消息
	else if (!regex.test(value))		// 如果用户名不能匹配正则表达式规则
		msg = "用户名不合法：";			// 改变提示消息
	usernameMsg.innerHTML = msg;		// 将提示消息放入SPAN
	usernameObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";	// 根据消息结果改变tr的颜色
	return msg == "";					// 如果提示消息为空则代表没出错, 返回true
}

function checkPassword() {		// 验证密码
	var regex = /^.{6,16}$/;			// 任意字符, 6到16位
	var value = passwordObj.value;
	var msg = "";
	if (!value)
		msg = "密码必须填写：";
	else if (!regex.test(value))
		msg = "密码不合法：";
	passwordMsg.innerHTML = msg;
	passwordObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";
	return msg == "";
}

function checkConfirm() {		// 验证确认密码
	var passwordValue = passwordObj.value;
	var confirmValue = confirmObj.value;
	var msg = "";

    if(!confirmValue){
	    msg = "确认密码必须填写";
	}
	else	if (passwordValue != confirmValue){
		msg = "密码必须保持一致";
    }
	confirmMsg.innerHTML = msg;
	confirmObj.parentNode.parentNode.style.color = msg == "" ? "black" : "red";
	return msg == "";
}
//发送验证码

function sendCode(){
	if(checkEmail()==false){
		return false;
	}
	alert(emailObj.value);
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/SendCode",
		type:"post",
		data:"code="+regetSixCode+"&eadress="+emailObj.value+"&pore=reg",
		success:function(result){
			if(result=="true"){
				$("#submit").hide();
				alert("验证码以发送，请注意查收");
				
			$("#emailCode").show();
				return true;
				
			}else if(result=="false"){
				confirm("验证码发送错误，请稍后再试");
				window.location.href="http://localhost:8080/LinearAlgebra2.0/client/reg.jsp";
				
				return false;
			}else if(result==0){
				confirm("用户已存在，正在跳转等录界面");
				window.location.href="http://localhost:8080/LinearAlgebra2.0/client/login.jsp";
				
				
			}
			
		}
	});
}
//随机生成验证码
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

