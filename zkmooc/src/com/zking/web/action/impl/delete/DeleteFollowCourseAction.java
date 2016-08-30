package com.zking.web.action.impl.delete;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.factory.ServiceFactory;

public class DeleteFollowCourseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String cuid = request.getParameter("cuid");
		String uuid = request.getParameter("uuid");
		
		//PrintWriter out = null;
		try {
			Boolean isDelete = 
					ServiceFactory.getUserCourseService()
					.deleteByCuidUuid(cuid, uuid);
			
//			out = response.getWriter();
//			JSONObject obj = new JSONObject();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		if (out != null) {
//			out.flush();
//			out.close();
//		}
		return null;
	}

}
