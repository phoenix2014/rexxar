package com.rexxar.dao.model;

public class NovelDirectoryWithBLOBs extends NovelDirectory {
    private String text;

    private String latestText;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getLatestText() {
        return latestText;
    }

    public void setLatestText(String latestText) {
        this.latestText = latestText == null ? null : latestText.trim();
    }
}