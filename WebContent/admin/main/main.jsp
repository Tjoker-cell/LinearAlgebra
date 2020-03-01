<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/base.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/admin/css/page.css">

<title>管理员后台</title>
</head>

<frameset >
<frameset rows="18,*,8" cols="*" frameborder="no" border="0" framespacing="0">
      <frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="../file.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
  <frame src="footer.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes><body>
</body></noframes>
</html>
