package com.udacity.jwdnd.course1.cloudstorage.Controller;

import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.Model.StorageForm;
import com.udacity.jwdnd.course1.cloudstorage.Model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Controller
public class FileUploadController {

    private FileService fileService;
    private UserService userService;

    public FileUploadController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }


    @GetMapping("/file")
    public String getFileList(@ModelAttribute("storageForm") StorageForm storageForm, Model model) {
        model.addAttribute("files", fileService.fileList(storageForm.getUserId()));
        return "home";
    }


    @PostMapping("/file")
    public ModelAndView uploadFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication auth, StorageForm storageForm, ModelMap attributes) throws IOException, SizeLimitExceededException {
        User user = userService.getUser(auth.getName());
        if (fileUpload.isEmpty()) {
            attributes.addAttribute("UploadErrorBool", true);
            attributes.addAttribute("UploadError", "Please select a file to upload! ");
        } else if (fileService.getFile(fileUpload.getOriginalFilename(), user.getUserId()) != null) {
            attributes.addAttribute("UploadErrorBool", true);
            attributes.addAttribute("UploadError", "There is a file by that name already, please upload another file, or rename your file! ");
        } else {
            this.fileService.addFile(fileService.convertMpFile(fileUpload, user.getUserId()));
            attributes.addAttribute("UploadedFiles", fileService.fileList(user.getUserId()));
            attributes.addAttribute("UploadSuccessBool", true);
            attributes.addAttribute("UploadSuccess", "Your file has been uploaded successfully! ");

        }
        return new ModelAndView("forward:/result", attributes);
    }


    @GetMapping("/delete")
    public ModelAndView deleteFile(StorageForm storageForm, ModelMap attributes, Authentication auth) {
        User user = userService.getUser(auth.getName());
        for (File file : this.fileService.fileList(user.getUserId())) {
            if (file.getFileName().equals(storageForm.getFileName())) {
                this.fileService.removeFile(user.getUserId(), file.getFileName());
                attributes.addAttribute("UploadSuccess", "Your file has been deleted succesfully");
            }

        }
        attributes.addAttribute("UploadedFiles", fileService.fileList(user.getUserId()));
        return new ModelAndView("forward:/result", attributes);

    }

    @GetMapping("/download")
    public ResponseEntity<ByteArrayResource> fileDownload(@RequestParam("fileName") String fileName, Authentication authentication) {

        User user = userService.getUser(authentication.getName());
        File file = fileService.getFile(fileName, user.getUserId());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handleError2(SizeLimitExceededException e, ModelMap attributes) {
        System.out.println("ExceptionHandler is entered upon error");
        attributes.addAttribute("UploadError", "The file you are trying to upload exceeds the 10MB limit! ");
        return new ModelAndView("forward:/result");


    }
}





