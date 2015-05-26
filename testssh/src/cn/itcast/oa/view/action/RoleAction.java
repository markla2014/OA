package cn.itcast.oa.view.action;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.domain.Role;
import cn.itcast.oa.service.RoleService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
 private Long[] privilegeIds;
 /**
  * privilegeIds get set 
  * @return
  */
	public Long[] getPrivilegeIds() {
	return privilegeIds;
}

public void setPrivilegeIds(Long[] privilegeIds) {
	this.privilegeIds = privilegeIds;
}

	/** 列表 */
	public String list() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		roleService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "addUI";
	}

	/** 添加 */
	public String add() throws Exception {
		// 得到参数，封装成对象，当使用实体做为Model时，也可以直接使用model
		// Role role = new Role();
		// role.setName(name);
		// role.setDescription(description);
		// roleService.save(role);

		// 保存到数据库中
		roleService.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Role role = roleService.getById(model.getId());
		// this.name = role.getName();
		// this.description = role.getDescription();

		ActionContext.getContext().getValueStack().push(role); // 放到栈顶
		return "editUI";
	}
	
	/** 修改 */
	public String edit() throws Exception {
		// 从数据库中取出原对象
		Role role = roleService.getById(model.getId());

		// 设置要修改的属性
		role.setName(model.getName());
		role.setDescription(model.getDescription());

		// 更新到数据库中
		roleService.update(role);

		return "toList";
	}

	// -----------------
	/** 设置权限和页面 */
	public String editPrivilegeUI() throws Exception {
		Role role=roleService.getById(model.getId());
		ActionContext.getContext().put("role",role);
		List<Privilege> privilegeList=privilegeService.findTopList();
   ActionContext.getContext().put("privilegeList",privilegeList);
  Long[] PrivilegeTemp=new Long[role.getPrivileges().size()];
   int i=0;
   for(Privilege privilege:role.getPrivileges()){
	  PrivilegeTemp[i++]=privilege.getId();
   }
   setPrivilegeIds(PrivilegeTemp);
	//	ActionContext.getContext().getValueStack().push(role); // 放到栈顶
		return "setPrivilegeUI";
	}
	
	/** 修改 */
	public String setPrivilege() throws Exception {
		// 准备会显的数据
		Role role = roleService.getById(model.getId());
       List<Privilege> privilegesList=privilegeService.getByIds(getPrivilegeIds());
		// 设置权限
		role.setPrivileges(new HashSet<Privilege>(privilegesList));
		

		// 更新到数据库中
		roleService.update(role);

		return "toList";
	}
}
