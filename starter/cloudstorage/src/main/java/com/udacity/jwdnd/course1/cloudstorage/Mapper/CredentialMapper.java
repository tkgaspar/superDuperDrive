package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * from CREDENTIALS where userid=#{userId}")
    List<Credential>getAllCredentials(Integer userId);

    @Insert("INSERT INTO credentials (url,username,key,password,userid) VALUES(#{url},#{userName},#{key},#{password},#{userId}) ")
    @Options(useGeneratedKeys = true, keyProperty="credentialId")
    int insert (Credential credential);

    @Update("UPDATE CREDENTIALS SET url=#{url}, username=#{userName}, key=#{key}, password=#{password}, userid=#{userid} WHERE credentialId = #{credentialId}")
    void updateCredential(String url, String userName, String key,String password,Integer userid);

    @Delete("DELETE from CREDENTIALS where credentialid=#{credentialId} AND userid=#{userId}")
    int deleteCredential(Integer credentialId,Integer userId);
}
