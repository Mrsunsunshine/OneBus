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
<script type="text/javascript">
	function queren(number) {

	}

	function bus_info() {

		if (document.submitBus.plateNumber.value == "") {
			alert("车牌号不能为空！");
			document.submitBus.number.focus();
			return false;
		}

		var reg = /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
		if (document.submitBus.plateNumber.value.search(reg) == -1) {
			alert("车牌号格式不正确，请重新输入！");
			document.submitBus.plateNumber.focus();
			return false;
		}

	}

	function driver_onload() {

		if ("${sB}" == "plateNumberExist") {
			alert("添加失败，该车牌号已存在！");
		}

	}
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

					<li class="nav_dropdown nav_icon"><a href="/onebus/main/smartSchedule"><span
							class="ui-icon-newwin "></span>智能排班</a>
					</li>

					<li class="nav_dropdown nav_icon"><a
						href="/onebus/main/searchVideo"><span
							class="ui-icon ui-icon-video"></span>视频监控</a></li>

					<li class="nav_dropdown nav_icon"><a
						href="/onebus/main/busLineFlow"><span
							class="ui-icon ui-icon-lightbulb"></span>客流量信息</a></li>

					<li class="nav_current nav_icon"><a><span
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

				<h1 class="">信息管理</h1>

				<div id="bread_crumbs">
					<a>信息管理</a> / <a href="/onebus/main/driverInfor"
						class="current_page">车辆信息</a> / <a class="current_page">添加新车辆</a>
				</div>
				<!-- #bread_crumbs -->


			</div>
			<!-- .content_pad -->

		</div>
		<!-- #masthead -->

		<div id="content" class="xgrid">

			<div class="x9">


				<div class="accordion_container">


					<div class="block">
						<form action="/onebus/main/submitBus" method="post"
							class="form label-inline uniform" name="submitBus"
							onsubmit='return bus_info()'>

							<div class="field">
								<label for="plateNumber">车牌号 </label> <input id="plateNumber"
									name="plateNumber" size="50" type="text" class="medium"
									placeholder="请输入车牌号" />
							</div>

							<div class="field">
								<label for="lineNumber">公交线路 </label> <select name="lineNumber">
									<c:forEach var="busLine" items="${busLines}">
										<!-- <td>${bus.getPlateNumber()}</td> -->
										<option value='${busLine.getLineNumber()}'>${busLine.getLineNumber()}</option>
									</c:forEach>
								</select>

							</div>



							<div class="field">
								<label for="busType">车辆类型 </label> <select name="busType">
									<option value='大型空调车'>大型空调车</option>
									<option value='中型空调车'>中型空调车</option>
									<option value='小型空调车'>小型空调车</option>
									<option value='大型车'>大型车</option>
									<option value='中型车'>中型车</option>
									<option value='小型车'>小型车</option>
								</select>
							</div>

							<br />

							<div class="buttonrow">
								<button type="submit" class="btn btn-orange">添加</button>

							</div>

						</form>

					</div>
					<div class="buttonrow">
						<a href="/onebus/main/busInfor"><button class="btn btn-orange">取消</button></a>
					</div>


				</div>
				<!-- .accordion_container -->

			</div>
			<!-- .x9 -->

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