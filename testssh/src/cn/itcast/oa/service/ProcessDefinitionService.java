package cn.itcast.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.ProcessDefinition;


public interface ProcessDefinitionService {


	List<ProcessDefinition> findAlllLastestVersion();

	void deleteByKey(String key);

	void deploy(ZipInputStream zipInputStream);

	InputStream getPorcessImageResourceAsStream(String processDefinitionId);

}
