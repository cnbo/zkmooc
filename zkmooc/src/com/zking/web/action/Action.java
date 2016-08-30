package com.zking.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.ActionForward;

/**
 * action接口
 * @author 胡博
 *
 */
public interface Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException;
	
}
