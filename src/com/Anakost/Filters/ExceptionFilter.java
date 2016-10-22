package com.Anakost.Filters;

import com.Anakost.HtmlViews.DivHtmlView;
import com.Anakost.HtmlViews.PageHtmlView;
import com.Anakost.HtmlViews.TextHtmlView;
import com.Anakost.HttpHelper;
import com.Anakost.IHtmlWriter;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.HttpURLConnection;


/**
 * Created by Анатолій on 12.10.2016.
 */
public class ExceptionFilter extends Filter {

    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
        OutputStream responseStream = httpExchange.getResponseBody();
        try {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            httpExchange.setStreams(httpExchange.getRequestBody(),outputStream);
            chain.doFilter(httpExchange);
            httpExchange.sendResponseHeaders((int)httpExchange.getAttribute(HttpHelper.STATUS_CODE_KEY),0);
            outputStream.close();
            outputStream.writeTo(responseStream);


        }catch (Exception e){
            e.printStackTrace();
            httpExchange.getResponseHeaders().clear();
            HttpHelper.setContentType(httpExchange,"text/html");
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR,0);
            httpExchange.setStreams(httpExchange.getRequestBody(),responseStream);

            try(IHtmlWriter htmlWriter = HttpHelper.getHtmlWriter(httpExchange, HttpURLConnection.HTTP_INTERNAL_ERROR)) {
                new PageHtmlView().addChild(new DivHtmlView().addChild(new TextHtmlView("Something Bad Happens"))).render(htmlWriter);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }finally {
            responseStream.close();
        }

    }

    @Override
    public String description() {
        return null;
    }
}
