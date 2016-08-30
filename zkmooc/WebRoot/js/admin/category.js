			var page = 1;
			var currentUpdateCcname;
			
			function requestList(){
				$.ajax({
    				"type":"GET",
    				"url":"adminCC.do?page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
			
			$(function(){
				requestList();
				
				$('#add_ccname').blur(function(){
					ccnameValidate("add");
				});
				$('#update_ccname').blur(function(){
					ccnameValidate("update");
				});
				
				$("#addForm").submit(function(){
					addSubmit();
					return false;
				});
				
				$("#searchForm").submit(function(){
					page = 1;
					search();
					return false;
				});
				
				$("#updateForm").submit(function(){
					updateSubmit();
					return false;
				});
				
				$("#addModal").on("hidden.bs.modal", function(){
					$("#add_ccname").val("");
					$("#add_ccname_validate").html("");
				});
				$("#updateModal").on("hidden.bs.modal", function(){
					$("#update_ccname").val("");
					$("#update_ccname_validate").html("");
				});
			});
			
			function ccnameValidate(operate){
				var ccname = $("#"+operate+"_ccname").val();
				
				if(operate=="update" && currentUpdateCcname == ccname){
					$("#"+operate+"_ccname_validate").html("");
					return true;
				}
				
				if(!ccname){
					$("#"+operate+"_ccname_validate").html(
						"<div style='color:red;'>课程类别不能为空</div>");
					return false;
				}
				
				var passValidate = false;
				$.ajax({
			   		type:"POST",
			   		url:"/zkmooc/addValidCategory.do",	
			   		data: {"ccname":ccname},
			   		async:false,
			   		success:function(msg){
			   			var isExist = JSON.parse(msg).isExist;
			   			if(isExist){
			   				$("#"+operate+"_ccname_validate").html(
			   					"<div style='color:red;'>此课程类别已存在</div>");
			   				passValidate =  false;
			   			}else{
				  			$("#"+operate+"_ccname_validate").html(
			   					"<div style='color:green;'>此课程类别可用</div>");
			   				passValidate =  true;
			   			}
			   		},
			   		error:function(msg){}
			   	});
						
				return passValidate;
			}
			
			function addSubmit() {
				
				if(!ccnameValidate("add")){
					$('#add_ccname').focus();
					return;
				}
				
				$.ajax({
    				"type":"GET",
    				"url":"addCourseCategory.do?"+$("#addForm").serialize()+"&page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
				$('#addModal').modal('hide');
			}
			
			
			function updateRead(ccname) {
				
				$.ajax({
    				"type":"GET",
    				"url":"updateReadCategory.do",	
    				data: {"ccname":ccname},
    				"success":function(msg){
   						var data = JSON.parse(msg);
   						var category = data.cc;
   						currentUpdateCcname = category.ccname;
   						$("#update_ccname").val(currentUpdateCcname);
   						$("#update_ccuid").val(category.ccuid);
    				},
    				"error":function(msg){}
    			});
			}
			
			function updateSubmit() {
				
				if(!ccnameValidate("update")){
					$('#update_ccname').focus();
					return;
				}
				
				$.ajax({
    				"type":"GET",
    				"url":"editCourseCategory.do?"+$("#updateForm").serialize()+"&page="+page,	 	
    				"success":function(msg){
    					refresh(msg);
    				},
    				"error":function(msg){}
    			});
				$('#updateModal').modal('hide');
			}
			
			function deleteRead(ccuid) {
				$("#delete_ccuid").val(ccuid);
			}
			
			function deleteSubmit() {
				$.ajax({
    				"type":"GET",
    				"url":"deleteCourseCategory.do?ccuid="+$("#delete_ccuid").val()+"&page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
			
			function refresh(msg) {
				var data = JSON.parse(msg);
				
				var categories = data.categories;
				var tableHtml = genTable(categories);
   				$("#categoryTable").html(tableHtml);
   				$("#sum").html("共"+categories.length+"条&nbsp;&nbsp;&nbsp;");

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
			
			function genTable(categories){
	    		var tableHtml = "";
	    		tableHtml += "<table class='table table-striped thread-table'>";
	    		tableHtml += 	"<thead>";
	    		tableHtml += 		"<tr>";
	    		tableHtml += 			"<th>课程类别名</th>";
	    		tableHtml += 			"<th class='thread-actions'>操作</th>";
	    		tableHtml += 		"</tr>";
	    		tableHtml += 	"</thead>";
	    		
	    		tableHtml += 	"<tbody>";
	    		for(var i = 0; i < categories.length; i++){
	    			var category = categories[i];
		    		tableHtml += 	"<tr>";
		    		tableHtml += 		"<td>"+category.ccname+"</td>";
		    		tableHtml += 		"<td>";
		    		tableHtml += 			"<a href='javascript:;' class='edit-thread' onclick='updateRead(\""+category.ccname+"\")'" +
		    								"data-toggle='modal' data-target='#updateModal'>修改 </a>";
		    		tableHtml += 			"<a href='javascript:;' class='delete-thread' onclick='deleteRead(\""+category.ccuid+"\")'" +
		    								"data-toggle='modal' data-target='#deleteModal'>删除</a>";
		    		tableHtml += 		"</td>";
		    		tableHtml += 	"</tr>";
	    		}
	    		tableHtml += 	"</tbody>";
	    		tableHtml += "</table>";
	    		
	    		return tableHtml;
    		}
			
			function search(){
				var searchKey = $("#searchKey").val();
				$.ajax({
    				"type":"GET",
    				"url":"searchCategory.do?page="+page,
    				data: {"ccname":searchKey},
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
