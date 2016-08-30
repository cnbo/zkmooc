package com.zking.web.action.impl.add;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.user.UserCourse;
import com.zking.web.factory.ServiceFactory;

public class AddFollowCourseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String cuid = request.getParameter("cuid");
		String uuid = request.getParameter("uuid");
		
		try {
			String ucuid =
					UUID.randomUUID().toString().replace("-", "");
			UserCourse ucourse = new UserCourse();
			ucourse.setUcuid(ucuid);
			ucourse.setCuid(cuid);
			ucourse.setUuid(uuid);
			
			ServiceFactory.getUserCourseService().saveUserCourse(ucourse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
