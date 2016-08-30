package com.zking.web.action.impl.update;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Video;
import com.zking.web.factory.ServiceFactory;

/**
 * 搜索需要编辑的教师
 * @author 不是胡博
 *
 */
public class UpdateReadVideoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String vname = request.getParameter("vname");
			Video video = ServiceFactory.getVideoService()
					.selectVideoByVname(vname);
			
			JSONObject obj = new JSONObject();
			obj.put("video", video);
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
