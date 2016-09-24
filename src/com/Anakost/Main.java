package com.Anakost;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        
        Path file = Paths.get("C:\\Users\\Анатолій\\AppData\\Roaming\\Skype\\My Skype Received Files\\PersonalInfo(1).csv");
        Path html = Paths.get("D:\\htmlS\\table.html");
        LinesBuffer lines = new LinesBuffer();





        Files.lines(file).forEach(line->
               lines.load(line)

        );
        try(BufferedWriter writer = Files.newBufferedWriter(html)) {

            lines.write(writer);
        }

    }}

