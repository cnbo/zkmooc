package com.zking.web.action.impl.add;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Video;
import com.zking.web.factory.ServiceFactory;
import com.zking.web.service.IVideoService;

public class UploadVideoAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		Part filePart = request.getPart("videoFile");
		String previousName = getFileName(filePart);
		if(previousName == null){
			return null;
		}
	    
		//根据upvuid查询得到对应的cuid作为父文件夹名
		IVideoService videoService = ServiceFactory.getVideoService();
		String vuid = request.getParameter("uploadVideoID");
		
		//响应内容：最后一个edit的Video的vuid，用于前端进度条显示
		PrintWriter out = response.getWriter();
	    
	    JSONObject obj = new JSONObject();
	    obj.put("uploadVideoID", vuid);
	    out.write(obj.toString());
	    
	    out.flush();
	    out.close();
		
		String cuid = null;
		Video video = null;
		try {
			video = videoService.selectVideoByVuid(vuid);
			cuid = video.getCuid();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	    String vpath = null;
		String path = request.getServletContext().getRealPath("/resourse/course/"+cuid);
		String fileName = vuid+previousName.substring(previousName.lastIndexOf("."));
		
		String pathTemp = saveFile(filePart, path, fileName);
		
		pathTemp = pathTemp.replace("\\", "/");
		pathTemp = pathTemp.substring(pathTemp.indexOf("/zkmooc"));
		vpath = pathTemp;
		
		//保存路径
		try {
			video.setVpath(vpath);
			videoService.updateVideo(video);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	    
		return null;
	}
	
	/**
	 * 
	 * @param filePart 就是filePart
	 * @param path 文件保存的路径
	 * @param fileName 文件保存的名字
	 * @return 文件保存的绝对路径
	 */
	private String saveFile(Part filePart, String path, String fileName) throws IOException {
		
		OutputStream outStream = null;
	    InputStream filecontent = null;
	    try {
			File uFile = new File(path);

			if (!uFile.exists()) {
				uFile.mkdirs();
			}
	        outStream = new FileOutputStream(new File(path, fileName));
	        
	        filecontent = filePart.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            outStream.write(bytes, 0, read);
	        }
	        
	        return path+"\\"+fileName;
	        
	    } catch (FileNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
	        if (outStream != null) {
	            outStream.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	    }
	    
	    return null;
	}
	/**
	 * 获取上传文件的名字 
	 * @param part
	 * @return
	 */
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : partHeader.split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	            		content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}


