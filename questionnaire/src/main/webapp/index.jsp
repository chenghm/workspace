<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>主页</title>

<style>
body {
	background: #2E5E79;
}
</style>
</head>
<frameset rows="70,*,36" frameborder="no" border="0" framespacing="0">
	<frame src="header.jsp" scrolling="no" noresize>
	<frameset cols="214,*" frameborder="no" border="0" framespacing="0">
		<frame src="menu.jsp" name="menu" scrolling="auto" noresize>
		<frame src="main.jsp" name="main" scrolling="auto" noresize>
	</frameset>
	<frame src="footer.html">
	<noframes>
		<body>
			<p>This page uses frames. The current browser you are using does
				not support frames.</p>
		</body>
	</noframes>
</frameset>
</html>