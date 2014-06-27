<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.net.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统控制</title>
<link rel="stylesheet" href="css/redmond/jquery-ui-1.10.3.custom.css">
<script src="js/jquery-1.9.0.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript">
	function restart(){
		 $('#form1').action="syscontrolaction_restart";
		 $('#form1').submit();
	}
	
	function stop(){
		
		$('#form1').action="syscontrolaction_stop";
		$('#form1').submit();
		
	}
	
	
</script>
</head>
<body>
	<form id="form1">
		<input type="button" value="系统重启" onclick="restart()" /> <input
			type="button" value="系统关闭" onclick="stop()">

	</form>
</body>
</html>