<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.zking.web.entity.teacher.Teacher" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'add_teacher.jsp' starting page</title>

		<link href="${ pageContext.request.contextPath }/css/admin.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/zkmooc/js/admin/teacher.js"></script>
	</head>

	<body>
		<div>
			<jsp:include page="/WEB-INF/admin/header.jsp" flush="true"/>
		</div>
		<div id="main" class="content">
		<article class="scrollable" style="height: 521px;">
		<h2>
			教师管理
		</h2>
		<div class="paginator">
			<strong style="float: left; margin-top: 10px;" id="sum"></strong>
			<ul class="pagination" style="margin-top: 3px;" id="pageUl"></ul>
		</div>
		<div>
			<form class="form-horizontal form-inline" id="searchForm">
				<fieldset class="tab-pane active" id="site">
					<input type="hidden" name="nonce" value="57174eacbf095">
					<label class="col-sm-2 control-label" for="">
						搜索教师
					</label>
					<div class="controls">
						<select id="search_type" class="form-control ">
							<option value="searchName">
								姓名
							</option>
							<option value="searchAcount">
								教职工编号
							</option>
						</select>
						<input id="searchKey" class="form-control" type="text" required="">
						<button class="btn btn-success" type="submit">
							查找教师
						</button>
						<button class="btn btn-primary add-thread" data-toggle="modal"
							data-target="#addModal">
							添加教师
						</button>
						<p class="help-block"></p>
					</div>
				</fieldset>
			</form>
			<div id="teacherTable">
			</div>
			<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">
								×
							</button>
							<h3>
								修改教师
							</h3>
						</div>
						<form class="form-horizontal" id="updateForm"
							action="/api/threads/create.json" method="post">
							<div class="modal-body">
								<input type="hidden" name="tuid" id="update_tuid" value="">
								<div class="form-group">
									<label class="col-xs-2 control-label">
										教师编号
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tacount" id="update_tacount" required=""
											placeholder="请输入教师职工编号" value="" disabled="disabled">
									</div>
									<div style="height:43px;margin: 0px 0px 0px 21.5%;" id="updateValidate">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										姓名
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tname" id="update_tname" required=""
											placeholder="请输入姓名" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										性别
									</label>
							      	<input type="radio" name="tsex" id="update-woman" 
								         value="女" style="margin-left: 20px"> 
								  		女
							      	<input type="radio" name="tsex" id="update-man" 
								         value="男" style="margin-left: 20px">
										男
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										年龄
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tage" id="update_tage" required=""
											placeholder="请输入年龄" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										邮箱
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tmail" id="update_tmail" required=""
											placeholder="请输入邮箱" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										手机号
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tphone" id="update_tphone" required=""
											placeholder="请输入手机号" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										教师简介
									</label>
									<div class="col-xs-10">
										<textarea required="" class="form-control" rows="3" name="tinfo" id="update_tinfo"></textarea>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" aria-hidden="true">
									取消
								</button>
								<button type="button" class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="updateSubmit()">
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
								添加教师
							</h3>
						</div>
						<form class="form-horizontal" id="addForm" method="post" >
							<div class="modal-body">
								<div class="form-group">
									<label class="col-xs-2 control-label">
										教职编号
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tacount" required="" id="add_tacount"
											placeholder="请输入教师职工编号" value="">
									</div>
									<div style="height:43px;margin: 0px 0px 0px 21.5%;" id="_registValidate">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										姓名
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tname" required="" id="add_tname"
											placeholder="请输入姓名" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										性别
									</label>
							      	<input type="radio" name="tsex" id="add-man" required=""
								         value="男" style="margin-left: 20px">
										男
							      	<input type="radio" name="tsex" id="add-woman" 
								         value="女" style="margin-left: 20px"> 
								  		女
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										年龄
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tage" required="" id="add_tage"
											placeholder="请输入年龄" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										邮箱
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tmail" required="" id="add_tmail"
											placeholder="请输入邮箱" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										手机号
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="text" name="tphone" required="" id="add_tphone"
											placeholder="请输入手机号" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										密码
									</label>
									<div class="col-xs-10">
										<input class="form-control" type="password" name="tpass" required="" id="add_tpass"
											placeholder="请输入密码" value="">
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-2 control-label">
										教师简介
									</label>
									<div class="col-xs-10">
										<textarea class="form-control" rows="3" name="tinfo" id="add_tinfo"></textarea>
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
								删除教师
							</h3>
						</div>
						<form class="form-horizontal" id="add-new-thread"
							action="/api/threads/create.json" method="post">	
							<input type="hidden" name="tuid" value="" id="delete_tuid">
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
