package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.oa.base.BaseDao;

public class HqlHelper {

	private String fromClause="";
	private String whereClause="";
	private String orderClause="";
	private List<Object> parameters=new ArrayList();
	
	public HqlHelper(Class clazz, String alias){
		this.fromClause="From "+clazz.getSimpleName()+" "+alias;
	}
	public HqlHelper(Class clazz){
		this.fromClause="From "+clazz.getSimpleName()+" o";
	}
	public HqlHelper addWhereCondiction(String condiction,Object... params){
		if(whereClause.length()==0){
			whereClause=" where "+condiction;
		}else{
			whereClause+=" and "+condiction;
		}
		if(params!=null&&params.length>0){
			for(Object obj:params){
				parameters.add(obj);
			}
		}
		return this;
	}
	/**
	 * 如果第一个参数为true 的情况下允许拼接
	 * @param append
	 * @param condiction
	 * @param params
	 */
public HqlHelper addWhereCondiction(boolean append,String condiction,Object... params){
	
 if(append){
	this.addWhereCondiction(condiction, params);
 }
	return this;
	}

	public HqlHelper addOrder(String perportyName,boolean isAsc){
		if(orderClause.length()==0){
			orderClause=" ORDER BY "+perportyName+(isAsc?" asc":" desc");
		}else{
			orderClause+=", "+perportyName+(isAsc?" asc":" desc");
		}
		return this;
	}
public HqlHelper addOrder(boolean append,String perportyName,boolean isAsc){
	if(append){
		this.addOrder(perportyName, isAsc);
	}
	return this;
	}
	public String getHsql(){
		return fromClause+whereClause+orderClause;
	}
   
	public List<Object> getParameters() {
		return parameters;
	}

	public void setParameters(List<Object> parameters) {
		this.parameters = parameters;
	}
	
	public String getCount(){
		return "select count(*) "+fromClause+whereClause;
	}
	/**
	 *  准备到页面去
	 * 查询分页信息放到站定
	 */

	public HqlHelper buildPageBeanStructs(int pageNum,BaseDao<?> service) {
		// TODO Auto-generated method stub
		PageBean pageBean=service.getPageBean(pageNum, this);
		ActionContext.getContext().getValueStack().push(pageBean);
		return this;
		
	}
	
}
