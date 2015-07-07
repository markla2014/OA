package cn.itcast.oa.test;

import org.junit.Test;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.HqlHelper;

public class HsqlHelperTest {
	private int viewType=0;
	private int orderBy=0;
	private boolean asc=true;
	private Forum forum=new Forum();
  @Test
  public void testHqlHelper(){
	 HqlHelper hqlHelper=new HqlHelper(Topic.class,"t");
	 hqlHelper.addWhereCondiction("t.forum=?", forum);
	 //方法链
		 hqlHelper.addWhereCondiction(viewType==1,"t.type=?",Topic.TYPE_BEST)
				.addOrder(orderBy==0,"(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)
				.addOrder(orderBy==0,"t.lastUpdateTime", false)
			.addOrder(orderBy==1,"t.lastUpdateTime",asc)
				.addOrder(orderBy==2,"t.postTime",asc)
				.addOrder(orderBy==3,"t.replyCount",asc);
		 System.out.println(hqlHelper.getHsql());
		 System.out.println(hqlHelper.getParameters());
  }
}
