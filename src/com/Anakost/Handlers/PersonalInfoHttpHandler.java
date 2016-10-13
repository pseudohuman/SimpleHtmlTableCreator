package com.Anakost.Handlers;


import com.Anakost.*;
import com.Anakost.HtmlViews.HeaderHtmlView;
import com.Anakost.HtmlViews.PageHtmlView;
import com.Anakost.HtmlViews.GridHtmlView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Анатолій on 27.09.2016.
 */
public class PersonalInfoHttpHandler implements HttpHandler {
    public static final String PATH = "/pi";

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200,0);
        try(BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(exchange.getResponseBody(), StandardCharsets.UTF_8))) {
            HtmlWriter htmlWriter = new HtmlWriter(writer);
            new PageHtmlView()
                .title("Personal Information")
                .addChild(new HeaderHtmlView().setUsername(HttpHelper.getSession(exchange).principal.login))
                .addChild(new GridHtmlView(loadPersonalInfo()))
                .render(htmlWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ITableModel loadPersonalInfo() throws Exception{
        PersonalInfoTableModel model=new PersonalInfoTableModel();
        try(InputStream stream=ClassLoader.getSystemClassLoader().getResourceAsStream("PersonalInfo.csv")) {
            PersonalInfoCsvParser.parse(new BufferedReader(new InputStreamReader(stream)),model);
        }

        return model;
    }
}
