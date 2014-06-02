<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人资料</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="css/redmond/jquery-ui-1.10.3.custom.css" />
<script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<style type="text/css">
	html, body {
    font-size: 11px;
</style>
<script type="text/javascript">
	$(function() {
		$("#tabs").tabs();
		
	});
	function updatepassword(){
		$.ajax({
			type : 'post',
			url : "manageraction_modifyPassword",
			dataType : 'json',
			data : $("#password-form").serialize(),
			success : function(json) {
				if (json.ajaxResult=="success") {
					$("#error_currentPassword").html("");
					$("#error_newPassword").html("");
					$("#error_confirmPassword").html("");
					alert("修改密码成功！");
				}else
				$.each(json.messages, function(index,
						obj) {
					$("#" + index).html(obj);
				});
			},
			error : function(response, status, xhr) {
				alert("发生错误,请重试:"+response.responseText);
				
				
				
			}
		});
	}
	
</script>
</head>
<body>

	<div id="tabs">
		<ul>
			<li><a href="#tabs-2">修改密码</a></li>
		</ul>
		<div id="tabs-2">
			<form id="password-form">
				<table>
					<tr>
						<th align="right">当前密码：</th>
						<td><input type="password" name="currentPassword"
							id="currentPassword" <%-- value="${currentPassword }" --%>
							class="text ui-widget-content ui-corner-all" /> <font
							color="red">*</font><br /> <span style="color: red"
							id="error_current_password"></span></td>
					</tr>
					<tr>
						<th align="right">新密码：</th>
						<td><input type="password" name="newPassword"
							id="newPassword" <%-- value="${newPassword }" --%>
							class="text ui-widget-content ui-corner-all" /> <font
							color="red">*</font><br /> <span style="color: red"
							id="error_new_password"></span></td>
					</tr>

					<tr>
						<th align="right">确认密码：</th>
						<td><input type="password" name="confirmPassword"
							id="confirmPassword" <%-- value="${confirmPassword }" --%>
							class="text ui-widget-content ui-corner-all" /> <font
							color="red">*</font><br /> <span style="color: red"
							id="error_confirm_password"></span></td>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<td><input type="button"  value="提交"
							class="text ui-widget-content ui-corner-all" onclick="updatepassword()"/></td>
					</tr>


				</table>



			</form>
		</div>
	</div>


</body>
</html>