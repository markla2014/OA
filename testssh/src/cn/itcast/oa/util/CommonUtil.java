package cn.itcast.oa.util;

import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;

public class CommonUtil {
	 public User getCurrentUser(){
		 return (User)ActionContext.getContext().getSession().get("user");
	 }
}
