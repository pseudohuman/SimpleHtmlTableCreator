package com.Anakost;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        
//        Path file = Paths.get("C:\\Users\\Анатолій\\AppData\\Roaming\\Skype\\My Skype Received Files\\PersonalInfo(1).csv");
        Path htmlFile = Paths.get("D:\\htmlS\\PersonalInfo.html");
//        LinesBuffer lines = new LinesBuffer();
//        try(InputStream stream=ClassLoader.getSystemClassLoader().getResourceAsStream("PersonalInfo.csv")) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//            while (true) {
//                String s=reader.readLine();
//                if (s==null) break;
//                lines.load(s);
//            }
//
//        }
//
//
////        Files.lines(file).forEach(line->
////               lines.load(line)
////
////        );
        try(BufferedWriter writer = Files.newBufferedWriter(htmlFile)) {

            new PersonalInfoPageHtmlView().render(writer);
        }

    }}

