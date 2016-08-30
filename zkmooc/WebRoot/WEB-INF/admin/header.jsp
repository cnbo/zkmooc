<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<html>
	<head>
	</head>
	<body style="background-color: #fff">
		<header id="header" class="nav navbar-inverse">
		<div class="container-fluid">
			<div class="logo f1" style="margin-top: 12px">
				<a href="/zkmooc/admin"> <img src="${ pageContext.request.contextPath }/images/logo2.png"></a> 
			</div>
			<div class="navbar-header f1">
				<a href="/zkmooc/admin/teacher">教师</a>
				<a href="/zkmooc/admin/category">课程类别</a>
				<a href="javascript:void(0)">待定</a>
				<a href="javascript:void(0)">待定</a>
				<a href="javascript:void(0)">待定</a>
			</div>

			<ul class="nav navbar-nav pull-right">
				<li class="dropdown">
					<a href=""
							style="font-size: 16px;"
							class="dropdown-toggle" data-toggle="dropdown">
							 <img src="/zkmooc/images/M1.jpg" class="img-circle"  width="32" height="32">
						</a>
						<ul class="dropdown-menu"
							style="right: 0; left: auto; margin-right: 25px;">
							
							<li>
								<a href="javascript:void(0)">${ admin.acount }</a>
							</li>
							<li>
							<a href="/zkmooc/logout.do?key=admin">登出</a>
							</li>
						</ul>
				</li>
			</ul>
		</div>
		</header>
	</body>
</html>