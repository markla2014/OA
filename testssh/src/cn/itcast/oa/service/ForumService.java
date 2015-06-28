package cn.itcast.oa.service;

import java.util.List;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Forum;

public interface ForumService extends BaseDao<Forum> {
    List<Forum> findAll();
	void moveUp(Long id);

	void moveDown(Long id);

}
