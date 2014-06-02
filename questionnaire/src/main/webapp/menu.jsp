<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜单</title>
<link rel="stylesheet" type="text/css" href="css/reset.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/text.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/grid.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/layout.css"
	media="screen" />
<link rel="stylesheet" type="text/css" href="css/nav.css" media="screen" />
<!--[if IE 6]><link rel="stylesheet" type="text/css" href="css/ie6.css" media="screen" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" type="text/css" href="css/ie.css" media="screen" /><![endif]-->
<!-- BEGIN: load jquery -->
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery-ui/jquery.ui.core.min.js"></script>
<script src="js/jquery-ui/jquery.ui.widget.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.ui.accordion.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.core.min.js"
	type="text/javascript"></script>
<script src="js/jquery-ui/jquery.effects.slide.min.js"
	type="text/javascript"></script>
<!-- END: load jquery -->
<!-- BEGIN: load jqplot -->
<link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="js/jqPlot/excanvas.min.js"></script><![endif]-->
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
<!-- END: load jqplot -->
<script src="js/setup.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//   setupDashboardChart('chart1');
		setupLeftMenu();
		setSidebarHeight();

	});
</script>
<style>
body {
	background: #2E5E79;
	padding-left: 14px;
}
</style>
</head>
<body>
	<div class="container_12">

		<div class="">
			<div class="box sidemenu">
				<div role="tablist"
					class="block ui-accordion ui-widget ui-helper-reset ui-accordion-icons"
					id="section-menu">
					<ul class="section menu">



						<li><a tabindex="0" aria-selected="true" aria-expanded="true"
							role="tab"
							class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-state-active ui-corner-top current"><span
								class="ui-icon ui-icon-triangle-1-s"></span>系统菜单</a>
							<ul role="tabpanel" style="height: auto;"
								class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current">
								<li><a href="questionnaire.html" target="main">问卷管理</a></li>
								<li><a href="question.html" target="main">问题管理</a></li>
								<li><a href="user.html" target="main">用户管理</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>


		<div class="clear"></div>
	</div>
	<div class="clear"></div>



</body>
</html>