var isFollow = false;
$(function(){
	if ($('#uuid').val() != '') {
		request();
	} else {
		$('#follow-course').mouseover(toggle);
		$('#follow-course').mouseout(toggle);
	}
});

//后台确认此课程是否被关注，若被关注
function request() {
	$.ajax({
		type: "POST",
		url: "checkFollowCourse.do?uuid="+$('#uuid').val()+"&cuid="+$('#cuid').val(),
		success: function(msg) {
			var isExist = JSON.parse(msg).isExist;
			if (isExist == true) {
				isFollow = true;
				toggle();
				$('#follow-course').html('&nbsp;&nbsp;已关注');
			} else {
				$('#follow-course').mouseover(toggle);
				$('#follow-course').mouseout(toggle);
			}
		},
		error: function(msg) {
			
		}
	});
}

function toggle() {
	$('#follow-course').toggleClass('icon-heart-empty');
	$('#follow-course').toggleClass('icon-heart');
}

function toggleFollow() {
	if ($('#uuid').val() == '') {
		alert('亲，您木有登录哦！>_<');
	} else if (isFollow == true) {
		deleteFollow();
		isFollow = false;
		$('#follow-course').html('&nbsp;&nbsp;关注');
		$('#follow-course').mouseover(toggle);
		$('#follow-course').mouseout(toggle);
	} else {
		addFollow();
		isFollow = true;
		$('#follow-course').html('&nbsp;&nbsp;已关注');
		$('#follow-course').unbind('mouseover');
		$('#follow-course').unbind('mouseout');
	}
	
}

function addFollow() {
	$.ajax({
		type: "POST",
		url: "addFollowCourse.do?uuid="+$('#uuid').val()+"&cuid="+$('#cuid').val(),
		success: function(msg) {
			
		},
		error: function(msg) {
			
		}
	});
}

function deleteFollow() {
	$.ajax({
		type: "POST",
		url: "deleteFollowCourse.do?uuid="+$('#uuid').val()+"&cuid="+$('#cuid').val(),
		success: function(msg) {
			
		},
		error: function(msg) {
			
		}
	});
}
