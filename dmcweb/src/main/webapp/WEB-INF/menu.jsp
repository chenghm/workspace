<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page
	import="com.cinsec.dmc.service.IResourceService,java.util.*,com.cinsec.dmc.entity.*"%>

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
	<%!List<Resource> getSubResources(List<Resource> resources, String resourceCode) {
		List<Resource> result = new ArrayList<Resource>();
		for (Resource resource : resources) {
			if (resource.getParentCode().equals(resourceCode)) {
				result.add(resource);
			}
		}
		return result;

	}

	List<Resource> getParentResources(List<Resource> resources) {
		List<Resource> result = new ArrayList<Resource>();
		int size = resources.size();
		for (int i = 0; i < size; i++) {
			if (resources.get(i).getCode()
					.equals(resources.get(i).getParentCode())) {
				continue;
			}
			for (int j = i + 1; j < size; j++) {
				if (resources.get(i).getCode()
						.equals(resources.get(j).getParentCode())) {
					result.add(resources.get(i));
					break;
				}
			}
		}
		return result;
	}%>

	<div class="container_12">

		<div class="">
			<div class="box sidemenu">
				<div role="tablist"
					class="block ui-accordion ui-widget ui-helper-reset ui-accordion-icons"
					id="section-menu">
					<ul class="section menu">


						<%
							ApplicationContext context = WebApplicationContextUtils
									.getWebApplicationContext(application);

							IResourceService resourceService = (IResourceService) context
									.getBean("resourceService");
							List<Resource> resources = resourceService
									.getCurrentUserResources();
							List<Resource> parentResources = getParentResources(resources);
							int size = parentResources.size();
							for (int i = 0; i < size; i++) {
								if (i == 0) {
						%>
						<li><a tabindex="0" aria-selected="true" aria-expanded="true"
							role="tab"
							class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-state-active ui-corner-top current"><span
								class="ui-icon ui-icon-triangle-1-s"></span><%=parentResources.get(i).getName()%></a>
							<ul role="tabpanel" style="height: auto;"
								class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ui-accordion-content-active current">
								<%
									} else {
								%>

								<li><a tabindex="-1" aria-selected="false"
									aria-expanded="false" role="tab"
									class="menuitem ui-accordion-header ui-helper-reset ui-state-default ui-corner-all"><span
										class="ui-icon ui-icon-triangle-1-e"></span><%=parentResources.get(i).getName()%></a>
									<ul role="tabpanel" style="height: auto; display: none;"
										class="submenu ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom">
										<%
											}
										%>
										<%
											for (Resource r : getSubResources(resources, parentResources
														.get(i).getCode())) {
										%>
										<li><a href="<%=r.getUrl().substring(1)%>" target="main"><%=r.getName()%></a></li>

										<%
											}
										%>
									</ul></li>
								<%
									}
								%>




							</ul>
				</div>
			</div>
		</div>


		<div class="clear"></div>
	</div>
	<div class="clear"></div>



</body>
</html>