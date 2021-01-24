package com.udacity.jwdnd.course1.cloudstorage.Controller;


import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.Model.StorageForm;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/delete")
public class VIewFileController {

    private FileService fileService;
    private UserService userService;

    @PostMapping()
    public String deleteFile(StorageForm storageForm, Model model, Authentication auth){
        User user = userService.getUser(auth.getName());

        for(File file:this.fileService.fileList(user.getUserId())){
            if(file.getFileName().equals(storageForm.getFileName())){
                this.fileService.removeFile(user.getUserId(), file.getFileName());
            }

        }
        return "home";

    }
}
