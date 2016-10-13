package com.Anakost.Handlers;

import com.Anakost.HttpHelper;
import com.Anakost.HtmlWriter;
import com.Anakost.HtmlViews.*;
import com.Anakost.IHtmlWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class DnsResolverHttpHandler implements HttpHandler {
    public static final String PATH = "/dns";

    private final String fieldName = "DNS";
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        httpExchange.sendResponseHeaders(200, 0);
        Map<String,String> map= HttpHelper.getQueryMap(httpExchange);
        String dnsValue=map.get(fieldName);

        DivHtmlView addressDiv=null;
        if(dnsValue!=null&&dnsValue.length()>0){
            try{
                addressDiv=new DivHtmlView().addChild(new TextHtmlView(InetAddress.getByName(dnsValue).getHostAddress()));
            } catch (UnknownHostException e){

                addressDiv=new DivHtmlView().addChild(new TextHtmlView("Неверный host"));
            }

        }
//        DivHtmlView varDiv=null;
//        if (!map.isEmpty()) varDiv=new DivHtmlView().addChild(new TextHtmlView(createText(map)));
        TextHtmlView varDiv=null;
        if (!map.isEmpty()) varDiv=new TextHtmlView(createText(map));

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody(), StandardCharsets.UTF_8))) {
            IHtmlWriter htmlTagWriter = new HtmlWriter(writer);
            new PageHtmlView()
                    .title("Personal Information")
                    .addChild(new HeaderHtmlView().setUsername(HttpHelper.getSession(httpExchange).principal.login))
                    .addChild(varDiv)
                    .addChild(addressDiv)
                    .addChild(
                            new FormHtmlView()
//                                    .action("")
                                    .method("POST")
                                    .addChild(new TextHtmlView("введите DNS: "))
                                    .addChild(new InputTextHtmlView().name(fieldName))
                                    .addChild(new InputSubmitHtmlView().text("Получить IP"))
                    )

                    .render(htmlTagWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StringBuilder createText(Map<String,String> stringHashMap){
        StringBuilder finalPageText= new StringBuilder();
        for (Map.Entry<String,String> entry : stringHashMap.entrySet()){
            finalPageText.append(entry.getKey()).append("=").append(entry.getValue()).append("; ");

        }
        return finalPageText;

    }


}

