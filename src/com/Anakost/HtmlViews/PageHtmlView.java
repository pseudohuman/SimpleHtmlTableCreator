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

    private ArrayList<String> cssLinkList;
    public PageHtmlView addCssLink(String link){
        if (cssLinkList ==null){
            cssLinkList =new ArrayList<>();
        }
        cssLinkList.add(link);
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
                        if (cssLinkList !=null && cssLinkList.size()>0) {
                            for (String cssRef: cssLinkList){
                                writer.newLine()
                                .openTag("link").attribute("rel","stylesheet").attribute("href",cssRef).closeTag();
                            }
                        }
                writer.newLine()
                .endTag()
                .newLine()
                .startTag("body");

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
