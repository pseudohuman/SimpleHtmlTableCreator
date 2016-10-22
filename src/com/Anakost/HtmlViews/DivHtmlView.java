package com.Anakost.HtmlViews;

import com.Anakost.IHtmlAttributeWriter;
import com.Anakost.IHtmlWriter;

import java.io.IOException;

/**
 * Created by Анатолій on 30.09.2016.
 */
public class DivHtmlView extends ContentElementHtmlView<DivHtmlView> {


    @Override
    public void render(IHtmlWriter writer) throws IOException {
        IHtmlAttributeWriter attrWriter = writer.openStartTag("div");
        if (classSet!=null && classSet.size()>0) attrWriter.attribute("class",String.join(" ",classSet));
        attrWriter.closeTag();
        for (IHtmlView view: children){
            view.render(writer);
        }

        writer.endTag();
    }
}
