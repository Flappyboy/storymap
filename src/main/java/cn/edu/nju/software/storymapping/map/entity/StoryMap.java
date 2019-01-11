package cn.edu.nju.software.storymapping.map.entity;

import java.util.List;

public class StoryMap {

    private Integer id;
    private String name;
    private Integer workSpaceId;
    private Integer userId;
    private String description;
    private List<Role> roleList;
    private List<ActivityCard> activityCardList;
    private List<Release> releaseList;

    @Override
	public String toString() {
		return "StoryMap [id=" + id + ", name=" + name + ", workSpaceId=" + workSpaceId + ", userId=" + userId
				+ ", description=" + description + ", roleList=" + roleList + ", activityCardList=" + activityCardList
				+ ", releaseList=" + releaseList + "]";
	}

	public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<ActivityCard> getActivityCardList() {
        return activityCardList;
    }

    public void setActivityCardList(List<ActivityCard> activityCardList) {
        this.activityCardList = activityCardList;
    }

    public List<Release> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(List<Release> releaseList) {
        this.releaseList = releaseList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkSpaceId() {
        return workSpaceId;
    }

    public void setWorkSpaceId(Integer workSpaceId) {
        this.workSpaceId = workSpaceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
