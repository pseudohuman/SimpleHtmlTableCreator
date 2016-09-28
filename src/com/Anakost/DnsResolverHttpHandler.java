package com.Anakost;

import com.Anakost.HtmlViews.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class DnsResolverHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200,0);
        try(BufferedWriter writer =new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody(), StandardCharsets.UTF_8))) {
            new PageHtmlView()
                .title("Personal Information")
                .bodyAppend(
                    new FormHtmlView()
                        .action("")
                        .method("")
                        .addChild(new TextHtmlView("введите DNS:"))
                        .addChild(new InputTextHtmlView().name("DNS"))
                        .addChild(new InputSubmitHtmlView().text("Получить IP"))
                )
                .render(writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
