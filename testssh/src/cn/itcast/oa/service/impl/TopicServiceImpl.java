package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.TopicService;
@Service
public class TopicServiceImpl extends BaseDaoImpl<Topic> implements TopicService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findByForum(Forum forum) {
	 return getSession().createQuery("From Topic t where t.forum=? order by t.type desc,t.lastUpdateTime desc ")
			 .setParameter(0,forum) .list();
	}

}
