package com.Anakost.Handlers;

import com.Anakost.HttpHelper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;

/**
 * Created by Анатолій on 21.10.2016.
 */
public class FileLoadHandler implements HttpHandler {
    public static final String PATH = "/upload";
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {



        if (httpExchange.getRequestMethod().equals("POST")) {
            HttpHelper.setStatusCode(httpExchange, HttpURLConnection.HTTP_OK);
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()))){
            reader.lines().forEach(s -> System.out.println(s));}

        }else{
            HttpHelper.redirect(httpExchange,IndexHttpHandler.PATH);
        }
    }
}
