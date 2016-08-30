package com.zking.web.action.impl.add;

import java.io.PrintWriter;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 验证此tacount是否已存在（表单验证）
 * @author 不是胡博
 * 
 */
public class AddValidTeacherAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		PrintWriter out = null;
		try {
			boolean isExist = false;
			String tacount = request.getParameter("tacount");
			Teacher teacher = ServiceFactory.getTeacherService()
					.selectByTacount(tacount);
			
			if (teacher != null) {
				isExist = true;
			}
			JSONObject obj = new JSONObject();
			obj.put("isExist", isExist);
			out = response.getWriter();
			out.print(obj.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
				out.flush();
			}
		}
		return null;
	}

}
