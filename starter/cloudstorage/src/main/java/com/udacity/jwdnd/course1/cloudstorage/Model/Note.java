package com.udacity.jwdnd.course1.cloudstorage.Model;

/*CREATE TABLE IF NOT EXISTS NOTES (
    noteid INT PRIMARY KEY auto_increment,
    notetitle VARCHAR(20),
    notedescription VARCHAR (1000),
    userid INT,
    foreign key (userid) references USERS(userid)*/

public class Note {

    private Integer noteId;
    private String noteTitle;
    private String notedescription;
    private Integer userId;

    public Note(){};
    public Note(Integer noteId, String noteTitle, String notedescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.notedescription = notedescription;
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return notedescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.notedescription = noteDescription;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
