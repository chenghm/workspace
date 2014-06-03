<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Dashboard | BlueWhale Admin</title>
    <link rel="stylesheet" type="text/css" href="css/reset.css"
          media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/text.css"
          media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/grid.css"
          media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/layout.css"
          media="screen"/>
    <link rel="stylesheet" type="text/css" href="css/nav.css" media="screen"/>
    <!--[if IE 6]>
    <link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen"/><![endif]-->
    <!--[if IE 7]>
    <link rel="stylesheet" type="text/css" href="css/ie.css" media="screen"/><![endif]-->
    <!-- BEGIN: load jquery -->
    <!-- <link rel="stylesheet" href="css/jquery-ui.css" />
    <script src="js/jquery-1.9.1.js"></script>
    <script src="js/jquery-ui.js"></script> -->
    <link rel="stylesheet" type="text/css" media="screen" href="css/redmond/jquery-ui-1.10.3.custom.css"/>
    <script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
    <!-- <script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
<script src="js/jquery-ui/jquery.ui.widget.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.accordion.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.core.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.slide.min.js"
	type="text/javascript"></script>
END: load jquery
BEGIN: load jqplot
<link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
[if lt IE 9]>
    <script language="javascript" type="text/javascript" src="js/jqPlot/excanvas.min.js"></script><![endif]
<script language="javascript" type="text/javascript"
	src="js/jqPlot/jquery.jqplot.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jqPlot/plugins/jqplot.barRenderer.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jqPlot/plugins/jqplot.pieRenderer.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jqPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jqPlot/plugins/jqplot.highlighter.min.js"></script>
<script language="javascript" type="text/javascript"
	src="js/jqPlot/plugins/jqplot.pointLabels.min.js"></script>
END: load jqplot
<script src="js/setup.js" type="text/javascript"></script> -->
    <!-- <script type="text/javascript"
        src="js/jupdatepasswordaction.jquery.js"></script> -->
    <script type="text/javascript">
        /* $(document).ready(function() {
         $(this).jUpdatePasswordAction();
         }); */
        function quit() {
            if (window.confirm("确认退出登录？")) {

                window.top.location = "logout";
            } else {

                return false;
            }

        }
    </script>


</head>
<body>
<div class="container_12">
    <div class="grid_12 header-repeat">
        <div id="branding">
            <div class="floatleft"></div>
            <div class="floatright">
                <div class="floatleft">
                    <img alt="Profile Pic" src="img/img-profile.jpg">
                </div>
                <div class="floatleft marginleft10">
                    <ul class="inline-ul floatleft">
                        <li>欢迎 <sec:authentication property="name"/></li>
                        <!-- <li><a href="#" onclick="window.open('profile.jsp', 'main') "  >修改密码</a></li> -->
                        <li><a href="#" onclick="quit()">退出</a></li>
                    </ul>


                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="clear"></div>
</div>
<div>
    <div id="password-dialog" title="修改密码">
        <form id="password-form">
            <fieldset>
                <dl>
                    <dd>
                        <span style="color: red" id="error_msg"></span>
                    </dd>
                </dl>
                <dl>
                    <dt>
                        <label for="currentPassword">当前密码:</label>
                    </dt>
                    <dd>
                        <input type="password" name="currentPassword"
                               id="currentPassword" size="32" maxlength="128"
                               value="${currentPassword }"
                               class="text ui-widget-content ui-corner-all"/> <font
                            color="red">*</font><br/> <span style="color: red"
                                                            id="error_current_password"></span>
                    </dd>
                </dl>
                <dl>
                    <dt>
                        <label for="newPassword">新密码:</label>
                    </dt>
                    <dd>
                        <input type="password" name="newPassword" id="newPassword"
                               size="32" maxlength="128" value="${newPassword }"
                               class="text ui-widget-content ui-corner-all"/> <font
                            color="red">*</font><br/> <span style="color: red"
                                                            id="error_new_password"></span>
                    </dd>
                </dl>
                <dl>
                    <dt>
                        <label for="confirmPassword">确认密码:</label>
                    </dt>
                    <dd>
                        <input type="password" name="confirmPassword"
                               id="confirmPassword" size="32" maxlength="128"
                               value="${confirmPassword }"
                               class="text ui-widget-content ui-corner-all"/> <font
                            color="red">*</font><br/> <span style="color: red"
                                                            id="error_confirm_password"></span>
                    </dd>
                </dl>

            </fieldset>
        </form>
    </div>
    <div id="message-dialog" title="修改密码">
        <p>
				<span class="ui-icon ui-icon-circle-check"
                      style="float: left; margin: 0 7px 50px 0;"></span> <span
                id="message-content"></span>
        </p>
    </div>
</div>


</body>
</html>