package com.Anakost;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Анатолій on 05.10.2016.
 */
public class SessionManager {
    private final HashMap<String,Session> sessIdMap=new HashMap<>();
    private final HashMap<String,PrincipalInfo> loginMap=new HashMap<>();


    public static final SessionManager instance= new SessionManager();
    static {
        instance.createPrincipal("admin","123456");
        instance.createPrincipal("root","qwerty");
        instance.createPrincipal("user","qwe12rty");
        instance.createPrincipal("notlogin","notpassword");
    }
    private SessionManager(){}

    public void createPrincipal(String login, String password){
        loginMap.put(login, new PrincipalInfo(login,password));


    }
    public Session authorize(String login, String password){
        PrincipalInfo pInfo=loginMap.get(login);
        if(pInfo==null) return null;
        if (!pInfo.password.equals(password)) return null;
        String sessId = UUID.randomUUID().toString();
        Session session=new Session(pInfo,sessId);
        sessIdMap.put(sessId,session);
        return session;
    }

    public Session authenticate (String sessId){
        return sessIdMap.get(sessId);
    }

    public void terminateSession(String sessId){sessIdMap.remove(sessId);}
}

