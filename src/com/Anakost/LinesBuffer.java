package com.Anakost;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Анатолій on 23.09.2016.
 */
public class LinesBuffer {

    String table = "<table border=\"3\">\n";
    String topHtml="<!DOCTYPE html>\n" +
            "<html>\n" +
            "   <head>\n" +
            "      <meta charset=\"utf-8\">\n" +
            "      <title>\n" +
            "         (Это title) Пример страницы на HTML5\n" +
            "      </title>\n" +
            "   </head>\n" +
            "   <body>"
            ;
    String bottomHtml ="   </body>\n" +
            "</html>";
    boolean flag=true;
    public void load(String line){

        table+="\t\t<tr>\n";


        if(!flag) {
            for (String cell : line.split("\t")) {
                table += "\t\t\t<td>" + cell + "</td> \n";
                System.out.println(cell);
            }
        }else {
            for (String cell : line.split("\t")) {
                table += "\t\t\t<th>" + cell + "</th> \n";
                System.out.println(cell);
            }
        }
        table+="\t\t</tr>\n";
        System.out.println(line);
        flag=false;
    }

    public  void write(BufferedWriter writer) throws IOException {
        table+="\t</table>";
        String html=topHtml+"\n"+table+"\n"+bottomHtml;
        for (char c : html.toCharArray()){
            writer.write(c);}

    }


}
