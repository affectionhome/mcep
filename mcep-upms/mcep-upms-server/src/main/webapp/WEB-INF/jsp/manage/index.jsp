<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>mcep信息化管理平台</title>

	<link href="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/material-design-iconic-font-2.2.0/css/material-design-iconic-font.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.min.css" rel="stylesheet"/>
	<link href="${basePath}/resources/zheng-admin/css/admin.css" rel="stylesheet"/>
	<link href="/resources/zheng-admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.css" rel="stylesheet"/>
	<style>
		/** skins **/
		<c:forEach var="upmsSystem" items="${upmsSystems}">
		#${upmsSystem.name} #header {background: ${upmsSystem.theme};}
		#${upmsSystem.name} .content_tab{background: ${upmsSystem.theme};}
		#${upmsSystem.name} .s-profile>a{background: url(${basePath}${upmsSystem.banner}) left top no-repeat;}
		</c:forEach>
		.menu-selected {
			background-color: #61CAD0;
			color: #FFFFFF !important;
		}
	</style>
</head>
<body>
<header id="header">
	<ul id="menu">
		<li id="guide" class="line-trigger">
			<div class="line-wrap">
				<div class="line top"></div>
				<div class="line center"></div>
				<div class="line bottom"></div>
			</div>
		</li>
		<li id="logo" class="hidden-xs">
			<a href="${basePath}/manage/index">
				<img src="${basePath}/resources/zheng-admin/images/mcep_logo.png"/>
			</a>
			<span id="system_title">权限管理系统</span>
		</li>
		<li class="pull-right">
			<ul class="hi-menu">
				<!-- 搜索 -->
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<span style="line-height: 34px;" >${upmsUser.realname}，您好！</span>
					</a>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-dropbox"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="skin-switch hidden-xs">
							请选择系统切换
						</li>
						<li class="divider hidden-xs"></li>
						<c:forEach var="upmsSystem" items="${upmsSystems}">
						<li>
							<a class="waves-effect switch-systems" href="javascript:;" systemid="${upmsSystem.systemId}" systemname="${upmsSystem.name}" systemtitle="${upmsSystem.title}"><i class="${upmsSystem.icon}"></i> ${upmsSystem.title}</a>
						</li>
						</c:forEach>
					</ul>
				</li>
				<li class="dropdown">
					<a class="waves-effect waves-light" data-toggle="dropdown" href="javascript:;">
						<i class="him-icon zmdi zmdi-more-vert"></i>
					</a>
					<ul class="dropdown-menu dm-icon pull-right">
						<li class="hidden-xs">
							<a class="waves-effect" data-ma-action="fullscreen" href="javascript:fullPage();"><i class="zmdi zmdi-fullscreen"></i> 全屏模式</a>
						</li>
						<li>
							<a class="waves-effect" data-ma-action="clear-localstorage" href="javascript:;"><i class="zmdi zmdi-delete"></i> 清除缓存</a>
						</li>
						<%--<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-face"></i> 隐私管理</a>
						</li>
						<li>
							<a class="waves-effect" href="javascript:;"><i class="zmdi zmdi-settings"></i> 系统设置</a>
						</li>--%>
						<li>
							<a class="waves-effect" href="javascript:;passwordAction()"><i class="zmdi zmdi-settings"></i> 修改密码</a>
						</li>
						<li>
							<a class="waves-effect" href="${basePath}/sso/logout"><i class="zmdi zmdi-run"></i> 退出登录</a>
						</li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</header>
<section id="main">
	<!-- 左侧导航区 -->
	<aside id="sidebar">
		<!-- 个人资料区 -->


		<!-- /个人资料区 -->
		<!-- 菜单区 -->
		<ul class="main-menu">
			<li>
				<a class="waves-effect" href="javascript:Tab.addTab('首页', 'home');"><i class="zmdi zmdi-home"></i> 首页</a>
			</li>
			<c:forEach var="upmsPermission" items="${upmsPermissions}" varStatus="status">
				<c:if test="${upmsPermission.pid == 0}">
				<li class="sub-menu system_menus system_${upmsPermission.systemId} ${status.index}" <c:if test="${upmsPermission.systemId != 1}">style="display:none;"</c:if>>
					<a href="javascript:;"><i class="${upmsPermission.icon}"></i> ${upmsPermission.name}</a>
					<ul>
						<c:forEach var="subUpmsPermission" items="${upmsPermissions}">
							<c:if test="${subUpmsPermission.pid == upmsPermission.permissionId}">
								<c:forEach var="upmsSystem" items="${upmsSystems}">
									<c:if test="${subUpmsPermission.systemId == upmsSystem.systemId}">
									<c:set var="systemBasePath" value="${upmsSystem.basepath}"/></c:if>
								</c:forEach>
								<c:if test="${subUpmsPermission.type==2}">
									<li class="two-menu">
										<a href="javascript:;"><i class="glyphicon glyphicon-leaf"></i>${subUpmsPermission.name}</a>
										<ul>
											<c:forEach var="upmsPermission3" items="${upmsPermissions}">
												<c:if test="${upmsPermission3.pid == subUpmsPermission.permissionId}">
													<li><a id="${subUpmsPermission.permissionId}" class="waves-effect" onclick="javascript:Tab.addTab('${upmsPermission3.name}', '${systemBasePath}${upmsPermission3.uri}',this);">${upmsPermission3.name}</a></li>
												</c:if>
											</c:forEach>

										</ul>
									</li>
								</c:if>
								<c:if test="${subUpmsPermission.type==3}">
									<li><a class="waves-effect" onclick="javascript:Tab.addTab('${subUpmsPermission.name}', '${systemBasePath}${subUpmsPermission.uri}',this);">${subUpmsPermission.name}</a></li>
								</c:if>
								</c:if>
						</c:forEach>
					</ul>
				</li>
				</c:if>
			</c:forEach>
			<li>
				<div class="upms-version">&copy; MCEP V1.0.0</div>
			</li>
		</ul>
		<!-- /菜单区 -->
	</aside>
	<!-- /左侧导航区 -->
	<section id="content">
		<div class="content_tab">
			<div class="tab_left">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-left"></i></a>
			</div>
			<div class="tab_right">
				<a class="waves-effect waves-light" href="javascript:;"><i class="zmdi zmdi-chevron-right"></i></a>
			</div>
			<ul id="tabs" class="tabs">
				<li id="tab_home" data-index="home" data-closeable="false" class="cur">
					<a class="waves-effect waves-light" href="javascript:;">首页</a>
				</li>
			</ul>
		</div>
		<div class="content_main">
			<div id="iframe_home" class="iframe cur">


				<%--<div class="row" style="display: none;">
					<div class="col-sm-6 col-md-6 col-lg-6">
						<iframe src="${mcep_flow_admin}/manage/process/index" style="width:100%;border:0px;"></iframe>

					</div>
					<div class="col-sm-6 col-md-6 col-lg-6">
						<iframe src="${mcep_flow_admin}/manage/process/historic" style="width:100%;border:0px;"></iframe>

					</div>

				</div>--%>



			</div>
		</div>
	</section>
</section>
<footer id="footer"></footer>
<script>var BASE_PATH = '${basePath}';</script>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.1.12.4.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/bootstrap-3.3.0/js/bootstrap.min.js"></script>
<script src="/resources/zheng-admin/plugins/bootstrap-table-1.11.0/bootstrap-table.min.js"></script>
<script src="/resources/zheng-admin/plugins/bootstrap-table-1.11.0/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/waves-0.7.5/waves.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/BootstrapMenu.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/device.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/jquery.cookie.js"></script>
<script src="${basePath}/resources/zheng-admin/js/admin.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.fullPage.min.js"></script>
<script src="${basePath}/resources/zheng-admin/plugins/fullPage/jquery.jdirk.min.js"></script>
<script>

	$(function(){
	    $("#iframe_home iframe").css("height",$(window).height()/2);
	});
    //注册消息事件，主要是处理跨域之前的消息处理
    window.onmessage = function (e){
        e = e || event;
        var str = e.data[0] +"(";			//第1个参数是要执行的方法，后面的参数都是方法所需要的参数
        for(var i=1;i<e.data.length;i++){
            str += "'"+e.data[i]+"',";
        }
        str = str.substring(0,str.length -1)+")";

        eval(str);
    }

    var passwordDialog;
    function passwordAction() {
        passwordDialog = $.dialog({
            animationSpeed: 300,
            title: '修改密码',
            content: 'url:/manage/user/password',
            onContentReady: function () {

            }
        });
    }


</script>


</body>
</html>