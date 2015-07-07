package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.cfg.Configuration;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;
import cn.itcast.oa.util.PageBean;
@Service
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findByForum(Forum forum) {
	 return getSession().createQuery("From Topic t where t.forum=? order by (CASE t.type WHEN 2 THEN 2 ELSE 0 END) desc,t.lastUpdateTime desc ")
			 .setParameter(0,forum) .list();
	}
	@Override
	public void save(Topic topic){
		//设置属性
//		 model.setLastReply(lastReply);
//		 
//		 model.setReplyCount(replyCount);
//		 model.setType(type);
		topic.setLastUpdateTime(topic.getPostTime());
		Forum forum=topic.getForum();
		forum.setTopicCount(forum.getTopicCount()+1);
		forum.setArticleCount(forum.getArticleCount()+1);
		forum.setLastTopic(topic);
		getSession().update(forum);
	}
	@Override
	public PageBean getPageBean(int pageNum, Forum forum) {
		int pageSize = Configuration.getPageSize();
		@SuppressWarnings("rawtypes")
		List list = getSession()
				.createQuery(
						"From Topic t where t.forum=? order by (CASE t.type WHEN 2 THEN 2 ELSE 0 END) desc,t.lastUpdateTime desc")
				.setParameter(0, forum).setMaxResults(pageSize)
				.setFirstResult((pageNum-1)*pageSize).list();

		long count = (long) getSession()
				.createQuery("select count(*) From Topic t where t.forum=?")
				.setParameter(0, forum).uniqueResult();
		//(int)count=count.intValue()
		return new PageBean(pageNum, pageSize, list, (int)count);
	}
  
}
