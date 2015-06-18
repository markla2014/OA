package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic> {
private Long forumId;

	 public String show() throws Exception{
		return "show";
		 
	 }
	 public String addUI() throws Exception{
		 //准备数据
	  	 Forum forum=forumService.getById(forumId);
	  	 ActionContext.getContext().put("fourm", forum);
		 return "addUI";
	 }
	 public String add() throws Exception{

		 model.setForum(forumService.getById(forumId));
        
         
		 //>>
		 model.setAuthor(getCurrentUser());//当前登录的用户
		 model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());//
		 model.setPostTime(new Date());
		 //>>可以放到业务方法中的配置
		topicService.save(model);
		
		//  topicService.save(model);
		 return "toTopicShow";
	 }
	 protected User getCurrentUser(){
		 return (User)ActionContext.getContext().getSession().get("user");
	 }
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	 
	}