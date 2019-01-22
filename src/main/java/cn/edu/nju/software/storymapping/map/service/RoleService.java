package cn.edu.nju.software.storymapping.map.service;

import cn.edu.nju.software.storymapping.map.entity.Role;

public interface RoleService {
	void updateRole(Role role);

    void deleteRole(Integer id);

    void insertRole(Role role);
    
    void addRole_Activity(Integer rid,Integer aid);

    void deleteRole_Activity(Integer id);
}
