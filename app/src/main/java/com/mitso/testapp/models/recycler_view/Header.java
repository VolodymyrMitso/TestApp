package com.mitso.testapp.models.recycler_view;

public class Header extends BaseModel {

    private String title;

    public Header(String _title) {
        this.title = _title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getType() {

        return TYPE_HEADER;
    }
}
