package cn.edu.nju.software.storymapping.system.entity;

import cn.edu.nju.software.storymapping.map.entity.Workspace;

import java.util.Date;
import java.util.List;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.username
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.description
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.phone
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.email
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.create_time
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.image_id
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    private Integer imageId;

    private List<Workspace> workspaceList;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public User(Integer id, String username, String password, String description, String phone, String email, Date createTime, Integer imageId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.createTime = createTime;
        this.imageId = imageId;
    }

    public List<Workspace> getWorkspaceList() {
        return workspaceList;
    }

    public void setWorkspaceList(List<Workspace> workspaceList) {
        this.workspaceList = workspaceList;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public User() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.username
     *
     * @return the value of user.username
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.username
     *
     * @param username the value for user.username
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.description
     *
     * @return the value of user.description
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.description
     *
     * @param description the value for user.description
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.phone
     *
     * @return the value of user.phone
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.phone
     *
     * @param phone the value for user.phone
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.create_time
     *
     * @return the value of user.create_time
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.create_time
     *
     * @param createTime the value for user.create_time
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.image_id
     *
     * @return the value of user.image_id
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.image_id
     *
     * @param imageId the value for user.image_id
     *
     * @mbg.generated Sat Dec 22 16:56:09 CST 2018
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}