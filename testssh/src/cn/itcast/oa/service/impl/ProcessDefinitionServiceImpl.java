package cn.itcast.oa.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.service.ProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private ProcessEngine processEngine;

	@Override
	public List<ProcessDefinition> findAlllLastestVersion() {
		
		List<ProcessDefinition> all=processEngine.getRepositoryService()
				.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc().list();
		Map<String,ProcessDefinition> map=new HashMap<String,ProcessDefinition>();
		for(ProcessDefinition pd:all){
			map.put(pd.getKey(), pd);
		}
		return new ArrayList<ProcessDefinition>(map.values());
	}

	@Override
	public void deleteByKey(String key) {
	
		List<ProcessDefinition> list=processEngine.getRepositoryService()
				.createProcessDefinitionQuery().processDefinitionKey(key).list();
       for(ProcessDefinition pd:list){
		processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId(), true);
       }
	}

	@Override
	public void deploy(ZipInputStream zipInputStream) {
		processEngine.getRepositoryService()
		.createDeployment()
				.addZipInputStream(zipInputStream)
				.deploy();

	}

	@Override
	public InputStream getPorcessImageResourceAsStream(
			String processDefinitionId) {
		ProcessDefinition pd=processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
	 return processEngine.getRepositoryService().getResourceAsStream(pd.getDeploymentId(),pd.getDiagramResourceName());
	}

}
