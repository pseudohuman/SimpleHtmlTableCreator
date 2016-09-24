package com.Anakost;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by Анатолій on 24.09.2016.
 */
public class TableHtmlView implements IHtmlView {

    private ITableModel model;

    TableHtmlView(ITableModel model){
        this.model = model;

    }

    public void render(Writer writer) throws IOException {

        writer.write("<table border=\"3\">");
        writer.write("\t<tr>\n");
        for (int i =0;i<model.columnCount();i++) {
            writer.write("\t\t<th>");
            writer.write(model.columnName(i));
            writer.write("\t\t</th>\n");

        }
        writer.write("\t</tr>");
        for (int i = 0;i<model.rowCount();i++){
            writer.write("\t<tr>\n");
            for (int j=0;j<model.columnCount();j++){
                writer.write("\t\t<td>");
                writer.write(model.getCell(j,i));
                writer.write("\t\t</td>\n");
            }
            writer.write("\t</tr>");
        }
        writer.write("</table>");


    }
}
