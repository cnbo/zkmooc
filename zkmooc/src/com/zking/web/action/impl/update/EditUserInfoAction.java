package com.zking.web.action.impl.update;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;
import com.zking.web.entity.user.User;
import com.zking.web.factory.ServiceFactory;

/**
 * 修改用户信息
 * @author 胡博
 *
 */
public class EditUserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		try {
			User user = (User) request.getSession().getAttribute("user");
			
			String uname = request.getParameter("uname");
			String uprofession = request.getParameter("uprofession");
			String address = request.getParameter("address");
			
			Part filePart = request.getPart("uimage");
			if(filePart != null){
				String path = request.getServletContext().getRealPath("/resourse/user/"+user.getUuid());
				String previousName = getFileName(filePart);
				String fileName = "uimage"+previousName.substring(previousName.lastIndexOf("."));
				
				String pathTemp = saveFile(filePart, path, fileName);
				
				pathTemp = pathTemp.replace("\\", "/");
				pathTemp = pathTemp.substring(pathTemp.indexOf("/zkmooc"));
				
				user.setUimage(pathTemp);
			}
			
			user.setUname(uname);
			user.setAddress(address);
			user.setUprofession(uprofession);
			
			ServiceFactory.getUserService().updateUser(user);
		} catch (Exception e) {
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
