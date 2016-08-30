package com.zking.web.action.impl.search;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 搜索需要编辑的教师
 * @author 胡博
 *
 */
public class SearchEditTeacherAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String tacount = 
					request.getParameter("searchKey");
			Teacher teacher =
					ServiceFactory.getTeacherService()
					.selectByTacount(tacount);
			
			JSONObject obj = new JSONObject();
			obj.put("teacher", teacher);
			out = response.getWriter();
			out.print(obj.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (out != null) {
			out.flush();
			out.close();
		}
		return null;
	}

}
