
package com.onecliquezone.oxford_v1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GrammaticalFeature {

    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
