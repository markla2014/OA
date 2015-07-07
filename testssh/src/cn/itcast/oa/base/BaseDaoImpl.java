package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.util.HqlHelper;
import cn.itcast.oa.util.PageBean;

// @Transactional注解可以被继承，即对子类也有效
@Transactional
@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	protected Class<T> clazz; // 这是一个问题！

	public BaseDaoImpl() {
		// 通过反射得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class) pt.getActualTypeArguments()[0];

		System.out.println("clazz = " + clazz.getName());
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		Object obj = getSession().get(clazz, id);
		getSession().delete(obj);
	}

	public T getById(Long id) {
		if (id == null) {
			return null;
		}
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		}

		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + " WHERE id IN(:ids)")//
				.setParameterList("ids", ids)//
				.list();
	}

	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName())//
				.list();
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public PageBean getPageBean(int pageNum, String queryListSql,
			Object[] parameters) {
		int pageSize = Configuration.getPageSize();
		Query listQuery=getSession().createQuery(queryListSql);
		if(parameters!=null&&parameters.length>0){
			for(int i=0;i<parameters.length;i++){
				listQuery.setParameter(i,parameters[i]);
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		@SuppressWarnings("rawtypes")
		List list = listQuery.list();
       Query countQuery=getSession().createQuery("select count(*) "+queryListSql);
   	if(parameters!=null&&parameters.length>0){
		for(int i=0;i<parameters.length;i++){
			countQuery.setParameter(i,parameters[i]);
		}
	}
		long count = (long)countQuery.uniqueResult();
		//(int)count=count.intValue()
		return new PageBean(pageNum, pageSize, list, (int)count);
	}

	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		int pageSize = Configuration.getPageSize();
		List<Object> parameters=hqlHelper.getParameters();
	     System.err.println(hqlHelper.getHsql());
	      
		Query listQuery=getSession().createQuery(hqlHelper.getHsql());
		if(parameters!=null&&parameters.size()>0){
			for(int i=0;i<parameters.size();i++){
				listQuery.setParameter(i,parameters.get(i));
			}
		}
		listQuery.setFirstResult((pageNum-1)*pageSize);
		listQuery.setMaxResults(pageSize);
		@SuppressWarnings("rawtypes")
		List list = listQuery.list();
       Query countQuery=getSession().createQuery(hqlHelper.getCount());
   	if(parameters!=null&&parameters.size()>0){
		for(int i=0;i<parameters.size();i++){
			countQuery.setParameter(i,parameters.get(i));
		}
	}
		long count = (long)countQuery.uniqueResult();
		//(int)count=count.intValue()
		return new PageBean(pageNum, pageSize, list, (int)count);
	}
 
}
