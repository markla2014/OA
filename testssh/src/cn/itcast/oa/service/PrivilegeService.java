package cn.itcast.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.itcast.oa.base.BaseDao;
import cn.itcast.oa.domain.Privilege;

public interface PrivilegeService extends BaseDao<Privilege> {

	List<Privilege> findTopList();

}
