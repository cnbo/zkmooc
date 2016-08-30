	$(function(){
		$("#user-form").submit(function(){
			
			$.ajax({
				type : "POST",
				url : "editUserInfo.do",
				success : function(msg){
					window.location.reload();
				},
				data: new FormData($('#user-form')[0]),
			    processData: false,
			    contentType: false
			});
			
			
			return false;
		});
	});