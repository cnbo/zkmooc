var page = 1;
			
			$(function(){
				$.ajax({
    				"type":"GET",
    				"url":"manageCourseList.do?page="+page,	 	
    				"success":function(msg){
   						refresh(msg);
						genSelect(msg);   						
    				},
    				"error":function(msg){}
    			});
				
				$('#edit_cname').blur(function(){
					cnameValidate();
				});
				
				$("#editForm").submit(function(){
					editSubmit();
					return false;
				});
				
				$("#searchForm").submit(function(){
					page = 1;
					search();
					return false;
				});
				
				$("#editModal").on("hidden.bs.modal", function(){
					addClear();
				});
				
			});
			
			function requestList(){
				$.ajax({
    				"type":"GET",
    				"url":"manageCourseList.do?page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
			
			function addClear(){
				$("#edit_cuid").val("");
  				$("#edit_cname").val("");
  				$("#edit_cname_validate").html("");
 				$("#edit_cinfo").val("");
			}
			
			function cnameValidate(){
				var passValidate = false;
				var cname = $("#edit_cname").val();
				
				if($("#edit_cuid").val() && currentUpdateCname == cname){
					$("#edit_cname_validate").html("");
					return true;
				}
				
				if(!cname){
					$("#edit_cname_validate").html(
						"<div style='color:red;'>课程名不能为空</div>");
					return false;
				}
				
				$.ajax({
			   		type:"GET",
			   		url:"/zkmooc/addValidCourse.do",	
			   		data:{"cname":cname},
			   		async:false,
			   		success:function(msg){
			   			var isExist = JSON.parse(msg).isExist;
			   			if(isExist){
			   				$("#edit_cname_validate").html(
			   					"<div style='color:red;'>此课程名已存在</div>");
			   				passValidate =  false;
			   			}else{
				  			$("#edit_cname_validate").html(
			   					"<div style='color:green;'>此课程名可用</div>");
			   				passValidate =  true;
			   			}
			   		},
			   		error:function(msg){}
			   	});
						
				return passValidate;
			}
			
			function editSubmit() {
				
				if(!cnameValidate()){
					$('#edit_cname').focus();
					return;
				}
				
				$.ajax({
    				"type":"POST",
    				"url":"saveCourse.do?page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){},
    				data: new FormData($('#editForm')[0]),
    			    processData: false,
    			    contentType: false
    			});
				
				$('#editModal').modal('hide');
			}
			
			//修改时读取修改项数据
			var currentUpdateCname;
			function updateRead(cname) {
				$.ajax({
    				"type":"GET",
    				"url":"updateReadCourse.do",		
    				data:{"cname":cname},
    				"success":function(msg){
   						var course = JSON.parse(msg).course;
   						$("#edit_cuid").val(course.cuid);
   						$("#edit_ccuid").val(course.ccuid);
   						currentUpdateCname = course.cname;
   						$("#edit_cname").val(currentUpdateCname);
   						$("#edit_cinfo").val(course.cinfo);
    				},
    				"error":function(msg){}
    			});
			}
			
			//删除时读取删除项数据
			function deleteRead(cuid) {
				$("#delete_cuid").val(cuid);
			}
			
			function deleteSubmit() {
				$.ajax({
    				"type":"GET",
    				"url":"deleteCourse.do?cuid="+$("#delete_cuid").val()+"&page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
			
			function refresh(msg) {
				var data = JSON.parse(msg);
				
				var courses = data.courses;
				var tableHtml = genTable(courses);
   				$("#courseTable").html(tableHtml);
				$("#sum").html("共"+courses.length+"条&nbsp;&nbsp;&nbsp;");
   				
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
			
			function genTable(courses){
	    		var tableHtml = "";
	    		tableHtml += "<table class='table table-striped thread-table'>";
	    		tableHtml += 	"<thead>";
	    		tableHtml += 		"<tr>";
	    		tableHtml += 			"<th class='thread-title'>课程名</th>";
	    		tableHtml += 			"<th class='thread-name'>课程简介</th>";
	    		tableHtml += 			"<th class='thread-title'>所属课程类别</th>";
	    		tableHtml += 			"<th class='thread-actions'>操作</th>";
	    		tableHtml += 		"</tr>";
	    		tableHtml += 	"</thead>";
	    		
	    		tableHtml += 	"<tbody>";
	    		for(var i = 0; i < courses.length; i++){
	    			var course = courses[i];
		    		tableHtml += 	"<tr>";
		    		tableHtml += 		"<td class='thread-name'>"+course.cname+"</td>";
		    		tableHtml += 		"<td class='thread-title'>"+course.cinfo+"</td>";
		    		tableHtml += 		"<td class='thread-title'>"+course.ccname+"</td>";
		    		tableHtml += 		"<td>";
		    		tableHtml += 			"<a href='javascript:;' class='edit-thread' onclick='updateRead(\""+course.cname+"\")'" +
		    								"data-toggle='modal' data-target='#editModal'>编辑 </a>";
		    		tableHtml += 			"<a href='javascript:;' class='delete-thread' onclick='deleteRead(\""+course.cuid+"\")'" +
		    								"data-toggle='modal' data-target='#deleteModal'>删除</a>";
		    		tableHtml += 		"</td>";
		    		tableHtml += 	"</tr>";
	    		}
	    		tableHtml += 	"</tbody>";
	    		tableHtml += "</table>";
	    		
	    		return tableHtml;
    		}
			
			function genSelect(msg){
				var categories = JSON.parse(msg).categories;
				var selectHtml = "";
				selectHtml += "<select name='ccuid' class='form-control' id='edit_ccuid'>";		
				for(var i = 0; i < categories.length; i++){
					var category = categories[i];
					selectHtml += "<option value="+category.ccuid+">"+category.ccname+"</option>";		
				}
				selectHtml += "</select>";	
				$("#categorySelect").html(selectHtml);
			}
			
			function search(){
				var searchKey = $("#searchKey").val();
				$.ajax({
    				"type":"GET",
    				"url":"searchCourse.do?page="+page,
    				data:{"searchCName":searchKey},
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}