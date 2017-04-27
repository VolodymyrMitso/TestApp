package com.mitso.testapp.models.entry;

public class ImContentTypeAttributes {

    private String term;
    private String label;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return
                "ImContentTypeAttributes{" +
                "term='" + term + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
