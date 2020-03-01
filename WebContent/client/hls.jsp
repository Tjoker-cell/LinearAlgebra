<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<h1>2. 行列式</h1>

<h2>2.1 定义</h2>

<p>      矩阵的行列式，determinate（简称det），是基于矩阵所包含的行列数据计算得到的一个标量。是<span style="color:#3366ff;">为求解线性方程组而引入的</span>。</p>

<h2>2.2 二阶行列式</h2>

<p>      计算方式：对角线法则</p>

<p>      <img alt="" class="has" src="https://img-blog.csdn.net/20161127170020443" /></p>

<h2>2.3 三阶行列式</h2>

<p>      计算方式：对角线法则</p>

<p>      <img alt="" class="has" src="https://img-blog.csdn.net/20161127170302327" /></p>

<h2>2.4 n阶行列式</h2>

<h3>2.4.1 计算排列的逆序数</h3>

<p>      <img alt="" class="has" src="https://img-blog.csdn.net/20161127171146792" />   </p>

<h3>2.4.2 计算n阶行列式</h3>

<p>      <img alt="" class="has" src="https://img-blog.csdn.net/20161127171514391" /></p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127171748989?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center" /></p>

<h3>2.4.3 简化计算总结</h3>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127172649625" /></p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127172811940" /></p>

<h3>2.4.4 行列式的3种表示方法</h3>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127173259196" /></p>

<h2>2.5 行列式的性质</h2>

<p><span style="color:#3366ff;">性质1</span>  行列式与它的转置行列式相等<br />
       注：行列式中行与列具有同等的地位,行列式的性质凡是对行成立的对列也同样成立.<br /><br /><span style="color:#3366ff;">性质2</span>  互换行列式的两行（列）,行列式变号<br /><span style="color:#3366ff;">推论</span>  如果行列式有两行（列）完全相同，则此行列式为零<br /><br /><span style="color:#3366ff;">性质3</span>  行列式的某一行（列）中所有的元素都乘以同一个倍数k，等于用数k乘以此行列式.<br /><span style="color:#3366ff;">推论</span>    行列式的某一行（列）中所有元素的公因子可以提到行列式符号的外面．<br /><br /><span style="color:#3366ff;">性质4</span>  行列式中如果有两行（列）元素成比例，则此行列式为零．<br /><br /><span style="color:#3366ff;">性质5</span>  若行列式的某一列（行）的元素都是两数之和,则等于对应的两个行列式之和.</p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127174444320" /></p>

<p><span style="color:#3366ff;">性质6</span>  把行列式的某一列（行）的各元素乘以同一个倍数然后加到另一列(行)对应的元素上去，行列式不变．</p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127174726652" /></p>

<h2>2.6 计算行列式的方法</h2>

<p>     1）利用定义<br />
     2）利用性质把行列式化为上三角形行列式，从而算得行列式的值</p>

<h2> </h2>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127190136630" /></p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127190250006" /></p>

<p>    定理中包含着三个结论：<br />
       1）方程组有解；（解的存在性） <br />
       2）解是唯一的；（解的唯一性）<br />
       3）解可以由公式(2)给出.</p>

<p><span style="color:#3366ff;">定理4</span>   如果线性方程组(1)的系数行列式不等于零，则该线性方程组一定有解,而且解是唯一的 .<br /><span style="color:#3366ff;">定理4′</span> 如果线性方程组无解或有两个不同的解，则它的系数行列式必为零.</p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161127214447336" /></p>

<p><span style="color:#3366ff;">齐次线性方程组的相关定理</span><br /><span style="color:#3366ff;">定理5</span>   如果齐次线性方程组的系数行列式D不等于0，则齐次线性方程组只有零解，没有非零解.<br /><span style="color:#3366ff;">定理5′</span> 如果齐次线性方程组有非零解,则它的系数行列式必为零.   </p>

<p>1. 用克拉默法则解线性方程组的两个条件<br />
    1) 方程个数等于未知量个数；<br />
    2) 系数行列式不等于零.<br /><br />
2. 克拉默法则的意义主要在于建立了线性方程组的解和已知的系数以及常数项之间的关系．它主要适用于理论推导．</p>

<h2>2.8 行列式按行(列)展开</h2>

<p>      对角线法则只适用于二阶与三阶行列式.<br />
      本节主要考虑如何用低阶行列式来表示高阶行列式.</p>

<p> </p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161129100715500" /></p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161129100827689" /></p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161129100931268" /></p>

<p><img alt="" class="has" src="https://img-blog.csdn.net/20161129101116503" /></p>

<p> </p>

</body>
</html>