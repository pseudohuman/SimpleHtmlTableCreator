package com.Anakost;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Анатолій on 05.10.2016.
 */
public class SessionManager {
    private final HashMap<String,Session> sessIdMap=new HashMap<>();
    private final HashMap<String,PrincipalInfo> loginMap=new HashMap<>();
    private final static SecureRandom random = new SecureRandom();
    private static String genSessId() {

        return Base64.getEncoder().encodeToString(random.generateSeed(33));
    }
    private  static final MessageDigest digest = createDigest();
    private  static final byte[] salt ={(byte)0x47, (byte)0xaa, (byte)0xcf, (byte)0xa0, (byte)0x22, (byte)0xfc, (byte)0x8b, (byte)0x88, (byte)0x26, (byte)0xf9, (byte)0x07, (byte)0xa5, (byte)0x8d, (byte)0xd8, (byte)0xca, (byte)0x43, (byte)0xcd, (byte)0x68, (byte)0x28, (byte)0xc0, (byte)0xb9, (byte)0xe4, (byte)0x04, (byte)0x5c, (byte)0xfe, (byte)0xd9, (byte)0xe0, (byte)0x9c, (byte)0x01, (byte)0xd5, (byte)0xbb, (byte)0x96, };

    private static MessageDigest createDigest(){
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private static byte[] hashPassword(String s){
        byte[] stringBytes= s.getBytes(StandardCharsets.UTF_8);

        for (int i =0,k=0;i<stringBytes.length;i++){
            stringBytes[i]^=salt[k];
            if(k<salt.length-1) k++;
            else k=0;
        }

        byte[] bytes=digest.digest(stringBytes);

        return bytes;
    }

    public static final SessionManager instance= new SessionManager();
    static {

        instance.createPrincipal("admin","123456");
        instance.createPrincipal("root","qwerty");
        instance.createPrincipal("user","qwe12rty");
        instance.createPrincipal("notlogin","notpassword");

    }
    private SessionManager(){}

    public void createPrincipal(String login, String password){

        loginMap.put(login, new PrincipalInfo(login,hashPassword(password)));


    }
    public Session authorize(String login, String password){
        PrincipalInfo pInfo=loginMap.get(login);
        if(pInfo==null) return null;
        if (!Arrays.equals(pInfo.password,hashPassword(password))) return null;
        String sessId = genSessId();
        Session session=new Session(pInfo,sessId);
        sessIdMap.put(sessId,session);
        return session;
    }



    public Session authenticate (String sessId){
        return sessIdMap.get(sessId);
    }

    public void terminateSession(String sessId){sessIdMap.remove(sessId);}
}

