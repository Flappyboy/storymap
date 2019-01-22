package cn.edu.nju.software.storymapping.map.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.nju.software.storymapping.map.dao.RoleMapper;
import cn.edu.nju.software.storymapping.map.entity.Role;
import cn.edu.nju.software.storymapping.map.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public void updateRole(Role role) {
		// TODO Auto-generated method stub
		logger.debug("RoleServiceImpl::updateRole Role = {}", role.toString());
		roleMapper.updateRole(role);
	}

	@Override
	public void deleteRole(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("RoleServiceImpl::deleteRole id = {}", id);
		roleMapper.deleteRole(id);
	}

	@Override
	public void insertRole(Role role) {
		// TODO Auto-generated method stub
		logger.debug("RoleServiceImpl::insertRole Role = {}", role.toString());
		roleMapper.insertRole(role);
		logger.debug("RoleServiceImpl::insertRole id = {}", role.getId());
	}

	@Override
	public void addRole_Activity(Integer rid, Integer aid) {
		// TODO Auto-generated method stub
		logger.debug("RoleServiceImpl::addRole_Activity rid = {},aid = {}", rid, aid);
		roleMapper.addRole_Activity(rid, aid);
	}

	@Override
	public void deleteRole_Activity(Integer id) {
		// TODO Auto-generated method stub
		logger.debug("RoleServiceImpl::deleteRole_Activity id = {}", id);
		roleMapper.deleteRole_Activity(id);
	}

}
