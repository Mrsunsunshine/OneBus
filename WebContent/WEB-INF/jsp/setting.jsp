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
<link rel="stylesheet" href="../css/grid.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="../css/layout.css" type="text/css"
	media="screen" title="no title" />

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

<link rel="stylesheet" href="../css/custom.css" type="text/css"
	media="screen" title="no title">

<!-- #图标 -->
<link rel="shortcut icon" href="../images/bus.png" type="image/x-icon" />


<script language="javascript">
	function password_info() {

		if (document.passwordInformation.password.value == "") {
			alert("密码不能为空！");
			document.passwordInformation.password.focus();
			return false;
		}

		if (document.passwordInformation.password2.value == "") {
			alert("新密码不能为空！");
			document.passwordInformation.password2.focus();
			return false;
		}

		if (document.passwordInformation.password2.value == document.passwordInformation.password.value) {
			alert("新密码与原密码相同！");
			document.passwordInformation.password2.focus();
			return false;
		}

		if (document.passwordInformation.password3.value == "") {
			alert("确认密码不能为空！");
			document.passwordInformation.password3.focus();
			return false;
		}

		if (document.passwordInformation.password3.value != document.passwordInformation.password2.value) {
			alert("确认密码与新密码不一致！");
			document.passwordInformation.password3.focus();
			return false;
		}

	}

	function sp_onload() {

		if ("${sp}" == "noUser") {
			alert("用户名不存在！");
		}

		if ("${sp}" == "wrongPassword") {
			alert("密码错误！");
		}
		if ("${sp}" == "success") {
			alert("密码修改成功！");
		}

	}
</script>




</head>

<body onload="sp_onload()">

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

					<li class="nav_current nav_icon"><a><span
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

				<h1 class="">账户管理</h1>

				<div id="bread_crumbs">
					<a  >账户管理</a> / <a href="" class="current_page">修改密码</a>
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

						<form action="/onebus/main/setPassword" method="post"
							accept-charset="utf-8" class="form label-inline"
							name="passwordInformation" onsubmit='return password_info()'>

							<div class="field">
								<label for="username">用户名: </label> <input id="username"
									name="username" size="50" type="text" value="${username}"
									class="medium" readonly />

							</div>


							<div class="field">
								<label for="password">旧密码: </label> <input id="password"
									name="password" size="50" type="password" class="medium" />
							</div>

							<div class="field">
								<label for="password2">新密码: </label> <input id="password2"
									name="password2" size="50" type="password" class="medium" />
							</div>
							<div class="field">
								<label for="password3">确认新密码: </label> <input id="password3"
									name="password3" size="50" type="password" class="medium" />
							</div>

							<br />

							<div class="buttonrow">
								<button  type="submit" class="btn btn-orange">更改密码</button>
							</div>

						</form>

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