package com.Anakost.HtmlViews;

import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Анатолій on 27.09.2016.
 */
public class PageHtmlView implements IHtmlView {

    private String title;
    public PageHtmlView title(String title){
        this.title=title;
        return this;
    }

    private final ArrayList<IHtmlView> body=new ArrayList<>();
    public PageHtmlView bodyAppend(IHtmlView view){
        body.add(view);
        return this;
    }

    @Override
    public void render(Writer writer) throws Exception {
        writer.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "   <head>\n" +
                "      <meta charset=\"utf-8\">\n" +
                "      <title>" + this.title+ "</title>\n" +
                "   </head>\n" +
                "   <body>\n"
        );
        for (IHtmlView view:body){
            view.render(writer);
        }
        writer.write( "   </body>\n" +
                "</html>");

    }

}
