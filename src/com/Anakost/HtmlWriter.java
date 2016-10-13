package com.Anakost;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Анатолій on 03.10.2016.
 */
public class HtmlWriter implements IHtmlWriter {
    private final Writer writer;
    private final String indent="\t";
    private final String newLine="\n";
    private final char quote='"';
    private final String quoteEntity=	"&quot;";
    private final char inlineSeparator=' ';
    private final IHtmlAttributeWriter attrWriter=new HtmlAttributeWriter();
    private final ArrayList<String> tags=new ArrayList<String>();
    private boolean isNewline=false;

    public HtmlWriter(Writer writer){
        this.writer=writer;
    }

    @Override
    public IHtmlWriter tag(String tagName) throws IOException {
        if (isNewline) writeIndent();
        writer.write("<");
        writer.write(tagName);
        writer.write(">");
        return this;
    }

    @Override
    public IHtmlWriter startTag(String tagName) throws IOException {
        if (isNewline) writeIndent();
        writer.write("<");
        writer.write(tagName);
        writer.write(">");
        tags.add(tagName);
        return this;
    }

    @Override
    public IHtmlAttributeWriter openStartTag(String tagName) throws IOException {
        if (isNewline) writeIndent();
        writer.write("<");
        writer.write(tagName);
        tags.add(tagName);
        return attrWriter;
    }

    @Override
    public IHtmlAttributeWriter openTag(String tagName) throws IOException {
        if (isNewline) writeIndent();
        writer.write("<");
        writer.write(tagName);
        return attrWriter;
    }

    @Override
    public IHtmlWriter writeText(CharSequence text) throws IOException {
        if (isNewline) writeIndent();
        for (int i=0;i<text.length();i++){
            char ch = text.charAt(i);
            if(ch=='<') writer.write("&lt;");
            else  if (ch=='>') writer.write("&gt;");
            else writer.write(text.charAt(i));
        }
        return this;
    }

    @Override
    public IHtmlWriter endTag() throws IOException {
        String tagName=tags.remove(tags.size()-1);
        if (isNewline) writeIndent();
        writer.write("</");
        writer.write(tagName);
        writer.write(">");
        return this;
    }

    @Override
    public IHtmlWriter newLine() throws IOException {
        writer.write(newLine);
        isNewline=true;
        return this;
    }

    private void writeIndent() throws IOException {
        isNewline=false;
        int nestingLevel = tags.size();

        for (int i=0;i<nestingLevel;i++){
            writer.write(indent);
        }

    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

    public class HtmlAttributeWriter implements IHtmlAttributeWriter {
        @Override
        public IHtmlAttributeWriter attribute(String name, CharSequence value) throws IOException {
            if (isNewline) writeIndent();
            else  writer.write(inlineSeparator);
            writer.write(name);
            writer.write("=");
            writer.write(quote);
            for (int i = 0;i<value.length();i++) {
                char ch = value.charAt(i);
                if (ch==quote) writer.write(quoteEntity);
                else if (ch=='&') writer.write("&amp;");
                else writer.write(ch);
            }
            writer.write(quote);

            return this;
        }

        @Override
        public IHtmlAttributeWriter attribute(String name) throws IOException {
            if (isNewline) writeIndent();
            else  writer.write(inlineSeparator);
            writer.write(name);
            return this;
        }



        @Override
        public IHtmlWriter closeTag() throws IOException {
            if (isNewline) writeIndent();
            else  writer.write(inlineSeparator);
            writer.write(">");
            return HtmlWriter.this;
        }

        @Override
        public IHtmlAttributeWriter newLine() throws IOException {
            writer.write(newLine);
            isNewline=true;
            return this;
        }
    }

}
