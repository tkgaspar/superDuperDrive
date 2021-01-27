package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView addCredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication auth, ModelMap attributes) {
        String saveError = null;
        User user = this.userService.getUser(auth.getName());
        if (credentialForm.getCredentialId() == null) {
            this.credentialService.addCredential(credentialForm, user.getUserId());
            System.out.println("CredentialForm credentialId is null, credential is being saved");
        }else{
            this.credentialService.updateCredential(credentialForm);
            System.out.println("CredentialForm credentialId is: "+credentialForm.getCredentialId()+" credential is being updated");
        }
        attributes.addAttribute("SavedCredentials", this.credentialService.getCredentialList(user.getUserId()));
        return new ModelAndView("forward:/home", attributes);
    }

    @GetMapping("/credential-delete")
    public ModelAndView deletecredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        for(Credential credential:this.credentialService.getCredentialList(user.getUserId())){
            System.out.println("credential credoId: "+credential.getCredentialId());
            System.out.println("credentialForm credoId: "+credentialForm.getCredentialId());
            if(credential.getUrl().equals(credentialForm.getUrl())){
                this.credentialService.deleteCredential(credential.getCredentialId(),user.getUserId());
            }
        }

        attributes.addAttribute("SavedCredentials", credentialService.getCredentialList(user.getUserId()));
        return new ModelAndView("forward:/home", attributes);
    }



}
