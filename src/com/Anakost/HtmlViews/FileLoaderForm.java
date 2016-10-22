package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;

/**
 * Created by Анатолій on 21.10.2016.
 */
public class FileLoaderForm implements IHtmlView{

    @Override
    public void render(IHtmlWriter writer) throws IOException {
        writer.openStartTag("form")
            .attribute("action","http://localhost:8080/upload")
            .attribute("enctype","multipart/form-data")
            .attribute("method","post")
            .closeTag()
                .openTag("input").attribute("type","file").attribute("value","\"Выбрать файл\"").closeTag()
                .openTag("input").attribute("type","submit").attribute("type","\"Отправить\"").closeTag()
            .endTag();

    }
}
