<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>1号公交</title>

<link rel="stylesheet" href="../css/bootstrap.css" type="text/css"
	media="screen" title="no title">

<link rel="stylesheet" href="../css/reset.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/text.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/form.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/buttons.css" type="text/css"
	media="screen" title="no title" />
<!--内容布局-->
<link rel="stylesheet" href="../css/grid.css" type="text/css"
	media="screen" title="no title" />
<!--网页头部-->
<link rel="stylesheet" href="../css/layout.css" type="text/css"
	media="screen" title="no title" />

<!--图标-->
<link rel="stylesheet"
	href="../css/ui-darkness/jquery-ui-1.8.12.custom.css" type="text/css"
	media="screen" title="no title" />

<link rel="stylesheet" href="../css/plugin/jquery.visualize.css"
	type="text/css" media="screen" title="no title" />
<link rel="stylesheet" href="../css/plugin/facebox.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/plugin/uniform.default.css"
	type="text/css" media="screen" title="no title" />
<link rel="stylesheet" href="../css/plugin/dataTables.css"
	type="text/css" media="screen" title="no title" />
<!--线条-->
<link rel="stylesheet" href="../css/custom.css" type="text/css"
	media="screen" title="no title">

<!-- #图标 -->
<link rel="shortcut icon" href="../images/bus.png" type="image/x-icon" />
<link href="../js/video-js/video-js.css" rel="stylesheet"
	type="text/css">
<script src="../js/video-js/video.js"></script>
<script>
	videojs.options.flash.swf = "../js/video-js/video-js.swf";
</script>

</head>

<body onload="driver_onload()">

	<div id="wrapper">



		<div id="header">

			<div class="content_pad">
				<div style="position: absolute; top: 30px; left: -60px">

					<a><img src="../images/onebus.png" width="60%"></a>

				</div>

				<ul id="nav">
					<li class="nav_dropdown nav_icon"><a href="/onebus/main/home"><span
							class="ui-icon ui-icon-home"></span>主页</a></li>

					<li class="nav_dropdown nav_icon"><a><span
							class="ui-icon-gripsmall-diagonal-se"></span>模拟调度</a>
						<div class="nav_menu">
							<ul>
								<li><a href="/onebus/main/simulationMap">实时地图</a></li>
								<li><a href="/onebus/main/simulationLine">模拟线路</a></li>
								<li><a href="/onebus/main/emergencyTreatment">应急处理</a></li>
							</ul>
						</div></li>

					<li class="nav_dropdown nav_icon"><a
						href="/onebus/main/smartSchedule"><span
							class="ui-icon-newwin "></span>智能排班</a></li>

					<li class="nav_current nav_icon"><a
						href="/onebus/main/searchVideo"><span
							class="ui-icon ui-icon-video"></span>视频监控</a></li>

					<li class="nav_dropdown nav_icon"><a
						href="/onebus/main/busLineFlow"><span
							class="ui-icon ui-icon-lightbulb"></span>客流量信息</a></li>

					<li class="nav_dropdown nav_icon"><a><span
							class="ui-icon ui-icon-pencil"></span>信息管理</a>
						<div class="nav_menu">
							<ul>
								<li><a href="/onebus/main/busInfor">车辆信息</a></li>
								<li><a href="/onebus/main/driverInfor">司机信息</a></li>
							</ul>

						</div></li>

					<li class="nav_dropdown nav_icon"><a><span
							class="ui-icon ui-icon-hand"></span>用户反馈</a>
						<div class="nav_menu">
							<ul>
								<li><a href="/onebus/main/userRecommendation">用户意见</a></li>
								<li><a href="/onebus/main/userComplaint">用户投诉</a></li>
							</ul>
						</div></li>

					<li class="nav_dropdown nav_icon"><a><span
							class="ui-icon ui-icon-person"></span>账户管理</a>
						<div class="nav_menu">
							<ul>
								<li><a href="/onebus/main/setting">修改密码</a></li>
								<li><a href="/onebus/main/settingInformation">修改信息</a></li>
								<li><a href="/onebus/main/index">注销</a></li>
							</ul>

						</div></li>
				</ul>
			</div>
			<!-- .content_pad -->

		</div>
		<!-- #header -->

		<div id="masthead">

			<div class="content_pad">

				<h1 class="">视频监控</h1>

				<div id="bread_crumbs">
					<a>${bus.getBusLine().getLineNumber()}:${bus.getPlateNumber()}</a>
				</div>
				<!-- #bread_crumbs -->


			</div>
			<!-- .content_pad -->

		</div>
		<!-- #masthead -->

		<div id="content" class="xgrid">



			<div class="accordion_container">


				<div class="block" align="center">



					<table>
						<tr>
							<td><font size="4">车前方：</font><video id="my_video_1"
									class="video-js vjs-default-skin" controls preload="auto"
									width="480" height="360"
									poster="../js/video-js/my_video_poster.png" data-setup="{}">
									<source src="../video/videoCreate.jsp?ppath=/video/1.mp4" type='video/mp4'>
								</video></td>
							<td>&nbsp;&nbsp;</td>
							<td><font size="4">司机：</font><video id="my_video_1"
									class="video-js vjs-default-skin" controls preload="auto"
									width="480" height="360"
									poster="../js/video-js/my_video_poster.png" data-setup="{}">
									<source src="../video/videoCreate1.jsp?ppath=/video/2.mp4" type='video/mp4'>
								</video></td>
						</tr>
						<tr>
							<td><font size="4">车厢：</font><video id="my_video_1"
									class="video-js vjs-default-skin" controls preload="auto"
									width="480" height="360"
									poster="../js/video-js/my_video_poster.png" data-setup="{}">
									<source src="../video/videoCreate2.jsp?ppath=/video/3.mp4" type='video/mp4'>
								</video></td>
								<td></td>
							<td><font size="4">下客门:</font><video id="my_video_1"
									class="video-js vjs-default-skin" controls preload="auto"
									width="480" height="360"
									poster="../js/video-js/my_video_poster.png" data-setup="{}">
									<source src="../video/videoCreate3.jsp?ppath=/video/4.mp4" type='video/mp4'>
								</video></td>
						</tr>
					</table>

					<br>
					<div class="field">
						<font size="4"> <label for="password2">车辆信息: </label>
							&nbsp;&nbsp; <label for="password2">车速: 30km/h</label>
							&nbsp;&nbsp;&nbsp; <label for="password2">载客量: 35人</label>
							&nbsp;&nbsp;&nbsp; <label for="password2">油量: 50L</label>
						</font>
					</div>

				</div>


			</div>
			<!-- .accordion_container -->

		</div>
		<!-- #content -->

		<div id="footer">
			<div class="content_pad">
				<p>&copy; 2014-15 Copyright MadeBy MavelCo</p>
			</div>
			<!-- .content_pad -->
		</div>
		<!-- #footer -->

	</div>
	<!-- #wrapper -->



</body>

</html>