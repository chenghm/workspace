<%@page import="com.cinsec.dmc.service.INodeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page
	import="com.cinsec.dmc.service.INodeService,java.util.*,com.cinsec.dmc.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜单</title>
<style>
*,body,ul,li,h1,h2 {
	margin: 0;
	padding: 0;
	list-style: none;
}

body {
	font: 12px "宋体";
	padding-top: 20px;
	background: #2E5E79;
	padding-left: 14px;
}

#menu {
	width: 200px;
}

#menu h1 {
	cursor: pointer;
	color: #FFF;
	font-size: 12px;
	padding: 5px 0 3px 10px;
	border: #C60 1px solid;
	margin-top: 1px;
	background-color: #F93;
}

#menu h2 {
	cursor: pointer;
	color: #777;
	font-size: 12px;
	padding: 5px 0 3px 10px;
	border: #E7E7E7 1px solid;
	border-top-color: #FFF;
	background-color: #F4F4F4;
}

#menu ul {
	padding-left: 15px;
	height: 100%;
	border: #E7E7E7 1px solid;
	border-top: none;
	overflow: auto;
}

#menu ul li {
	padding: 5px 0 3px 10px;
}

.no {
	display: none;
}

a {
	text-decoration: none;
	color: yellow
}
</style>
<script language="JavaScript">

function ShowMenu(obj,noid){
	var block =	document.getElementById(noid);
	var n = noid.substr(noid.length-1);
	 if(noid.length==4){
		var ul = document.getElementById(noid.substring(0,3)).getElementsByTagName("ul");
		var h2 = document.getElementById(noid.substring(0,3)).getElementsByTagName("h2");
		for(var i=0; i<h2.length;i++){
			h2[i].innerHTML = h2[i].innerHTML.replace("+","-");
			h2[i].style.color = "";
		}
		obj.style.color = "#FF0000";
		for(var i=0; i<ul.length; i++){if(i!=n){ul[i].className = "no";}}
	}else{
		var span = document.getElementById("menu").getElementsByTagName("span");
		var h1 = document.getElementById("menu").getElementsByTagName("h1");
		for(var i=0; i<h1.length;i++){
			h1[i].innerHTML = h1[i].innerHTML.replace("+","-");
			h1[i].style.color = "";
		}
		obj.style.color = "#0000FF";
		for(var i=0; i<span.length; i++){
			if(i!=n){span[i].className = "no";}}
	 } 
	if(block.className == "no"){
		block.className = "";
		obj.innerHTML = obj.innerHTML.replace("-","+");
	}else{
		block.className = "no";
		obj.style.color = "";
	}
}

</script>
</head>
<body>
	<% int i=0; %>
	<div id="menu">
		<a href="general.html" target="main"><h1>综合查询</h1></a>

		<%
							ApplicationContext context = WebApplicationContextUtils
									.getWebApplicationContext(application);

							INodeService nodeService = (INodeService) context
									.getBean("nodeService");
							List<Node> nodes = nodeService.findAllNodes();
							int size = nodes.size();
							int nodeNumber,nodeId;
							Node node;
							for (i=0; i < size; i++) {
								node =nodes.get(i);
								nodeId = node.getId();
								nodeNumber = node.getNumber();
						
						%>
		<h1 onClick="javascript:ShowMenu(this,'NO<%=i %>')">
			- 节点<%=nodeNumber%></h1>
		<span id="NO<%=i %>" class="no">
			<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>0')">- 上网行为</h2> <% if("1".equals(node.getIsHttp()) || "1".equals(node.getIsSearch()) || "1".equals(node.getIsThrough())
				|| "1".equals(node.getIsCommmerce()) || "1".equals(node.getIsSocial()) || "1".equals(node.getIsPassword())) { %>
			<ul id="NO<%=i %>0" class="no">
				<% if("1".equals(node.getIsHttp())) { %><li><a
					href="http.html?nodeId=<%=nodeId%>" target="main">网页浏览</a></li>
				<% } %>
				<% if("1".equals(node.getIsSearch())) { %><li><a
					href="general.html" target="main">搜索引擎</a></li>
				<% } %>
				<% if("1".equals(node.getIsThrough())) { %><li><a
					href="general.html" target="main">破网行为</a></li>
				<% } %>
				<% if("1".equals(node.getIsCommmerce())) { %><li><a
					href="general.html" target="main">电子商务</a></li>
				<% } %>
				<% if("1".equals(node.getIsSocial())) { %><li><a
					href="general.html" target="main">社交网络</a></li>
				<% } %>
				<% if("1".equals(node.getIsPassword())) { %><li><a
					href="general.html" target="main">口令信息</a></li>
				<% } %>
			</ul> <% } %> <% if("1".equals(node.getIsFtp()) || "1".equals(node.getIsNetdisk()) || "1".equals(node.getIsChat()) ) { %>
			<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>1')">- 文件传送</h2>
			<ul id="NO<%=i %>1" class="no">
				<% if("1".equals(node.getIsFtp())) { %><li><a href="general.html"
					target="main">FTP信息</a></li>
				<% } %>
				<% if("1".equals(node.getIsNetdisk())) { %><li><a
					href="general.html" target="main">网盘信息</a></li>
				<% } %>
				<% if("1".equals(node.getIsChat())) { %><li><a
					href="general.html" target="main">即时通讯</a></li>
				<% } %>
			</ul> <% } %> <% if("1".equals(node.getIsControl())){ %>
			<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>2')">- 可疑行为</h2>
			<ul id="NO<%=i %>2" class="no">
				<li><a href="general.html" target="main">远程控制</a></li>
			</ul> <% } %> <% if("1".equals(node.getIsChat())) { %>
			<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>3')">- 网络聊天</h2>
			<ul id="NO<%=i %>3" class="no">
				<li><a href="general.html" target="main">MSN</a></li>
				<li><a href="general.html" target="main">QQ</a></li>
				<li><a href="general.html" target="main">飞信</a></li>
				<li><a href="general.html" target="main">微信</a></li>
				<li><a href="general.html" target="main">Skype</a></li>
			</ul> <% } %> <% if("1".equals(node.getIsMail())) { %>
			<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>4')">- 电子邮件</h2>
			<ul id="NO<%=i %>4" class="no">
				<li><a href="general.html" target="main">POP3</a></li>
				<li><a href="general.html" target="main">SMTP</a></li>
				<li><a href="general.html" target="main">WEB收</a></li>
				<li><a href="general.html" target="main">WEB发</a></li>
				<li><a href="general.html" target="main">存草稿</a></li>
				<li><a href="general.html" target="main">收草稿</a></li>
				<li><a href="general.html" target="main">邮件渗透</a></li>
			</ul> <% } %>
		</span>

		<% } %>


		<h1 onClick="javascript:ShowMenu(this,'NO<%=i %>')">- 系统管理</h1>
		<span id="NO<%=i %>" class="no"> <a href="user.html"
			target="main"><h2>用户管理</h2></a> <a href="node.html" target="main"><h2>
					节点管理</h2></a>
		</span>

		<h1 onClick="javascript:ShowMenu(this,'NO4')">- 四级菜单D</h1>
		<span id="NO4" class="no">
			<h2 onClick="javascript:ShowMenu(this,'NO40')">- 四级菜单D_1</h2>
			<ul id="NO40" class="no">
				<!-- <li>四级菜单D_0</li>
			<li>四级菜单D_1</li>
			<li>四级菜单D_2</li>
			<li>四级菜单D_11</li> -->
			</ul>
			<h2 onClick="javascript:ShowMenu(this,'NO41')">- 四级菜单D_2</h2>
			<ul id="NO41" class="no">
				<!-- 	<li>四级菜单D_0</li>
			<li>四级菜单D_1</li>
			<li>四级菜单D_2</li>
			<li>四级菜单D_11</li>
			<li>四级菜单D_4</li>
			<li>四级菜单D_5</li> -->
			</ul>
		</span>
		<%-- <h1 onClick="javascript:ShowMenu(this,'NO<%=i %>')"> - 四级菜单D</h1>
	<span id="NO<%=i %>" class="no">
		<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>0')"> - 四级菜单D_1</h2>
		<ul id="NO<%=i %>0" class="no">
			<!-- <li>四级菜单D_0</li>
			<li>四级菜单D_1</li>
			<li>四级菜单D_2</li>
			<li>四级菜单D_11</li> -->
		</ul>
		<h2 onClick="javascript:ShowMenu(this,'NO<%=i %>1')"> - 四级菜单D_2</h2>
		<ul id="NO<%=i %>1" class="no">
		<!-- 	<li>四级菜单D_0</li>
			<li>四级菜单D_1</li>
			<li>四级菜单D_2</li>
			<li>四级菜单D_11</li>
			<li>四级菜单D_4</li>
			<li>四级菜单D_5</li> -->
		</ul>
	</span> --%>
	</div>
</body>
</html>