<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>1号公交</title>

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

<script src="../js/jquery/jquery-1.5.2.min.js"></script>
<script src="../js/Chart.min.js"></script>

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
<style type="text/css">
#map_canvas123456 {
	width: 100%;
	height: 90%;
	margin-left: auto;
	margin-right: auto;
}

#result {
	width: 100%
}
</style>


<script type="text/javascript" src="../js/jquery/jquery-1.5.2.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		var obj = parent.document.getElementById("wapQ");
		if (obj != 'undefind' && obj != null) {
			parent.document.getElementById("wapQ").style.height = '560px';
		}
	});
</script>
<link rel="stylesheet" type="text/css" href="../css/basic.css">

</head>

<body>

	<div id="wrapper">


		<div id="header">

			<div class="content_pad">
				<div style="position: absolute; top: 30px; left: -60px">

					<a><img src="../images/onebus.png" width="60%"></a>

				</div>

				<ul id="nav">
					<li class="nav_dropdown nav_icon"><a href="/onebus/main/home"><span
							class="ui-icon ui-icon-home"></span>主页</a></li>

					<li class="nav_current nav_icon"><a><span
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

					<li class="nav_dropdown nav_icon"><a
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

				<h1 class="">模拟调度</h1>

				<div id="bread_crumbs">
					<a>模拟调度</a> / <a class="current_page">模拟线路</a>
				</div>
				<!-- #bread_crumbs -->


			</div>
			<!-- .content_pad -->

		</div>
		<!-- #masthead -->

	</div>
	<!-- #wrapper -->


	<div id="page-wrapper" align="center">
		<div >
		
			<font size="6" >43路-上行</font>

		</div>
		<div align="center">
			<section class="live" id="live">
				<div class="detail">
					<h3 style="margin-bottom: 10px;">
						西工大西门<span>→</span>电力医院
					</h3>
					<p>首末班车：06:00-23:00</p>
					<p class="map-hintx" style="margin-top: 0px;">
						<a><img src="../images/bus_run.png"
							style="width: 20px; height: 11px; vertical-align: middle;">
							<font style="font-size: 1.1rem">途中</font></a><a
							style="margin-left: 10px;"><img src="../images/bus_stop.png"
							style="width: 20px; height: 11px; vertical-align: middle;">
							<font style="font-size: 1.1rem">到站</font></a>
					</p>
				</div>
				<div id="map-wrapper" class="map"
					style="margin-top: 117px; height: 401px;">
					<div class="wrap" id="my-map-wrap"
						style="padding-top: 70px; width: 1780px; -webkit-transition: 0ms cubic-bezier(0.1, 0.57, 0.1, 1); transition: 0ms cubic-bezier(0.1, 0.57, 0.1, 1); -webkit-transform: translate(0px, 0px) translateZ(0px);">
						<ul>
							<li class="before"><i class="bus-run"></i><a>1西工大西门</a></li>
							<li class="before"><a>2大唐西市</a></li>
							<li class="before"><a>3草阳村</a></li>
							<li class="before"><i class="bus-stop"></i><a>4安康办</a></li>
							<li class="before"><a>5丰庆路东口</a></li>
							<li class="before"><a>6水司</a></li>
							<li class="before"><a>7西门(外)</a></li>
							<li class="before"><i class="bus-stop"></i><a>8西门</a></li>
							<li class="before"><i class="bus-run"></i><a>9桥梓口</a></li>
							<li class="before"><a>10广济街</a></li>
							<li class="before"><a>11钟楼</a></li>
							<li class="before"><i class="bus-run"></i><a>12端履门</a></li>
							<li class="before"><a>13大差市</a></li>
							<li class="before"><a>14东门</a></li>
							<li class="before"><a>15鸡市拐</a></li>
							<li class="before"><a>16兴庆公园北门</a></li>
							<li class="before"><a>17兴庆路</a></li>
							<li class="before"><i class="bus-run"></i><a>18互助路立交</a></li>
							<li class="before"><a>19金花北路</a></li>
							<li class="before"><a>20金花路</a></li>
							<li class="before"><a>21轻工市场</a></li>
							<li class="before"><a>22公园北路</a></li>
							<li class="before"><a>23省药材公司</a></li>
							<li class="current"><a>24电力医院</a></li>
						</ul>
					</div>
				</div>
			</section>
		</div>

		<div class="footer1">
			<div style="display: inline;">
				<a id="reverse-btn" class="redirect" href="javascript:void(0)"
					style="margin-top: 13px; display: inline; margin-bottom: 5px;">
					<img src="../images/icon-redirect.png">换向
				</a> <a id="refresh-btn" class="refresh" href="javascript:void(0)"
					style="margin-top: 13px; display: inline; margin-bottom: 5px;">
					<img src="../images/icon-refresh.png">刷新
				</a>
			</div>
		</div>
	</div>






	<div id="footer">
		<div class="content_pad">
			<p>&copy; 2015 Copyright MadeBy MavelCo</p>
		</div>
		<!-- .content_pad -->
	</div>



</body>
</html>


