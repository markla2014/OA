package cn.itcast.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.CommonUtil;
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2305238778936089183L;
	private Long topicId;
	public String addUI() throws Exception{
        Topic topic=topicService.getById(topicId);
        ActionContext.getContext().put("topic",topic);
        
		return "addUI";
	}
	public String add() throws Exception{
  model.setTopic(topicService.getById(topicId));
		
		model.setAuthor(new CommonUtil().getCurrentUser());
		model.setPostTime(new Date());
		model.setIpAddr(ServletActionContext.getRequest().getLocalAddr());
		replyServce.save(model);
		return "toTopicShow";
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
}
