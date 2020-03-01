package Tjoker.client.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Tjoker.pojo.Log;
import Tjoker.pojo.Matrix;
import Tjoker.pojo.User;
import Tjoker.service.LogService;
import javafx.scene.shape.Circle;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.tools.jar.resources.jar;


public class JisuanServlet extends HttpServlet {
	//获取matrix
	Matrix j=new Matrix();
	Matrix j1=new Matrix();
	Matrix j2=new Matrix();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求数据
		//申明日志对象实体类对象
		   Log l=new Log();
		   	//service层对象
		   LogService ls=new LogService();
		String name=req.getParameter("name");
		System.out.println(name);
		 User u= (User)(req.getSession().getAttribute("user"));
		 l.setUser_id(u.getId());
		 l.setUser_name(u.getUsername());
		 l.setUser_role(u.getRole());
	
		if("nijuzheng".equals(name)) {
			String[] data=req.getParameterValues("list");
			int cols=Integer.parseInt(req.getParameter("cols"));
			int rows=Integer.parseInt(req.getParameter("rows"));
			String[][] matrix=changeArray(data,rows,cols);////将一维数组转为二维数组
			//计算矩阵的值
			int deter=calDeterminant(matrix,rows,cols);
			System.out.println("矩阵的值为="+deter);
			PrintWriter out = null;
			if(deter==0) {
				resp.sendRedirect("client/jisuan/nijuzheng.jsp");
				JOptionPane.showConfirmDialog(null, "矩阵不可逆,其值为0");
				 l.setUser_state("失败");
		 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】求逆矩阵失败：矩阵不可逆");
		 			int index1=ls.addLog(l);
		 			if(index1>0) {
		 				System.out.println("jisuan日志写入成功");
		 			}
		 			return;
			}
			String[][] a=calnimatrix(matrix, cols, rows);
			//打印结果
			ergo(a, rows, cols);
			 l.setLog_action("求逆矩阵");
			 l.setUser_state("成功");
	 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】求逆矩阵成功");
			//输出
			printMatrix(j, resp);
		
		}else if("hangliesi".equals(name)) {
			String[] data=req.getParameterValues("list");
			int cols=Integer.parseInt(req.getParameter("cols"));
			int rows=Integer.parseInt(req.getParameter("rows"));
			String[][] matrix=changeArray(data,rows,cols);////将一维数组转为二维数组
			//行列式计算
			//利用性质3：行列式等于它的任意列（或行）各个元素与其对应代数余子式乘积的和。
			int deter=calDeterminant(matrix,rows,cols);
			System.out.println(deter);
			//求矩阵的特征值
			String[] rank=rank(matrix,rows,cols);
			PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 l.setLog_action("求行列式的值");
			 l.setUser_state("成功");
	 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】进行求行列式的值成功");
			out.print(deter);
			out.flush();//强制刷出缓冲池的数据
			out.close();
			
			
		}else if("shucheng".equals(name)){
			//获取请求数据
				//将data解析为json对象
			JSONObject data=JSONObject.fromObject(req.getParameter("data").toString());
			String[] dataA=req.getParameterValues("listA");
			String[] dataB=req.getParameterValues("listB");
			//将矩阵转为二维矩阵
			String[][] matrixA= changeArray(dataA,data.getInt("rowsA"),data.getInt("colsA"));
			String[][] matrixB= changeArray(dataB,data.getInt("rowsB"),data.getInt("colsB"));
		//进行矩阵的数乘
			String [][] arr=matrixSC(matrixA,matrixB,data);
			ergo(arr, j.getRow(), j.getCol());
			//通过流输出
			PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Matrix> list=new ArrayList<Matrix>();//通过列表的方式进行输出
			list.add(j);
			out.print(new Gson().toJson(list));
			 l.setLog_action("矩阵数乘计算");
			 l.setUser_state("成功");
	 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】进行矩阵乘法");
			out.flush();//强制刷出缓冲池的数据
			out.close();
			
		}else if("jiajiang".equals(name)) {
			//矩阵行列
			JSONObject data=JSONObject.fromObject(req.getParameter("data").toString());
			String[] dataA=req.getParameterValues("listA");//矩阵A
			String[] dataB=req.getParameterValues("listB");//矩阵B
			//将矩阵转为二维矩阵
			String[][] matrixA= changeArray(dataA,data.getInt("rowsA"),data.getInt("colsA"));
			String[][] matrixB= changeArray(dataB,data.getInt("rowsB"),data.getInt("colsB"));
			String[][] arr=	matrixJJ(matrixA,matrixB,data);//进行矩阵的加乘
			
			PrintWriter out = null;
			try {
				out = resp.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Matrix> list=new ArrayList<Matrix>();//通过列表的方式进行输出
			list.add(j);
			list.add(j2);
//			out.print(new Gson().toJson(j));
			out.print(new Gson().toJson(list));
			 l.setLog_action("矩阵加减计算");
			 l.setUser_state("成功");
 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】进行矩阵加减");
			out.flush();//强制刷出缓冲池的数据
			out.close();
			
			
		}else {
			String[] data=req.getParameterValues("list");
			int cols=Integer.parseInt(req.getParameter("cols"));
			int rows=Integer.parseInt(req.getParameter("rows"));
			String[][] arr=	changeArray(data,rows,cols);////将一维数组转为二维数组
			matrixPractise(arr,rows,cols);//进行矩阵转置
			ergo(arr,rows,cols);//遍历数组
			 l.setLog_action("矩阵转置");
			 l.setUser_state("成功");
 			l.setLog_content("【"+u.getRole()+u.getUsername()+"】进行矩阵转置成功");
			printMatrix(j,resp);//将数组写进json中传回到ajax
		}
		//将log写进数据库中去
		int index1=ls.addLog(l);
		if(index1>0) {
			System.out.println("jisuan日志写入成功");
		}
		
		

	    	
		}
//矩阵的特征值计算
private String[] rank(String[][] matrix, int rows, int cols) {
	String[][] data=new String[cols][rows];
	int n;
	for(int i=0;i<cols;i++) {
		for(int t=0;t<cols;t++) {
			if(i==t) {
//				data[i][t]=String.valueOf(n-(Integer.parseInt(matrix[i][t])*(-1)));
			}
			else{
			data[i][t]=String.valueOf(Integer.parseInt(matrix[i][t])*(-1));
			}
		}
	}
		return null;
	}

//逆矩阵计算
private String[][] calnimatrix(String[][] matrix, int cols, int rows) {
	//申明新数组存储逆矩阵
	String[][] dat=new String[cols][cols];
	String[][] data1=new String[cols][cols];
	int[][] data=new int[cols][cols];
	
	//求（h,l）位置的余子式
	int l=0;
	for(int h=0;h<cols;h++) {
		for(l=0;l<rows;l++) {
		data[h][l]=(int) (calDeterminant(getConfactor(matrix, h, l), cols-1, rows-1)*Math.pow(-1, h+l));
		data1[h][l]=String.valueOf(data[h][l]);
			
		}
		
	
	}
	//将data进行转置得到伴随矩阵
	
	int deter=calDeterminant(matrix,rows,cols);
	for(int i=0;i<cols;i++) {
		for(int t=0;t<cols;t++) {
			//m是除数（分母），n是被除数
			String num=getDiv((data[i][t]), deter);
			data1[i][t]=num;
		}
	}
	//将结果进行转置
	dat=matrixPractise(data1, rows, cols);
	j.setData(dat);
	j.setCol(cols);
	j.setRow(rows);
		return dat;
	}
//m是除数（分母），n是被除数
private static String getDiv(int n, int m) {
	// TODO Auto-generated method stub
	
	if(m == 0)
		return "除数不能为 0 ";
	
	if(n == 0)
		return "0";
	
	//进行约数化简
	int p = getMax(n,m);
	n = n/p;
	m = m/p;
	
	return Integer.toString(n) + "/" + Integer.toString(m);
	
}


//辗转相除法求最大公约数
private static int getMax(int n, int m) {
	// TODO Auto-generated method stub
	
	if(n < m){
		int tp = n;
		n = m;
		m = tp;
	}
	
	int p = n%m;
	while(p != 0){
		n = m;
		m = p;
		p = n%m;
	}
	
	return m;
}



//行列式求值
private int calDeterminant(String[][] data, int rows, int cols) {
		//将0行各个元素与其对应代数余子式乘积的和
	if(rows==1||cols==1) {
		return Integer.parseInt(data[0][0]);
	}
	
	int sum=0;// 累加求和变量
	for(int j=0;j<cols;j++) {//遍历最后一行元素p[cols-1][j];
		int pt=(cols-1)+j;//判断符号
			
		String[][] p1 = new String[cols][cols];
		
		// 此过程是为了把行列式存放到临时数组中，不改变但前行列式
		for (int row = 0; row < cols; row++) {
			for (int col = 0; col < cols; col++) {
				p1[row][col] = data[row][col];  
			}
		}

		// 此过程，是为了将元素 p[n-1][j] 所在列之后的每一列向前移动一列，为后面获取该元素对应的余子式做准备
		for(int i=0;i<cols-1;i++) {
			for(int t=j;t<cols-1;t++) {
				p1[i][t]=data[i][t+1];
			}
			
		}
		
		// 此过程，截取临时数组 p1 左上角 n-1  阶行列式，提取元素 p[n-1][j] 的余子式
		String [][] temp=new String[cols-1][cols-1];
			for(int h=0;h<cols-1;h++) {
				for(int l=0;l<cols-1;l++) {
					temp[h][l]=p1[h][l];
				}
			}
			// 求和：sum += 元素 * 符号 * 余子式
			// 因为，余子式是去除某一元素所在的行和列之后剩下的元素构成的 n-1 阶行列式
			// 即，余子式本质还是行列式，所以需要递归求行列式的值
		sum+=Integer.parseInt(data[cols-1][j])*Math.pow(-1, pt)*calDeterminant(temp, rows-1, cols-1);
	}
	
	
		return sum;
	}


//矩阵与矩阵之间的相乘
private String[][] matrixSC(String[][] matrixA, String[][] matrixB, JSONObject data) {
	//申明新矩阵
	String[][] ndata=new String[data.getInt("rowsA")][data.getInt("colsB")];
	for(int h=0;data.getInt("rowsA")>h;h++){
		for(int l=0;data.getInt("colsB")>l;l++) {
			int num =0;
		for(int t=0;t<data.getInt("colsA");t++) {
		num+=(Integer.parseInt(matrixA[h][t])*(Integer.parseInt(matrixB[t][l])));
		}	
		ndata[h][l]=String.valueOf(num);
		}
		
	}
	j.setCol(data.getInt("colsB"));
	j.setRow(data.getInt("rowsA"));
	j.setData(ndata);
	return ndata;
	}
//矩阵的加减乘
private String[][] matrixJJ(String[][] matrixA, String[][] matrixB, JSONObject data) {
		// TODO Auto-generated method stub
//矩阵相加
	String[][] n3data=new String[data.getInt("rowsA")][data.getInt("colsB")];
	String[][] n2data=new String[data.getInt("rowsA")][data.getInt("colsA")];
	String[][] ndata=new String[data.getInt("rowsA")][data.getInt("colsA")];
	for(int h=0;h<data.getInt("rowsA");h++) {
		for(int l=0;l<data.getInt("colsA");l++) {
			ndata[h][l]=String.valueOf((Integer.parseInt(matrixA[h][l])+Integer.parseInt(matrixB[h][l])));
		}// 强转
	
	}
	 
	j.setCol(data.getInt("colsA"));
	j.setRow(data.getInt("rowsA"));
	j.setData(ndata);
//矩阵相减
	for(int h=0;h<data.getInt("rowsA");h++) {
		for(int l=0;l<data.getInt("colsA");l++) {
			n2data[h][l]=String.valueOf((Integer.parseInt(matrixA[h][l])-Integer.parseInt(matrixB[h][l])));
		}
	}
	j2.setCol(data.getInt("colsA"));
	j2.setRow(data.getInt("rowsA"));
	j2.setData(n2data);
//矩阵相乘
	for(int h=0;h<data.getInt("rowsA");h++) {
		for(int l=0;l<data.getInt("colsA");l++) {
			n3data[h][l]=String.valueOf((Integer.parseInt(matrixA[h][l])-Integer.parseInt(matrixB[h][l])));
		}
	}
	
	
	
	return ndata;
	
	}
//将装置矩阵通过流输出	
private void printMatrix(Matrix j, HttpServletResponse resp) {
		// TODO Auto-generated method stub
	PrintWriter out = null;
	try {
		out = resp.getWriter();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	out.print(new Gson().toJson(j));
	out.flush();//强制刷出缓冲池的数据
	out.close();
	}

//遍历矩阵查看结果	
	private void ergo(String[][] arr, int rows, int cols) {
		// TODO Auto-generated method stub
		//遍历矩阵查看结果
		for(int t=0;t<rows;t++) {
			for(int n=0;n<cols;n++) {
				System.out.print(" "+arr[t][n]);
			}
			System.out.println("");
		}
	}


//将一维数组转为二维数组
private String[][] changeArray(String[] data, int rows, int cols) {
		// TODO Auto-generated method stub
	//将一维数组转为二维数组
			String[][] arr=new String[rows][cols];
			String[] a=new String[cols*rows];
			for(int h=0;h<rows;h++) {
			a=data[h].split(",");
			arr[h]=a;
			
			}
			return arr;
	}



//进行矩阵转置
	private String[][] matrixPractise(String[][] data,int rows,int cols) {
		// TODO Auto-generated method stub
		//将转置矩阵的行列写进  matrix中
		j.setCol(rows);
		j.setRow(cols);
		//创建新的data
		String[][] ndata=new String[cols][rows];
		for(int h=0;h<rows;h++) {
			for(int l=0;l<cols;l++) {
				ndata[l][h]=data[h][l];
			}
		}
		//将其写进matrix中
		j.setData(ndata);
		//通过流将其写入json中
		return ndata;
		
		
	}
	
//求（h,l）坐标位置的余子式坐标从0开始
private String[][] getConfactor(String[][] matrix, int h, int l) {
		int rows=matrix.length-1;
		int cols=matrix[0].length-1;
		int i=0;
		int j=0;
	String[][] data=new String[rows][cols];
		for(i=0;i<rows;i++) {
			if(i<h) {
				for(j=0;j<cols;j++) {
					if(j<l) {
				data[i][j]=matrix[i][j];
					}else {
						data[i][j]=matrix[i][j+1];
					}
				}
			}else {
				for(j=0;j<cols;j++) {
					if(j<l) {
						data[i][j]=matrix[i+1][j];
					}else {
						data[i][j]=matrix[i+1][j+1];
					}
				}
			}
		}
		return data;
	}
}


/*1.json，可以分开传write多个值（对象），其返回形式{{}，{}} 用json[];0 

 * 
 * 
 * 
 * 
 * 
 * 
 * */
