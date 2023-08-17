package com.example.entity;

import lombok.Data;

@Data
public class LdapConfig {

    private String url;

    private String base;

    private String userDn;

    private String userPwd;

    private String domainName;


}
