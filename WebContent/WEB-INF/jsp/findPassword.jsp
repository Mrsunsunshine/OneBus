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
<link rel="stylesheet" href="../css/login.css" type="text/css"
	media="screen" title="no title" />

 <!-- #图标 --> 
<link rel="shortcut icon" href="../images/bus.png" type="image/x-icon" />


<script language="javascript">
	function login_info()
	{
		if(document.loginInformation.username.value=="")
 		{
 			alert("用户名不能为空！");
 			document.loginInformation.username.focus();
			return false;
 		}

		if(document.loginInformation.email.value=="")
 		{
 			alert("邮箱地址不能为空！");
 			document.loginInformation.email.focus();
			return false;
 		}


 		var email = document.getElementById("email").value;
  		if (email != "") 
  		{
     		var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
       		if (!reg.test(email)) 
       		{
            	alert("邮箱格式不正确，请重新输入！");
            	document.loginInformation.email.focus();
            	return false;
        	}
    	}
 		
	}
	
	function find_onload() {

		if ("${find}"=="noUser") {
			alert("用户名不存在！");
		}

		if ("${find}"=="noEmail") {
			alert("邮箱不匹配！");
		}
			

	}

</script>




</head>

<body onload="find_onload()">

	<div id="login">
		<img src="../images/onebus.png" width="70%">
		<div id="login_panel">
			<form action="/onebus/main/findPasswordByEmail" method="post" accept-charset="utf-8" name="loginInformation" onsubmit='return login_info()'>
				<div class="login_fields">
					
					<div class="field">
						<label for="username"><h3>用户名</h3></label> <input type="text"
							name="username" value="" id="username" tabindex="1"
							placeholder="请输入用户名" />
					</div>
					
					<div class="field">
						<label for="email"><h3>邮箱</h3></label> <input type="text"
							name="email" value="" id="email" tabindex="1"
							placeholder="请输入邮箱" />
					</div>
				</div>
				<!-- .login_fields -->

				<div class="login_actions">
					<button type="submit" class="btn btn-orange" tabindex="3">提交</button>
					
				</div>
			</form>
		</div>
		<!-- #login_panel -->
	</div>
	<!-- #login -->

</body>

</html>