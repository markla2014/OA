package cn.itcast.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Forum;
@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum> {
	
 public String list() throws Exception{
	 return "list";
 }

 public String show() throws Exception{
	return "show";
	 
 }
}
