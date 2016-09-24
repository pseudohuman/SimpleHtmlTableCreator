package com.Anakost;

import java.io.*;

/**
 * Created by Анатолій on 24.09.2016.
 */
public class PersonalInfoPageHtmlView implements IHtmlView {
    public void render(Writer writer) throws Exception{
        PersonalInfoTableModel model=new PersonalInfoTableModel();
        try(InputStream stream=ClassLoader.getSystemClassLoader().getResourceAsStream("PersonalInfo.csv")) {
            PersonalInfoCsvParser.parse(new BufferedReader(new InputStreamReader(stream)),model);


        }
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "   <head>\n" +
                "      <meta charset=\"utf-8\">\n" +
                "      <title>\n" +
                "         (Это title) Пример страницы на HTML5\n" +
                "      </title>\n" +
                "   </head>\n" +
                "   <body>\n"
               );
          new TableHtmlView(model ).render(writer);
        writer.write( "   </body>\n" +
                "</html>");
    }
}
