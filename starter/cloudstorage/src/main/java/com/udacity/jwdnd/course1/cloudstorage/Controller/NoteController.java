package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Note;
import com.udacity.jwdnd.course1.cloudstorage.Model.NoteForm;
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
import org.springframework.web.bind.annotation.RequestMapping;
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
        model.addAttribute("SavedNotes", noteService.getNotesList(noteForm.getUserId()));
        return "home";
    }


    @PostMapping("/note")
    public ModelAndView addNote(NoteForm noteForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        if (noteForm.getNoteId() == null) {
            if(this.noteService.addNote(noteForm, user.getUserId())==1) {
                attributes.addAttribute("noteUploadSuccess", "Your note has been saved succesfully ! ");
                attributes.addAttribute("SavedNotes", noteService.getNotesList(noteForm.getUserId()));
            }else{
                attributes.addAttribute("noteUploadError", "Something went wrong, please try again!");
            }
        } else{
            this.noteService.updateNote(noteForm);
            attributes.addAttribute("noteUploadSuccess", "Your note has been saved succesfully ! ");
            attributes.addAttribute("SavedNotes", noteService.getNotesList(noteForm.getUserId()));

        }
        return new ModelAndView("forward:/result", attributes);
    }

    @GetMapping("/note-delete")
    public ModelAndView deleteNote(@ModelAttribute("noteForm") NoteForm noteForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        for (Note note : this.noteService.getNotesList(user.getUserId())) {
            if (note.getNoteTitle().equals(noteForm.getNoteTitle())) {
                this.noteService.deleteNote(note.getNoteTitle(), user.getUserId());
            }
        }
        attributes.addAttribute("SavedNotes", noteService.getNotesList(noteForm.getUserId()));
        return new ModelAndView("forward:/home", attributes);
    }

    @GetMapping("/result")
    public String getResultPage() {
        return "result";
    }

    @PostMapping("/result")
    public String postResultPage() {
        return "result";
    }

}
