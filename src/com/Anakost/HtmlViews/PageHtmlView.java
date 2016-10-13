package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;
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
    public PageHtmlView addChild(IHtmlView view){
        if (view!=null) {
            body.add(view);
        }
        return this;
    }

    @Override
    public void render(IHtmlWriter writer) throws IOException {
        writer
            .openTag("!DOCTYPE")
                .attribute("html")
            .closeTag()
            .newLine()
            .startTag("html")
                .newLine()
                .startTag("head")
                    .newLine()
                        .openTag("meta")
                        .attribute("charset","utf-8")
                        .closeTag()
                        .newLine();
                        if (title!=null) writer.startTag("title").writeText(this.title).endTag();
                writer.newLine()
                .endTag()
                .newLine()
                .startTag("body");

//        writer.write("<!DOCTYPE html>\n" +
//                "<html>\n" +
//                "   <head>\n" +
//                "      <meta charset=\"utf-8\">\n" +
//                "      <title>" + this.title+ "</title>\n" +
//                "   </head>\n" +
//                "   <body>\n"
//        );
        for (IHtmlView view:body){
            view.render(writer);
        }
        writer
            .newLine()
            .endTag()
            .newLine()
            .endTag();

    }

}
