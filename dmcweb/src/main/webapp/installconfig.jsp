<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.net.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安装配置</title>
<link rel="stylesheet" href="css/redmond/jquery-ui-1.10.3.custom.css">
<style type="text/css">
html,body,div,h1,h2,h3,h4,h5,h6,h7,p,blockquote,form,ul,ol,dl,li {
	font-size: 12px;
	font-family: Lucida Grande, ​Lucida Sans, ​Arial,
		​sans-serifLucida Grande, ​Lucida Sans, ​Arial, ​sans-serif;
}

div.ui-jqdialog-content td.form-view-data {
	white-space: normal !important;
	height: auto;
	vertical-align: middle;
	padding-top: 3px;
	padding-bottom: 3px
}
</style>
<script src="js/jquery-1.9.0.min.js"></script>
<script src="js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript">
	/* $(document).ready(function() {
		//   setupDashboardChart('chart1');
		setupLeftMenu();
		setSidebarHeight();

	}); */
	
	 $(function() {
	    $( "#tabs" ).tabs();
	  }); 
</script>
<style>
/* body {
	background: #2E5E79;
	padding-left: 14px;
} */
</style>
</head>
<body>
	<div id="tabs">
		&nbsp;
		<!--  <ul>
    <li><a href="#tabs-1">安装配置</a></li>
  </ul> -->
		<table width="100%">
			<tr>
				<td width="50%">
					<fieldset style="height: 200px">
						<legend>网络连接设置</legend>
						<font color="red">${message }</font>
						<form action="installconfigaction" method="post">
							<table>
								<tr>
									<td align="right">网卡：</td>
									<td><select name="device" style="width: 130px" onchange="">
											<option></option>
											<%
  		Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
  		String name;
  	    while (en.hasMoreElements()) {
  	    	name = en.nextElement().getName();
  	      %>
											<option value="<%=name%>"><%=name%></option>
											<% 

  	    }
  		%>
									</select></td>
								</tr>
								<tr>
									<td align="right">IP地址：</td>
									<td><input type="text" name="ipaddr" /></td>
								</tr>
								<tr>
									<td align="right">子网掩码：</td>
									<td><input type="text" name="netmask" /></td>
								</tr>
								<tr>
									<td align="right">网关：</td>
									<td><input type="text" name="gateway" /></td>
								</tr>
								<tr>
									<td align="right">DNS1：</td>
									<td><input type="text" name="dns1" /></td>
								</tr>
								<tr>
									<td align="right">DNS2：</td>
									<td><input type="text" name="dns2" /></td>
								</tr>
								<tr>
									<td colspan="2" align="center"><input type="submit"
										value="提交">&nbsp;&nbsp; <input type="reset" value="重置">
									</td>
								</tr>
							</table>
						</form>

					</fieldset>
				</td>
				<td>
					<fieldset style="height: 200px">
						<legend>网络连接设置</legend>
					</fieldset>

				</td>
			</tr>
		</table>
	</div>
</body>
</html>