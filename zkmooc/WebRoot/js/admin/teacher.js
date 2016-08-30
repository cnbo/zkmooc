var page = 1;
function requestList(){
	$.ajax({
		"type":"GET",
		"url":"adminTeacher.do?page="+page,		 	
		"success":function(msg){
			refresh(msg);
		},
		"error":function(msg){}
	});
}

$(function(){
	requestList();
	
	$('#add_tacount').blur(function(){
		registValidate();
	});
	
	$("#addForm").submit(function(){
		addSubmit();
		return false;
	});
	
	$("#updateForm").submit(function(){
		updateSubmit();
		return false;
	});
	
	$("#searchForm").submit(function(){
		page = 1;
		search();
		return false;
	});
	
	$("#addModal").on("hidden.bs.modal", function(){
		$("#add_tuid").val("");
		$("#add_tacount").val("");
		$("#_registValidate").html("");
		$("#add_tname").val("");
		$("#add-man").prop("checked",false);
		$("#add-woman").prop("checked",false);
		$("#add_tage").val("");
		$("#add_tmail").val("");
		$("#add_tphone").val("");
		$("#add_tinfo").val("");
		$("#add_tphone").val("");
		$("#add_tpass").val("");
	});
	
});

function registValidate(){
	var passValidate = false;
	var tacount = $("#add_tacount").val();
	
	if(!tacount){
		$("#_registValidate").html(
			"<div style='color:red;'>教师编号不能为空</div>");
		return false;
	}
	
	$.ajax({
   		type:"GET",
   		url:"/zkmooc/addValidTeacher.do?tacount="+tacount,	
   		async:false,
   		success:function(msg){
   			var isExist = JSON.parse(msg).isExist;
   			if(isExist){
   				$("#_registValidate").html(
   					"<div style='color:red;'>此教师编号已存在</div>");
   				passValidate =  false;
   			}else{
	  			$("#_registValidate").html(
   					"<div style='color:green;'>此教师编号可用</div>");
   				passValidate =  true;
   			}
   		},
   		error:function(msg){}
   	});
			
	return passValidate;
}

function addSubmit() {
	
	if(!registValidate()){
		$('#add_tacount').focus();
		return;
	}
	
	$.ajax({
		"type":"GET",
		"url":"/zkmooc/addTeacher.do?"+$("#addForm").serialize()+"&page="+page,		 	
		"success":function(msg){
			refresh(msg);
		},
		"error":function(msg){}
	});
	$('#addModal').modal('hide');
}

//修改时读取修改项数据
function updateRead(tacount) {
	$.ajax({
		"type":"GET",
		"url":"searchEditTeacher.do?searchKey="+tacount,		 	
		"success":function(msg){
			var data = JSON.parse(msg);
			var teacher = data.teacher;
			$("#update_tuid").val(teacher.tuid);
			$("#update_tacount").val(teacher.tacount);
			$("#update_tname").val(teacher.tname);
			if ('男' == teacher.tsex) {
				$('#update-man').prop('checked', true);
			} else {
				$('#update-woman').prop('checked', true);
			}
			$("#update_tage").val(teacher.tage);
			$("#update_tmail").val(teacher.tmail);
			$("#update_tphone").val(teacher.tphone);
			$("#update_tinfo").val(teacher.tinfo);
		},
		"error":function(msg){}
	});
}

function updateSubmit() {
	$.ajax({
		"type":"GET",
		"url":"adminEditTeacher.do?"+$("#updateForm").serialize()+"&page="+page,	 	
		"success":function(msg){
			refresh(msg);
		},
		"error":function(msg){}
	});
	$('#updateModal').modal('hide');
}

//删除时读取删除项数据
function deleteRead(tuid) {
	$("#delete_tuid").val(tuid);
}

function deleteSubmit() {
	$.ajax({
		"type":"GET",
		"url":"deleteTeacher.do?tuid="+$("#delete_tuid").val()+"&page="+page,		 	
		"success":function(msg){
			refresh(msg);
		},
		"error":function(msg){}
	});
}

function refresh(msg) {
	var data = JSON.parse(msg);
	
	var teachers = data.teachers;
	var tableHtml = genTable(teachers);
	$("#teacherTable").html(tableHtml);
	$("#sum").html("共"+teachers.length+"条&nbsp;&nbsp;&nbsp;");

	var pages = data.pages;
	page = data.page;
	var pageUlHtml = genPageUl(pages, page);
	$("#pageUl").html(pageUlHtml);
	
	$("#searchKey").val("");
}

function turnPage(){
	var searchKey = $("#searchKey").val();
	if(searchKey == ""){
		requestList();
	}else{
		search();
	}
}

function genPageUl(pages, page){
	var pageUlHtml = "";

	var midPage = page;
	
	if(page == 1){
		pageUlHtml += 
			"<li class='disabled'><a href='javascript:;'>&laquo;</a></li>";
	} else{
		pageUlHtml += 
			"<li><a href='javascript:;' onclick='page--;turnPage()'>&laquo;</a></li>";
	}
	
	if(pages > 5){
		if(page < 3){
			midPage = 3;
		} else if(page > pages - 2){
			midPage = pages - 2;
		}
		for (var i = -2; i <= 2; i++) {
			if(midPage + i == page){
				pageUlHtml += 
					"<li class='active'><a href='javascript:;' onclick='page="+(midPage + i)+";turnPage()'>"+(midPage + i)+"</a></li>";
			} else{
				pageUlHtml += 
					"<li><a href='javascript:;' onclick='page="+(midPage + i)+";turnPage()'>"+(midPage + i)+"</a></li>";
			}
		}
	} else{
		for (var i = 1; i <= pages; i++) {
			if(i == page){
				pageUlHtml += 
					"<li class='active'><a href='javascript:;' onclick='page="+i+";turnPage()'>"+i+"</a></li>";
			} else{
				pageUlHtml += 
					"<li><a href='javascript:;' onclick='page="+i+";turnPage()'>"+i+"</a></li>";
			}
		}
	}
	
	if(page == pages || pages == 0){
		pageUlHtml += 
			"<li class='disabled'><a href='javascript:;' >&raquo;</a></li>";
	} else{
		pageUlHtml += 
			"<li><a href='javascript:;' onclick='page++;turnPage()'>&raquo;</a></li>";
	}
	
	return pageUlHtml;
}

function genTable(teachers){
	var tableHtml = "";
	tableHtml += "<table class='table table-striped thread-table'>";
	tableHtml += 	"<thead>";
	tableHtml += 		"<tr>";
	tableHtml += 			"<th class='thread-comments' >教职工编号</th>";
	tableHtml += 			"<th class='thread-datetime'>姓名</th>";
	tableHtml += 			"<th class='thread-title'>性别</th>";
	tableHtml += 			"<th class='thread-comments' >年龄</th>";
	tableHtml += 			"<th class='thread-name'>邮箱</th>";
	tableHtml += 			"<th class='thread-name'>手机号</th>";
	tableHtml += 			"<th class='thread-actions'>操作</th>";
	tableHtml += 		"</tr>";
	tableHtml += 	"</thead>";
	
	tableHtml += 	"<tbody>";
	for(var i = 0; i < teachers.length; i++){
		var teacher = teachers[i];
		tableHtml += 	"<tr>";
		tableHtml += 		"<td>"+teacher.tacount+"</td>";
		tableHtml += 		"<td>"+teacher.tname+"</td>";
		tableHtml += 		"<td>"+teacher.tsex+"</td>";
		tableHtml += 		"<td>"+teacher.tage+"</td>";
		tableHtml += 		"<td>"+teacher.tmail+"</td>";
		tableHtml += 		"<td>"+teacher.tphone+"</td>";
		tableHtml += 		"<td>";
		tableHtml += 			"<a href='javascript:;' class='edit-thread' onclick='updateRead(\""+teacher.tacount+"\")'" +
								"data-toggle='modal' data-target='#updateModal'>编辑 </a>";
		tableHtml += 			"<a href='javascript:;' class='delete-thread' onclick='deleteRead(\""+teacher.tuid+"\")'" +
								"data-toggle='modal' data-target='#deleteModal'>删除</a>";
		tableHtml += 		"</td>";
		tableHtml += 	"</tr>";
	}
	tableHtml += 	"</tbody>";
	tableHtml += "</table>";
	
	return tableHtml;
}

function search(){
	var searchType = $("#search_type").val();
	var searchKey = $("#searchKey").val();
	$.ajax({
		"type":"POST",
		"url":"searchTeacher.do?searchType="+searchType+"&page="+page,
		"data":{"searchKey":searchKey},
		"success":function(msg){
			refresh(msg);
		},
		"error":function(msg){}
	});
}







