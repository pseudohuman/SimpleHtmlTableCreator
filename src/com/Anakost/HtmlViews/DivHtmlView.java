package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Анатолій on 30.09.2016.
 */
public class DivHtmlView implements IHtmlView {
    private final ArrayList<IHtmlView> children =new ArrayList<>();
    public DivHtmlView addChild(IHtmlView view){
        children.add(view);
        return this;
    }

    @Override
    public void render(IHtmlWriter writer) throws IOException {
        writer.startTag("div");
        for (IHtmlView view: children){
            view.render(writer);
        }
        writer.endTag();
    }
}
