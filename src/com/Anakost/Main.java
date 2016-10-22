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
        Filter exceptionFilter = new ExceptionFilter();
        Filter authenticationFilter = new AuthenticationFilter();
        List<Filter> filters =server.createContext(PersonalInfoHttpHandler.PATH,new PersonalInfoHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);
        filters.add(authenticationFilter);

        filters=server.createContext(DnsResolverHttpHandler.PATH,new DnsResolverHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);
        filters.add(authenticationFilter);

        filters=server.createContext(LoginHttpHandler.PATH,new LoginHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);


        filters=server.createContext(IndexHttpHandler.PATH,new IndexHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);
        filters.add(authenticationFilter);

        filters=server.createContext(ExceptionTestHttpHandler.PATH,new ExceptionTestHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);

        filters=server.createContext(ResourceHttpHandler.PATH,new ResourceHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);

        filters=server.createContext("/favicon.ico",new ResourceHttpHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);

        filters=server.createContext(FileLoadHandler.PATH,new FileLoadHandler()).getFilters();
        filters.add(exceptionFilter);
        filters.add(clearCacheFilter);
        server.start();
        System.out.println("Server is running...");




    }}

