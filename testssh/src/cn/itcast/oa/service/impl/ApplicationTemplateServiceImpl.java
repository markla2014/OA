package cn.itcast.oa.service.impl;

import java.io.File;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.ApplicationTemplate;
import cn.itcast.oa.service.ApplicationTemplateService;

@Service
public class ApplicationTemplateServiceImpl extends
		BaseDaoImpl<ApplicationTemplate> implements ApplicationTemplateService {
    @Override
	public void delete(Long id){
		ApplicationTemplate applicationTemplate=getById(id);
		getSession().delete(applicationTemplate);
		File file=new File(applicationTemplate.getPath());
		if(file.exists()){
			file.delete();
		}
	}
}
