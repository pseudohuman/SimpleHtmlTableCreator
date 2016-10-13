package com.Anakost.Handlers;

import com.Anakost.HtmlWriter;
import com.Anakost.HtmlViews.PageHtmlView;
import com.Anakost.HttpHelper;
import com.Anakost.IHtmlWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * Created by Анатолій on 13.10.2016.
 */
public class ExceptionTestHttpHandler implements HttpHandler {
    public static final String PATH = "/ex";
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type","text/html");
        try (IHtmlWriter htmlTagWriter = HttpHelper.getHtmlWriter(httpExchange, HttpURLConnection.HTTP_OK)) {
            new PageHtmlView()
                .title("ExceptionTestHttpHandler").addChild((writer1) -> {
                writer1.
                    openStartTag("div").attribute("style","text-align:center;").closeTag()
                    .writeText("Hello World")
                    .endTag()
                ;
            }).render(htmlTagWriter);
        }
    }
}
