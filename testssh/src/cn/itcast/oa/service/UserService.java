package cn.itcast.oa.service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.User;

public interface UserService extends BaseDao<User> {

	User getByloginNameAndPassword(String loginName, String password);

}
