package com.zking.web.action.impl.other;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;

public class LogoutAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String key = request.getParameter("key");
		String location = null;
		if ("admin".equals(key)) {
			location = "/zkmooc/admin";
		} else if ("teacher".equals(key)) {
			location = "/zkmooc/teacher";
		} else {
			location = "/zkmooc/list.do";
		}
		//location = "";
		request.getSession().removeAttribute(key);
		return new ActionForward(location, true);
	}

}
