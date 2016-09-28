package com.Anakost.HtmlViews;

import java.io.Writer;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class TextHtmlView implements IHtmlView {
    private String text;
    public TextHtmlView(String text){
        this.text=text;

    }
    @Override
    public void render(Writer writer) throws Exception {
        writer.write(text);
    }
}
