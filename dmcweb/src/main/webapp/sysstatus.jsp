<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.net.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统状态</title>
<link rel="stylesheet" href="css/redmond/jquery-ui-1.10.3.custom.css">
<script src="js/jquery-1.9.0.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
	});
</script>
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">系统状态</a></li>
		</ul>
		<div id="tabs-1">

			<fieldset>
				<legend>系统状态</legend>
				<table>
					<tr>
						<td align="right">持续运行时间：</td>
						<td>
							<div id="duration">${duration }</div>
						</td>
					</tr>
					<tr>
						<td align="right">系统日期：</td>
						<td><div id="time">${time }</div>
					</tr>
				</table>

			</fieldset>

			<fieldset>
				<legend>系统资源</legend>
				<table>
					<tr>
						<td align="right">CPU占用率：</td>
						<td>
							<div id="cpu">${cpu }</div>
						</td>
					</tr>
					<tr>
						<td align="right">内存占用率：</td>
						<td><div id="memory">${memory }</div>
					</tr>
					<tr>
						<td align="right">硬盘使用情况：</td>
						<td><div id="harddisk">${harddisk }</div>
					</tr>
				</table>

			</fieldset>
		</div>
	</div>
</body>
</html>