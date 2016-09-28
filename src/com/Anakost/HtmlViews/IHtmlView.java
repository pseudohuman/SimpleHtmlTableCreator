package com.Anakost.HtmlViews;

import java.io.Writer;

public interface IHtmlView {
    void render(Writer writer) throws Exception;
}
