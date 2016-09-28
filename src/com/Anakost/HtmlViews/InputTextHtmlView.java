package com.Anakost.HtmlViews;

import java.io.Writer;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class InputTextHtmlView implements IHtmlView{
    private  String name;

    public InputTextHtmlView name(String name){
        this.name=name;
        return this;
    }
    @Override
    public void render(Writer writer) throws Exception {
        writer.write("<input type=\"text\" name="+name+">");
    }
}
