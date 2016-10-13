package com.Anakost;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Анатолій on 03.10.2016.
 */
public interface IHtmlWriter extends Closeable {
    IHtmlWriter tag(String tagName) throws IOException;//<br>
    IHtmlWriter startTag(String tagName) throws IOException;//<tagName>
    IHtmlAttributeWriter  openStartTag(String tagName) throws IOException;//<tagName
    IHtmlAttributeWriter  openTag(String tagName) throws IOException;//<tagName
    IHtmlWriter writeText(CharSequence text) throws IOException;
    IHtmlWriter endTag() throws IOException;//  </tagName>
    IHtmlWriter newLine() throws IOException;

}
