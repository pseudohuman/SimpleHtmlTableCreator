package com.Anakost.HtmlViews;

import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class FormHtmlView implements IHtmlView {
    @Override
    public void render(Writer writer) throws Exception {
        writer.write("<form action="+action+"method="+method+">");

        for (IHtmlView view:children){
            view.render(writer);
        }
        writer.write("</from>");

    }
    private String action;
    public FormHtmlView action(String action){
        this.action = action;
        return this;
    }

    private final ArrayList<IHtmlView> children=new ArrayList<>();
    public FormHtmlView addChild(IHtmlView view){
        children.add(view);
        return this;
    }
    private String method;
    public FormHtmlView method(String method){
        this.method =method;
        return this;
    }




}
