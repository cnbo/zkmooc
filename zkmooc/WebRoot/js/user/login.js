$(function(){
			$("#submit_login").submit(function(){
				loginSubmit();
				return false;
			});
			$("#close_login").click(function(){
				$("#uaccountLoginVali").html("");
				$("#upassLoginVali").html("");
				$("#loginFalse").html("");
			});
		});
		function uaccountLoginVali(){
			var uaccount = $("#uaccount_login").val();
			var regexPhone = /^1(3[0-9]|4[5|7]|5[0-3|5-9]|8[0-9]|70)\d{8}$/;
			var regexEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
			
			if(regexPhone.test(uaccount) || regexEmail.test(uaccount)){
				$("#uaccountLoginVali").html("");
				return true;
			}else{
				$("#uaccountLoginVali").html("<div style='color:red'>邮箱或手机号格式错误</div>");
				return false;
			}
		}
		function upassLoginVali(){
			var upass = $("#upass_login").val();
			if(upass.length < 6){
				$("#upassLoginVali").html("<div style='color:red'>密码长度必须大于等于6位数</div>");
				return false;
			}else if(upass.length > 15){
				$("#upassLoginVali").html("<div style='color:red'>密码长度必须小于等于15位数</div>");
				return false;
			}else{
				$("#upassLoginVali").html("");
				return true;
			}
		}
		
		function loginSubmit(){
			if(!uaccountLoginVali()){
				$("#uaccount_login").focus();
				return;
			}
			if(!upassLoginVali()){
				$("#upass_login").focus();
				return;
			}
			
			$.ajax({
   				"type":"GET",
   				"url":"userLogin.do?"+$("#submit_login").serialize(),		 	
   				"success":function(msg){
   					var isLogin = JSON.parse(msg).isLogin;
   					if(isLogin == true){
 						window.location.href = window.location.href;
   					} else if (isLogin == false) {
   						$("#loginFalse").html("<div style='color: red;font-size:15px'>账号或密码错误</div>");
   					}
   					
   				},
   				"error":function(msg){}
   			});
		}