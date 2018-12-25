package cn.edu.nju.software.storymapping.system.dao;


import cn.edu.nju.software.storymapping.system.entity.User;

public interface UserMapper {
    public User selectUser(String username);

    public int updateUser(User user);

}
