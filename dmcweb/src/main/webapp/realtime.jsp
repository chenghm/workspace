<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.net.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>实时统计</title>
<link rel="stylesheet" href="css/redmond/jquery-ui-1.10.3.custom.css">
<script src="js/jquery-1.9.0.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js"></script>
<style>
/* body {
	background: #2E5E79;
	padding-left: 14px;
} */
</style>
</head>
<body>
	<div id="tabs">
		<div id="tabs-1">
			&nbsp;
			<fieldset>
				<legend>实时统计</legend>
				<table width="80%">
					<tr>
						<td align="right">网页浏览：</td>
						<td><a href="http.html">${httpCount }</a></td>
						<td align="right">搜索引擎：</td>
						<td><a href="search.html">${searchCount }</a></td>
						<td align="right">破网行为：</td>
						<td><a href="through.html">${throughCount }</a></td>
					</tr>
					<tr>
						<td align="right">电子商务：</td>
						<td><a href="commerce.html">${commerceCount }</a></td>
						<td align="right">社交网络：</td>
						<td><a href="social.html">${socialCount }</a></td>
						<td align="right">口令信息：</td>
						<td><a href="password.html">${passwordCount }</a></td>
					</tr>
					<tr>
						<td align="right">FTP：</td>
						<td><a href="ftp.html">${ftpCount }</a></td>
						<td align="right">网盘：</td>
						<td><a href="netdisk.html">${netdiskCount }</a></td>
						<td align="right">远程控制：</td>
						<td><a href="control.html">${controlCount }</a></td>
					</tr>
					<tr>
						<td align="right">MSN：</td>
						<td><a href="msn.html">${msnCount }</a></td>
						<td align="right">QQ：</td>
						<td><a href="qq.html">${qqCount }</a></td>
						<td align="right">飞信：</td>
						<td><a href="fetion.html">${fetionCount }</a></td>
					</tr>
					<tr>
						<td align="right">微信：</td>
						<td><a href="wechat.html">${wechatCount }</a></td>
						<td align="right">Skype：</td>
						<td><a href="skype.html">${skypeCount }</a></td>
						<td align="right">Pop3：</td>
						<td><a href="pop3.html">${pop3Count }</a></td>
					</tr>
					<tr>
						<td align="right">SMTP：</td>
						<td><a href="smtp.html">${smtpCount }</a></td>
						<td align="right">WEB收：</td>
						<td><a href="webreceiving.html">${webreceiveCount }</a></td>
						<td align="right">WEB发：</td>
						<td><a href="websending.html">${websendCount }</a></td>
					</tr>
					<tr>
						<td align="right">存草稿：</td>
						<td><a href="draftsaving.html">${savedraftCount }</a></td>
						<td align="right">收草稿：</td>
						<td><a href="draftreceiving.html">${receivedraftCount }</a></td>
						<td align="right">邮件渗透：</td>
						<td><a href="penetration.html">${penetrationCount }</a></td>
					</tr>




				</table>
				</form>

			</fieldset>
		</div>
	</div>
</body>
</html>