$(function(){
	$('#headKeyWord').blur(function(){
		if ($('#headKeyWord').val().length == 0) {
			$('#headSearchStr').html('搜索');
		}
	});
	
	$('#headKeyWord').focus(function(){
		$('#headSearchStr').html('');
		$('#inputKeyWord').val('');
		$('#searchStr').html('搜索');
	});
	
	$('#inputKeyWord').blur(function(){
		if ($('#inputKeyWord').val().length == 0) {
			$('#searchStr').html('搜索');
		}	
	});
	
	$('#inputKeyWord').focus(function(){
		$('#searchStr').html('');
		$('#headKeyWord').val('');
		$('#headSearchStr').html('搜索');
	});
	
	$('#headKeyWord').keydown(function(event){
		if (event.keyCode == 13) {
			headSearch();
		}
	});
	
	$('#inputKeyWord').keydown(function(event){
		if (event.keyCode == 13) {
			searchCourse();
		}
	});
});


function searchCourse() {
	var keyword = $('#inputKeyWord').val();
	if (keyword.length != 0) {
		$.ajax({
			type: "POST",
			url: "userSearchCourse.do",		
			data: {"keyword":keyword, "isDirect":true},
			success: function(msg){
				refresh(msg);
			},
			error: function(msg){}
		});
	}
	
}

function headSearch() {
	var keyword = $('#headKeyWord').val();
	if (keyword.length != 0) {
		$.ajax({
			type: "POST",
			url: "userSearchCourse.do",		
			data: {"keyword":keyword, "isDirect":true},
			success: function(msg){
				refresh(msg);
			},
			error: function(msg){}
		});
	}
}

function refresh(msg) {
	var data = JSON.parse(msg);
	var courses = data.courses;
	var videoListHtml = genVideoList(courses);
	$('#video-item').html(videoListHtml);
}

function genVideoList(courses) {
	var videoListHtml = "";
	for (var i = 0; i < courses.length; i++) {
		videoListHtml += "<a class='item' target='_blank' " +
							"href='play.do?cname="+courses[i].cname+"'>" +
							"<div class='left' style='height: 130px; width: 200px'>" +
								"<img src='"+courses[i].cimage+"' style='height: 130px; width: 200px'>" + 
							"</div>" +
							"<div class='right'>" +
								"<div class='title'>" + courses[i].cname + "</div>" +
								"<div class='date'>" + courses[i].cinfo + "</div>" +
							"</div>" +
						"</a>";
					
	}
	
	return videoListHtml;
}