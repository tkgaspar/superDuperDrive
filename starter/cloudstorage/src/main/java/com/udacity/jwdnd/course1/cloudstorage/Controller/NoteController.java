package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.Model.StorageForm;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {

    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/note")
    public String getNoteList(@ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        model.addAttribute("notes", noteService.getNotesList(noteForm.getUserId()));
        return "home";
    }


    @PostMapping("/note")
    public ModelAndView addNote(@ModelAttribute("noteForm") NoteForm noteForm, Authentication auth, ModelMap attributes) {
        String saveError=null;
        User user=this.userService.getUser(auth.getName());
        Note note=new Note(noteForm.getNoteTitle(),noteForm.getNoteText(), user.getUserId());
        if(noteForm.getNoteTitle()==null){
            attributes.addAttribute("Please give your note a title, before saving!", saveError);
        }else if(noteForm.getNoteText()==null){
            attributes.addAttribute("Your note is empty!!!", saveError);
        }else{
            this.noteService.addNote(note);
        }
        attributes.addAttribute("SavedNotes", noteService.getNotesList(noteForm.getUserId()));
        return new ModelAndView("forward:/home", attributes);
        }


}
