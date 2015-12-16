$(document).ready(function() {

	checkConfirm();
	checkPassword();
});
// 验证用户名是否存在
function checkConfirm() {
	var result;
	$("#userName").blur(
			function() {
				var gradename = $(this).val();
				var changeUrl = "/ShuanghuServer/user/checkUsername?userName="
						+ gradename;
				$.get(changeUrl, function(data) {
					if ("error" == data.result) {
						$("#gradeInfo1").html(
								"<font color=\"red\">您输入的用户名存在！请重新输入！</font>");
						result = false;
					} else {
						$("#gradeInfo1").html(
								"<font color=\"green\">恭喜您，用户名可用！</font>");
						result = true;
					}
				})

			})
	return result;
}
function checkPassword() {
	var result;
	$("#comfirmpassword").blur(function() {
		var pass = $("#password").val();
		var conpass = $("#comfirmpassword").val();
		if (pass != conpass) {
			$("#gradeInfo2").html("<font color=\"red\">两次输入密码不一致！</font>");
			result = false;
		} else {
			$("#gradeInfo2").html("");
			result = true;
		}

	})
	return result;
}
function adduser() {

	var form = document.forms[0];
	form.action = "/ShuanghuServer/user/regist";
	form.method = "post";
	form.submit();
}
