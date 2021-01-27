package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
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
public class CredentialController {
    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService= credentialService;
    }

    @GetMapping("/credential")
    public String getCredentialList(@ModelAttribute("credentialForm")CredentialForm credentialForm, Model model) {
        model.addAttribute("SavedCredentials", credentialService.getCredentialList(credentialForm.getUserId()));
        return "home";
    }

    @PostMapping("/credential")
    public ModelAndView addcredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication auth, ModelMap attributes) {
        String saveError = null;
        User user = this.userService.getUser(auth.getName());
        if (credentialForm.getCredentialId() == null) {
            this.credentialService.addCredential(credentialForm, user.getUserId());
        }else{
            this.credentialService.updateCredential(credentialForm);

        }
        attributes.addAttribute("SavedCredentials", this.credentialService.getCredentialList(user.getUserId()));
        return new ModelAndView("forward:/home", attributes);
    }

    @GetMapping("/credential-delete")
    public ModelAndView deletecredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        for(Credential credential:this.credentialService.getCredentialList(user.getUserId())){
            if(credential.getCredentialId().equals(credentialForm.getCredentialId())){
                this.credentialService.deleteCredential(credential.getCredentialId(),user.getUserId());
            }
        }
        attributes.addAttribute("SavedCredentials", credentialService.getCredentialList(user.getUserId()));
        return new ModelAndView("forward:/home", attributes);
    }

}
