package com.Anakost.Handlers;


import com.Anakost.*;
import com.Anakost.HtmlViews.HeaderHtmlView;
import com.Anakost.HtmlViews.PageHtmlView;
import com.Anakost.HtmlViews.GridHtmlView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Created by Анатолій on 27.09.2016.
 */
public class PersonalInfoHttpHandler implements HttpHandler {
    public static final String PATH = "/pi";

    @Override
    public void handle(HttpExchange http) throws IOException {


        try(IHtmlWriter htmlWriter = HttpHelper.getHtmlWriter(http, HttpURLConnection.HTTP_OK)) {

            new PageHtmlView()
                .title("Personal Information")
                .addChild(new HeaderHtmlView().setUsername(HttpHelper.getSession(http).principal.login))
                .addChild(new GridHtmlView(loadPersonalInfo()))
                .render(htmlWriter);

        }
    }

    private ITableModel loadPersonalInfo() throws IOException{
        PersonalInfoTableModel model=new PersonalInfoTableModel();
        try(InputStream stream=PersonalInfoHttpHandler.class.getResourceAsStream("/PersonalInfo.csv")) {
            PersonalInfoCsvParser.parse(new BufferedReader(new InputStreamReader(stream)),model);
        }

        return model;
    }
}
