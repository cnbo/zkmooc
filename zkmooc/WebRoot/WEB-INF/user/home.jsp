<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<title>${ sessionScope.user.unickName }的个人主页</title>
	<link href="${ pageContext.request.contextPath }/css/index.css" rel="stylesheet">
	<link href="${ pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/search.css">
	<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ pageContext.request.contextPath }/js/user/home.js" type="text/javascript"></script>
</head>
<body>
	<div>
		<header style="background-color: rgb(0,188,212)">
				<ul class="actions" style="margin-left: 80px">
					<li class="action action-image v-link-active">
						<a href="/zkmooc/list.do">
							<img src="${ pageContext.request.contextPath }/images/logo2.png">
						</a>
					</li>
					
				</ul>
				<ul class="actions secondary" style="margin-right: 60px">
					<li class="action action-image"><img class="avatar inline"
						src="${ sessionScope.user.uimage }"></li>
					<li class="action dropdown">
						<a href="/zkmooc/logout.do?key=user" style="color: #fff">
						登出</a>
					</li>
				</ul>
		</header>
	
		<div class="main">
			<div class="settings">
				<div class="container settings-container">
					<div class="aside hide-when-not-large">
						<h2>用户主页</h2>
						<div class="aside-title true" style="font-size: 18px;">
							<a href="#top"	>个人资料</a>
						</div>
						<div class="aside-title" style="font-size: 18px;">
							<a href="#follow">关注课程</a>
						</div>
						
					</div>
										
					<div class="profile-settings" >
						<h3>个人资料</h3>
						<form id="user-form" enctype="multipart/form-data" >
							<div class="settings-cell">
								<label class="col-xs-2 control-label" style="font-size: 15px;text-align: center;">
										个人头像
								</label>
								<img class="user-avatar"
									src="${ sessionScope.user.uimage }">
								<div class="upload action">
									仅支持 jpg、png 格式大小 5M 以内图片。<input type="file" class="upload-input">
									<div>
										<input type="file" class="inline upload-file-button" name="uimage">
									</div>
								</div>
							</div>
							<div class="settings-cell">
								<label class="col-xs-2 control-label">姓名</label>
								<input type="text"
									placeholder="填写你的姓名" name="uname" value="${ sessionScope.user.uname }"
									class="action settings-input username-input">
								
							</div>
							<div class="settings-cell">
								<label class="col-xs-2 control-label">职业</label>
								<input type="text" value="${ sessionScope.user.uprofession }"
									placeholder="填写你的职业" name="uprofession"
									class="action settings-input job-title-input">
								
							</div>
							<div class="settings-cell">
								<label class="col-xs-2 control-label">地址</label>
								<input type="text" value="${ sessionScope.user.address }"
									placeholder="填写你的地址" name="address"
									class="action settings-input company-input">
								
							</div>
							<div class="settings-cell" >	
								<div style="float: right">
									<input type="reset" class="btn btn-default" value="重置">	
									<input type="submit" class="btn btn-primary" value="提交">
								</div>
							</div>	
						</form>
					</div>
					<a name="follow"></a>	
					<div style="height: 60px"></div>
					<div class="account-settings" >
						<h3>关注课程</h3>
						<div class="video-list" id="video-item" style="display: block; min-height: 0px">
							<c:forEach items="${ requestScope.courses }" var="course">
								<a class="item" 
								style="box-shadow: 0px 0px 2px 0px rgba(0, 0, 0, 0.23), 
								0 0px 0px 0 rgba(0, 0, 0, 0);" 
								target="_blank" href="/zkmooc/play.do?cname=${ course.cname }">
									<div class="left"
										style="background-image: url(${ course.cimage }); height: 120px; width: 65px">
									</div>
									<div class="right" style="width: 150px">
										<div class="title">
											${ course.cname }
										</div>
										<div class="date">
											${ course.cinfo }
										</div>
									</div>
								 </a>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>