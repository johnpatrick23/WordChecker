
package com.onecliquezone.oxford;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lexicalEntries")
    @Expose
    private List<LexicalEntry> lexicalEntries = null;
    @SerializedName("word")
    @Expose
    private String word;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LexicalEntry> getLexicalEntries() {
        return lexicalEntries;
    }

    public void setLexicalEntries(List<LexicalEntry> lexicalEntries) {
        this.lexicalEntries = lexicalEntries;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
