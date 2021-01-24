package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.File;
import com.udacity.jwdnd.course1.cloudstorage.Model.StorageForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }


    public void addFile(File file) {
        fileMapper.insert(file);
    }

    public void removeFile(Integer userId, String fileName) {
        fileMapper.delete(fileName, userId);
    }

    public File convertMpFile(MultipartFile mpFile, Integer userId) throws IOException {
        File file = new File(mpFile.getOriginalFilename(), mpFile.getContentType(), mpFile.getSize() + "", userId, mpFile.getBytes());
        return file;
    }

    public File getFile(String fileName, Integer userId) {
        return this.fileMapper.getFile(fileName, userId);
    }

    public List<File> fileList(Integer userId) {
        return this.fileMapper.getFiles(userId);
    }
}
