package com.Anakost.Filters;

import com.Anakost.HttpHelper;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

/**
 * Created by Анатолій on 06.10.2016.
 */
public class ClearCacheFilter extends Filter {
    @Override
    public void doFilter(HttpExchange httpExchange, Chain chain) throws IOException {
        HttpHelper.clearCache(httpExchange);
        chain.doFilter(httpExchange);
    }

    @Override
    public String description() {

        return "ClearCacheFilter";
    }
}
