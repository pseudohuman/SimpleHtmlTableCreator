package com.Anakost.Handlers;

import com.Anakost.*;
import com.Anakost.HtmlViews.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by Анатолій on 05.10.2016.
 */
public class LoginHttpHandler implements HttpHandler {
    public static final String PATH = "/login";
    public static final String LOGOUT_PARAM ="logout" ;

    private final String loginName = "Login";
    private final String passwordName = "Password";

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        switch (httpExchange.getRequestMethod()) {
            case "GET":
                handleGet(httpExchange);
                break;
            case "POST":
                handlePost(httpExchange);
                break;
            default:
                httpExchange.sendResponseHeaders(405, 0);
                break;
        }

    }

    private void handleGet(HttpExchange httpExchange) throws IOException {
        if (HttpHelper.getQueryMap(httpExchange).containsKey(LOGOUT_PARAM)) HttpHelper.logout(httpExchange);
        else if (HttpHelper.createSession(httpExchange) != null) {
            HttpHelper.redirect(httpExchange, IndexHttpHandler.PATH);
            return;
        }
        httpExchange.sendResponseHeaders(200, 0);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody(), StandardCharsets.UTF_8))) {
            IHtmlWriter htmlTagWriter = new HtmlWriter(writer);
            createPage(null, null)
                .render(htmlTagWriter);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handlePost(HttpExchange httpExchange) throws IOException {

        HttpHelper.logout(httpExchange);

        Map<String, String> queryMap = HttpHelper.getQueryMap(httpExchange);
        String login = queryMap.get(loginName);
        String password = queryMap.get(passwordName);

        Session session = SessionManager.instance.authorize(login, password);
        if (session != null) {
            HttpHelper.setCookie(httpExchange, Session.NAME, session.id, true, 3600);
            String postLoginUrl = queryMap.get(HttpHelper.POST_LOGIN_URL_NAME);
            HttpHelper.redirect(httpExchange, postLoginUrl != null ? postLoginUrl : IndexHttpHandler.PATH);
        } else {
            httpExchange.sendResponseHeaders(200, 0);
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody(), StandardCharsets.UTF_8))) {
                IHtmlWriter htmlTagWriter = new HtmlWriter(writer);
                createPage(new DivHtmlView().addChild(new TextHtmlView("Invalid login or password !")), login)
                    .render(htmlTagWriter);

            }
        }


    }

    private PageHtmlView createPage(IHtmlView error,String login) {
        return new PageHtmlView()
            .title("Login")
            .addChild(error)
            .addChild(
                new FormHtmlView()
                    .method("POST")
                    .addChild((writer)->{
                        writer.newLine()
                        .openStartTag("div").attribute("style","text-align:center").closeTag().newLine()

                            .openStartTag("table").attribute("style","display:inline-block;background:#e3eef4;border-radius:8px;box-shadow: -10px 14px 10px 6px rgba(0,0,0,0.32);").closeTag().newLine()
                                .startTag("tr").newLine()
                                    .startTag("td").writeText("Логин: ").endTag()
                                    .startTag("td");
                                        new InputTextHtmlView().name(loginName).value(login).render(writer);
                                    writer.endTag().newLine()
                                .endTag().newLine()
                                .startTag("tr").newLine()
                                    .startTag("td").writeText("Пароль: ").endTag()
                                    .startTag("td");
                                        new InputTextHtmlView().name(passwordName).render(writer);
                                    writer.endTag().newLine()
                                .endTag().newLine()
                                .startTag("tr").openStartTag("td").attribute("colspan","2").closeTag();
                                    new InputSubmitHtmlView().text("Войти").render(writer);
                                writer.endTag().endTag().newLine()

                            .endTag().newLine()
                        .endTag().newLine();

                    }));


    }
}
