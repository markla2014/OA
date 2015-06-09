package cn.itcast.oa.service.impl;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDaoImpl;
import cn.itcast.oa.domain.User;
import cn.itcast.oa.service.UserService;

@Service
public class UserServiceImpl extends BaseDaoImpl<User> implements UserService {

	@Override
	public User getByloginNameAndPassword(String loginName, String password) {
		/**
		 * return (User) getSession() .createQuery(
		 * "from User a where a.loginName= " + loginName + " and a.password= " +
		 * password).list().get(0);
		 **/
		User test=(User) getSession()
				.createQuery(
						"from User a where a.loginName= ? and a.password= ?")
				.setParameter(0, loginName).setParameter(1, password)
				.uniqueResult();
		return test;

	}

}
