$(function(){
	$('#headKeyWord').blur(function(){
		$('#headSearchStr').html('搜索');
		$('#headKeyWord').val('');
	});
	
	$('#headKeyWord').focus(function(){
		$('#headSearchStr').html('');
	});
	
	$('#headKeyWord').keydown(function(event){
		if (event.keyCode == 13) {
			headSearch();
		}
	});
});

function headSearch() {
	var keyword = $('#headKeyWord').val();
	if (keyword.length > 0) {
		window.location.href="userSearchCourse.do?keyword="+keyword+"&isDirect=false";
	}
}
