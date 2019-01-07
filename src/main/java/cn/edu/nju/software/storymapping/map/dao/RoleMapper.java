package cn.edu.nju.software.storymapping.map.dao;

import cn.edu.nju.software.storymapping.map.entity.Role;

public interface RoleMapper {
    public void updateRole(Role role);

    public void deleteRole(Integer id);

    public void insertRole(Role role);
}
