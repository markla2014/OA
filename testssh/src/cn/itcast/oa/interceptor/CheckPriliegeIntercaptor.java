package cn.itcast.oa.interceptor;
import cn.itcast.oa.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class CheckPriliegeIntercaptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
//        System.out.println("之前");
//        String result=invocation.invoke();
//        System.out.println("之后");
//		return null;
	 User user=(User)ActionContext.getContext().getSession().get("user");
	 // 获取当前的url 并去掉当前应用的前缀(+actionname)
	 String namespace=invocation.getProxy().getNamespace();
	 String actionName=invocation.getProxy().getActionName();
	 String url=null;
	 if(namespace.endsWith("/")){
		 url=namespace+actionName;
	 }else{
		 url=namespace+"/"+actionName;
	 }
	 if(url.startsWith("/")){
		 url=url.substring(1);
	 }
		if(user==null){
			if(!url.startsWith("userAction_login")){
				return "loginUI";
			}else{
				//如果是正在使用登陆功能，就放行
				return invocation.invoke();
			}
		}else{
			if(user.hasPrivilegeByUrl(url)){
				return invocation.invoke();
			}else{
				return "noPrivilegeError";
			}
		}
		
	}

}
