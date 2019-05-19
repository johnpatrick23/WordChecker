
package com.onecliquezone.oxford_v1;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sense {

    @SerializedName("crossReferences")
    @Expose
    private List<CrossReference> crossReferences = null;

    public List<CrossReference> getCrossReferences() {
        return crossReferences;
    }

    public void setCrossReferences(List<CrossReference> crossReferences) {
        this.crossReferences = crossReferences;
    }

}
