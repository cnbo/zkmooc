package com.zking.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zking.web.entity.admin.Admin;
import com.zking.web.entity.teacher.Teacher;
/**
 * 登陆过滤器
 * @author 胡博
 *
 */
/*
 *测试中出现的问题：
 *1、若chain.doFilter(request, response);或请求转发、重定向语句后还有代码的话
 *需加上return;。否则会出现很好玩的画面。
 */
public class LoginFilter extends HttpServlet implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest =
				(HttpServletRequest) request;
		HttpSession session = hRequest.getSession(false);
		String uri = hRequest.getRequestURI();
		
		if (uri.endsWith("admin/teacher") ||
				uri.endsWith("admin/category") ||
				uri.endsWith("addTeacher.do") ||
				uri.endsWith("addCourseCategory.do") ||
				uri.endsWith("deleteTeacher.do") ||
				uri.endsWith("deleteCourseCategory.do") ||
				uri.endsWith("adminEditTeacher.do") ||
				uri.endsWith("editCourseCategory.do") ||
				uri.endsWith("adminCC.do") ||
				uri.endsWith("adminTeacher.do") ||
				uri.endsWith("searchCategory.do") ||
				uri.endsWith("searchTeacher.do")) {
			if (session != null && session.getAttribute("admin") == null) {
				((HttpServletResponse) response).sendRedirect("/zkmooc/admin/login");
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
		
		if (uri.endsWith("teacher/course") ||
				uri.endsWith("teacher/video") ||
				uri.endsWith("saveCourse.do") ||
				uri.endsWith("saveVideo.do") ||
				uri.endsWith("deleteCourse.do") ||
				uri.endsWith("deleteVideo.do") ||
				uri.endsWith("manageCourseList.do") ||
				uri.endsWith("manageVideoList.do") ||
				uri.endsWith("searchCourse.do") ||
				uri.endsWith("searchVideo.do")) {

			if (session != null && session.getAttribute("teacher") == null) {
				((HttpServletResponse) response).sendRedirect("/zkmooc/teacher/login");
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		}

		if (uri.endsWith("admin/login")) {
			if (session != null && session.getAttribute("admin") != null) {
				((HttpServletResponse) response).sendRedirect("/zkmooc/admin/teacher");
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
		
		if (uri.endsWith("teacher/login")) {
			if (session != null && session.getAttribute("teacher") != null) {
				((HttpServletResponse) response).sendRedirect("/zkmooc/teacher/course");
				return;
			} else {
				chain.doFilter(request, response);
				return;
			}
		}
		
		if (uri.endsWith("user/home") || uri.endsWith("userHome.do")) {
			if (session != null && session.getAttribute("user") != null) {
				chain.doFilter(request, response);
				return;
			} else {
				((HttpServletResponse) response).sendRedirect("/zkmooc/list.do");
				return;
			}
		}
		
		if (uri.endsWith("play.do")) {
			String cname = 
					request.getParameter("cname");
			if (cname == null) {
				request.getRequestDispatcher("list.do")
				.forward(request, response);
				return;
			}
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

}
