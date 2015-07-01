package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Reply;
import cn.itcast.oa.domain.Topic;
import cn.itcast.oa.util.PageBean;

public interface ReplyService extends BaseDao<Reply> {
   @Deprecated
	List<Reply> findByTopic(Topic topic);

	PageBean getPageBean(int pageNum, Topic topic);

}
