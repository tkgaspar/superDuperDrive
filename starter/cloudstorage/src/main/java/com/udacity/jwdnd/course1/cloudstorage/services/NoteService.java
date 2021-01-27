package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotesList(Integer userId) {
        return this.noteMapper.getAllNotes(userId);
    }


    public Integer addNote(NoteForm noteform, Integer userId) {
        Note note = new Note();
        note.setNoteTitle(noteform.getNoteTitle());
        note.setNoteDescription(noteform.getNoteDescription());
        note.setUserId(userId);
        return this.noteMapper.insert(note);
    }

    public void deleteNote(String noteTitle, Integer userid) {
        this.noteMapper.delete(noteTitle, userid);
    }

    public Note getNote (String noteTitle,Integer userId){
        return this.noteMapper.getNote(noteTitle, userId);
    }

    public void updateNote(NoteForm noteForm){
        this.noteMapper.updateNote(noteForm.getNoteTitle(),noteForm.getNoteDescription(), noteForm.getNoteId());
    }


}
