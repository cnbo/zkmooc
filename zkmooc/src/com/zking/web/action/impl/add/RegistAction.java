package com.zking.web.action.impl.add;

import java.io.PrintWriter;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.user.User;
import com.zking.web.factory.ServiceFactory;

/**
 * 处理用户注册
 * @author 胡博
 *
 */
public class RegistAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String uaccount = request.getParameter("uaccount");
		String upass = request.getParameter("upass");
		String unickName = request.getParameter("unickName");
		
		//需验证用户注册的账号是邮箱还是手机号
		//需验证此账号是否已经注册
		
		boolean isRegist = false;
		String umail = "";
		String uphone = "";
		PrintWriter out = null;
		try {
			User user = null;
			if (uaccount.contains("@")) {
				umail = uaccount;
				user = 
					ServiceFactory.getUserService()
					.selectUserByUmail(umail);
			} else {
				uphone = uaccount;
				user = 
					ServiceFactory.getUserService()
					.selectUserByUphone(uphone);
			}
			
			//若此账号还没没有注册，则注册
			if(user == null && upass != null) {
				user = new User();
				//给用户生成一个全球唯一标识
				String uuid = 
					UUID.randomUUID().toString().replace("-", "");
				user.setUuid(uuid);
				user.setUmail(umail);
				user.setUphone(uphone);
				user.setUpass(upass);
				user.setUnickName(unickName);
				ServiceFactory.getUserService().saveUser(user);
			} 
			
			if(user != null) {
				isRegist = true;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("isRegist", isRegist);
			out = response.getWriter();
			out.print(obj.toString());
		} catch(Exception e) {
			e.printStackTrace();
			out.print("error");
		}
		
		if (out != null) {
			out.flush();
			out.close();
		}
		
		return null;
	}

}
