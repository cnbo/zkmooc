<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>

<html>
  <head>
	<script type="text/javascript" src="/zkmooc/js/user/login.js"></script>

  </head>
  
  <body>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content"
					style="width: 435px; margin: 0 auto; margin-top: 120px;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" id="close_login"
							aria-hidden="true">
							×
						</button>
						<h3 style="font-weight: normal; font-size: 20px; color: #666666;">
							登录
						</h3>
					</div>
					<div
						style="height: 260px; width: 370px; margin: 0 auto; margin-top: 5px; 
						margin-bottom: 20px">
						<div id="loginFalse" style="height:8px;padding:0px 0px 10px 25px" ></div>
						<form class="form-signin" id="submit_login">
							<input type="text" id="uaccount_login" class="signin-form-control" name="uaccount" 
								placeholder="请输入邮箱或手机号" required="" >
							<div style="height:22px" id="uaccountLoginVali">
								
							</div>
							<input type="password" id="upass_login" name="upass"
								class="signin-form-control" placeholder="请输入密码" required="" style="margin-bottom: 0px;">
							<div style="height:22px" id="upassLoginVali">
								
							</div>
							<div class="checkbox">
								<label>
									<input type="checkbox" value="remember-me">
									自动登录？
								</label>
							</div>
							<button class="btn btn-lg btn-primary btn-block" type="submit">
								登录
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
