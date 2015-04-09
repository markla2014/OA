package cn.itcast.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.oa.base.BaseAction;
import cn.itcast.oa.domain.Department;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department> {
 private Long parentId; 
	/** 列表 */
	public String list() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		departmentService.delete(model.getId());
		return "toList";
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		return "saveUI"; 
	}
 
	/** 添加 */
	public String add() throws Exception {
		// 1，新建对象并封装属性，也可以使用model
		// 2，保存到数据库中
		model.setParent(departmentService.getById(parentId));
		departmentService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		// 准备回显的信息
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		 long test=model.getId();
		Department department = departmentService.getById(test);
		ActionContext.getContext().getValueStack().push(department);
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		ActionContext.getContext().put("departmentList", departmentList);
		// 1，从数据库中取出原对象
		Department department = departmentService.getById(model.getId());

		// 2，设置要修改的属性
		department.setName(model.getName());
		department.setDescription(model.getDescription());
        department.setParent(departmentService.getById(parentId));
		// 3，更新到数据库中
		departmentService.update(department);

		return "toList";
	}

}
