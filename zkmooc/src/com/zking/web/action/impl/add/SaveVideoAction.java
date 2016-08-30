package com.zking.web.action.impl.add;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Video;
import com.zking.web.factory.ServiceFactory;
import com.zking.web.service.IVideoService;

/**
 * 保存视频
 * @author 胡博
 *
 */
public class SaveVideoAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String cuid = request.getParameter("cuid");
		String vuid = request.getParameter("vuid");
		String vname = request.getParameter("vname");

		
		try {
			Video video = null;
			IVideoService videoService = ServiceFactory.getVideoService();
			
			if ("".equals(vuid)) {	
				//add
				video = new Video();
				vuid = UUID.randomUUID().toString().replace("-", "");
				video.setCuid(cuid);
				video.setVname(vname);
				video.setVuid(vuid);
				
				videoService.saveVideo(video);
			} else {	
				//update
				video = videoService.selectVideoByVuid(vuid);
				video.setCuid(cuid);
				video.setVname(vname);

				videoService.updateVideo(video);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("editVuid", vuid);
		
		return new ActionForward("manageVideoList.do");
	}

}
