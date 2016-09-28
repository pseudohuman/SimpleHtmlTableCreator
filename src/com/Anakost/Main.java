package com.Anakost;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create();
        InetSocketAddress tcpAdress= new InetSocketAddress(8080);

        server.bind(tcpAdress,0);
//        server.createContext("/pi",new PersonalInfoHttpHandler());
        server.createContext("/pi",new DnsResolverHttpHandler());

        server.start();
        System.out.println("Server is running...");




    }}

