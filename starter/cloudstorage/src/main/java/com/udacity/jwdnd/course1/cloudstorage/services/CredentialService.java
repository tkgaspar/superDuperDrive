package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialForm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentialList(Integer userId) {
        return this.credentialMapper.getAllCredentials(userId);
    }

    public void addCredential(CredentialForm credoForm, Integer userId) {
        SecureRandom random=new SecureRandom();
        byte [] key= new byte[16];
        String encodedKey= Base64.getEncoder().encodeToString(key);
        EncryptionService encryptionService= new EncryptionService();
        String encodedPassword=encryptionService.encryptValue(credoForm.getPassword(),encodedKey);
        Credential credo = new Credential();
        credo.setUrl(credoForm.getUrl());
        credo.setUserName(credoForm.getUserName());
        credo.setKey(encodedKey);
        credo.setPassword(encodedPassword);
        credo.setUserId(userId);
        System.out.println("If inserted into database, then this results in 1!, so:" + this.credentialMapper.insert(credo));
    }

    public void updateCredential(CredentialForm credoForm) {
        this.credentialMapper.updateCredential(credoForm.getCredentialId(), credoForm.getUrl(), credoForm.getUserName(),  credoForm.getPassword());
    }

    public void deleteCredential(Integer credentialId, Integer userId) {
        System.out.println("it gets deleted?" + this.credentialMapper.deleteCredential(credentialId, userId));
    }
}
