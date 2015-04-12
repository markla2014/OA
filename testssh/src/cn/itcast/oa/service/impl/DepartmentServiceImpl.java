package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Department;
import cn.itcast.oa.service.DepartmentService;

@Service
public class DepartmentServiceImpl extends BaseDaoImpl<Department> implements DepartmentService {

	@Override
	public List findTopList() {
	 return getSession().createQuery("from Department where parent is NULL").list();
	}

	@Override
	public List findChildList(long id) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Department where id=?").setParameter(0,id).list();
	}

	 
}
