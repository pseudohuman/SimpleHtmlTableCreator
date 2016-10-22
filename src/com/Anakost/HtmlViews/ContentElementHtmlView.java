package com.Anakost.HtmlViews;

import java.util.ArrayList;

/**
 * Created by Анатолій on 17.10.2016.
 */
public abstract class ContentElementHtmlView<T extends ContentElementHtmlView> extends BaseElementHtmlView<T> {
    protected final ArrayList<IHtmlView> children =new ArrayList<>();
    public T addChild(IHtmlView view){
        children.add(view);
        return (T)this;
    }
}
