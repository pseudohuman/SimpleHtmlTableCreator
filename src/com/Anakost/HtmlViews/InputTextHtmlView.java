package com.Anakost.HtmlViews;

import com.Anakost.IHtmlAttributeWriter;
import com.Anakost.IHtmlWriter;

import java.io.IOException;

/**
 * Created by Анатолій on 28.09.2016.
 */
public class InputTextHtmlView implements IHtmlView{
    private  String name;
    private  String value;

    public InputTextHtmlView name(String name){
        this.name=name;
        return this;
    }
    public InputTextHtmlView value(String value){
        this.value=value;
        return this;
    }

    @Override
    public void render(IHtmlWriter writer) throws IOException {
        IHtmlAttributeWriter attributeWriter=writer
            .openTag("input").attribute("type","text").attribute("style","box-shadow: rgba(0, 0, 0, 0.2) 1px 3px 2px inset; border-radius: 5px;");
                if (name!=null)attributeWriter.attribute("name",name);
                if (value!=null)attributeWriter.attribute("value",value);
            attributeWriter.closeTag()
        ;

    }
}
