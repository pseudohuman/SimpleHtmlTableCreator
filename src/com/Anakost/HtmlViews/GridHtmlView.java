package com.Anakost.HtmlViews;

import com.Anakost.IHtmlWriter;
import com.Anakost.ITableModel;

import java.io.IOException;

/**
 * Created by Анатолій on 24.09.2016.
 */
public class GridHtmlView implements IHtmlView {

    private ITableModel model;

    public GridHtmlView(ITableModel model){
        this.model = model;

    }

    public void render(IHtmlWriter writer) throws IOException {
        writer
            .newLine()
            .openStartTag("table")
                .newLine()
                .attribute("border", "3")
                .attribute("style","border-collapse:collapse")
            .closeTag()
            .newLine()
            .startTag("tr");

        for (int i = 0; i < model.columnCount(); i++) {
            writer
                .newLine()
                .startTag("th")
                    .writeText(model.columnName(i))
                .endTag();

        }
        writer.newLine().endTag();

        for (int i = 0; i < model.rowCount(); i++) {
            writer.newLine().startTag("tr");

            for (int j = 0; j < model.columnCount(); j++) {
                writer.startTag("td")
                        .writeText(model.getCell(j, i))
                    .endTag();
            }
            writer.endTag();
        }

        writer.newLine()
            .endTag();


    }
}
