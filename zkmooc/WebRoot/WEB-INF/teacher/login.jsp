<%@ page language="java" import="java.util.*" 
contentType="text/html;charset=UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>教师登录</title>
		<link href="${ pageContext.request.contextPath }/css/login.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/self_bt.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<style type="text/css">
			.login-content {
				position: relative;
				background-color: #fff;
				border: 1px solid #fff;
				border-radius: 6px;
				background-clip: padding-box;
				outline: none
			}
			.login-modal-header {
				min-height: 16.42857143px;
				text-align: center;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				
				
				
			});
			
			function loginSubmit(){
				$.ajax({
    				"type":"GET",
    				"url":"/zkmooc/teacherLogin.do?"+$("#teacher_login").serialize(),		 	
    				"success":function(msg){
    					var isLogin = JSON.parse(msg).isLogin;
    					if(isLogin == true){
   							window.location.href='/zkmooc/teacher/course';
    					} else if (isLogin == false) {
    						alert("账号或密码错误！");
    					}
    					
    				},
    				"error":function(msg){}
    			});
				
				return false;
			}
			
		</script>
	</head>

	<body style="background: url(${ pageContext.request.contextPath }/images/zlogin.jpg)">
		<div class="login-content"
					style="width: 400px; height: 350px;  margin-left: 150px; margin-top: 160px; padding: 10px">
			<div class="login-modal-header" style="margin-top: 30px">
				<h3 style="font-weight: normal; font-size: 26px; color: #666666;">
				教师登录
				</h3>
			</div>
			<div>
				<form class="form-signin" onsubmit="return loginSubmit()" id="teacher_login">
					<input type="text" id="tacount" class="signin-form-control" name="tacount"
						placeholder="请输入教师账号" required="" autofocus="">
					<div style="height: 30px">
					</div>
					<input type="password" id="tpass" name="tpass"
						class="signin-form-control" placeholder="请输入教师密码" required="">
					<div class="checkbox">
						<label  style="font-weight: normal; font-size: 14px; color: #666666;">
							<input type="checkbox" value="remember-me" >
							记住密码
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">
						登录
					</button>
				</form>
			</div>
		</div>
	</body>
</html>