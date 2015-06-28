package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;

public interface ReplyService extends BaseDao<Reply> {

	List<Reply> findByTopic(Topic topic);

}
