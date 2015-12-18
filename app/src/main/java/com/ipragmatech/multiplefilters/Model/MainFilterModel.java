package com.ipragmatech.multiplefilters.Model;

import java.util.ArrayList;

public class MainFilterModel {

    public static int SIZE = 1;
    public static int COLOR = 2;
    public static int STYLE = 3;

    public static int INDEX_SIZE = 0;
    public static int INDEX_COLOR = 1;
    public static int INDEX_STYLE = 2;


    String title, sub;
    boolean isSelected;
    ArrayList<String> subtitles = new ArrayList<String>();

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public ArrayList<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(ArrayList<String> subtitles) {
        this.subtitles = subtitles;

    }
}
