<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>用户登录</TITLE>
<LINK href="images/Default.css" type=text/css rel=stylesheet>
<LINK href="images/xtree.css" type=text/css rel=stylesheet>
<LINK href="images/User_Login.css" type=text/css rel=stylesheet>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16674" name=GENERATOR>
<SCRIPT type="text/javascript">
if (window != top) 
    top.location.href = location.href; 
</SCRIPT>
</HEAD>
<BODY id=userlogin_body>
	<form action="${pageContext.request.contextPath}/login" method="post">
		<DIV></DIV>

		<DIV id=user_login>
			<DL>
				<DD id=user_top>
					<UL>
						<LI class=user_top_l></LI>
						<LI class=user_top_c></LI>
						<LI class=user_top_r></LI>
					</UL>
				<DD id=user_main>
					<UL>
						<LI class=user_main_l></LI>
						<LI class=user_main_c>
							<DIV class=user_main_box>
								<c:choose>
									<c:when test="${param.error}">
										<div style="color: red">登陆失败，用户名或者密码错误！</div>
									</c:when>
									<c:when test="${param.expired}">
										<div style="color: red">由于同一账号多个回话，您被强制退出。</div>

									</c:when>
									<c:when test="${param.logout}">
										<div style="color: red">您已成功注销！</div>
									</c:when>
									<c:otherwise>
										<div class="blank_box"></div>
									</c:otherwise>
								</c:choose>
								<UL>
									<LI class=user_main_text>用户名：</LI>
									<LI class=user_main_input><INPUT class=TxtUserNameCssClass
										id=TxtUserName maxLength=20 name="username"></LI>
								</UL>
								<UL>
									<LI class=user_main_text>密 码：</LI>
									<LI class=user_main_input><INPUT class=TxtPasswordCssClass
										id=TxtPassword type=password name="password"></LI>
								</UL>
							</DIV>
						</LI>
						<LI class=user_main_r><INPUT class=IbtnEnterCssClass
							id=IbtnEnter
							style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
							onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("IbtnEnter", "", true, "", "", false, false))'
							type=image src="images/user_botton.gif" name=IbtnEnter></LI>
					</UL>
				<DD id=user_bottom>
					<UL>
						<LI class=user_bottom_l></LI>
						<LI class=user_bottom_c></LI>
						<LI class=user_bottom_r></LI>
					</UL>
				</DD>
			</DL>
		</DIV>
		<SPAN id=ValrUserName style="DISPLAY: none; COLOR: red"></SPAN> <SPAN
			id=ValrPassword style="DISPLAY: none; COLOR: red"></SPAN> <SPAN
			id=ValrValidateCode style="DISPLAY: none; COLOR: red"></SPAN>
		<DIV id=ValidationSummary1 style="DISPLAY: none; COLOR: red"></DIV>

		<DIV></DIV>

	</FORM>
</BODY>
</HTML>
