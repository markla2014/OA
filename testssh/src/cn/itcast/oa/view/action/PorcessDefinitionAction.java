package cn.itcast.oa.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseAction;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class PorcessDefinitionAction extends BaseAction{
	private File upload;//临时文件
	private String id;
	private InputStream inputStream;
	private String key;
	
	/*
	 * frontpage transition data
	 */
	   public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
/*
 *##########################################################################333
 */
	public String list() throws Exception{
		List<ProcessDefinition> processDefinitionList=processDefinitionService.findAlllLastestVersion();
		ActionContext.getContext().put("processDefinitionList", processDefinitionList);
    	  
    	  return "list";
      }
	
      public String add() throws Exception{
    	  
		ZipInputStream zipInputStream=new ZipInputStream(new FileInputStream(upload));
    	  try{
    	  processDefinitionService.deploy(zipInputStream);
    	  }finally{
    		  zipInputStream.close();
    	  }
    	  return "toList";
      }
   

	public String addUI() throws Exception{
    	  
    	  return "addUI";
      }
      public String delete() throws Exception{
    	  processDefinitionService.deleteByKey(key);
    	  return "toList";
      }
      public String showProcessImage() throws Exception{
    
		inputStream=processDefinitionService.getPorcessImageResourceAsStream(id);
    	  return "downloadProcessImage";
      }
      
}
