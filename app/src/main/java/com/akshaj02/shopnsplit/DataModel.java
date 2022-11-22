package com.akshaj02.shopnsplit;

import java.util.List;

public class DataModel {

    private final List<String> nestedList;
    private final String itemText;
    private boolean isExpandable;

    public DataModel(List<String> itemList, String itemText) {
        this.nestedList = itemList;
        this.itemText = itemText;
        isExpandable = false;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<String> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }
}
