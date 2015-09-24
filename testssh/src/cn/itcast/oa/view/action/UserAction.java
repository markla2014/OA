package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.base.ModelDriverBaseAction;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.util.HqlHelper;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class UserAction extends ModelDriverBaseAction<User> {

	private Long departmentId;
	private Long[] roleIds;
	private String errorMessage; 
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/** 列表 */
	public String list() throws Exception {
//		List<User> userList = userService.findAll();
//		ActionContext.getContext().put("userList", userList);
		new HqlHelper(User.class).buildPageBeanStructs(pageNum, userService);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		userService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		// 准备数据：departmentList
		// TODO 应是显示树状结构，先使用所有的部门列表代替
		if(errorMessage!=null){
			ActionContext.getContext().put("warning",errorMessage);
		}
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 1，新建对象并设置属性（也可以使用model）
		String message="";
		Department department = departmentService.getById(departmentId);
		model.setDepartment(department);
        if(userService.findByUserName(model.getLoginName())){
        	message="login name already exist";
            ActionContext.getContext().put("warning",message);	
            return "errorSaveUI";
        }else{
		List<Role> roleList = roleService.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));

		String passwdMD5 = DigestUtils.md5Hex("1234");
		model.setPassword(passwdMD5); // 默认密码为1234，应使用MD5摘要

		// 2，保存到数据库
		userService.save(model);
        }

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备数据：departmentList
		// TODO 应是显示树状结构，先使用所有的部门列表代替
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);

		// 准备数据：roleList
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);

		// 准备回显的数据
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getDepartment() != null) {
			departmentId = user.getDepartment().getId();
		}
		if (user.getRoles().size() > 0) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}

		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性
		// >> 普通属性
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setEmail(model.getEmail());
		user.setDescription(model.getDescription());
		// >> 所属部门
		Department department = departmentService.getById(departmentId);
		user.setDepartment(department);
		// >> 关联的岗位
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}

	/** 初始化密码为“1234” */
	public String initPassword() throws Exception {
		// 1，从数据库中取出原对象
		User user = userService.getById(model.getId());

		// 2，设置要修改的属性（要使用MD5摘要）
		String passwdMD5 = DigestUtils.md5Hex("1111");
		user.setPassword(passwdMD5);

		// 3，更新到数据库
		userService.update(user);

		return "toList";
	}
	/**
	 * 登陆界面
	 * @return
	 * @throws Exception
	 */
	public String loginUI() throws Exception {
      
		return "loginUI";
	}
  //登陆
	public String login() throws Exception {
		if("".compareTo(model.getPassword())!=0){
		String md5=DigestUtils.md5Hex(model.getPassword());
        User user=userService.getByloginNameAndPassword(model.getLoginName(),md5);
       
		if(user==null){
			 //国际化，配置国际文件 addFieldError("login",getText"login.error");
			addFieldError("login","login.error");
			return "loginUI";
		}else{
			ActionContext.getContext().getSession().put("user",user);
			return "toIndex";
		}
		}else{
			return "toIndex";
		}
		}
		
	//注销
	public String logout() throws Exception {
    ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	// -------

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

}
