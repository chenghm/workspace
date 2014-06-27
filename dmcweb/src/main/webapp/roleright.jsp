<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="a" uri="/core-tag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/dtree.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/dtree.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/basic.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/roleright.js"></script>
<title>角色分配权限</title>




</head>

<body>
	<div style="margin-top: 10px">
		<form id="form_role_right">
			<div style="margin-left: 10px">
				<table style="width: 98%">
					<tr>
						<td><input type="button" id="query_role_right" name="query"
							value="查询权限" onclick="queryRoleRight()" />&nbsp;&nbsp; <input
							type="button" id="save_role_right" name="save" value="保存授权"
							onclick="saveRoleRight()" /></td>
					</tr>
				</table>
			</div>
			<div style="color: red" id="error_role_right"></div>
			<div style="width: 100%; margin-left: 10px;">
				<div
					style="display: inline-block; width: 25%; height: 400px; vertical-align: top;">
					<table border="0" width="100%">
						<tr>
							<th align="left">应用角色信息(双击选择)</th>
						</tr>
						<tr>
							<td><select id="roleId" name="roleId" multiple="multiple"
								style="width: 100%; height: 400px;">
									<c:forEach items="${roles}" var="role">
										<option value="${role.id}"
											<c:if test="${roleId==role.id}">selected</c:if>
											ondblclick="queryRoleRight()">${role.name}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>
				</div>

				<div style="display: inline-block; width: 35%; height: 400px;">
					<table border="0" width="100%">
						<tr>
							<th align="left">应用角色权限列表</th>
						</tr>
						<tr>
							<td>
								<div
									style="border: solid 1px silver; width: 100%; height: 396px; overflow: auto; vertical-align: top;">
									<a:roleRightTreeTag treeName="dc" check="true"
										imgPath="${pageContext.request.contextPath}/img/dtree" />
								</div>
							</td>
						</tr>
					</table>
				</div>

				<div style="display: inline-block; width: 35%; height: 400px;">
					<table border="0" width="100%">
						<tr>
							<th align="left">应用角色权限预览</th>
						</tr>
						<tr>
							<td>
								<div
									style="border: solid 1px silver; width: 100%; height: 396px; overflow: auto;">
									<a:roleRightTreeTag treeName="d" check="false"
										imgPath="${pageContext.request.contextPath}/img/dtree" />
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
