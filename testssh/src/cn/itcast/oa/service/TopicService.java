package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.PageBean;

public interface TopicService extends BaseDao<Topic> {
       @Deprecated
	List<Topic> findByForum(Forum forum);

	PageBean getPageBean(int pageNum, Forum forum);

}
