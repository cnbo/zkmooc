
$(function(){
	$("#_uaccount").blur(function(){
		registValidate();
	});
	$("#_upass").blur(function(){
		pswValidate();
	});
	$("#_reupass").blur(function(){
		repswValidate();
	});
	$("#_unickName").blur(function(){
		unickNameValidate();
	});
	$("#user_regist").submit(function(){
		return registSubmit();
	});
	$("#close_regist").click(function(){
		registClose();
	});
	
});	

function registSubmit(){
	if(!registValidate()){
		$("#_uaccount").focus();
		return false;
	}
	if(!pswValidate()){
		$("#_upass").focus();
		return false;
	}
	if(!repswValidate()){
		$("#_reupass").focus();
		return false;
	}
	if(!unickNameValidate()){
		$("#_unickName").focus();
		return false;
	}
	$.ajax({
   		"type":"GET",
   		"url":"regist.do?"+$("#user_regist").serialize(),		 	
   		"success":function(msg){
			alert("注册成功");
			window.location.reload();
   		},
   		"error":function(msg){}
   	});
	return false;
}
		
function registValidate(){
	var passValidate = false;
	var uaccount = $("#_uaccount").val();
	
	if(uaccount.length == 0){
		$("#_registValidate").html(
			"<div style='color:red;'>邮箱/手机号内容不能为空</div>");
 		return passValidate;
	}
			
	$.ajax({
   		type:"GET",
   		url:"regist.do?uaccount="+uaccount,	
   		async:false,
   		success:function(msg){
   			var isRegist = JSON.parse(msg).isRegist;
   			if(isRegist){
   				$("#_registValidate").html(
   					"<div style='color:red;'>该邮箱/手机号已被注册</div>");
   				passValidate =  false;
   			}else{
	  			$("#_registValidate").html(
   					"<div style='color:green;'>该邮箱/手机号可用</div>");
   				passValidate =  true;
   			}
   		},
   		error:function(msg){}
   	});
			
	return passValidate;
}
		
function pswValidate(){
	var upass = $("#_upass").val();
	if(upass.length < 6){
		$("#_pswValidate").html(
			"<div style='color:red;'>密码长度不能小于6位</div>");
		return false;
	}
	$("#_pswValidate").html(
			"<div style='color:green;'>该密码可用</div>");
	return true;
}
function repswValidate(){
	var reupass = $("#_reupass").val();
	var upass = $("#_upass").val();
	if(reupass != upass){
		$("#_repswValidate").html(
			"<div style='color:red;'>两次密码输入不一致</div>");
		return false;
	}
	$("#_repswValidate").html(
			"<div style='color:green;'>两次密码输入一致</div>");
	return true;
}
function unickNameValidate(){
	$("#_unickNameValidate").html(
			"<div style='color:green;'>该用户名可用</div>");
	return true;			
}
function registClose(){
	$("#_registValidate").html("");
	$("#_pswValidate").html("");
	$("#_repswValidate").html("");
	$("#_unickNameValidate").html("");
	
	$("#_registValidate").val("");
	$("#_pswValidate").val("");
	$("#_repswValidate").val("");
	$("#_unickNameValidate").val("");
}


