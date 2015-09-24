package cn.itcast.oa.view.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDriverBaseAction;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;
import cn.itcast.oa.util.HqlHelper;
import cn.itcast.oa.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class ForumAction extends ModelDriverBaseAction<Forum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -974317659327175339L;
	private int viewType=0;
	private int orderBy=0;
	private boolean asc=true;
public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public void setAsc(boolean asc) {
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
	 List<Object> parameters=new ArrayList<Object>();
//	 String hql="From Topic t where t.forum=?";
//	 parameters.add(forum);
//	 if(viewType==1){
//		 hql+=" and t.type=? ";
//		 parameters.add(Topic.TYPE_BEST);
//	 }
//	 if(orderBy==0){
//			hql+= " order by (CASE t.type WHEN 2 THEN 2 ELSE 0 END) desc,t.lastUpdateTime desc";
//	 }else if(orderBy==1){
//		 hql+= " order by t.lastUpdateTime"+(asc ?" ASC":" Desc");
//	 }else if(orderBy==2){
//		 hql+=" order by t.postTime"+(asc ?" ASC":" Desc");
//	 }else if(orderBy==3){
//		 hql+=" order by t.replyCount"+(asc ?" ASC":" Desc");
//	 }
	 HqlHelper hqlHelper=new HqlHelper(Topic.class,"t");
	 hqlHelper.addWhereCondiction("t.forum=?", forum);
	 //方法链
		 hqlHelper.addWhereCondiction(viewType==1,"t.type=?",Topic.TYPE_BEST)
				.addOrder(orderBy==0,"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)
				.addOrder(orderBy==0,"t.lastUpdateTime", false)
			.addOrder(orderBy==1,"t.lastUpdateTime",asc)
				.addOrder(orderBy==2,"t.postTime",asc)
				.addOrder(orderBy==3,"t.replyCount",asc)
		        .buildPageBeanStructs(pageNum,replyServce);
//	 PageBean pageBean=replyServce.getPageBean(pageNum, hqlHelper);
//	 ActionContext.getContext().getValueStack().push(pageBean);
	 return "show";
	 
 }

}
