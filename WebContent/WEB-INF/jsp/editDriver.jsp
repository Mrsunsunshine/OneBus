<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	function edit_Driver() {

		if (document.editDriver.number.value == "") {
			alert("工号不能为空！");
			document.editDriver.number.focus();
			return false;
		}

		if (document.editDriver.name.value == "") {
			alert("姓名不能为空！");
			document.editDriver.name.focus();
			return false;
		}

		if (document.editDriver.age.value == "") {
			alert("年龄不能为空！");
			document.editDriver.age.focus();
			return false;
		}

		var age = document.editDriver.age.value;
		if (!isNaN(age)) {
			if (age<26 || age>60) {
				alert('公交司机年龄不在要求范围内(26-60)!');
				document.editDriver.age.focus();
				return false;
			}
		} else {
			alert("年龄格式不正确,请输入数字！");
			document.editDriver.age.focus();
			return false;
		}

	}
</script>



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
					<a  >信息管理</a> / <a href="/onebus/main/driverInfor"
						class="current_page">司机信息</a> / <a   class="current_page">编辑司机</a>
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
						<form action="/onebus/main/submitEditDriver" method="post"
							class="form label-inline uniform" name="editDriver"
							onsubmit='return edit_Driver()'>

							<div class="field">
								<label for="number">工号 </label> <input id="number" name="number"
									size="50" type="text" class="medium" placeholder="请输入工号" value="${driver.getNumber()}" readonly/>
							</div>

							<div class="field">
								<label for="name">姓名 </label> <input id="name" name="name"
									size="50" type="text" class="medium" placeholder="请输姓名" value="${driver.getName()}"/>
							</div>

							<div class="field">
								<label for="age">年龄 </label> <input id="age" name="age"
									size="50" type="text" class="medium" placeholder="请输年龄" value="${driver.getAge()}"/>
							</div>


							<div class="field">
								<label for="sex">性别</label> 男<input type="radio" id="1"
									name="sex" value="男" checked="checked" /> 女<input type="radio"
									id="2" name="sex" value="女" />
							</div>
							<!-- 
							<div class="field">
								<label for="company">公司 </label> <select name="company">
									<option value='公交1公司'>公交1公司</option>
									<option value='公交2公司'>公交2公司</option>
									<option value='公交3公司'>公交3公司</option>
									<option value='公交4公司'>公交4公司</option>
									<option value='公交5公司'>公交5公司</option>
									<option value='公交6公司'>公交6公司</option>
								</select>
							</div> -->

							<br />

							<div class="buttonrow">
								<button type="submit" class="btn btn-orange">确认修改</button>
								<a href="/onebus/main/driverInfor"><button
										class="btn btn-orange">取消</button></a>
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