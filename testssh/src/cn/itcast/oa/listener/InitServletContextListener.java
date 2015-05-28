package cn.itcast.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;

public class InitServletContextListener implements ServletContextListener {
 private Logger logger;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application=sce.getServletContext();
		//选择当前容器，工具类
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(application);
		/**
		 * 没有加入容器对象
		 * 所以加入容器
		 */
		PrivilegeService privilegeService=(PrivilegeService)ac.getBean("privilegeServiceImpl");
		List<Privilege> topPrivilegeList=privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		//logger.info("已经准备好初始菜单");
		System.out.println("准备好了");
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
