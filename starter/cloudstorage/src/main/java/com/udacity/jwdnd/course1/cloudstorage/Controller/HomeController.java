package com.udacity.jwdnd.course1.cloudstorage.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class HomeController {


    @GetMapping()
    public String getHomePage(/*@ModelAttribute("chatForm") ChatForm chatForm, Model model*/) {
        /*  model.addAttribute("messages", messageService.getMessages());*/
        return "home";
    }

   /* @PostMapping()
    public String HomePage(Authentication auth, ChatForm chatForm, Model model) {
         chatForm.setName(auth.getName());
            messageService.addMessages(chatForm);
            chatForm.setMessage("");
            model.addAttribute("ChatMessages", messageService.getMessages());

        return "chat";
    }*/


}


