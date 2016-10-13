package com.Anakost.Filters;

import com.Anakost.HttpHelper;
import com.Anakost.Session;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Анатолій on 05.10.2016.
 */
public class AuthenticationFilter extends Filter {

    @Override
    public void doFilter(HttpExchange http, Chain chain) throws IOException {
        Session session = HttpHelper.createSession(http);

        if (session != null) chain.doFilter(http);
        else {
            // /path?query#fragment
            URI uri = http.getRequestURI();
            HttpHelper.redirect(http, "/login?" + HttpHelper.POST_LOGIN_URL_NAME + "=" + getUrlPath(uri));
        }
    }

    private String getUrlPath(URI uri) {
        StringBuilder builder = new StringBuilder().append(uri.getPath());
        String query = uri.getQuery();
        if (query != null) builder.append("?").append(query);
        String fragment = uri.getFragment();
        if (fragment != null) builder.append("#").append(uri.getFragment());
        return builder.toString();

    }

    @Override
    public String description() {
        return "Authorizer";
    }


}
