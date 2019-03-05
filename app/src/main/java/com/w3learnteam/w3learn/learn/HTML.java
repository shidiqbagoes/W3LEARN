package com.w3learnteam.w3learn.learn;

public class HTML {
    String titleHTML;
    String descHTML;

    public HTML() {
    }

    public HTML(String titleHTML, String descHTML) {
        this.titleHTML = titleHTML;
        this.descHTML = descHTML;
    }

    public String getTitleHTML() {
        return titleHTML;
    }

    public void setTitleHTML(String titleHTML) {
        this.titleHTML = titleHTML;
    }

    public String getDescHTML() {
        return descHTML;
    }

    public void setDescHTML(String descHTML) {
        this.descHTML = descHTML;
    }
}
