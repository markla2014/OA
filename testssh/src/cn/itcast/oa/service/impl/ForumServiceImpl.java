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
		int oldPosition=forum.getPosition();
		Forum other=(Forum)getSession().createQuery("from Forum f where f.position<? order by f.position ASC")
				.setParameter(0, oldPosition)
				.setFirstResult(0)
				.setMaxResults(0)
				.uniqueResult();
		if(other ==null){
			return;
		}
		forum.setPosition(other.getPosition());
		other.setPosition(oldPosition);
		
	}

	@Override
	public void moveDown(Long id) {
		// TODO Auto-generated method stub
		Forum forum=getById(id);
		int oldPosition=forum.getPosition();
		Forum other=(Forum)getSession().createQuery("from Forum f where f.position<? order by f.position ASC")
				.setParameter(0, oldPosition)
				.setFirstResult(0)
				.setMaxResults(0)
				.uniqueResult();
		if(other ==null){
			return;
		}
		forum.setPosition(other.getPosition());
		other.setPosition(oldPosition);
		
	}

}
