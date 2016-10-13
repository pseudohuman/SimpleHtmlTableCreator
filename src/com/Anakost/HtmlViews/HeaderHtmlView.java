package com.Anakost.HtmlViews;

import com.Anakost.Handlers.LoginHttpHandler;
import com.Anakost.IHtmlWriter;

import java.io.IOException;

/**
 * Created by Анатолій on 07.10.2016.
 */
public class HeaderHtmlView implements IHtmlView {


    String username;

    public HeaderHtmlView setUsername(String username){
        this.username=username;
        return this;
    }
    @Override

    public void render(IHtmlWriter writer) throws IOException {
        writer
            .openStartTag("div").attribute("style","display:inline-block;float:right;background:#baf0f2;border: 2px solid #0f0247;").closeTag()
                .openStartTag("span").attribute("style","color:blue;border:1px solid;font-weight:600").closeTag()
                    .writeText(username)
                    .writeText(" ")

                .endTag()
                .openStartTag("a").attribute("href", LoginHttpHandler.PATH+"?"+LoginHttpHandler.LOGOUT_PARAM).attribute("style","color:red").closeTag()
                .writeText("Logout")
                .endTag()

            .endTag()
        ;

    }
}
