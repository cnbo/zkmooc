<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.zking.web.entity.course.CourseCategory" %>
<html>
	<head>
		<title>My JSP 'add_course_category.jsp' starting page</title>

		<link href="${ pageContext.request.contextPath }/css/admin.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/zkmooc/js/admin/category.js"></script>
	</head>

	<body>
		<div>
			<jsp:include page="/WEB-INF/admin/header.jsp" flush="true"/>
		</div>
		<div id="main" class="content">
		<article class="scrollable" style="height: 523px;">
		<h2>
			课程类别
		</h2>
		<div class="paginator">
			<strong style="float: left; margin-top: 10px;" id="sum"></strong>
			<ul class="pagination" style="margin-top: 3px;" id="pageUl"></ul>
		</div>
		<div>
			<form class="form-horizontal form-inline" id="searchForm" onsubmit="search();return false;">
				<fieldset class="tab-pane active" id="site">
					<label class="col-sm-2 control-label" for="">
						搜索课程类别
					</label>
					<div class="controls">
						<input id="searchKey" class="form-control" type="text" required="">
						<button class="btn btn-success" type="submit">
							查找课程类别
						</button>
						<button class="btn btn-primary add-thread" data-toggle="modal"
							data-target="#addModal">
							添加课程类别
						</button>
						<p class="help-block"></p>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="categoryTable"></div>
		
		</div>
		<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							×
						</button>
						<h3>
							修改课程类别
						</h3>
					</div>
					<form class="form-horizontal" id="updateForm" >
						<div class="modal-body">
							<div class="form-group">
								<label class="col-xs-2 control-label">
									课程类别
								</label>
								<div class="col-xs-10">
									<input class="form-control" type="text" name="ccname" id="update_ccname"
										placeholder="请输入课程类别" value="" required="">
								</div>
								<div style="height:43px;margin: 0px 0px 0px 21.5%;" id="update_ccname_validate">
								</div>
							</div>
							<input type="hidden" name="ccuid" id="update_ccuid" value="">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" aria-hidden="true">
								取消
							</button>
							<button type="submit" class="btn btn-primary">
								修改
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							×
						</button>
						<h3>
							添加课程类别
						</h3>
					</div>
					<form class="form-horizontal" id="addForm" method="post" >
						<div class="modal-body">
							<div class="form-group">
								<label class="col-xs-2 control-label">
									课程类别
								</label>
								<div class="col-xs-10">
									<input class="form-control" type="text" name="ccname" id="add_ccname"
										placeholder="请输入课程类别" value="" required="" >
								</div>
								<div style="height:43px;margin: 0px 0px 0px 21.5%;" id="add_ccname_validate">
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" aria-hidden="true">
								取消
							</button>
							<button type="submit" class="btn btn-primary">
								添加
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
								删除课程类别
							</h3>
						</div>
						<form class="form-horizontal" id="add-new-thread"
							action="/api/threads/create.json" method="post">	
							<input type="hidden" name="ccuid" value="" id="delete_ccuid">
							<div>
								<h4>确定删除此类课程？</h4>
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
		</article>
	</body>
</html>
