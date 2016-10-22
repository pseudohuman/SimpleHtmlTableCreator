package com.Anakost.Handlers;

import com.Anakost.HttpHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Анатолій on 16.10.2016.
 */
public class ResourceHttpHandler implements HttpHandler {
    public static final String PATH="/res";
    private static final Map<String, String> map= new HashMap<>();
    private static final Map<String, String> mimeTypeMap= new HashMap<>();
    static {
        map.put("/favicon.ico","/site-icon.png");
        map.put("/res/1","/style.css");

        mimeTypeMap.put("ico","image/x-icon");
        mimeTypeMap.put("png","image/png");
        mimeTypeMap.put("gif","image/gif");
        mimeTypeMap.put("jpeg","image/jpeg");
        mimeTypeMap.put("jpg","image/jpeg");
        mimeTypeMap.put("txt", "text/plain");
        mimeTypeMap.put("html", "text/html");
        mimeTypeMap.put("htm", "text/html");
        mimeTypeMap.put("css", "text/css");
        mimeTypeMap.put("js", "application/javascript");
        mimeTypeMap.put("json", "application/json");
        mimeTypeMap.put("xml", "application/xml");
        mimeTypeMap.put("xhtml", "application/xhtml+xml");



    }
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().getPath();
        String resName = map.get(path);

        if (resName == null) {
            HttpHelper.setStatusCode(httpExchange, HttpURLConnection.HTTP_NOT_FOUND);
            return;
        }
        String mimeType = getMimeType(resName);
        if (mimeType==null){
            HttpHelper.setStatusCode(httpExchange,HttpURLConnection.HTTP_NOT_FOUND);
            return;
        }
        HttpHelper.setContentType(httpExchange,mimeType);

        HttpHelper.setStatusCode(httpExchange, HttpURLConnection.HTTP_OK);
        try (InputStream stream = ResourceHttpHandler.class.getResourceAsStream(resName)) {
            if (stream==null) throw new IOException(String.format("the resource '%s' is not found.",resName));
            OutputStream outputStream=httpExchange.getResponseBody();
            byte[] buffer= new byte[stream.available()];
            stream.read(buffer,0,stream.available());
            outputStream.write(buffer);

        }
    }
    private static String getMimeType(String resName){
        int index = resName.lastIndexOf(".");
        if (index==-1||index==resName.length()-1)return null;
        return mimeTypeMap.get(resName.substring(index+1,resName.length()));

    }
}
