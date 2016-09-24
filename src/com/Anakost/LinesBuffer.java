package com.Anakost;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Анатолій on 23.09.2016.
 */
public class LinesBuffer {
    String table = "<table border=\"3\">\n";
    boolean flag=true;
    public void load(String line){

        table+="<tr>\n";


        if(!flag) {
            for (String cell : line.split("\t")) {
                table += "\t<td>" + cell + "</td> \n";
                System.out.println(cell);
            }
        }else {
            for (String cell : line.split("\t")) {
                table += "\t<th>" + cell + "</th> \n";
                System.out.println(cell);
            }
        }
        table+="</tr>\n";
        System.out.println(line);
        flag=false;
    }

    public  void write(BufferedWriter writer) throws IOException {
        table+="</table>";
        for (char c : table.toCharArray()){
            writer.write(c);}

    }


}
