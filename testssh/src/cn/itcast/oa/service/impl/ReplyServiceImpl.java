package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.service.ReplyService;
@Service
public class ReplyServiceImpl extends BaseDaoImpl<Reply> implements ReplyService {

	@Override
	public List<Reply> findByTopic(Topic topic) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Reply r where r.topic=? order by r.postTime Asc").setParameter(0,topic).list();
	}

}
