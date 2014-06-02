<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>send</title>
</head>
<body>
<form action="messageAction">
<div>${message }</div>
	客户端ID:<input type="text" name = "clientId"> 
	<br/>
	
	消&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;息:<textarea rows="5" cols="20" name="text"></textarea>
	<br/>
	<input type="submit" value="发送">
</form>
</body>
</html>