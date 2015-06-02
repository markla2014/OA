package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.itcast.oa.service.DepartmentService;
import cn.itcast.oa.service.ForumService;
import cn.itcast.oa.service.PrivilegeService;
import cn.itcast.oa.service.RoleService;
import cn.itcast.oa.service.UserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	@Resource
	protected RoleService roleService;
	@Resource
	protected DepartmentService departmentService;
	@Resource
	protected UserService userService;
	@Resource
     protected PrivilegeService privilegeService;
	@Resource
	protected ForumService forumService;
     
	protected T model;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];

			// 通过反射生成model的实例
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
}
