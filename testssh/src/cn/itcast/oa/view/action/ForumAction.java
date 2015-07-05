package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -974317659327175339L;
	private int viewType=0;
	private int orderBy=0;
	private int asc=0;
public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public int getAsc() {
		return asc;
	}

	public void setAsc(int asc) {
		this.asc = asc;
	}

public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

public String list() throws Exception{
  List<Forum> forumList=forumService.findAll();
  ActionContext.getContext().put("forumList", forumList);
	 return "list";
 }

 public String show() throws Exception{
	 Forum forum=forumService.getById(model.getId());
	 ActionContext.getContext().put("forum",forum);
			 //准备数据
//	 List<Topic> topicList=topicService.findByForum(forum);
//	 ActionContext.getContext().put("topicList",topicList);
	//使用独立方法
//	 PageBean pageBean=topicService.getPageBean(pageNum,forum);
//	 ActionContext.getContext().getValueStack().push(pageBean);
	 //使用工哦方法
//	 String hql="From Topic t where t.forum=? order by (CASE t.type WHEN 2 THEN 2 ELSE 0 END) desc,t.lastUpdateTime desc";
//	 Object[] parameters=new Object[]{forum};
//	 PageBean pageBean=replyServce.getPageBean(pageNum,hql,parameters);
//	 ActionContext.getContext().getValueStack().push(pageBean);
	 String hql="From Topic t where t.forum=?";
	 		hql+= "order by (CASE t.type WHEN 2 THEN 2 ELSE 0 END) desc,t.lastUpdateTime desc";
	 Object[] parameters=new Object[]{forum,Topic.TYPE_BEST};
	 PageBean pageBean=replyServce.getPageBean(pageNum,hql,parameters);
	 ActionContext.getContext().getValueStack().push(pageBean);
	 return "show";
	 
 }
}
