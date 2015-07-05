package cn.itcast.oa.base;

import java.util.List;

import cn.itcast.oa.util.PageBean;

public interface BaseDao<T> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	void update(T entity);

	/**
	 * 查询实体，如果id为null，则返回null，并不会抛异常。
	 * 
	 * @param id
	 * @return
	 */
	T getById(Long id);

	/**
	 * 查询实体
	 * 
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);

	/**
	 * 公用的查询分页信息的方法
	 * @param pageNum
	 * @param queryListSql 查询list 的 hsql 语句
	 * @return
	 */
	List<T> findAll();
	PageBean getPageBean(int pageNum,String queryListSql,Object[] parameters);
}
