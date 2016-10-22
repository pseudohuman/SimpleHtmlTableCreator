package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;

import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * Created by Анатолій on 17.10.2016.
 */
public abstract class BaseElementHtmlView<T extends BaseElementHtmlView> implements IHtmlView {
    protected LinkedHashSet<String> classSet;

    @Override
    public abstract void render(IHtmlWriter writer) throws IOException;

    public T addClass(String className) {
        if (classSet == null) {
            classSet = new LinkedHashSet<>();
        }
        classSet.add(className);
        return (T)this;
    }
}
