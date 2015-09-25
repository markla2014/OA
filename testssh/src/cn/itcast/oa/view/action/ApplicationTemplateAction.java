package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.activiti.engine.repository.ProcessDefinition;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.ModelDriverBaseAction;
import cn.itcast.oa.domain.ApplicationTemplate;

@Controller
@Scope("prototype")
public class ApplicationTemplateAction extends ModelDriverBaseAction<ApplicationTemplate> {
private InputStream inputStream;
private File upload;

	public File getUpload() {
	return upload;
}
public void setUpload(File upload) {
	this.upload = upload;
}
	public InputStream getInputStream() {
	return inputStream;
}
public void setInputStream(InputStream inputStream) {
	this.inputStream = inputStream;
}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String list() throws Exception{
		List<ApplicationTemplate> applicationTemplateList=applicationTemplateService.findAll();
		ActionContext.getContext().put("applicationTemplateList", applicationTemplateList);
		return "list";
	}
	public String delete() throws Exception{
		applicationTemplateService.delete(model.getId());
		return "toList";
	}
	public String addUI() throws Exception{
		//准备数据
		List<ProcessDefinition> processDefinitionList=processDefinitionService.findAlllLastestVersion();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		return "addUI";
	}
	public String editUI() throws Exception{
		return "editUI";
	}
	public String add() throws Exception{
	String basePath=ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
	 String path=basePath+"\\"+UUID	.randomUUID().toString();
	 upload.renameTo(new File(path));
	model.setPath(path);
	applicationTemplateService.save(model);
		return "toList";
	}
	public String edit() throws Exception{
		return "toList";
	}
	public String download() throws Exception{
		Long idtem=model.getId();
		ApplicationTemplate applicationTemplate=applicationTemplateService.getById(idtem);
		inputStream=new FileInputStream(applicationTemplate.getPath());
		return "download";
	}
}
