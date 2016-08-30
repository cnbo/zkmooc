<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
	<head>
		<title>manager video</title>
		<link href="${ pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/jquery-form.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/teacher/video.js"></script>
	</head>

	<body>
		<div>
			<jsp:include page="/WEB-INF/teacher/header.jsp" flush="true"/>
		</div>
		
		<div id="zzztest"></div>
		
		<div id="main" class="content">
			<article class="scrollable" style="height: 521px;">
			<h2>
				视频管理
			</h2>
			<div class="paginator">
				<strong style="float: left; margin-top: 10px;" id="sum"></strong>
				<ul class="pagination" style="margin-top: 3px;" id="pageUl"></ul>
			</div>
			<div>
				<form class="form-horizontal form-inline" onsubmit="search();return false;">
					<fieldset class="tab-pane active" id="site">
						<label class="col-sm-2 control-label" for="">
							搜索视频
						</label>
						<div class="controls">
							<input id="searchKey" class="form-control" type="text" required="">
							<button class="btn btn-success" type="submit">
								查找视频
							</button>
							<button class="btn btn-primary add-thread" data-toggle="modal" data-target="#editModal"
							>
								添加视频
							</button>
							<p class="help-block"></p>
						</div>
					</fieldset>
				</form>
				
				<div id="videoTable"></div>
				
				<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">
									×
								</button>
								<h3>
									添加视频
								</h3>
							</div>
							
							<form class="form-horizontal" id="editForm" onsubmit="return false;">
								<div class="modal-body">
									<input type="hidden" name="vuid" id="edit_vuid">
									<div class="form-group">
										<label class="col-xs-2 control-label">
											所属课程
										</label>
										<div class="col-xs-10" id="courseSelect"></div>
									</div>
									<div class="form-group">
										<label class="col-xs-2 control-label">
											视频名
										</label>
										<div class="col-xs-10">
											<input class="form-control" type="text" name="vname" id="edit_vname"
												required="" placeholder="请输入视频名" value="">
										</div>
										<div style="height:43px;margin: 0px 0px 0px 21.5%;" id="edit_vname_validate">
										</div>
									</div>
								</div>
							</form>
							<div id="uploadFormDiv" style="margin-left: 20%">
								
							</div>
							
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" aria-hidden="true">
									取消
								</button>
								<button type="button" class="btn btn-primary" id="edit_confirm">
									确认
								</button>
							</div>
							
						</div>
					</div>
				</div>
				<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">
									×
								</button>
								<h3>
									删除教师
								</h3>
							</div>
							<form class="form-horizontal" id="add-new-thread"
								action="/api/threads/create.json" method="post">	
								<input type="hidden" name="vuid" value="" id="delete_vuid">
								<div>
									<h4>确定删除此教师？</h4>
								</div>						
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal" aria-hidden="true">
										取消
									</button>
									<button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="deleteSubmit()">
										删除
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			</article>
		</div>
	</body>
</html>
