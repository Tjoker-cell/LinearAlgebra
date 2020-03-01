<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>矩阵数乘</title>

</head>

<body style="background: #f6f5fa;">
	<div class="super-content">
		<div class="superCtab">
			<div class="ctab-Main">
				<div class="ctab-Main-title">
					<ul class="clearfix">
						<li class="cur">矩阵数乘</li>
					</ul>
				</div>
				<div class="wd-msg" style="color:red" >注意事项</div><HR  />
				<span>一行元素用空格分开，换行代表下一行</span>
			</div>
		</div>
		</div>
		<div style="font-size: 18px;" >
		<span style="font-size: 15px;color: red">请输入矩阵的行和列</span>
		<br><br>
		矩阵A：<INPUT type="text" style="width: 35px;" id="rowsA" placeholder="0" >&nbsp;行</INPUT>
		<INPUT type="text" style="width: 35px;"id="colsA" placeholder="0" onchange="create('tf')">&nbsp;列</INPUT>
		</div><br>
		<div id="haha" >
		<table  id="tf"> </table>
          <div style="font-size: 18px;" >
		矩阵B：<INPUT type="text" style="width: 35px;" id="rowsB" placeholder="0" >&nbsp;行</INPUT>
		<INPUT type="text" style="width: 35px;"id="colsB" placeholder="0" onchange="create('tg')">&nbsp;列</INPUT>
		</div><br>
		<table id="tg"></table>
		<input type="button" value="计算"  onclick="getMess()" style=" width: 200px;height: 50px;color:red;font-size: 18px"></input>
		</div>
		
		<div style="position: fixed;left: 680px ;top:280px">
				<span style="position: fixed;left: 650px;top:230px;font-size: 18px;">矩阵相乘结果为：</span>
		
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
function create(id){
	var rowsA=document.getElementById("rowsA").value;
	var colsA=document.getElementById("colsA").value;
	var rowsB=document.getElementById("rowsB").value;
	var colsB=document.getElementById("colsB").value;
	createTable(rowsA,colsA,id);
	createTable(rowsB,colsB,id);
}


	function createTable(rows,cols,id){
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
		$("#"+id+" tr").remove();
		$("#"+id+"").append(temp);
		return;
	
	}
function getMess(){
		//申明变量
		var value;
		var colsA=parseInt(document.getElementById("colsA").value);//将var cols对象强制转为int，否则js做+时是连接符并不是加法
		var rowsA=parseInt(document.getElementById("rowsA").value);
		var colsB=parseInt(document.getElementById("colsB").value);//将var cols对象强制转为int，否则js做+时是连接符并不是加法
		var rowsB=parseInt(document.getElementById("rowsB").value);//将var cols对象强制转为int，否则js做+时是连接符并不是加法
		if(colsA!=rowsB){
			alert("此两矩阵不能相乘,请是矩阵A的列等于矩阵B的行");
			return;
		}
		
		//申明一个数组存储所有数据
		var dataA=new Array();
		var dataB=new Array();
		var listA=[];
		var listB=[];
		$("#tf input").each(//遍历数据矩阵A
				function(){
					value=$(this).val();
					dataA.push(value);
					
				});
		$("#tg input").each(//遍历数据矩阵B
				function(){
					value=$(this).val();
					dataB.push(value);
					
				});
		
		//将一维数组按行列顺序排列形成二位数组
	  	for(var i=0;i<dataA.length;i=(i+colsA)){
		  		listA.push(dataA.slice(i,(i+colsA)));
		  		
		  	}
	  	for(var i=0;i<dataB.length;i=(i+colsB)){
	  		listB.push(dataB.slice(i,(i+colsB)));
	  	}
	  
	  	//将数据封装成一个json
		var Data={
				"colsA":colsA,"rowsA":rowsA,"colsB":colsB,"rowsB":rowsB
		};
		
	//利用ajax将其数据发送给servlet进行处理
	$.ajax({
		url:"http://localhost:8080/LinearAlgebra2.0/JisuanServlet",
		type:"post",
		data:{data:JSON.stringify(Data),listA:listA,listB:listB,name:"shucheng"},
		traditional:true, //默认false,空指针 JSON.stringify(Data);将其封装成json对象
		success:function(data){
			alert(data);
			eval("value="+data);
			alert(value[0].col);
				var temp;
			for(var rows=0;rows<value[0].row;rows++){
				temp+='<tr>';
				for(var cols=0;cols<value[0].col;cols++){
					temp+='<td>'+'<input  style="max-width: 60px;" type="number"  value="'+value[0].data[rows][cols]+'"/>'+'</td>';
				}
				cols=0;
				temp+='</tr>';
				alert("hahah");
			}
		//矩阵相乘
				$(".fz tr").remove();
				$(".fz").append(temp);
			
			}
		
	

	});
	}
	
</SCRIPT>	
	<script src="${pageContext.request.contextPath }/client/Scripts/jquery-1.9.1.min.js"></script> 
		
</body>
</html>