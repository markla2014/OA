package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Privilege;
import cn.itcast.oa.service.PrivilegeService;
@Service
public class PrivilegeServiceImpl extends BaseDaoImpl<Privilege>implements PrivilegeService {

	@Override
	public List<Privilege> findTopList() {
		
		return getSession().createQuery("from Privilege p where p.parent is NuLL").list();
	}

	@Override
	public List<String> getAllPrivileges() {
		return getSession().createQuery("select distinct p.url from Privilege p where p.url is not null ").list();
	}

}
