package com.onecliquezone.wordchecker;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Words {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("commonWords")
    @Expose
    private List<String> commonWords = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    List<String> getCommonWords() {
        return commonWords;
    }

    public void setCommonWords(List<String> commonWords) {
        this.commonWords = commonWords;
    }

}