<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>逆矩阵</title>

</head>

<body style="background: #f6f5fa;">
	<div class="super-content">
		<div class="superCtab">
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li class="cur">求逆矩阵</li>
					</ul>
				</div>
				<div class="wd-msg" style="color:red" >注意事项</div><HR  />
				<span>一行元素用空格分开，换行代表下一行</span>
			</div>
		</div>
		</div>
		<div style="font-size: 18px;" >
		<span style="font-size: 15px;color: red">阶</span>
		<br><br>
		矩阵：<INPUT type="text" style="width: 35px;"id="cols" placeholder="0" onchange="create()">&nbsp;行列</INPUT>
		</div><br>
		<div id="haha">
		<table  id="tf">
		
                      
          </table>
		
		<input type="button" value="提交"  onclick="getMess()" style=" width: 200px;height: 50px;color:red;font-size: 18px"></input>
		</div>
		<div style="position: fixed;left: 680px ;top:280px">
				<span style="position: fixed;left: 650px;top:230px;font-size: 18px;">结果为：</span>
		
		 <table class=" fz">

                        <tr>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                        </tr>

                        <tr>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                        </tr>

                        <tr>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                            <td><input  style="max-width: 60px;" type="number" value="0" /></td>

                        </tr>

                    </table>
		
		
		</div>
		
		
<SCRIPT type="text/javascript">
function create(){
	var cols=document.getElementById("cols").value;
	var rows=cols;
	createTable(rows,cols,"left");
}


	function createTable(rows,cols,location){
		var temp;
	if(rows==0||cols==0){
		alert("行或列不能为0");
		return ;
	}
	var col=cols;
	for(rows;rows>0;rows--){
		
		temp+='<tr>';
		for(cols;cols>0;cols--){
			temp+='<td>'+'<input  style="max-width: 60px;" type="number"  value="0"/>'+'</td>';
			};
		cols=col;
		temp+='</tr>';
		
	}
	if(location=="left"){
	$("#tf tr").remove();
	$("#tf").append(temp);
	}else{
		$(".fz tr").remove();
		$(".fz").append(temp);
	}
	}
function getMess(){
		//申明变量
		var value;
		var cols=parseInt(document.getElementById("cols").value);//将var cols对象强制转为int，否则js做+时是连接符并不是加法
		var rows=cols;
	
		var i=0;
		//申明一个数组存储所有数据
		var data=new Array();
		$("#tf input").each(//遍历数据
				function(){
					value=$(this).val();
					data.push(value);
					
				});
		var list=[];
		//将一维数组按行列顺序排列形成二位数组
	  	for(i;i<data.length;i=(i+cols)){
		  		list.push(data.slice(i,(i+cols)));
		  		
		  	}
		
		
		
	//利用ajax将其数据发送给servlet进行处理
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/JisuanServlet",
		type:"post",
		data:{list:list,cols:cols,rows:rows,name:"nijuzheng"},
		traditional:true, //默认false,空指针
		success:function(data){
			var value=data;
			eval("value="+data);
			var rows=0;
				var temp;
			var cols=0;
			for(rows;rows<value.row;rows++){
				temp+='<tr>';
				for(cols;cols<value.col;cols++){
					temp+='<td>'+'<input  style="max-width: 60px;" type="number"  value="'+value.data[rows][cols]+'"/>'+'</td>';
					};
				cols=0;
				temp+='</tr>';
				
			}
				$(".fz tr").remove();
				$(".fz").append(temp);
		
			}
		
	

	});
	}
	
</SCRIPT>	
	<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.9.1.min.js"></script> 
		
</body>
</html>