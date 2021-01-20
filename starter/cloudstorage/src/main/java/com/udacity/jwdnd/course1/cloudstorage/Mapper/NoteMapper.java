package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper {

    @Select("SELECT from NOTES where userid=#{userId}")
    Note note(String userId);

    @Insert("INSERT INTO notes (notetitle,notedescription,userid) VALUES (#{noteTitle},#{notedescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty="noteId")
    int insert(String userId);


}
