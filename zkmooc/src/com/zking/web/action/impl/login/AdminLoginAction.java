package com.zking.web.action.impl.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.admin.Admin;
import com.zking.web.factory.ServiceFactory;

/**
 * 处理管理员登录
 * @author 胡博
 *
 */
public class AdminLoginAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String aacount =request.getParameter("aacount");
		String apass = request.getParameter("apass");
		
		PrintWriter out = null;

		Boolean isLogin = true;
		try {
			Admin admin = ServiceFactory.getAdminService().getAdmin(aacount);
			
			if (admin != null && admin.getApass().equals(apass)) {
				request.getSession().setAttribute("admin", admin);
			} else {
				isLogin = false;
			}
			
			JSONObject obj = new JSONObject();
			out = response.getWriter();
			obj.put("isLogin", isLogin);
			out.print(obj.toString());
		} catch(Exception e) {
			out.print("error");
		}
		
		if (out != null) {
			out.flush();
			out.close();
		}
		return null;
	}

}
