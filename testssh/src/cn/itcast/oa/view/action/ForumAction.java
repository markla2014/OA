package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;

import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

 public String list() throws Exception{
  List<Forum> forumList=forumService.findAll();
  ActionContext.getContext().put("forumList", forumList);
	 return "list";
 }

 public String show() throws Exception{
	 Forum forum=forumService.getById(model.getId());
	 ActionContext.getContext().put("forum",forum);
			 //准备数据
	 List<Topic> topicList=topicService.findByForum(forum);
	 ActionContext.getContext().put("topicList",topicList);
	return "show";
	 
 }
}
