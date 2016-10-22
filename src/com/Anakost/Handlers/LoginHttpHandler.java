package com.Anakost.Handlers;

import com.Anakost.*;
import com.Anakost.HtmlViews.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
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
    public void handle(HttpExchange http) throws IOException {

        switch (http.getRequestMethod()) {
            case "GET":
                handleGet(http);
                break;
            case "POST":
                handlePost(http);
                break;
            default:
                HttpHelper.setStatusCode(http,HttpURLConnection.HTTP_BAD_METHOD);
                break;
        }

    }

    private void handleGet(HttpExchange http) throws IOException {
        if (HttpHelper.getQueryMap(http).containsKey(LOGOUT_PARAM)) HttpHelper.logout(http);
        else if (HttpHelper.createSession(http) != null) {
            HttpHelper.redirect(http, IndexHttpHandler.PATH);
            return;
        }
        try (IHtmlWriter htmlWriter = HttpHelper.getHtmlWriter(http, HttpURLConnection.HTTP_OK)) {

            createPage(null, null)
                .render(htmlWriter);

        }

    }

    private void handlePost(HttpExchange http) throws IOException {

        HttpHelper.logout(http);

        Map<String, String> queryMap = HttpHelper.getQueryMap(http);
        String login = queryMap.get(loginName);
        String password = queryMap.get(passwordName);

        Session session = SessionManager.instance.authorize(login, password);
        if (session != null) {
            HttpHelper.setCookie(http, Session.NAME, session.id, true, 3600);
            String postLoginUrl = queryMap.get(HttpHelper.POST_LOGIN_URL_NAME);
            HttpHelper.redirect(http, postLoginUrl != null ? postLoginUrl : IndexHttpHandler.PATH);
        } else {

            try (IHtmlWriter htmlWriter = HttpHelper.getHtmlWriter(http, HttpURLConnection.HTTP_OK)) {
                createPage(new DivHtmlView().addChild(new TextHtmlView("Invalid login or password !")), login)
                    .render(htmlWriter);
            }
        }


    }

    private PageHtmlView createPage(IHtmlView error,String login) {
        return new PageHtmlView()
            .title("Login")
            .addCssLink("/res/1")
            .addChild(error)
            .addChild(
                new FormHtmlView()
                    .method("POST")
                    .addChild((writer)->{
                        writer.newLine()
                        .openStartTag("div").attribute("style","text-align:center").closeTag().newLine()

                            .openStartTag("table").attribute("class","login-form").closeTag().newLine()
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
