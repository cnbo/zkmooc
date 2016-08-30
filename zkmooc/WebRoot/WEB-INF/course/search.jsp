<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
	<head>
		<title>ZKMOOC搜索</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${ pageContext.request.contextPath }/css/main.css"rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/login.css"rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/self_bt.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/search.css">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script src="${ pageContext.request.contextPath }/js/course/search.js"></script>
		
	</head>
	<body>
		<div id="indextop" class="header ">
			<jsp:include page="/WEB-INF/course/header.jsp" flush="true"/>
		</div>
		<div style="background-color: #fff; text-align: center; margin-top: 20px; ">
			
			<div id="searchDiv"
				style="font-size: 16px; line-height: 24px; width: 100%; height: 48px; 
				display: inline-block; position: relative; background-color: transparent; 
				font-family: Roboto, sans-serif; transition: height 200ms cubic-bezier(0.23, 1, 0.32, 1) 0ms; 
				max-width: 500px; mui-prepared: ;">
				<div id="searchStr"
					style="position: absolute; opacity: 1; color: #00bcd4; transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms; bottom: 12px; color: #00bcd4">
					搜索
				</div>
				<input  id="inputKeyWord" value="" 
					style="-webkit-tap-highlight-color: #00bcd4; 
					padding: 0; position: relative; width: 100%; 
					height: 100%; border: none; outline: none;
					 background-color: rgba(0, 0, 0, 0); color: #00bcd4; 
					 font: inherit; mui-prepared: ;"
					type="text">
				<div>
					<hr
						style="border-style: none none solid; border-bottom-width: 2px;
						 border-color: #00bcd4; bottom: 8px; box-sizing: content-box; 
						 margin: 0px; position: absolute; width: 100%; transform: scaleX(1); 
						 transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;">
					
				</div>
			</div>
			<a href="javascript:void(0)"
				style="width: 48px; height: 48px; display: inline-block; 
				position: relative; margin-bottom: -12px;" onclick="searchCourse()">
				<svg
					style="display:inline-block;fill:#00bcd4;
									height:24px;width:24px;user-select:none;
									transition:all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;
									position:absolute;top:24px;left:12px;mui-prepared:;"
					viewBox="0 0 24 24">
				<path
					d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"></path>
				</svg>
			</a>
		</div>
		<div class="video-list container" id="video-item" style="display: block;">
			<c:forEach items="${ courses }" var="course">
				<a class="item" target="_blank" href="play.do?cname=${ course.cname }">
					<div class="left" style="height: 130px; width: 200px">
						<img src="${ course.cimage }" style="height: 130px; width: 200px">
					</div>
					<div class="right">
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
	</body>
</html>
