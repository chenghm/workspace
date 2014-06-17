<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/redmond/jquery-ui-1.10.3.custom.css" />
<script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>

<style type="text/css">
html,body {
	font-size: 11px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		
	});
	
</script>
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">个人资料</a></li>
			<div align="right">
				<a href="useraction_initModifyProfile">修改个人资料</a>
			</div>
		</ul>
		<div id="tabs-1">
			<form id="profile-form">
				<table>
					<tr>
						<th align="right">用户名：</th>
						<td>${session.SPRING_SECURITY_CONTEXT.authentication.principal.username}</td>
					</tr>
					<tr>
						<th align="right">中文名：</th>
						<td>${session.SPRING_SECURITY_CONTEXT.authentication.principal.chineseName}</td>
					</tr>

					<tr>
						<th align="right">邮箱：</th>
						<td>${session.SPRING_SECURITY_CONTEXT.authentication.principal.email}</td>
					</tr>
					<tr>
						<th align="right">电话：</th>
						<td>${session.SPRING_SECURITY_CONTEXT.authentication.principal.phone}</td>
					</tr>
					<tr>
						<th align="right">备注：</th>
						<td>${session.SPRING_SECURITY_CONTEXT.authentication.principal.descn}</td>
					</tr>
				</table>
			</form>

		</div>
	</div>
</body>
</html>