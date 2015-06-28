package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.base.BaseDao;

public interface TopicService extends BaseDao<Topic> {

	List<Topic> findByForum(Forum forum);

}
