package com.Anakost;

import com.Anakost.Filters.AuthenticationFilter;
import com.Anakost.Filters.ClearCacheFilter;
import com.Anakost.Filters.ExceptionFilter;
import com.Anakost.Handlers.*;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        InetSocketAddress tcpAdress= new InetSocketAddress(8080);

        server.bind(tcpAdress,0);
        Filter clearCacheFilter = new ClearCacheFilter();
        List<Filter> filters =server.createContext(PersonalInfoHttpHandler.PATH,new PersonalInfoHttpHandler()).getFilters();
        filters.add(clearCacheFilter);
        filters.add(new AuthenticationFilter());

        filters=server.createContext(DnsResolverHttpHandler.PATH,new DnsResolverHttpHandler()).getFilters();
        filters.add(clearCacheFilter);
        filters.add(new AuthenticationFilter());

        server.createContext(LoginHttpHandler.PATH,new LoginHttpHandler()).getFilters().add(clearCacheFilter);

        filters=server.createContext(IndexHttpHandler.PATH,new IndexHttpHandler()).getFilters();
        filters.add(clearCacheFilter);
        filters.add(new AuthenticationFilter());

        filters=server.createContext(ExceptionTestHttpHandler.PATH,new ExceptionTestHttpHandler()).getFilters();
        filters.add(new ExceptionFilter());
        filters.add(clearCacheFilter);

        server.start();
        System.out.println("Server is running...");




    }}

