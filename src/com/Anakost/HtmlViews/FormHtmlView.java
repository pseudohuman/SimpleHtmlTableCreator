package com.Anakost.HtmlViews;

import com.Anakost.IHtmlAttributeWriter;
import com.Anakost.IHtmlWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class FormHtmlView implements IHtmlView {
////    @Override
//    public void render(Writer writer) throws Exception {
//        writer.write("<form action=\""+action+"\" method=\""+method+"\">\n");
//
//        for (IHtmlView view:children){
////            view.render(writer);
//        }
//        writer.write("</form>\n");
//
//    }
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


    @Override
    public void render(IHtmlWriter writer) throws IOException {
        IHtmlAttributeWriter attrWriter=
            writer
            .newLine()
            .openStartTag("form");

            if (action!=null) attrWriter.attribute("action",action);
            if (method!=null) attrWriter.attribute("method",method);
            attrWriter.closeTag()
            .newLine();
        for (IHtmlView view:children){
            view.render(writer);
        }
        writer
            .newLine()
            .endTag()
            .newLine();

    }
}
