package com.udacity.jwdnd.course1.cloudstorage.Model;


/*CREATE TABLE IF NOT EXISTS CREDENTIALS (
        credentialid INT PRIMARY KEY auto_increment,
        url VARCHAR(100),
        username VARCHAR (30),
        key VARCHAR,
        password VARCHAR,
        userid INT,
        foreign key (userid) references USERS(userid)*/

public class Credential {
    private Integer credentialId;
    private String url;
    private String userName;
    private String key;
    private String password;
    private Integer userId;

    public Credential(){};

    public Credential(Integer credentialId, String url, String userName, String key, String password, Integer userId) {
        this.url = url;
        this.userName = userName;
        this.key = key;
        this.password = password;
        this.userId = userId;
        this.credentialId=credentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }
}
