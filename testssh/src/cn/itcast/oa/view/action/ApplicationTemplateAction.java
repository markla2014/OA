package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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
		List<ProcessDefinition> processDefinitionList=processDefinitionService.findAlllLastestVersion();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
		// 准备回显的数据
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(applicationTemplate);
		return "addUI";
	}
	public String add() throws Exception{
		String path = saveUploadFile(upload);
	model.setPath(path);
	applicationTemplateService.save(model);
		return "toList";
	}

	public String edit() throws Exception{
		ApplicationTemplate applicationTemplate = applicationTemplateService.getById(model.getId());

		// 2，设置要修改的属性
		applicationTemplate.setName(model.getName());
		applicationTemplate.setProcessDefinitionKey(model.getProcessDefinitionKey());
		if (upload != null) { // 如果上传了文件
			// 删除老文件
			File file = new File(applicationTemplate.getPath());
			if (file.exists()) {
				file.delete();
			}

			// 使用新文件
			String path = saveUploadFile(upload);
			applicationTemplate.setPath(path);
		}

		// 3，更新到DB
		applicationTemplateService.update(applicationTemplate);
		return "toList";
	}
	public String download() throws Exception{
		Long idtem=model.getId();
		ApplicationTemplate applicationTemplate=applicationTemplateService.getById(idtem);
		inputStream=new FileInputStream(applicationTemplate.getPath());
		//下载指定默认文件名
		String fileName= URLEncoder.encode(applicationTemplate.getName(),"UTF-8");
		ActionContext.getContext().put("fileName",fileName);
		
		return "download";
	}
}
