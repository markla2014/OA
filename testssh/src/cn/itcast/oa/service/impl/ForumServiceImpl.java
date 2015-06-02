package cn.itcast.oa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.Forum;
import cn.itcast.oa.service.ForumService;
@Service
public class ForumServiceImpl  extends BaseDaoImpl<Forum> implements ForumService {
    @SuppressWarnings("unchecked")
	@Override
	public List<Forum> findAll(){
    	return getSession().createQuery("from Forum f order by f.position ASC").list();
    }
    @Override
    public void save(Forum forum){
    	
    	getSession().save(forum);
    	forum.setPosition(forum.getId().intValue());
    	//因为持久化状态，所以不用update
    }
	@Override
	public void moveUp(Long id) {
		// TODO Auto-generated method stub
		Forum forum=getById(id);
		Forum other=null;
		forum.setPosition(other.getPosition());
		other.setPosition(forum.getPosition());
		
	}

	@Override
	public void moveDown(Long id) {
		// TODO Auto-generated method stub
		
	}

}
