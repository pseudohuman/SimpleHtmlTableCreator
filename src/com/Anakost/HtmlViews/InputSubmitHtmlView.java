package com.Anakost.HtmlViews;

import java.io.Writer;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class InputSubmitHtmlView implements IHtmlView {

    private String text;
    public InputSubmitHtmlView text(String text){
        this.text=text;
        return this;
    }
    @Override
    public void render(Writer writer) throws Exception {
        writer.write("<input type=\"submit\""+" value=\""+text+"\">");

    }
}
