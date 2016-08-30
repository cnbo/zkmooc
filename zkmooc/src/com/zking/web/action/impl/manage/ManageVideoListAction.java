package com.zking.web.action.impl.manage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
 * 教师管理其视频
 * @author Administrator
 *
 */
public class ManageVideoListAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		/*request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");*/
		PrintWriter out = null;
		String pageStr = request.getParameter("page");
		int page = Integer.parseInt(pageStr);
		int count = 9;
		
		try {
			out = response.getWriter();
			//课程集合
			List<Course> courses = 
				ServiceFactory.getCourseService().selectCourses();
			Map<String, String> courseIdNameMap = 
				ServiceFactory.getCourseService().selectCourseIdNameMap();
			
			String tacount = 
				((Teacher) request.getSession().getAttribute("teacher")).getTacount();
			Teacher teacher =
				ServiceFactory.getTeacherService().selectByTacount(tacount);
			//要显示页的视频集合
			List<Video> videos = 
				ServiceFactory.getVideoService().selectVideoByTuidPage(teacher.getTuid(), page, count);
			
			
			//视频的总数
			int videoCount = 
				ServiceFactory.getVideoService().selectVideoCount();
			//总页数
			int pages = (int) Math.ceil((double)videoCount/count);

			List<ShowVideo> showVideos = getShowVideoList(videos, courseIdNameMap);
			
			JSONObject obj = new JSONObject();
			obj.put("courses", courses);
			obj.put("pages", pages);
			obj.put("videos", showVideos);
			obj.put("page", page);
			
			String editVuid = (String)request.getAttribute("editVuid");
			if(editVuid != null){
				obj.put("editVuid", editVuid);
			}
			out.write(obj.toString());
			
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

	public List<ShowVideo> getShowVideoList(List<Video> videos, Map<String, String> courseIdNameMap) {
		List<ShowVideo> showVideos = new ArrayList<ShowVideo>();
		
		for (Video video : videos) {
			ShowVideo showVideo = new ShowVideo();
			
			showVideo.setVname(video.getVname());
			showVideo.setVuid(video.getVuid());
			showVideo.setCuid(video.getCuid());
			showVideo.setCname(courseIdNameMap.get(video.getCuid()));
			
			showVideos.add(showVideo);
		}
		
		return showVideos;
	}
}
