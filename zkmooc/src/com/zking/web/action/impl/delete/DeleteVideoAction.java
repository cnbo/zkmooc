package com.zking.web.action.impl.delete;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Video;
import com.zking.web.factory.ServiceFactory;

/**
 * 珊瑚视频
 * @author 胡博
 *
 */
public class DeleteVideoAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {

		String vuid = request.getParameter("vuid");

		try {
			Video video = 
					ServiceFactory.getVideoService()
					.selectVideoByVuid(vuid);

			String vpath = video.getVpath();
			if (vpath != null) {
				vpath = request.getServletContext().getRealPath("/")+
					vpath.replaceFirst("/zkmooc/", "");
				File file = new File(vpath);
				file.delete();
			}
			
			ServiceFactory.getVideoService().deleteVideo(vuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("manageVideoList.do", false);
	}

}
