<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>1号公交</title>

<link rel="stylesheet" href="css/reset.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="css/text.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="css/form.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="css/buttons.css" type="text/css"
	media="screen" title="no title" />
<link rel="stylesheet" href="css/login.css" type="text/css"
	media="screen" title="no title" />

<!-- #图标 -->
<link rel="shortcut icon" href="images/bus.png" type="image/x-icon" />


<script language="javascript">
	function login_info() {

		if (document.loginInformation.username.value == "") {
			alert("用户名不能为空！");
			document.loginInformation.username.focus();
			return false;
		}

		if (document.loginInformation.password.value == "") {
			alert("密码不能为空！");
			document.loginInformation.password.focus();
			return false;
		}
	}
	
	
	function window_onload() {

		if ("${login}"=="noUser") {
			alert("用户名不存在！");
		}

		if ("${login}"=="wrongPassword") {
			alert("密码错误！");
		}
			

	}
</script>




</head>

<body onload="window_onload()">


	<div id="login">
		<img src="images/onebus.png" width="70%">
		<div id="login_panel">
			<form action="/onebus/main/checkManager" method="post"
				accept-charset="utf-8" name="loginInformation"
				onsubmit='return login_info()'>
				<div class="login_fields">
					<div class="field">
						<label for="username"><h3>用户名</h3></label> <input type="text"
							name="username" value="" id="username" tabindex="1"
							placeholder="请输入用户名" />
					</div>

					<div class="field">
						<label for="password"><h3>密码</h3> <small><a
								href="/onebus/main/findPassword">忘记密码？</a></small></label> <input
							type="password" name="password" value="" id="password"
							tabindex="2" placeholder="请输入密码" />
					</div>
				</div>
				<!-- .login_fields -->

				<div class="login_actions">
					<button type="submit" class="btn btn-orange" tabindex="3">登录</button>

				</div>
			</form>
		</div>
		<!-- #login_panel -->
	</div>
	<!-- #login -->

</body>

</html>