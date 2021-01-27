package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Model.CredentialForm;
import org.springframework.stereotype.Service;

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
        Credential credo = new Credential();
        credo.setUrl(credoForm.getUrl());
        credo.setUserName(credoForm.getUserName());
        credo.setKey(credoForm.getKey());
        credo.setPassword(credoForm.getPassword());
        System.out.println("If inserted into database, then this results in 1!, so:"+this.credentialMapper.insert(credo));
    }

    public void updateCredential(CredentialForm credoForm) {
        this.credentialMapper.updateCredential(credoForm.getUrl(), credoForm.getUserName(), credoForm.getKey(), credoForm.getPassword(), credoForm.getUserId());
    }

    public void deleteCredential(Integer credentialId, Integer userId) {
        this.credentialMapper.deleteCredential(credentialId, userId);
    }
}
