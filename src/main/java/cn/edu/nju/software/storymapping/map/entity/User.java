package cn.edu.nju.software.storymapping.map.entity;

import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String description;
    private String phone;
    private Date createTime;
    private Integer imageId;
    private List<Workspace> workspaceList;

    public List<Workspace> getWorkspaceList() {
        return workspaceList;
    }

    public void setWorkspaceList(List<Workspace> workspaceList) {
        this.workspaceList = workspaceList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}
