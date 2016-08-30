package com.zking.web.action.impl.search;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.course.Video;
import com.zking.web.entity.show.ShowVideo;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 模糊搜索视频
 * @author 胡博
 *
 */
public class SearchVideoAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			String vname = request.getParameter("vname");
			String pageStr = request.getParameter("page");
			int page = 1;
			if (pageStr != null && pageStr.length() > 0) {
				page = Integer.parseInt(pageStr);
			}
			
			Teacher teacher =
					(Teacher) request.getSession().getAttribute("teacher");
			
			int count = 9;
			List<Video> videos =
				ServiceFactory.getVideoService()
				.selectVideoByTuidKeyPage(vname, teacher.getTuid(), page, count);
			int videoCount = 
				ServiceFactory.getVideoService()
				.selectCountByTuidKey(vname, teacher.getTuid());
			int pages = (int) Math.ceil((double)videoCount/count);
			
			List<ShowVideo> showVideos = new ArrayList<ShowVideo>();

			for (Video video : videos){
				Course course = 
					ServiceFactory.getCourseService().selectByCuid(video.getCuid());
				ShowVideo showVideo = getShowVideo(video, course);
				showVideos.add(showVideo);
			}
			
			JSONObject obj = new JSONObject();
			obj.put("videos", showVideos);
			obj.put("page", page);
			obj.put("pages", pages);
			out = response.getWriter();
			out.print(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			out.print("error");
		}
		
		if (out != null) {
			out.flush();
			out.close();
		}
		
		return null;
	}

	public ShowVideo getShowVideo(Video video, Course course) {
		ShowVideo showVideo = new ShowVideo();
		
		showVideo.setVname(video.getVname());
		showVideo.setVuid(video.getVuid());
		showVideo.setCuid(video.getCuid());
		showVideo.setCname(course.getCname());

		return showVideo;
	}
}
