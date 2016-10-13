package com.Anakost;

import java.io.IOException;

public interface IHtmlAttributeWriter{
    IHtmlAttributeWriter attribute(String name, CharSequence value) throws IOException;// name=value
    IHtmlAttributeWriter attribute(String name) throws IOException;//name
    IHtmlWriter closeTag() throws IOException; // >
    IHtmlAttributeWriter newLine() throws IOException;
}
