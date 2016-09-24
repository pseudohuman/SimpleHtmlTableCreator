package com.Anakost;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        
//        Path file = Paths.get("C:\\Users\\Анатолій\\AppData\\Roaming\\Skype\\My Skype Received Files\\PersonalInfo(1).csv");
        Path html = Paths.get("D:\\htmlS\\table.html");
        LinesBuffer lines = new LinesBuffer();
        try(InputStream stream=ClassLoader.getSystemClassLoader().getResourceAsStream("PersonalInfo.csv")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            while (true) {
                String s=reader.readLine();
                if (s==null) break;
                lines.load(s);
            }

        }

//        Files.lines(file).forEach(line->
//               lines.load(line)
//
//        );
        try(BufferedWriter writer = Files.newBufferedWriter(html)) {

            lines.write(writer);
        }

    }}

