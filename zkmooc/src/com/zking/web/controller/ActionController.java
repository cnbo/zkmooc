package com.zking.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zking.web.action.Action;
import com.zking.web.action.ActionForward;

/**
 * zkmooc的前端控制器
 * @author 胡博
 *
 */
@MultipartConfig
public class ActionController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html; charset=utf-8");
		//得到请求
		String uri = processUri(request);
		//得到action实例
		Action action = processActionCreate(uri);
		//处理action实例，得到actionforward实例
		ActionForward af = processAction(action, request, response);
		//执行跳转
		processActionJump(af, request, response);
	}
	
	private void processActionJump(ActionForward af, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (af == null) {
			return;
		}
		af.forward(request, response);
	}
	
	private ActionForward processAction(Action action, HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		return action.execute(request, response);
	}

	/**
	 * 创建action
	 * @param uri
	 * @return 对应的action
	 */
	private Action processActionCreate(String uri) {
		Action action = null;
		Properties config = (Properties) this.getServletContext().getAttribute("config");
		String className = config.getProperty(uri);
		try {
			action = (Action) Class.forName(className).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return action;
	}

	private String processUri(HttpServletRequest request) {
		String uri = null;
		uri = request.getRequestURI();
		int a = uri.lastIndexOf("/");
		int b = uri.lastIndexOf(".");
		uri = uri.substring(a+1, b);
		return uri;
	}

	@Override
	public void init() throws ServletException {
		Properties config = new Properties();
		InputStream in =
			ActionController.class.getClassLoader()
			.getResourceAsStream("config.properties");
		try {
			//加载配置文件
			config.load(in);
			this.getServletContext().setAttribute("config", config);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
