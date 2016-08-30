<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ page import="com.zking.web.entity.course.Course" %>
<html>
	<head>
		
		<link href="${ pageContext.request.contextPath }/css/admin.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/zkmooc/js/teacher/course.js"></script>
	</head>

	<body>
		<div>
			<jsp:include page="/WEB-INF/teacher/header.jsp" flush="true"/>
		</div>
		<div id="main" class="content">
			<article class="scrollable" style="height: 521px;">
			<h2>
				课程管理
			</h2>
			<div class="paginator">
				<strong style="float: left; margin-top: 10px;" id="sum"></strong>
				<ul class="pagination" style="margin-top: 3px;" id="pageUl"></ul>
			</div>
			<div>
				<form class="form-horizontal form-inline" id="searchForm">
					<fieldset class="tab-pane active" id="site">
						<label class="col-sm-2 control-label" for="">
							搜索课程
						</label>
						<div class="controls">
							<input id="searchKey" class="form-control" type="text" required="">
							<button class="btn btn-success" type="submit" >
								查找课程
							</button>
							<button class="btn btn-primary add-thread" data-toggle="modal" data-target="#editModal"
							>
								添加课程
							</button>
							<p class="help-block"></p>
						</div>
					</fieldset>
				</form>
				<div id="courseTable"></div>
			</div>
			</article>
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
									编辑课程
								</h3>
							</div>
							<form class="form-horizontal" id="editForm" enctype="multipart/form-data">
								<div class="modal-body">
									<input type="hidden" name="cuid" id="edit_cuid">
									<div class="form-group">
										<label class="col-xs-2 control-label">
											课程类别
										</label>
										<div class="col-xs-10" id="categorySelect"></div>
									</div>
									<div class="form-group">
										<label class="col-xs-2 control-label">
											课程名
										</label>
										<div class="col-xs-10">
											<input class="form-control" type="text" name="cname" id="edit_cname" required=""
												placeholder="请输入课程名" value="">
										</div>
										<div style="height:43px;margin: 0px 0px 0px 21.5%;" id="edit_cname_validate">
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-2 control-label">
											课程简介
										</label>
										<div class="col-xs-10">
											<textarea class="form-control" rows="3" name="cinfo" id="edit_cinfo"></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-xs-2 control-label">
											课程图片
										</label>
										<div class="col-xs-10">
											<input type="file" name="cimage">
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal" aria-hidden="true">
										取消
									</button>
									<button type="submit" class="btn btn-primary">
										确认
									</button>
								</div>
							</form>
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
								<input type="hidden" name="cuid" value="" id="delete_cuid">
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
	</body>
</html>
