package cn.itcast.oa.base;

import javax.annotation.Resource;

import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.ProcessDefinitionService;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
     protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
     @Resource
     protected TopicService topicService;
     @Resource 
     protected ReplyService replyServce;
     @Resource
     protected ProcessDefinitionService processDefinitionService;

	protected int pageNum=1;

	 public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
