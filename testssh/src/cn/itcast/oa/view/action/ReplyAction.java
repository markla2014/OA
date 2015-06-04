package cn.itcast.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Reply;
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{

	public String addUI() throws Exception{
		return "addUI";
	}
	public String add() throws Exception{
		return "toShow";
	}
}
