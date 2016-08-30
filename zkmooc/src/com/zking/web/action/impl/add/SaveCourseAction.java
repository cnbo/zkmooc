package com.zking.web.action.impl.add;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.course.Course;
import com.zking.web.entity.teacher.Teacher;
import com.zking.web.factory.ServiceFactory;

/**
 * 保存课程
 * @author 胡博
 *
 */
public class SaveCourseAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String ccuid = request.getParameter("ccuid");
		String cuid = request.getParameter("cuid");
		String tuid = ((Teacher) request.getSession().getAttribute("teacher")).getTuid();
		String cname = request.getParameter("cname");
		String cinfo = request.getParameter("cinfo");
		
		boolean isAdd = true;
		if("".equals(cuid)){
			isAdd = true;
		}else{
			isAdd = false;
		}
		
		String imagePath = null;
		Course course = null;		
		try {
			if(isAdd){
				//给课程生成一个全球唯一标识
				cuid = UUID.randomUUID().toString().replace("-", "");
			}
			//文件保存
			Part filePart = request.getPart("cimage");
			String previousName = getFileName(filePart);
			if(previousName.length() > 0){
				String path = request.getServletContext().getRealPath("/resourse/course/"+cuid);
				String fileName = "cimage"+previousName.substring(previousName.lastIndexOf("."));
				
				String pathTemp = saveFile(filePart, path, fileName);
				
				pathTemp = pathTemp.replace("\\", "/");
				pathTemp = pathTemp.substring(pathTemp.indexOf("/zkmooc"));
				imagePath = pathTemp;
			}
			//需验证课程名是否已存在
			if (isAdd) {
				course = new Course();
				
				course.setCuid(cuid);
				course.setCcuid(ccuid);
				course.setTuid(tuid);
				course.setCname(cname);
				course.setCinfo(cinfo);
				if(imagePath != null){
					course.setCimage(imagePath);
				}else{
					course.setCimage("/zkmooc/resourse/course/default/cimage.jpg");
				}
				
				ServiceFactory.getCourseService().saveCourse(course);
 			} else {
 				course =
 					ServiceFactory.getCourseService().selectByCuid(cuid);
 				course.setCcuid(ccuid);
 				course.setCname(cname);
 				course.setCinfo(cinfo);
 				if(imagePath != null){
 					course.setCimage(imagePath);
 				}
 				
 				ServiceFactory.getCourseService().updateCourse(course);
 			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ActionForward("manageCourseList.do");
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
