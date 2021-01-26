package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * from CREDENTIALS Where userId=#{userid}")
    List<Credential> getAllCredentials(Integer userId);

    @Insert("INSERT INTO credentials (url,username,key,password,userid) VALUES(#{url},#{userName},#{key},#{password},#{userid}) ")
    @Options(useGeneratedKeys = true, keyProperty="credentialId")
    int insert (Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, key=#{key}, password=#{password}, userid=#{userid} WHERE credentialId = #{credentialId}")
    void updateCredential(String url,String userName, String key,String password,Integer userid);

    @Delete("DELETE from CREDENTIALS where credentialid=#{credentialId} AND userid=#{userId}")
    int deleteCredential(Integer credentialId,Integer userId);
}
