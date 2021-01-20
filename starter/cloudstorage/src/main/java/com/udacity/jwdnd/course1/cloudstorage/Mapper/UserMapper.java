package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM Users WHERE username=#{username}")
    User getUser(String userName);

    @Insert("INSERT INTO Users (userName, salt, password,firstName,lastName) VALUES (#{userName},#{salt},#{password},#{firstName},#{lastName})")
    @Options(useGeneratedKeys = true, keyProperty="userId")
    int insert (User users);
}
