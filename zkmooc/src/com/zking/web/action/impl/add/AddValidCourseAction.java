package com.zking.web.action.impl.add;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.factory.ServiceFactory;

/**
 * 验证此cname是否已存在（表单验证）
 * @author 不是胡博
 * 
 */
public class AddValidCourseAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		PrintWriter out = null;
		try {
			boolean isExist = false;
			String cname = request.getParameter("cname");
			Course course = ServiceFactory.getCourseService()
					.selectCourseByCname(cname);
			
			if (course != null) {
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
