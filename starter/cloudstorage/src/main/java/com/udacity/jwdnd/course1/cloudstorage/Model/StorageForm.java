package com.udacity.jwdnd.course1.cloudstorage.Model;

public class StorageForm {


    private String fileName;
    private Integer userId;

    //  private String fileSize;
    public StorageForm() {
    };



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
