package cn.edu.nju.software.storymapping.map.dao;

import cn.edu.nju.software.storymapping.map.entity.Role;

public interface RoleMapper {
    public void updateRole(Role role);

    public void deleteRole(Integer id);

    public void insertRole(Role role);
    
    public void addRole_Activity(Integer rid,Integer aid);
    
    public void deleteRole_Activity(Integer id);
    
    public void listByStoryMapId(Integer id);
}
