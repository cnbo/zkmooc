package com.zking.web.action.impl.login;

import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.user.User;
import com.zking.web.factory.ServiceFactory;


/**
 * 处理用户登录
 * @author 胡博
 *
 */
public class UserLoginAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String uaccount = request.getParameter("uaccount");
		String upass = request.getParameter("upass");
		
		PrintWriter out = null;
		Boolean isLogin = true;
		try {
			User user = null;
			if (uaccount.contains("@")) {
				user = ServiceFactory.getUserService().selectUserByUmail(uaccount);
			} else if (uaccount.matches("\\d{11}")) {
				user = ServiceFactory.getUserService().selectUserByUphone(uaccount);
			} else {
				user = ServiceFactory.getUserService().selectUserByUnickName(uaccount);
			}
			
			if (user != null && user.getUpass().equals(upass)) {
				if(user.getUimage() == null){
					user.setUimage("/zkmooc/resourse/user/default/uimage.jpg");
				}
				request.getSession().setAttribute("user", user);
			} else{
				isLogin = false;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("isLogin", isLogin);
			out = response.getWriter();
			out.print(obj.toString());
		} catch(Exception e) {
			out.print("error");
			e.printStackTrace();
		}
		
		if (out != null) {
			out.flush();
			out.close();
		}
		
		return null;
	}

}
