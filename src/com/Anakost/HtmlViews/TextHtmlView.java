package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class TextHtmlView implements IHtmlView {
    private CharSequence text;
    public TextHtmlView(CharSequence text){
        this.text=text;

    }


    @Override
    public void render(IHtmlWriter writer) throws IOException {
        writer.writeText(text);
    }
}
