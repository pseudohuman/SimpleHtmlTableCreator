package com.Anakost.Handlers;

import com.Anakost.HtmlWriter;
import com.Anakost.HtmlViews.HeaderHtmlView;
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
 * Created by Анатолій on 07.10.2016.
 */
public class IndexHttpHandler implements HttpHandler {
    public static final String PATH = "/";

    @Override
    public void handle(HttpExchange http) throws IOException {

        try (IHtmlWriter htmlTagWriter = HttpHelper.getHtmlWriter(http,HttpURLConnection.HTTP_OK)) {

            new PageHtmlView()
                .title("Index Page").addChild((writer1) -> {
                writer1.
                openStartTag("div").attribute("style","text-align:center;").closeTag()
                    .openStartTag("a").attribute("href",DnsResolverHttpHandler.PATH).closeTag()
                        .writeText("DNSResolver")
                    .endTag()
                .endTag()
                .openStartTag("div").attribute("style","text-align:center").closeTag()
                    .openStartTag("a").attribute("href",PersonalInfoHttpHandler.PATH).closeTag()
                        .writeText("Personal Info")
                    .endTag()
                .endTag()
                ;
            }).addChild(new HeaderHtmlView().setUsername(HttpHelper.getSession(http).principal.login)).render(htmlTagWriter);


        }


    }
}
