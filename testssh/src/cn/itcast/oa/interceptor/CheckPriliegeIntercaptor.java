package cn.itcast.oa.interceptor;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class CheckPriliegeIntercaptor extends AbstractInterceptor {

	public String intercept(ActionInvocation invocation) throws Exception {
        System.out.println("之前");
        String result=invocation.invoke();
        System.out.println("之后");
		return null;
		
	}

}
