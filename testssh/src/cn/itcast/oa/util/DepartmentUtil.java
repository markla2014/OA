package cn.itcast.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cn.itcast.oa.domain.Department;

public class DepartmentUtil {
  public static List<Department> getAllDepartment(List<Department> topList){
		List<Department> list = new ArrayList<Department>();
		walkDepartemntTrees(topList, "┣", list);
		return list;
  }

private static void walkDepartemntTrees(Collection<Department> set,
		String prefix, List<Department> list) {
	for(Department top:set){
		Department copy=new Department();
		copy.setId(top.getId());
		top.setName(prefix+top.getName());
		list.add(top);
		walkDepartemntTrees(top.getChildren()," >"+prefix,list);
	}
}
  
}
