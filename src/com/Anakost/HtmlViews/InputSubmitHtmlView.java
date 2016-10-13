package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;

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
    public void render(IHtmlWriter writer) throws IOException {
        writer.openTag("input")
            .attribute("type","submit")
            .attribute("value",text)
            .closeTag();

    }
}
