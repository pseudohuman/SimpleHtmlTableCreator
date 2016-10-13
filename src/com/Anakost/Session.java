package com.Anakost;

public class Session {
    public static final String NAME = "SessionId";

    Session(PrincipalInfo principal, String id){
        this.principal=principal;
        this.id = id;
    }

    public final  PrincipalInfo principal;
    public final String id;




}
