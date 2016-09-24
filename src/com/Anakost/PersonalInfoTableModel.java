package com.Anakost;

import java.util.ArrayList;

public class PersonalInfoTableModel extends ArrayList<PersonalInfo> implements ITableModel,IPersonalInfoConsumer{

    private String[] headers;

    @Override
    public int columnCount() {
        return headers.length;
    }

    @Override
    public String columnName(int index) {
        return headers[index];
    }

    @Override
    public String getCell(int x, int y) {
        PersonalInfo row =this.get(y);
        switch (x){
            case 0:return row.name;
            case 1:return row.adress;
            case 2:return row.city;
            case 3:return row.country;
            default:new IllegalArgumentException("x");
        }
        return null;
    }

    @Override
    public int rowCount() {
        return this.size();
    }

    @Override
    public void append(PersonalInfo info) {
        this.add(info);
    }

    @Override
    public void setHeaders(String[] headers) {
        this.headers=headers;
    }
}
