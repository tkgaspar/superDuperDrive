package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {


    @Select("SELECT * from files where userid=#{userid}")
    List<File>getFiles(Integer userId);

    @Select("SELECT * FROM files where fileName=#{fileName} and userid=#{userid}")
    File getFile(String fileName,Integer userid);

    @Options(useGeneratedKeys = true, keyProperty="fileId")
    @Insert("INSERT INTO files (filename,contenttype,filesize,userid,filedata) VALUES (#{fileName},#{contentType},#{fileSize},#{userid},#{fileData})")
    int insert(File file);

    @Delete("DELETE from FILES where fileName=#{fileName} and userid=#{userid}")
    int delete(String fileName, Integer userid);


    /*CREATE TABLE IF NOT EXISTS FILES (
    fileId INT PRIMARY KEY auto_increment,
    filename VARCHAR,
    contenttype VARCHAR,
    filesize VARCHAR,
    userid INT,
    filedata BLOB,
    foreign key (userid) references USERS(userid)
);*/
}
