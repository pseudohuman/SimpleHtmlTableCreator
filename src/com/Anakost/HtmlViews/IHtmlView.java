package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;

public interface IHtmlView {
    void render(IHtmlWriter writer) throws IOException;
}
