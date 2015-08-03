package com.Picloud.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.Picloud.web.model.Action;
import com.Picloud.web.model.Module;


@WebServlet(name = "MenuConfig", urlPatterns = { "/MenuConfig" }, loadOnStartup = 1)
public class MenuConfig  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public MenuConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		ServletContext application = this.getServletContext();
		List<Module> basicModules = new ArrayList<Module>();
		List<Module> personalModules = new ArrayList<Module>();
		
		basicModules.add(new Module("Index", "首页", "home", "index"));
		basicModules.add(new Module("space", "图片服务器", "link", "space/spaces"));
		basicModules.add(new Module("process", "应用中心", "th-large", "space/spaces"));
		basicModules.add(new Module("basic", "数据中心", "link", "space/spaces"));
		
		personalModules.add(new Module("user", "个人中心", "user", "user/index"));
		personalModules.add(new Module("Log", "日志", "log", "user/log"));
		
		application.setAttribute("BASIC_MODULE", basicModules);
		application.setAttribute("PERSONAL_MODULE", personalModules);
		
	}
}
