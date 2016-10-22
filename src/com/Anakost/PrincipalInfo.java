package com.Anakost;

public class PrincipalInfo{
    public byte[] password;
    public String login;
    PrincipalInfo(String login,byte[] password){
        this.password=password;
        this.login=login;
    }
}
