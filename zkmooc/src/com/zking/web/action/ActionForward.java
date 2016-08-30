package com.zking.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跳转
 * 若isRedirect==true则为重定向，否则为内部转发
 * @author 胡博
 *
 */
public class ActionForward {
	private String path;
	private Boolean isRedirect;
	
	/**
	 * 
	 * @param path url路径
	 */
	public ActionForward(String path) {
		this(path, false);
	}
	
	/**
	 * 
	 * @param path  url路径
	 * @param isRedirect  是否重定向
	 */
	public ActionForward(String path, Boolean isRedirect) {
		this.path = path;
		this.isRedirect = isRedirect;
	}
	
	public void forward(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (isRedirect) {
			response.sendRedirect(path);
		} else {
			request.getRequestDispatcher(path)
				.forward(request, response);
			return;
		}
		
	}
}
