package com.Anakost;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Анатолій on 01.10.2016.
 */
public class HttpHelper {
    private static final String COOKIE_KEY ="cookie";
    private static final String QUERY_KEY ="query";
    private static final String SESSION_KEY ="session";
    public static final String POST_LOGIN_URL_NAME = "postLoginUrl";
    public static final String STATUS_CODE_KEY = "status-code";



    public static void decode(String data,Map<String,String> map) throws UnsupportedEncodingException {

        for (String string : data.split("&")){
            int index = string.indexOf("=");
            if (index==-1){
                map.put(string,"");
                return;
            }
            String name = string.substring(0, index);
            String value = string.substring( index+1,string.length());
            map.put(URLDecoder.decode(name,"UTF-8"), URLDecoder.decode(value,"UTF-8"));
        }

    }

    public static IHtmlWriter getHtmlWriter(HttpExchange http, int statusCode){
        http.setAttribute(STATUS_CODE_KEY,statusCode);
        return new HtmlWriter(new BufferedWriter(new OutputStreamWriter(http.getResponseBody(), StandardCharsets.UTF_8)));

    }
    public static Map<String,String> getQueryMap(HttpExchange http) throws IOException {

        Map<String, String> map = (Map) http.getAttribute(QUERY_KEY) ;
        if (map != null) return map;
        map=new HashMap<>();
        if (http.getRequestMethod().equals("POST"))
            HttpHelper.decode(new BufferedReader(new InputStreamReader(http.getRequestBody())).readLine(), map);
        String query = http.getRequestURI().getQuery();
        if (query != null) HttpHelper.decode(http.getRequestURI().getQuery(), map);
        http.setAttribute(QUERY_KEY,map);
        return map;
    }

    private static void parseCookie(String cookie, Map<String,String> map) {
        String[] cookies = cookie.split(";");
        String name;
        String value;
        for (String s : cookies) {
            int index = s.indexOf('=');
            if (index==-1) map.put(s.trim(),"");
            else {
                name = s.substring(0, index).trim();
                value = s.substring(index + 1, s.length()).trim();
                map.put(name, value);
            }

        }

    }

    public static Map<String,String> getCookieMap(HttpExchange http) {
        Map<String, String> map = (Map<String, String>) http.getAttribute(COOKIE_KEY);
        if (map != null) return map;
        map = new HashMap<String, String>();
        List<String> cookieList = http.getRequestHeaders().get("Cookie");
        if (cookieList != null) {
            for (String cookie : cookieList) {
                parseCookie(cookie, map);
            }
        }
        http.setAttribute(COOKIE_KEY,map);
        return map;
    }

    public static Session getSession(HttpExchange http){
        return  (Session)http.getAttribute(SESSION_KEY);
    }
    public static Session createSession(HttpExchange http){
        String sessId=HttpHelper.getCookieMap(http).get(Session.NAME);
        Session session= SessionManager.instance.authenticate(sessId);
        if (session!=null) http.setAttribute(SESSION_KEY,session);
        return session;
    }
    public static void redirect(HttpExchange http,String adress) throws IOException {

        http.getResponseHeaders().add("Location", adress);
        http.sendResponseHeaders(302, 0);
    }

    public static void setCookie(HttpExchange http,String name,String value,boolean httpOnly,int maxAge){
        StringBuilder builder = new StringBuilder().append(name).append("=").append(value);
        if (maxAge>0) builder.append(";Max-Age=").append(maxAge);
        if (httpOnly) builder.append(";HttpOnly");
        http.getResponseHeaders().add("Set-Cookie", builder.toString());

    }
    public static void deleteCookie(HttpExchange http,String name){


        http.getResponseHeaders().add("Set-Cookie", name+"=");

    }
    public static void logout(HttpExchange http){
        Session session = getSession(http);
        if(session==null) {
            session=createSession(http);
            if (session==null)return;
        }
        SessionManager.instance.terminateSession(session.id);
        deleteCookie(http,Session.NAME);


    }
    public static void clearCache(HttpExchange http){
        http.setAttribute(COOKIE_KEY,null);
        http.setAttribute(SESSION_KEY,null);
        http.setAttribute(QUERY_KEY,null);
    }

}
