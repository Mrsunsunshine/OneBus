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

	function manager_info() {
		
		if (document.submitManager.number.value == "") {
			alert("工号不能为空！");
			document.submitManager.number.focus();
			return false;
		}

		if (document.submitManager.username.value == "") {
			alert("用户名不能为空！");
			document.submitManager.username.focus();
			return false;
		}

		if (document.submitManager.password.value == "") {
			alert("密码不能为空！");
			document.submitManager.password.focus();
			return false;
		}

		if (document.submitManager.email.value == "") {
			alert("邮箱不能为空！");
			document.submitManager.email.focus();
			return false;
		}

		var email = document.submitManager.email.value;
		if (email != "") {
			var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			if (!reg.test(email)) {
				alert("邮箱格式不正确，请重新输入！");
				document.submitManager.email.focus();
				return false;
			}
		}
		var mobile = document.submitManager.phone.value;
		if (document.submitManager.phone.value != "") {
			var myreg = /^((1)+(3|5|8)+\d{9})$/;
			if (!myreg.test(mobile)) {
				alert("手机号码格式不正确！");
				document.submitManager.phone.focus();
				return false;
			}

		}

	}

	function addManager_onload() {
		
		if ("${sAM}" == "numberExist") {
			alert("工号已存在！");
		}

		if ("${sAM}" == "usernameExist") {
			alert("用户名已存在！");
		}
		if ("${sAM}" == "emailExist") {
			alert("邮箱已存在！");
		}
		if ("${sAM}" == "phoneExist") {
			alert("电话已存在！");
		}
		if ("${sAM}" == "success") {
			alert("用户信息修改成功！");
		}

	}
</script>



</head>

<body onload="addManager_onload()">

	<div id="wrapper">


		<div id="header">

			<div class="content_pad">
				<div style="position: absolute; top: 30px; left: 0px">

					<a ><img src="../images/onebus.png" width="60%"></a>

				</div>

				<ul id="nav">
					<li class="nav_current nav_icon"><a><span
							class="ui-icon ui-icon-home"></span>管理员信息</a></li>

					<li class="nav_dropdown nav_icon"><a href="/onebus/main/index"><span
							class="ui-icon ui-icon-person"></span>注销</a></li>
				</ul>
			</div>
			<!-- .content_pad -->

		</div>
		<!-- #header -->

		<div id="masthead">

			<div class="content_pad">

				<h1 class="">管理员信息</h1>

				<div id="bread_crumbs">
					<a href="/onebus/main/managerInfor">管理员信息</a> / <a href=""
						class="current_page">添加管理员</a>
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
						<form action="/onebus/main/submitAddManager" method="post"
							class="form label-inline uniform" name="submitManager"
							onsubmit='return manager_info()'>
							
							<div class="field">
								<label for="number">工号 </label> <input id="number"
									name="number" size="50" type="text" class="medium"
									placeholder="请输入工号" />
							</div>

							<div class="field">
								<label for="fname">用户名 </label> <input id="username"
									name="username" size="50" type="text" class="medium"
									placeholder="请输入用户名" />
							</div>

							<div class="field">
								<label for="fname">密码 </label> <input id="password"
									name="password" size="50" type="text" class="medium"
									placeholder="请输入密码" />
							</div>

							<div class="field">
								<label for="email">邮箱 </label> <input id="email" name="email"
									size="50" type="text" class="medium" placeholder="请输入邮箱" />
							</div>

							<div class="field">
								<label for="phone">电话 </label> <input id="phone" name="phone"
									size="50" type="text" class="medium" placeholder="请输入电话" />
							</div>


							<div class="field">
								<label for="description">地址</label>
								<textarea rows="7" cols="50" id="address" name="address"></textarea>
							</div>

							<br />

							<div class="buttonrow">
								<button type="submit" class="btn btn-orange">添加</button>
							</div>
							</form>
							<div class="buttonrow" align="right">
								<a href="/onebus/main/managerInfor"><button
										class="btn btn-orange">取消</button></a>
							</div>
							

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