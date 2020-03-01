<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:f="http://java.sun.com/jsf/core" xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>资料上传</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/base.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/page.css">
	<!--[if lte IE 8]>
	<link href="css/ie8.css" rel="stylesheet" type="text/css"/>
	<!--[if IE]>
	<script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
	<![endif]-->

<body style="background: rgb(246, 245, 250);">
	<!--content S-->
	<div class="super-content">
		
		<div class="superCtab">
			<div class="publishArt">
				<h4>资料上传</h4>
				<div class="pubMain">
					<a href="javascript:history.go(-1)" class="backlistBtn"><i class="ico-back"></i>返回列表</a>
					<form action="${pageContext.request.contextPath}/UploadServlet" method="post" enctype="multipart/form-data">
						<h5 class="pubtitle">上传资料名</h5>
						<input type="file" name="filename"></input>
						<h5 class="pubtitle">资料简介</h5>
						<div class="pub-area-bar">
							<textarea name="description" rows="" cols="3"></textarea>
							
						</div>
						<div class="pub-btn">
							<input type="submit" id="" value="上传" class="saveBtn">
						</div>
					</form>
				</div>
			</div>
		
		</div>
		<!--main-->
		
	</div>


</body></html>
	