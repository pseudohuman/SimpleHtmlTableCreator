package com.Anakost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Анатолій on 24.09.2016.
 */
public class PersonalInfoCsvParser {
    public static void parse(BufferedReader reader, IPersonalInfoConsumer consumer) throws IOException {
        String s =reader.readLine();
        if(s==null) return;
        consumer.setHeaders(s.split("\t"));

        while (true) {
            s=reader.readLine();
            if (s==null) break;

            consumer.append(parseString(s));

        }


    }
    private static PersonalInfo parseString(String s) {
        PersonalInfo info = new PersonalInfo();
        String[] fields=s.split("\t");
        info.name=fields[0];
        info.adress=fields[1];
        info.city=fields[2];
        info.country=fields[3];

        return info;
    }


}
