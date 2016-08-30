<%@ page language="java" import="java.util.*" 
contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		课程列表 - Zking Mooc
	</title>
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/search.css">
	<link href="${ pageContext.request.contextPath }/css/main.css" rel="stylesheet">
	<link href="${ pageContext.request.contextPath }/css/login.css"rel="stylesheet">
	<link href="${ pageContext.request.contextPath }/css/admin_header.css" rel="stylesheet">
	<link href="${ pageContext.request.contextPath }/css/signin.css" rel="stylesheet">
	<link href="${ pageContext.request.contextPath }/css/course_list.css" rel="stylesheet">
	<link href="${ pageContext.request.contextPath }/css/self_bt.css" rel="stylesheet">
	<script src="${ pageContext.request.contextPath }/js/jquery-2.0.0.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script src="${ pageContext.request.contextPath }/js/course/indirect-search.js"></script>

	<style type="text/css">
		.theactive{
		    background: #00BCD4;
    		color: #ffffff;
		}
	</style>
</head>

<body id="List_courseId">

	<div id="indextop" class="header ">
		<jsp:include page="/WEB-INF/course/header.jsp" flush="true"/>
	</div>

	<div id="main">
		<div class="container">
			<div class="course-content">
				<div class="course-nav-box">
					<div style="font-size: 16px; font-weight: 700;
  						color: #14191e;position: relative; margin-top: 20px;">
						<span>课程列表</span>
					</div>
					<div style="border-bottom: 1px solid #d0d6d9;">
					</div>
					<div class="course-nav-row clearfix">
						<span class="hd l">分类：</span>
						<div class="bd">
							<ul class="">
								<c:forEach items="${ requestScope.categories }" var="category">
									<c:choose>
										<c:when test="${ requestScope.ccname eq category.ccname}">
											<li class="course-nav-item on">
												<a href="list.do?ccname=${ category.ccname }">${ category.ccname }</a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="course-nav-item">
												<a href="list.do?ccname=${ category.ccname }">${ category.ccname }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>

				<div class="course-list">
					<div class="js-course-lists">
						<ul>
							<c:forEach items="${ requestScope.courses }" var="course">
								<li class="course-one">
									<a target="_blank" href="play.do?cname=${ course.cname }">
										<div class="course-list-img">
											<img width="240" height="135" src="${ course.cimage }">
										</div>
										<h5><span>${ course.cname }</span></h5>
										<div class="tips">
											<p class="text-ellipsis">${ course.cinfo }</p>
										</div>
									</a>
								</li>
							</c:forEach>
						</ul>
					</div>
					<div class="page">
						<c:choose>
							<c:when test="${ requestScope.page == 1 }">
								<span class="disabled_page">首页</span>
								<span class="disabled_page">上一页</span>
							</c:when>
							<c:otherwise>
								<a href="list.do?page=1">首页</a>
								<a href="list.do?page=${ requestScope.page - 1 }zz">上一页</a>
							</c:otherwise>
						</c:choose>
						
						<c:forEach begin="0" end="6" step="1" varStatus="status">
							<c:choose>
								<c:when test="${ page ==  requestScope.page + status.index - 3}">
									<a class="active" href="list.do?page=${ requestScope.page + status.index - 3 }">${ requestScope.page + status.index - 3 }</a>								
								</c:when>
								<c:otherwise>
									<c:if test="${ requestScope.page + status.index - 3 > 0 && requestScope.page + status.index - 3 <= requestScope.pages }">
										<a href="list.do?page=${ requestScope.page + status.index - 3 }">${ requestScope.page + status.index - 3 }</a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:choose>
							<c:when test="${ requestScope.page >= requestScope.pages }">
								<span class="disabled_page">下一页</span>
								<span class="disabled_page">尾页</span>
							</c:when>
							<c:otherwise>
								<a href="list.do?page=${ requestScope.page + 1 }">下一页</a>
								<a href="list.do?page=${ requestScope.pages }">尾页</a>
							</c:otherwise>
						</c:choose>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	</body>
</html>

