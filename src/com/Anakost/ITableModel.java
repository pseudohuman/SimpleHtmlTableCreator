package com.Anakost;

import java.util.ArrayList;

public interface ITableModel{
    int columnCount();
    String columnName(int index);
    String getCell(int x,int y);
    int rowCount();
}
