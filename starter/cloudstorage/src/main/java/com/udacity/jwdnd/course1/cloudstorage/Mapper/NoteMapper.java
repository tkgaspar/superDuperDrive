package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * from NOTES where userid=#{userId}")
    List<Note> getAllNotes(Integer userId);

    @Select(("SELECT * FROM NOTES WHERE notetitle=#{noteTitle} and userid=#{userId}"))
    Note getNote(String noteTitle,Integer userId);

    @Insert("INSERT INTO notes (notetitle,notedescription,userid) VALUES (#{noteTitle},#{notedescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty="noteId")
    int insert(Note note);

    @Delete("DELETE from NOTES where notetitle=#{noteTitle} and userid=#{userid}")
    int delete(String noteTitle, Integer userid);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid = #{noteId}")
    void updateNote(String noteTitle,String noteDescription, Integer noteId);


}
