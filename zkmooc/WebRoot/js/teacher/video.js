var page = 1;
var uploadVideosIndex = -1;
var uploadVideos = new Array(10);	

			$(function(){
				$.ajax({
    				"type":"GET",
    				"url":"manageVideoList.do?page="+page,	 	
    				"success":function(msg){
   						refresh(msg);
						genSelect(msg);   						
    				},
    				"error":function(msg){}
    			});
				
				$("#edit_confirm").click(function(){
					editSubmit();
				});
				
				$('#edit_vname').blur(function(){
					vnameValidate();
				});
				
				$("#editModal").on("hidden.bs.modal", function(){
					addClear();
					
					$("#uploadForm" + uploadVideosIndex).hide();
				});
				
				$('#editModal').on('show.bs.modal', function () {
					uploadVideosIndex++;
					createUploadForm();
				})
				
			});
			
			function createUploadForm(){
				var uploadFormHtml = "";
				//uploadForm的ID用uploadVideosIndex（index）区别，因为此时还木有生成VUID。
				//之后再将index与生成的VUID对应联系起来
				uploadFormHtml += "<form id='uploadForm"+uploadVideosIndex+"' action='uploadVideo.do' method='post' enctype='multipart/form-data'>";
				uploadFormHtml += 	"<input id='videoFile' type='file' name='videoFile'/>";
				uploadFormHtml += "</form>";
				
				$("#uploadFormDiv").append(uploadFormHtml);
			}
			
			function requestList(){
				$.ajax({
    				"type":"GET",
    				"url":"manageVideoList.do?page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
			
			function addClear(){
				//$("#edit_cuid").val("");
  				$("#edit_vname").val("");
  				$("#edit_vname_validate").html("");
  				$("#edit_vuid").val("");
			}
			
			function vnameValidate(){
				var passValidate = false;
				var vname = $("#edit_vname").val();
				
				if($("#edit_vuid").val() && currentUpdateVname == vname){
					$("#edit_vname_validate").html("");
					return true;
				}
				
				if(!vname){
					$("#edit_vname_validate").html(
						"<div style='color:red;'>视频名不能为空</div>");
					return false;
				}
				
				$.ajax({
			   		type:"GET",
			   		url:"/zkmooc/addValidVideo.do",	
			   		data:{"vname":vname},
			   		async:false,
			   		success:function(msg){
			   			var isExist = JSON.parse(msg).isExist;
			   			if(isExist){
			   				$("#edit_vname_validate").html(
			   					"<div style='color:red;'>此课程名已存在</div>");
			   				passValidate =  false;
			   			}else{
				  			$("#edit_vname_validate").html(
			   					"<div style='color:green;'>此课程名可用</div>");
			   				passValidate =  true;
			   			}
			   		},
			   		error:function(msg){}
			   	});
						
				return passValidate;
			}
			
			function editSubmit() {
				
				if(!vnameValidate()){
					$('#edit_vname').focus();
					return;
				}
				
				$.ajax({
    				"type":"GET",
    				"url":"saveVideo.do?"+$("#editForm").serialize()+"&page="+page,		 	
    				"success":function(msg){
    					var editVuid = JSON.parse(msg).editVuid;
    					uploadVideos[uploadVideosIndex] = editVuid;
   						
    					refresh(msg);
   						
   						$("#uploadForm" + uploadVideosIndex).ajaxSubmit({
   							beforeSubmit:function(){
   								//如果局部变量editVuid不存在则放弃提交
   								return editVuid;
   							},
   							
   							uploadProgress: function(event, position, total, percentComplete) {
   						    	
   						    	$("#progress"+editVuid).width(percentComplete+"%");;
   						    	$("#progressVal"+editVuid).html("&nbsp;&nbsp;&nbsp;"+(percentComplete-1)+"%");;
   						    },
   						    success : function(msg) {
   						    	//上传成功后删除uploadVideos中的记录并刷新
   						    	var uploadVideoID = JSON.parse(msg).uploadVideoID;
   						    	
   						    	var videoIndex = 0;	//上传完成要被删除的VideoId的下标
   						    	for(var i = 0; i < uploadVideosIndex + 1; i++){ //uploadVideosIndex + 1 = uploadVideosLength
   						    		if(uploadVideos[i] == uploadVideoID){
   						    			videoIndex = i;
   						    			break;
   						    		}
   						    	}
   						    	if(videoIndex != uploadVideosIndex){
   						    		for(var i = videoIndex; i < uploadVideosIndex; i++){
   						    			uploadVideos[i] = uploadVideos[i+1];
   						    		}
   						    	}
   						    	uploadVideosIndex--;
   						    	
   						    	$("#uploadForm"+videoIndex).remove();
   						    	
   						    	requestList();
   						    },
   						    extraData : { uploadVideoID : ""+editVuid },
   						}); 
    				},
    				"error":function(msg){}
    			});
				$('#editModal').modal('hide');
			}
			
			//修改时读取修改项数据
			var currentUpdateVname;
			function updateRead(vname) {
				$.ajax({
    				"type":"GET",
    				"url":"updateReadVideo.do",		 	
    				data:{"vname":vname},
    				"success":function(msg){
   						var video = JSON.parse(msg).video;
   						$("#edit_cuid").val(video.cuid);
   						currentUpdateVname = video.vname;
  						$("#edit_vname").val(currentUpdateVname);
  						$("#edit_vuid").val(video.vuid);
    				},
    				"error":function(msg){}
    			});
			}
			
			//删除时读取删除项数据
			function deleteRead(vuid) {
				$("#delete_vuid").val(vuid);
			}
			
			function deleteSubmit() {
				$.ajax({
    				"type":"GET",
    				"url":"deleteVideo.do?vuid="+$("#delete_vuid").val()+"&page="+page,		 	
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}
			
			function refresh(msg) {
				var data = JSON.parse(msg);
				
				var videos = data.videos;
				var tableHtml = genTable(videos);
   				$("#videoTable").html(tableHtml);
				$("#sum").html("共"+videos.length+"条&nbsp;&nbsp;&nbsp;");
   				
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
			
			function genTable(videos){
	    		var tableHtml = "";
	    		tableHtml += "<table class='table table-striped thread-table'>";
	    		tableHtml += 	"<thead>";
	    		tableHtml += 		"<tr>";
	    		tableHtml += 			"<th class='thread-name'>视频名</th>";
	    		tableHtml += 			"<th class='thread-name'>所属课程</th>";
	    		tableHtml += 			"<th class='thread-actions'>操作</th>";
	    		tableHtml += 		"</tr>";
	    		tableHtml += 	"</thead>";
	    		
	    		tableHtml += 	"<tbody>";
	    		for(var i = 0; i < videos.length; i++){
	    			var video = videos[i];
		    		tableHtml += 	"<tr>";
		    		tableHtml += 		"<td class='thread-name'>"+video.vname+"</td>";
		    		tableHtml += 		"<td class='thread-name'>"+video.cname+"</td>";
		    		tableHtml += 		"<td>";
		    		
		    		var isUpload = false;
		    		for(var j = 0; j < uploadVideosIndex + 1; j++){	//uploadVideosIndex + 1 = uploadVideosLength
		    			if(uploadVideos[j] == video.vuid){
		    				isUpload = true;
		    				break;
		    			}
		    		}
		    		
		    		if(isUpload){
	    				tableHtml += "<div class='progress' style='height:8px;width:74px;margin:5px 0px 0px 0px;float: left;'>";
	    				tableHtml += 	"<div class='progress-bar' role='progressbar' aria-valuenow='60' aria-valuemin='0' aria-valuemax='100' " +
	    									"style='width: 10%;' id=progress"+video.vuid+">";
	    				tableHtml += 	"</div>";
	    				tableHtml += "</div>";
	    				tableHtml += "<p style='color: #3080C8;margin:0px 0px 0px 0px' id=progressVal"+video.vuid+"></p>"
	    					
	    			}else{
	    				tableHtml += "<a href='javascript:;' class='edit-thread' onclick='updateRead(\""+video.vname+"\")'" +
	    								"data-toggle='modal' data-target='#editModal'>编辑 </a>&nbsp;";
	    				tableHtml += "<a href='javascript:;' class='delete-thread' onclick='deleteRead(\""+video.vuid+"\")'" +
	    								"data-toggle='modal' data-target='#deleteModal'>删除</a>";
	    			}
		    		
		    		
		    		tableHtml += 		"</td>";
		    		tableHtml += 	"</tr>";
	    		}
	    		tableHtml += 	"</tbody>";
	    		tableHtml += "</table>";
	    		
	    		return tableHtml;
    		}
			
			function genSelect(msg){
				var courses = JSON.parse(msg).courses;
				var selectHtml = "";
				selectHtml += "<select name='cuid' class='form-control' id='edit_cuid'>";		
				for(var i = 0; i < courses.length; i++){
					var course = courses[i];
					selectHtml += "<option value="+course.cuid+">"+course.cname+"</option>";		
				}
				selectHtml += "</select>";	
				$("#courseSelect").html(selectHtml);
			}
			
			function search(){
				var searchKey = $("#searchKey").val();
				$.ajax({
    				"type":"GET",
    				"url":"searchVideo.do?page="+page,
    				data:{"vname":searchKey},
    				"success":function(msg){
   						refresh(msg);
    				},
    				"error":function(msg){}
    			});
			}