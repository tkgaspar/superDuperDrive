package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CredentialMapper {
    @Select("SELECT from CREDENTIALS Where userId=#{userid}")
    Credential getCredential(String userId);

    @Insert("INSERT INTO credentials (url,username,key,password,userid) VALUES(#{url},#{userName},#{key},#{password},#{userId}) ")
    @Options(useGeneratedKeys = true, keyProperty="credentialId")
    int insert (User users);


}
