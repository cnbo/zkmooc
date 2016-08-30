<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>${ requestScope.course.cname }</title>
		<link href="${ pageContext.request.contextPath }/css/main.css"rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/login.css"rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
		<link href="${ pageContext.request.contextPath }/css/self_bt.css" rel="stylesheet">
		<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
    	<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
    	<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/font-awesome.min.css">
  		<script src="${ pageContext.request.contextPath }/js/course/indirect-search.js"></script>
  		<script src="${ pageContext.request.contextPath }/js/course/follow.js"></script>
  
	</head>
	<body style="background-color: rgb(247, 247, 247)">

		<input type="hidden" id="uuid" value="${ user.uuid }">
		<input type="hidden" id="cuid" value="${ course.cuid }">
		<div id="indextop" class="header ">
			<jsp:include page="/WEB-INF/course/header.jsp" flush="true"/>
		</div>
		<div
			style="text-align: center; color: rgb(0, 188, 212); box-sizing: border-box; padding: 30px 16px; background-color: rgb(255, 255, 255);">
			<h1 class="rmq-106fafbb rmq-572b3dda"
				style="letter-spacing: 2px; display: inline-block; border-bottom-width: 2px; border-bottom-style: solid; border-bottom-color: rgb(255, 226, 0); padding-bottom: 1rem; margin-bottom: 2rem; font-size: 20px;">
				${ requestScope.video.vname }
			</h1>
			<div style="max-width: 1000px; margin: 0px auto;">
				<video controls="" style="width: 100%;">
				<source
					src="${ requestScope.video.vpath }"
					type="video/mp4">
				<p>
					To view this video please enable JavaScript, and consider upgrading
					to a web browser that supports HTML5 video.
				</p>
				</video>
			</div>
		</div>

		<div style="display: flex;
			max-width: 1000px; margin: 0px auto;">
			<div style="margin-top: 50px; 
				box-shadow: rgba(0, 0, 0, 0.117647) 0px 1px 6px, rgba(0, 0, 0, 0.117647) 0px 1px 4px; 
				border-radius: 2px; background-color: rgb(255, 255, 255); width: 480px; height: 350px">
				<span id="follow-teacher" class="icon-heart-empty" style="font-size: 18px; 
					float: right; padding-top: 8px; padding-right: 8px; 
					color: rgb(0, 188, 212);"
					aria-hidden="true">&nbsp;&nbsp;关注</span> 
				<img src="/zkmooc/images/M1.jpg"
					style="display: block; width: 80px; height: 80px; margin: 30px auto 20px;">
				<div
					style="font-size: 20px; color: rgb(0, 0, 0); text-align: center;">
					教师简介
				</div>
				<div style="padding: 16px; margin-bottom: 30px;">
					${ teacher.tname }
					${ teacher.tinfo }
				</div>
			</div>
			<div style="margin-top: 50px; 
				box-shadow: rgba(0, 0, 0, 0.117647) 0px 1px 6px, rgba(0, 0, 0, 0.117647) 0px 1px 4px; 
				border-radius: 2px; background-color: rgb(255, 255, 255); width: 480px; margin-left: 40px;">
				<span id="follow-course" class="icon-heart-empty" style="font-size: 18px; 
					float: right; padding-top: 8px; padding-right: 8px; 
					color: rgb(0, 188, 212);"
					aria-hidden="true" onclick="toggleFollow()">&nbsp;&nbsp;关注</span> 
				<img src="${ course.cimage }"
					style="display: block; width: 80px; height: 80px; margin: 30px auto 20px;">
				<div
					style="font-size: 20px; color: rgb(0, 0, 0); text-align: center;">
					课程简介
				</div>
				<div style="padding: 16px; margin-bottom: 30px;">
					${ course.cinfo }	
				</div>
			</div>
		</div>

		<div class="rmq-705b3f38"
			style="margin-top: 50px; padding-top: 30px; padding-bottom: 30px; background-color: rgb(255, 255, 255);">
			<div
				style="box-shadow: rgba(0, 0, 0, 0.117647) 0px 1px 6px, rgba(0, 0, 0, 0.117647) 0px 1px 4px; border-radius: 2px; max-width: 1000px; margin: 0px auto;">
				<h3
					style="color: rgb(255, 255, 255); text-align: center; padding: 30px 0px; font-size: 25px; margin-top: 0px; margin-bottom: 0px; background: rgb(0, 188, 212);">
					课程内容
				</h3>
				<div style="padding: 16px 16px 30px;">
					<div style="padding: 8px 0px;">
						<c:forEach items="${ requestScope.vnames }" var="thevname">
							<div>
								<div
									style="color: rgba(0, 0, 0, 0.6); display: block; font-size: 16px; line-height: 16px; position: relative; transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms; margin-left: 0px; padding: 16px 16px 16px 72px;">
									<svg viewBox="0 0 24 24"
										style="display: block; fill: rgb(117, 117, 117); 
										height: 24px; width: 24px; 
										transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;
										 position: absolute; top: 0px; margin: 12px; color: rgb(117, 117, 117);
										  left: 4px; -webkit-user-select: none;">
									<path d="M2.01 21L23 12 2.01 3 2 10l15 2-15 2z"></path>
									</svg>
									<a href="play.do?cname=${ course.cname }&vname=${ thevname }" style="color: #428bca;">
										${ thevname }
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
    
	</body>
</html>
